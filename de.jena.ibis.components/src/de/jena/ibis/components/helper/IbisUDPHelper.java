/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.components.helper;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.gecko.osgi.messaging.MessagingService;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.util.promise.PromiseFactory;

import de.jena.ibis.apis.IbisUDPServiceConfig;
import de.jena.ibis.apis.helper.IbisMQTTHelper;
import de.jena.ibis.apis.helper.IbisResponseHelper;
import de.jena.ibis.gnsslocationservice.DocumentRoot;

/**
 * 
 * @author ilenia
 * @since Apr 3, 2023
 */
public class IbisUDPHelper {

	private static PromiseFactory promiseFactory = new PromiseFactory(Executors.newFixedThreadPool(4));
	private final static Logger LOGGER = Logger.getLogger(IbisUDPHelper.class.getName());

	public static void checkUDPServiceConfig(IbisUDPServiceConfig serviceConfig) throws ConfigurationException {
		if(serviceConfig.multiCastGroupIP().isEmpty()) {
			String msg = String.format("Multicast Group for UDP Communication is not properly set for %s", serviceConfig.serviceId());
			LOGGER.severe(() -> msg);
			throw new ConfigurationException("multiCastGroupIP", msg);
		} else if(serviceConfig.listenerNetworkInterface().isEmpty()) {
			String msg = String.format("Listener Network Interface for UDP Communication is not properly set for %s", serviceConfig.serviceId());
			LOGGER.severe(() -> msg);
			throw new ConfigurationException("listenerNetworkInterface", msg);
		}  
	}


	public static Integer setupUDPConnection(MulticastSocket socket, IbisUDPServiceConfig serviceConfig, 
			String operation, ComponentServiceObjects<ResourceSet> rsFactory, MessagingService messagingService) {
		try {
			
			InetAddress inetAddress = InetAddress.getByName(serviceConfig.multiCastGroupIP());
			InetSocketAddress group = new InetSocketAddress(inetAddress, serviceConfig.multiCastGroupPort());
			NetworkInterface networkInterface = NetworkInterface.getByName(serviceConfig.listenerNetworkInterface());
			socket.joinGroup(group, networkInterface);
			promiseFactory.submit(() -> {
				LOGGER.info("=============================================");
				LOGGER.info("Connecting to " + inetAddress + " - " + serviceConfig.multiCastGroupPort());
				
				while (true) {

					byte[] buffer = new byte[2048];
					DatagramPacket response = new DatagramPacket(buffer, buffer.length);
					socket.receive(response);
					String received = new String(
							response.getData(), 0, response.getLength());
					received = received.replaceAll("^\\x00*", "");
					EClass responseEClass = IbisResponseHelper.getResponseEClass(serviceConfig.serviceName(), operation);
					if(responseEClass != null) {
						LOGGER.info("=============================================");
						LOGGER.info("Got something to " + inetAddress + " - " + serviceConfig.multiCastGroupPort());
						ResourceSet set = rsFactory.getService();
						set.getPackageRegistry().put(null, responseEClass.getEPackage());
						try {							
							Resource res = set.createResource(URI.createURI("temp.xml"), "application/xml");
							Map<String, Object> options = new HashMap<>();
							options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
							options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
							options.put(XMLResource.OPTION_ENCODING, "UTF-8");
							options.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, true);
							res.load(new BufferedInputStream(new ByteArrayInputStream(received.getBytes())), options);
							res.getWarnings().forEach(w -> System.out.println("WARNING " +w.getMessage()));
							res.getErrors().forEach(e -> System.out.println("ERROR " +e.getMessage()));
							
							if(res.getContents() != null && !res.getContents().isEmpty()) {
								EObject content = res.getContents().get(0);
								if(content instanceof DocumentRoot docRoot) {
									IbisMQTTHelper.sendToMQTT(String.format("UDPPacket/%s/%s/%s/%s", serviceConfig.refDeviceId(), serviceConfig.refDeviceType(), serviceConfig.serviceName(), operation), 
											docRoot.getGNSSLocationServiceData(), rsFactory, messagingService);
								} else {
									LOGGER.severe(String.format("Content in Resource for UDPPacket/%s/%s/%s/%s is not of type DocumentRoot", serviceConfig.refDeviceId(), serviceConfig.refDeviceType(), serviceConfig.serviceName(), operation));
								}
							}
							else {
								LOGGER.severe(String.format("No content in Resource for UDPPacket/%s/%s/%s/%s", serviceConfig.refDeviceId(), serviceConfig.refDeviceType(), serviceConfig.serviceName(), operation));
							}
						} finally {
							rsFactory.ungetService(set);
						}
					} else {
						LOGGER.severe(() -> String.format("No matching response EClass for %s %s", serviceConfig.serviceId(), operation));
						return false;
					}
					if ("end".equals(received)) {
						break;
					}
				}
				LOGGER.info("=============================================");
				LOGGER.info("Not connected anymore to" + inetAddress + " - " + serviceConfig.multiCastGroupPort());		
				socket.leaveGroup(group, networkInterface);
				socket.close();
				return true;
			}).onFailure(t -> {
				socket.leaveGroup(group, networkInterface);
				LOGGER.log(Level.SEVERE, String.format("Something went wrong when trying to listen to UDP packets for %s", serviceConfig.serviceId()), t);
			}).onSuccess(s -> {
				LOGGER.info(String.format("PushStream is open for UDP packets from service %s", serviceConfig.serviceId()));
			});
			return 200;
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, String.format("Something went wrong when trying to connect to multicast group for %s", serviceConfig.serviceId()), e);
			return -1;
		}	
	}
}
