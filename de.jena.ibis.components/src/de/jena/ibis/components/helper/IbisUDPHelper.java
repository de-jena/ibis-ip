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
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.util.promise.PromiseFactory;

import de.jena.ibis.apis.IbisUDPServiceConfig;

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


	public static Integer setupUDPConnection(MulticastSocket socket, IbisUDPServiceConfig serviceConfig, String operation, ComponentServiceObjects<ResourceSet> rsFactory, EventAdmin eventAdmin) {
		try {
			InetAddress inetAddress = InetAddress.getByName(serviceConfig.multiCastGroupIP());
			InetSocketAddress group = new InetSocketAddress(inetAddress, serviceConfig.multiCastGroupPort());
			NetworkInterface networkInterface = NetworkInterface.getByName(serviceConfig.listenerNetworkInterface());
			socket.joinGroup(group, networkInterface);
			
			promiseFactory.submit(() -> {
				while (socket.isConnected()) {

					byte[] buffer = new byte[512];
					DatagramPacket response = new DatagramPacket(buffer, buffer.length);
					socket.receive(response);

					ResourceSet set = rsFactory.getService();
					
					try {
						Resource res = set.createResource(URI.createURI("temp.xml"), "application/xml");
						Map<String, Object> options = new HashMap<>();
						options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
						options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
						options.put(XMLResource.OPTION_ENCODING, "UTF-8");
						res.load(new BufferedInputStream(new ByteArrayInputStream(buffer)), options);
						if(res.getContents() != null && !res.getContents().isEmpty()) {
							Map<String, Object> properties = new HashMap<>();
							properties.put("serviceId", serviceConfig.serviceId());
							properties.put("operation", operation);
							properties.put("data", res.getContents().get(0));
							Event evt = new Event("UDPPacket/"+serviceConfig.serviceId()+"/"+operation, properties);								
							eventAdmin.postEvent(evt);
						}
					} finally {
						rsFactory.ungetService(set);
					}
				}
				return true;
			});
			return 200;
		} catch(IOException e) {
			LOGGER.severe(() -> String.format("Something went wrong when trying to connect to multicast group for %s", serviceConfig.serviceId()));
			e.printStackTrace();
			return -1;
		}	
	}
	
}
