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

import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sensinact.prototype.PrototypePush;
import org.gecko.core.pool.Pool;
import org.gecko.qvt.osgi.api.ConfigurableModelTransformatorPool;
import org.gecko.qvt.osgi.api.ModelTransformator;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.osgi.service.event.propertytypes.EventTopics;

import de.jena.model.sensinact.ibis.IbisAdmin;
import de.jena.model.sensinact.ibis.IbisDevice;
import de.jena.model.sensinact.ibis.IbisSensinactFactory;

/**
 * This event handler listens to the ibis data the bus,tram,etc are sending 
 * 
 * @author ilenia
 * @since Apr 18, 2023
 */
@Component(immediate= true, name = "IbisTCPEventHandler", service = EventHandler.class)
@EventTopics("TCPResponse/*")
public class IbisTCPEventHandler implements EventHandler {
	
	@Reference
	PrototypePush sensinact;
	
	@Reference(target = ("(pool.componentName=modelTransformatorService)"))
	private ConfigurableModelTransformatorPool poolComponent;
	
	public static final Logger LOGGER = Logger.getLogger(IbisTCPEventHandler.class.getName());
	
	@Activate 
	public void activate() {
		LOGGER.info("TCP Event Handler is active!");
	}
	/* 
	 * (non-Javadoc)
	 * @see org.osgi.service.event.EventHandler#handleEvent(org.osgi.service.event.Event)
	 */
	@Override
	public void handleEvent(Event evt) {
		LOGGER.info("Event arrived for topic " + evt.getTopic());
		publish((EObject) evt.getProperty("data"), (String) evt.getProperty("deviceId"), (String) evt.getProperty("deviceType"));
	}
	
	private void publish(EObject data, String providerId, String deviceType) {
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-ibisPool");
		if(pool != null) {
			ModelTransformator transformator = pool.poll();
			try {
				IbisDevice push = (IbisDevice) transformator.startTransformation(data);
				push.setId(providerId);
				
				IbisAdmin ibisAdmin = IbisSensinactFactory.eINSTANCE.createIbisAdmin();
				ibisAdmin.setDeviceType(deviceType);
				push.setIbisAdmin(ibisAdmin);
				
				sensinact.pushUpdate(push);
			} catch(Exception e) {
				e.printStackTrace();
			}			
			finally {
				pool.release(transformator);
			}
		}
	}

}
