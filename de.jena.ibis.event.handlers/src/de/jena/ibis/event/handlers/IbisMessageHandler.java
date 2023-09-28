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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
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
import de.jena.ibis.gnsslocationservice.GNSSLSPackage;
import de.jena.ibis.gnsslocationservice.GNSSLocationServiceDataStructure;
import de.jena.model.ibis.common.IbisCommonPackage;
import de.jena.model.sensinact.ibis.IbisAdmin;
import de.jena.model.sensinact.ibis.IbisDevice;
import de.jena.model.sensinact.ibis.IbisSensinactFactory;
import de.jena.udp.model.trafficos.publictransport.PTUpdate;

/**
 * This handler listens to the ibis data the bus,tram,etc are sending, pushes them to sensinact and save them in the db
 * 
 * @author ilenia
 * @since Apr 18, 2023
 */
@Component(immediate= true, name = "IbisMessageHandler")
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
			LOGGER.log(Level.SEVERE, String.format("Exception while subscribing to TCP and/or UDP ibis messages!"), e);
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
		EClass rootObjClass = null;
		
		if(topic.startsWith("TCP")) {
			rootObjClass = IbisCommonPackage.eINSTANCE.getGeneralResponse();
		} else if(topic.startsWith("UDP")) {
			rootObjClass = GNSSLSPackage.eINSTANCE.getGNSSLocationServiceDataStructure();			
		}
		if(rootObjClass != null) return loadResource(URI.createFileURI("temp.json"), "application/json", content, Collections.singletonMap(EMFJs.OPTION_ROOT_ELEMENT, rootObjClass));
		return null;
	}

	private EObject loadResource(URI uri, String mediaType, byte[] content, Map<String, Object> options) {
		ResourceSet set = rsFactory.getService();
		try {
			Resource res = set.createResource(uri, mediaType);
			res.load(new ByteArrayInputStream(content),options);
			if(res.getContents() != null & !res.getContents().isEmpty()) {
				return res.getContents().get(0);
			}
			return null;
		} catch(IOException e) {
			LOGGER.log(Level.SEVERE, String.format("IOException while reading payload from MQTT"), e);
			return null;
		}
		finally {
			rsFactory.ungetService(set);
		}
	}
	
//	private void publishToSensinact(EObject data, String providerId, String deviceType) {
//		Map<String,Pool<ModelTransformator>> poolMap = sensinactPoolComponent.getPoolMap();
//		Pool<ModelTransformator> pool = poolMap.get("ibisToSensinactPool-sensinactPool");
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
//				LOGGER.log(Level.SEVERE, String.format("Something went wrong when publishing data on sensinact for provider %s", providerId), e);
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
				PTUpdate update = (PTUpdate) transformator.startTransformation(data);
				update.setRefVehicleId(vehicleId);
				update.setDataSource("IBIS");
				if(data instanceof GNSSLocationServiceDataStructure locationData) {
					XMLGregorianCalendar date = locationData.getDate().getValue();
					XMLGregorianCalendar time = locationData.getTime().getValue();
					LocalDate locDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
					LocalTime locTime = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());
					LocalDateTime locDateTime = LocalDateTime.of(locDate, locTime);
					update.setTimestamp(locDateTime.atZone(ZoneId.of("GMT+2")).toInstant().toEpochMilli());
				} 		
				if(update != null) {
					tosPTUpdateService.savePTUpdate(update);
				}
			} catch(Exception e) {
				LOGGER.log(Level.SEVERE, String.format("Something went wrong when transforming and savinfǵ data on TOS for vehicle %s", vehicleId), e);
			}			
			finally {
				pool.release(transformator);
			}
		}
	}
}
