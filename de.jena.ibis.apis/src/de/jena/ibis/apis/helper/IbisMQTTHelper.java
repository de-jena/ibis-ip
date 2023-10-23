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
package de.jena.ibis.apis.helper;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.emf.json.constants.EMFJs;
import org.gecko.osgi.messaging.MessagingService;
import org.osgi.service.component.ComponentServiceObjects;
import org.eclipse.emf.common.util.URI;

/**
 * 
 * @author ilenia
 * @since Sep 28, 2023
 */
public class IbisMQTTHelper {
	
	private static final Logger LOGGER = Logger.getLogger(IbisMQTTHelper.class.getName());
	
	public static void sendToMQTT(String topic, EObject object, ComponentServiceObjects<ResourceSet> rsFactory, MessagingService messagingService) {
		ResourceSet set = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
			if(object != null && rsFactory != null) {
				set = rsFactory.getService();		
				Resource resource = set.createResource(URI.createFileURI("temp_"+UUID.randomUUID().toString()+".json"));
				resource.getContents().add(object);
				resource.save(baos, Collections.singletonMap(EMFJs.OPTION_SERIALIZE_DEFAULT_VALUE, true));
			}			
			messagingService.publish(topic, ByteBuffer.wrap(baos.toByteArray()));
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, String.format("Could not forward event on topic %s", topic), e);
		} finally {
			if(set != null) rsFactory.ungetService(set);
		}
	}

}
