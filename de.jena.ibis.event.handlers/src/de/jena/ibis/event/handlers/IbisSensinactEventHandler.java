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

import org.eclipse.sensinact.prototype.notification.ResourceDataNotification;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.propertytypes.EventTopics;
import org.osgi.service.typedevent.TypedEventHandler;

/**
 * This handler should receives the data that have been published in sensinact...???
 * @author ilenia
 * @since Apr 24, 2023
 */
@Component(name="IbisSensinactEventHandler")
@EventTopics("DATA/*")
public class IbisSensinactEventHandler implements TypedEventHandler<ResourceDataNotification>{

	/* 
	 * (non-Javadoc)
	 * @see org.osgi.service.typedevent.TypedEventHandler#notify(java.lang.String, java.lang.Object)
	 */
	@Override
	public void notify(String topic, ResourceDataNotification event) {
		System.out.println(
				String.format("Sensinact event for %s/%s/%s/%s Old Value: %s New Value: %s", 
						event.model, event.provider, event.service, event.resource, event.oldValue, event.newValue));
		
	}

}
