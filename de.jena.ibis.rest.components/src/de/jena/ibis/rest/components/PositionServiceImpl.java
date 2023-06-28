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
package de.jena.ibis.rest.components;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;
import org.gecko.core.pool.Pool;
import org.gecko.qvt.osgi.api.ConfigurableModelTransformatorPool;
import org.gecko.qvt.osgi.api.ModelTransformator;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.osgi.service.typedevent.propertytypes.EventTopics;

import de.jena.ibis.rest.apis.PositionService;
import de.jena.model.ibis.rest.PositionData;

/**
 * 
 * @author ilenia
 * @since Jun 28, 2023
 */
@Component(immediate = true, name = "PositionService", service = {PositionService.class, EventHandler.class})
@EventTopics({"UDPPacket/*"})
public class PositionServiceImpl implements PositionService, EventHandler{
	
	@Reference(target = ("(pool.componentName=ibisToApiTransformatorService)"))
	private ConfigurableModelTransformatorPool poolComponent;
	
	private static final Map<String, PositionData> LAST_KNOWN_POSITION_MAP = new HashMap<>();
	private static final Logger LOGGER = Logger.getLogger(PositionServiceImpl.class.getName());

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.rest.apis.PositionService#getPositionData(java.lang.String)
	 */
	@Override
	public PositionData getPositionData(String deviceId) {
		return LAST_KNOWN_POSITION_MAP.get(deviceId);
	}
	
	/* 
	 * (non-Javadoc)
	 * @see org.osgi.service.event.EventHandler#handleEvent(org.osgi.service.event.Event)
	 */
	@Override
	public void handleEvent(Event evt) {
		LOGGER.info("Event arrived for deviceId " + (String) evt.getProperty("deviceId"));
		transformAndStorePosition((EObject) evt.getProperty("data"), (String) evt.getProperty("deviceId"));
	}

	
	private void transformAndStorePosition(EObject eObj, String deviceId) {
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("ibisToApiTransformatorService-ibisToApiPool");
		if(pool != null) {
			ModelTransformator transformator = pool.poll();
			try {
				PositionData result = (PositionData) transformator.startTransformation(eObj);
				LAST_KNOWN_POSITION_MAP.put(deviceId, result);
			} catch(Exception e) {
				LOGGER.severe(String.format("Error during mmt for device %s", deviceId));
				throw new IllegalStateException(e);
			}			
			finally {
				pool.release(transformator);
			}
		}
		else {
			LOGGER.severe("No ModelTransformator available. This should not happen!");
			throw new IllegalStateException("No ModelTransformator available. This should not happen!");
		}
	}
}
