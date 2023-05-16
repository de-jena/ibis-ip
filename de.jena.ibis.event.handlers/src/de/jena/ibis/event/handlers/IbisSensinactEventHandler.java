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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sensinact.prototype.notification.ResourceDataNotification;
import org.gecko.emf.json.constants.EMFJs;
import org.gecko.osgi.messaging.MessagingService;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.propertytypes.EventTopics;
import org.osgi.service.typedevent.TypedEventHandler;

import de.jena.udp.model.sensinact.generic.message.ListValueUpdate;
import de.jena.udp.model.sensinact.generic.message.UpdateMessage;
import de.jena.udp.model.sensinact.generic.message.util.SensinactGenericMessageUtil;

/**
 * This handler should receives the data that have been published in sensinact...???
 * @author ilenia
 * @since Apr 24, 2023
 */
@Component(name="IbisSensinactEventHandler")
@EventTopics("DATA/*")
public class IbisSensinactEventHandler implements TypedEventHandler<ResourceDataNotification>{

	private static final Logger logger = Logger.getLogger(IbisSensinactEventHandler.class.getName());

	@Reference(target = "(id=full)")
	MessagingService messaging;
	
	@Reference
	ComponentServiceObjects<ResourceSet> setObjects;
	
	/* 
	 * (non-Javadoc)
	 * @see org.osgi.service.typedevent.TypedEventHandler#notify(java.lang.String, java.lang.Object)
	 */
	@Override
	public void notify(String topic, ResourceDataNotification event) {
		logger.info(
				String.format("Sensinact event for %s/%s/%s/%s Old Value: %s New Value: %s Timestamp: %s", 
						event.model, event.provider, event.service, event.resource, event.oldValue, event.newValue, event.timestamp));
		
		try {
			UpdateMessage update = SensinactGenericMessageUtil.createUpdateMessageForType(event.newValue != null ? event.newValue.getClass() : event.type);
			update.setTimestamp(event.timestamp);
			update.setResource(event.resource);
			setValue(update, event.oldValue, "oldValue");
			setValue(update, event.newValue, "newValue");
			send(String.format("5g/sensinact/event/data/%s/%s/%s/%s", event.model, event.provider, event.service, event.resource), update );
		} catch (Throwable e) {
			logger.severe("Could not send update message: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @param update
	 * @param value
	 */
	private void setValue(UpdateMessage update, Object value, String name) {
		if(update instanceof ListValueUpdate && value == null) value = Collections.EMPTY_LIST;
		EStructuralFeature feature = update.eClass().getEStructuralFeature(name);
		update.eSet(feature, value);
	}
	

	private void send(String topic, EObject object) {
		if(logger.isLoggable(Level.INFO)) {
			logger.log(Level.INFO, String.format("forwarding event on topic %s", topic));
		}
		
		ResourceSet set = setObjects.getService();
		
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			Resource resource = set.createResource(URI.createFileURI("temp.json"));
			resource.getContents().add(object);
			resource.save(baos, Collections.singletonMap(EMFJs.OPTION_SERIALIZE_DEFAULT_VALUE, true));
			messaging.publish(topic, ByteBuffer.wrap(baos.toByteArray()));
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not forward event on topic " + topic);
			e.printStackTrace();
		} finally {
			setObjects.ungetService(set);
		}
	}

}
