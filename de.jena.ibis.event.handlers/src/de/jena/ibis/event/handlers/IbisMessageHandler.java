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
package de.jena.ibis.event.handlers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLResource.MissingPackageHandler;
import org.eclipse.sensinact.prototype.PrototypePush;
import org.gecko.core.pool.Pool;
import org.gecko.emf.json.constants.EMFJs;
import org.gecko.osgi.messaging.Message;
import org.gecko.osgi.messaging.MessagingService;
import org.gecko.qvt.osgi.api.ConfigurableModelTransformatorPool;
import org.gecko.qvt.osgi.api.ModelTransformator;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.pushstream.PushStream;

import de.dim.trafficos.publictransport.apis.PTUpdateService;
import de.jena.model.ibis.common.IbisCommonPackage;
import de.jena.model.ibis.customerinformationservice.TripDataResponse;
import de.jena.model.ibis.gnsslocationservice.IbisGNSSLocationServicePackage;
import de.jena.model.sensinact.ibis.GNSSLocationData;
import de.jena.model.sensinact.ibis.IbisAdmin;
import de.jena.model.sensinact.ibis.IbisDevice;
import de.jena.model.sensinact.ibis.IbisSensinactFactory;
import de.jena.udp.model.trafficos.publictransport.PTPositionUpdate;
import de.jena.udp.model.trafficos.publictransport.PTTripUpdate;
import de.jena.udp.model.trafficos.publictransport.PTUpdate;
import de.jena.udp.model.trafficos.publictransport.PTUpdateValueType;
import de.jena.udp.model.trafficos.publictransport.TOSPublicTransportFactory;

/**
 * This handler listens to the ibis data the bus,tram,etc are sending, pushes them to sensinact and save them in the db
 * 
 * @author ilenia
 * @since Apr 18, 2023
 */
@Component(immediate= true, name = "IbisMessageHandler", service = IbisMessageHandler.class)
public class IbisMessageHandler {
	
//	@Reference
//	PrototypePush sensinact;
	
	@Reference(target = "(id=full)")
	MessagingService messagingService;
	
	@Reference
	private ComponentServiceObjects<ResourceSet> rsFactory;
	
//	@Reference(target = ("(pool.componentName=ibisToSensinactPool)"))
//	private ConfigurableModelTransformatorPool sensinactPoolComponent;
	
	@Reference(target = ("(pool.componentName=ibisToTOSPool)"))
	private ConfigurableModelTransformatorPool tosPoolComponent;
	
	@Reference
	PTUpdateService tosPTUpdateService;
	
	private static final Logger LOGGER = Logger.getLogger(IbisMessageHandler.class.getName());
	private static final String TCP_MQTT_TOPIC = "TCPResponse/#";
	private static final String UDP_MQTT_TOPIC = "UDPPacket/#";

	
	@Activate 
	public void activate() {
		LOGGER.info("Ibis Message Handler is active!");
		try {
			PushStream<Message> subscription = messagingService.subscribe(TCP_MQTT_TOPIC);
			subscription = subscription.merge(messagingService.subscribe(UDP_MQTT_TOPIC));
			subscription.forEach(this::handleMessage);			
		} catch (Exception e) {
			LOGGER.severe(String.format("Exception while subscribing to TCP and/or UDP ibis messages!"));
			e.printStackTrace();
		}
	}
	
	private void handleMessage(Message message) {
		LOGGER.info(String.format("Received event for topic %s", message.topic()));
		String[] topicSegments = message.topic().split("/");
		if(topicSegments.length != 5) {
			LOGGER.severe(String.format("Received event has been published in a non conformed topic %s. \n "
					+ "Expected form is TCPResponse/<deviceId>/<deviceType>/<serviceName>/<serviceOperation> or \n "
					+ "UDPPacket/<deviceId>/<deviceType>/<serviceName>/<serviceOperation>", message.topic()));
			return;
		}
		String deviceId = topicSegments[1];
		String deviceType = topicSegments[2];
		byte[] content = message.payload().array();
		EObject obj = extractMessageContent(content, message.topic());
//		publishToSensinact(obj, deviceId, deviceType);
		saveToTOSDB(obj, deviceId);		
	}

	
	private EObject extractMessageContent(byte[] content, String topic) {
		if(content.length == 0) return null;
		if(topic.startsWith("TCP")) {
			return loadResource(URI.createFileURI("temp.json"), "application/json", content, Collections.singletonMap(EMFJs.OPTION_ROOT_ELEMENT, IbisCommonPackage.eINSTANCE.getGeneralResponse()));
		} else if(topic.startsWith("UDP")) {
			Map<String, Object> options = new HashMap<>();
			options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
			options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
			options.put(XMLResource.OPTION_ENCODING, "UTF-8");
			options.put(XMLResource.OPTION_MISSING_PACKAGE_HANDLER, new MissingPackageHandler() {

				@Override
				public EPackage getPackage(String nsURI) {
					return IbisGNSSLocationServicePackage.eINSTANCE.getGNSSLocationData().getEPackage();
				}}
			);
			return loadResource(URI.createFileURI("temp.xml"), "application/xml", content, options);
		}
		return null;
	}

	private EObject loadResource(URI uri, String mediaType, byte[] content, Map<String, Object> options) {
		ResourceSet set = rsFactory.getService();
		try {
			Resource res = set.createResource(uri, mediaType);
			res.load(new ByteArrayInputStream(content),options);
			if(res.getContents() != null & !res.getContents().isEmpty()) return res.getContents().get(0);
			return null;
		} catch(IOException e) {
			LOGGER.severe(String.format("IOException while reading payload from MQTT"));
			e.printStackTrace();
			return null;
		}
		finally {
			rsFactory.ungetService(set);
		}
	}
	
//	private void publishToSensinact(EObject data, String providerId, String deviceType) {
//		Map<String,Pool<ModelTransformator>> poolMap = sensinactPoolComponent.getPoolMap();
//		Pool<ModelTransformator> pool = poolMap.get("ibisToSensinactPool-ibisToSensinact");
//		if(pool != null) {
//			ModelTransformator transformator = pool.poll();
//			try {
//				IbisDevice push = (IbisDevice) transformator.startTransformation(data);
//				push.setId(providerId);
//				
//				IbisAdmin ibisAdmin = IbisSensinactFactory.eINSTANCE.createIbisAdmin();
//				ibisAdmin.setDeviceType(deviceType);
//				push.setIbisAdmin(ibisAdmin);
//				
//				sensinact.pushUpdate(push);
//			} catch(Exception e) {
//				e.printStackTrace();
//			}			
//			finally {
//				pool.release(transformator);
//			}
//		}
//	}
	
	private void saveToTOSDB(EObject data, String vehicleId) {
		Map<String,Pool<ModelTransformator>> poolMap = tosPoolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("ibisToTOSPool-tosPool");
		if(pool != null) {
			ModelTransformator transformator = pool.poll();
			try {
				PTUpdate update = TOSPublicTransportFactory.eINSTANCE.createPTUpdate();
				update.setDataSource("IBIS");
				update.setRefVehicleId(vehicleId);
				if(data instanceof TripDataResponse tripDataResponse) {
					update.setType(PTUpdateValueType.TRIP_DATA);
					update.setTimestamp(tripDataResponse.getTripData().getTimeStamp().getValue().getMillisecond());
					PTTripUpdate updateValue = (PTTripUpdate) transformator.startTransformation(tripDataResponse);
					update.setValue(updateValue);
				} else if(data instanceof GNSSLocationData locationData) {
					update.setType(PTUpdateValueType.GEO_INFO);
					update.setTimestamp(locationData.getTimestamp().toEpochMilli());
					PTPositionUpdate updateValue = (PTPositionUpdate) transformator.startTransformation(locationData);
					update.setValue(updateValue);
				}
				if(update.getValue() != null) {
					tosPTUpdateService.savePTUpdate(update);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}			
			finally {
				pool.release(transformator);
			}
		}
	}
}
