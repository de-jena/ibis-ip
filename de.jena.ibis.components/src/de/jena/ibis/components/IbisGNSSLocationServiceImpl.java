/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.components;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.gecko.emf.json.constants.EMFJs;
import org.gecko.osgi.messaging.MessagingService;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.IbisGNSSLocationService;
import de.jena.ibis.apis.IbisUDPServiceConfig;
import de.jena.ibis.apis.helper.IbisResponseHelper;
import de.jena.ibis.components.helper.IbisUDPHelper;
import de.jena.model.ibis.common.IbisCommonPackage;
import de.jena.model.ibis.enumerations.IbisEnumerationsPackage;
import de.jena.model.ibis.gnsslocationservice.GNSSLocationData;
import de.jena.model.ibis.gnsslocationservice.IbisGNSSLocationServicePackage;
import de.jena.model.ibis.gnsslocationservice.util.IbisGNSSLocationServiceResourceFactoryImpl;

/**
 * 
 * @author ilenia
 * @since Jan 18, 2023
 */
@Component(immediate = true, name = "IbisGNSSLocationService", service = { IbisGNSSLocationService.class,
		GeneralIbisService.class }, configurationPid = "GNSSLocationService", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class IbisGNSSLocationServiceImpl implements IbisGNSSLocationService {

	private static final Logger LOGGER = Logger.getLogger(IbisGNSSLocationServiceImpl.class.getName());

	private static final String OPERATION = "GetGNSSLocationData";

	@Reference
	private ComponentServiceObjects<ResourceSet> rsFactory;

	@Reference(target = "(id=full)")
	MessagingService messaging;

	private IbisUDPServiceConfig config;
	private Map<String, Object> outConfig = Collections.singletonMap(EMFJs.OPTION_DATE_FORMAT,
			"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'zzz");

	private MulticastSocket socket;

	private ExecutorService executor;

	private String topic;

	private GNSSLocationData currentLocation = null;

	private ScheduledExecutorService scheduledExecutor;

	private GNSSLocationData oldLocation;

	@Activate
	public void activate(IbisUDPServiceConfig config) throws ConfigurationException {
		this.config = config;
		IbisUDPHelper.checkUDPServiceConfig(config);

		topic = "5g/ibis/" + config.refDeviceType() + "/" + config.refDeviceId() + "/" + config.serviceName();
		executor = Executors.newCachedThreadPool();
		executor.execute(this::readMulticastGNSSPackage);
		scheduledExecutor = Executors.newScheduledThreadPool(1);
		scheduledExecutor.scheduleAtFixedRate(this::sendGNSS, 5, 1, TimeUnit.SECONDS);
		LOGGER.info("GNSSLocationService is up and running on " + config.listenerNetworkInterface() + ":"
				+ config.multiCastGroupIP() + ":" + config.multiCastGroupPort() + " - " + config.listenerPort());
	}

	private Object readMulticastGNSSPackage() {
		try {
			socket = new MulticastSocket(config.listenerPort());
			InetAddress inetAddress = InetAddress.getByName(config.multiCastGroupIP());
			InetSocketAddress group = new InetSocketAddress(inetAddress, config.multiCastGroupPort());
			NetworkInterface networkInterface = NetworkInterface.getByName(config.listenerNetworkInterface());
			socket.joinGroup(group, networkInterface);

			byte[] buffer = new byte[4096];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			while (true) {
				socket.receive(packet);
				String data = new String(packet.getData(), 0, packet.getLength() - 1, StandardCharsets.UTF_8);
				readData(packet, data);
			}
		} catch (Exception e) {
			LOGGER.severe(String.format("Something went wrong when trying to connect to multicast group for %s",
					config.serviceId()));
			e.printStackTrace();
			return -1;
		}
	}

	private void readData(DatagramPacket packet, String data) throws IOException {
		LOGGER.info(String.format("Multicast packet received from %s Data is : %s", packet.getAddress(), data));
		EClass responseEClass = IbisResponseHelper.getResponseEClass(config.serviceName(), OPERATION);
		if (responseEClass != null) {
			ResourceSet set = rsFactory.getService();
			initResourceSet(set);

			try {
				Resource res = set.createResource(URI.createURI(UUID.randomUUID().toString() + ".gnsslocation"));
				res.load(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)), getLoadOptions(set));
				EList<EObject> contents = res.getContents();
				if (contents != null && !contents.isEmpty()) {
					currentLocation = (GNSSLocationData) res.getContents().get(0);
					if (LOGGER.isLoggable(Level.INFO)) {
						double lat = currentLocation.getLatitude().getDegree().getValue();
						double lon = currentLocation.getLongitude().getDegree().getValue();
						double alt = currentLocation.getAltitude().getValue();
						LOGGER.log(Level.INFO, String.format("Location lat: %s lon: %s alt: %s", lat, lon, alt));
					}
				}
			} finally {
				rsFactory.ungetService(set);
			}
		} else {
			LOGGER.severe(String.format("No matching response EClass for %s %s", config.serviceId(), OPERATION));
		}
	}

	private Map<String, Object> getLoadOptions(ResourceSet rs) {
		Map<String, Object> options = new HashMap<>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		options.put(XMLResource.OPTION_EXTENDED_META_DATA, new BasicExtendedMetaData() {
			@Override
			public EPackage getPackage(String namespace) {
				if (Objects.isNull(namespace)) {
					return rs.getPackageRegistry().getEPackage(IbisGNSSLocationServicePackage.eNS_URI);
				}
				return super.getPackage(namespace);
			}

			@Override
			protected boolean isFeatureNamespaceMatchingLax() {
				return Boolean.TRUE;
			}
		});
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		options.put(XMLResource.OPTION_SUPPRESS_DOCUMENT_ROOT, Boolean.TRUE);
		return options;
	}

	@Deactivate()
	public void deactivate() {
		executor.shutdownNow();
		scheduledExecutor.shutdownNow();
		executeAllUnsubscriptionOperations();
	}

	private void initResourceSet(ResourceSet set) {
		set.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		set.getPackageRegistry().put(XMLTypePackage.eNS_URI, XMLTypePackage.eINSTANCE);
		set.getPackageRegistry().put(XMLNamespacePackage.eNS_URI, XMLNamespacePackage.eINSTANCE);
		set.getPackageRegistry().put(IbisGNSSLocationServicePackage.eNS_URI, IbisGNSSLocationServicePackage.eINSTANCE);
		set.getPackageRegistry().put(IbisEnumerationsPackage.eNS_URI, IbisEnumerationsPackage.eINSTANCE);
		set.getPackageRegistry().put(IbisCommonPackage.eNS_URI, IbisCommonPackage.eINSTANCE);
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("gnsslocation",
				new IbisGNSSLocationServiceResourceFactoryImpl());
	}

	@Override
	public void connectToGNSSLocationData() {
	}

	@Override
	public void executeAllSubscriptionOperations() {
	}

	@Override
	public void executeAllUnsubscriptionOperations() {
		if (socket != null && socket.isConnected()) {
			socket.disconnect();
		}
	}

	@Override
	public String getServiceName() {
		return config.serviceName();
	}

	@Override
	public String getServiceId() {
		return config.serviceId();
	}

	@Override
	public String getRefDeviceId() {
		return config.refDeviceId();
	}

	public String getRefDeviceType() {
		return config.refDeviceType();
	}

	private void sendGNSS() {
		if (currentLocation == null) {
			return;
		}
		if (!EcoreUtil.equals(oldLocation, currentLocation)) {
			ResourceSet set = rsFactory.getService();
			try {
				Resource outResource = set.createResource(URI.createFileURI(UUID.randomUUID().toString() + ".json"));
				outResource.getContents().add(currentLocation);
				ByteArrayOutputStream bao = new ByteArrayOutputStream();
				outResource.save(bao, outConfig);
				messaging.publish(topic, ByteBuffer.wrap(bao.toByteArray()));
				oldLocation = currentLocation;
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE,
						String.format("Something went wrong when sending current position via %s", config.serviceId()),
						e);
			} finally {
				rsFactory.ungetService(set);
			}
		}

	}
}
