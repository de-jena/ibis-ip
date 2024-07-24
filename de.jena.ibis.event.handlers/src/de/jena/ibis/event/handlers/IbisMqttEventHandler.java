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

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.emf.json.constants.EMFJs;
import org.gecko.osgi.messaging.MessagingService;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.osgi.service.event.propertytypes.EventTopics;
import org.osgi.service.typedevent.annotations.RequireTypedEvent;

/**
 * This event handler listens to the Ibis data the bus, tram, etc are sending and
 * forwarding it to the MQTT broker
 * 
 */
@Component(immediate = true, name = "IbisMqttEventHandler", service = EventHandler.class)
@EventTopics({ "TCPResponse/*", "UDPPacket/*" })
@RequireTypedEvent
public class IbisMqttEventHandler implements EventHandler {
	public static final Logger LOGGER = Logger.getLogger(IbisMqttEventHandler.class.getName());

	private static final String TOPIC = "5g/ibis/";

	@Reference(target = "(id=full)")
	MessagingService messaging;

	@Reference
	ComponentServiceObjects<ResourceSet> setObjects;

	@Activate
	public void activate() {
		LOGGER.info("Ibis MQTT Event Handler is active!");
	}

	@Override
	public void handleEvent(Event evt) {
		LOGGER.finest("Event arrived for topic " + evt.getTopic());
		publish((EObject) evt.getProperty("data"), (String) evt.getProperty("deviceId"),
				(String) evt.getProperty("deviceType"));
	}

	private void publish(EObject data, String deviceId, String deviceType) {

		ResourceSet set = setObjects.getService();
		String topic = TOPIC + deviceId + "/" + deviceType;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			Resource resource = set.createResource(URI.createFileURI(UUID.randomUUID().toString()+"-mqtt.json"));
			resource.getContents().add(data);
			resource.save(baos, Collections.singletonMap(EMFJs.OPTION_SERIALIZE_DEFAULT_VALUE, true));
			messaging.publish(topic, ByteBuffer.wrap(baos.toByteArray()));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Could not forward event on topic " + topic);
			e.printStackTrace();
		} finally {
			setObjects.ungetService(set);
		}

	}
}
