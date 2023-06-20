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

import java.util.Map;
import java.util.logging.Logger;

import org.gecko.core.pool.Pool;
import org.gecko.qvt.osgi.api.ConfigurableModelTransformatorPool;
import org.gecko.qvt.osgi.api.ModelTransformator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import de.jena.ibis.apis.IbisCustomerInformationService;
import de.jena.ibis.apis.constants.CustomerInformationServiceConstants;
import de.jena.ibis.rest.apis.TripInformationService;
import de.jena.model.ibis.common.GeneralResponse;
import de.jena.model.ibis.rest.TripData;

/**
 * 
 * @author ilenia
 * @since Jun 20, 2023
 */
@Component(name = "TripInformationService", service = TripInformationService.class, scope = ServiceScope.PROTOTYPE)
public class TripInformationServiceImpl implements TripInformationService {
	
	@Reference
	BundleContext bundleCtx;
	
	@Reference(target = ("(pool.componentName=ibisToApiTransformatorService)"))
	private ConfigurableModelTransformatorPool poolComponent;
	
	private static final Logger LOGGER = Logger.getLogger(TripInformationServiceImpl.class.getName());

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.rest.apis.TripInformationService#getTripData(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public TripData getTripData(String deviceId) {
		try {
			ServiceReference<IbisCustomerInformationService>[] serviceRef = (ServiceReference<IbisCustomerInformationService>[]) bundleCtx.getAllServiceReferences(IbisCustomerInformationService.class.getName(), "(serviceId=CustomerInformationService-"+deviceId+")");
			if(serviceRef == null || serviceRef.length == 0) {
				LOGGER.severe(String.format("No IbisCustomerInformationService reference found for device %s", deviceId));
				return null;
			}
			IbisCustomerInformationService service = bundleCtx.getService(serviceRef[0]);
			GeneralResponse response = service.executeGetOperation(CustomerInformationServiceConstants.OPERATION_GET_TRIP_DATA);
			if(response == null) {
				LOGGER.severe(String.format("IbisCustomerInformationService TripData response is null for device %s.", deviceId));
				return null;
			}
			Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
			Pool<ModelTransformator> pool = poolMap.get("ibisToApiTransformatorService-ibisToApiPool");
			if(pool != null) {
				ModelTransformator transformator = pool.poll();
				try {
					TripData tripData = (TripData) transformator.startTransformation(response);
					return tripData;
				} catch(Exception e) {
					LOGGER.severe(String.format("Error during mmt for TripData and device %s", deviceId));
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
		} catch (InvalidSyntaxException e) {
			LOGGER.severe("Illegal syntax for service filter. This should not happen!");
			throw new IllegalStateException(e);
		}
	}

}
