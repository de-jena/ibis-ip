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

import org.eclipse.emf.ecore.EObject;
import org.gecko.core.pool.Pool;
import org.gecko.qvt.osgi.api.ConfigurableModelTransformatorPool;
import org.gecko.qvt.osgi.api.ModelTransformator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.IbisCustomerInformationService;
import de.jena.ibis.apis.constants.CustomerInformationServiceConstants;
import de.jena.ibis.rest.apis.TripInformationService;
import de.jena.model.ibis.common.GeneralResponse;
import de.jena.model.ibis.common.IBISIPInt;
import de.jena.model.ibis.common.IbisCommonFactory;
import de.jena.model.ibis.customerinformationservice.CurrentStopIndexResponse;
import de.jena.model.ibis.customerinformationservice.IbisCustomerInformationServiceFactory;
import de.jena.model.ibis.customerinformationservice.PartialStopSequenceRequest;
import de.jena.model.ibis.rest.StopSequence;
import de.jena.model.ibis.rest.TripData;

/**
 * 
 * @author ilenia
 * @since Jun 20, 2023
 */
@Component(name = "TripInformationService", service = TripInformationService.class, scope = ServiceScope.PROTOTYPE)
public class TripInformationServiceImpl implements TripInformationService {

	@Reference(target = ("(pool.componentName=ibisToApiTransformatorService)"))
	private ConfigurableModelTransformatorPool poolComponent;

	private static final Logger LOGGER = Logger.getLogger(TripInformationServiceImpl.class.getName());

	private BundleContext bundleCtx;

	@Activate
	public void activate(BundleContext bundleCtx) {
		this.bundleCtx = bundleCtx;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.rest.apis.TripInformationService#getTripData(java.lang.String)
	 */
	@Override
	public TripData getTripData(String deviceId) {
		IbisCustomerInformationService service = (IbisCustomerInformationService) retrieveIbisService("CustomerInformationService", deviceId);
		if(service == null) {
			return null;
		}
		GeneralResponse response = service.executeGetOperation(CustomerInformationServiceConstants.OPERATION_GET_TRIP_DATA);
		if(response == null) {
			LOGGER.severe(String.format("IbisCustomerInformationService TripData response is null for device %s.", deviceId));
			return null;
		}
		return (TripData) doTransformation(response, deviceId);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.rest.apis.TripInformationService#getNextStops(java.lang.String)
	 */
	@Override
	public StopSequence getNextStops(String deviceId, int startStopIndex) {
		IbisCustomerInformationService service = (IbisCustomerInformationService) retrieveIbisService("CustomerInformationService", deviceId);
		if(service == null) {
			return null;
		}
		PartialStopSequenceRequest request = IbisCustomerInformationServiceFactory.eINSTANCE.createPartialStopSequenceRequest();
		IBISIPInt startIndex = IbisCommonFactory.eINSTANCE.createIBISIPInt();
		startIndex.setValue(startStopIndex);
		request.setStartingStopIndex(startIndex);
		IBISIPInt numStops = IbisCommonFactory.eINSTANCE.createIBISIPInt();
		numStops.setValue(Integer.MAX_VALUE);
		request.setNumberOfStopPoints(numStops);
		GeneralResponse response = 
				service.executeRetrieveOperation(CustomerInformationServiceConstants.OPERATION_RETRIEVE_PARTIAL_STOP_SEQUENCE, request);

		if(response == null) {
			LOGGER.severe(String.format("IbisCustomerInformationService PartialStopSequence response is null for device %s.", deviceId));
			return null;
		}
		return (StopSequence) doTransformation(response, deviceId);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.rest.apis.TripInformationService#getCurrentStopIndex(java.lang.String)
	 */
	@Override
	public int getCurrentStopIndex(String deviceId) {
		IbisCustomerInformationService service = (IbisCustomerInformationService) retrieveIbisService("CustomerInformationService", deviceId);
		if(service == null) {
			return -1;
		}
		GeneralResponse response = service.executeGetOperation(CustomerInformationServiceConstants.OPERATION_GET_CURRENT_STOP_INDEX);
		if(response == null) {
			LOGGER.severe(String.format("IbisCustomerInformationService CurrentStopIndex response is null for device %s.", deviceId));
			return -1;
		}
		if(response instanceof CurrentStopIndexResponse stopIndexResponse) {
			return stopIndexResponse.getCurrentStopIndexData().getCurrentStopIndex().getValue();
		} else {
			LOGGER.severe("Response is not an instance of CurrentStopIndexResponse. This should not happen!");
			throw new IllegalStateException("Response is not an instance of CurrentStopIndexResponse. This should not happen!");
		}
	}


	private EObject doTransformation(GeneralResponse response, String deviceId) {
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("ibisToApiTransformatorService-ibisToApiPool");
		if(pool != null) {
			ModelTransformator transformator = pool.poll();
			try {
				return transformator.startTransformation(response);
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

	@SuppressWarnings("unchecked")
	private GeneralIbisService retrieveIbisService(String serviceName, String deviceId) {
		try {
			ServiceReference<GeneralIbisService>[] serviceRef = (ServiceReference<GeneralIbisService>[]) 
					bundleCtx.getAllServiceReferences(GeneralIbisService.class.getName(), 
							"(serviceId="+serviceName+"-"+deviceId+")");
			if(serviceRef == null || serviceRef.length == 0) {
				LOGGER.severe(String.format("No IbisCustomerInformationService reference found for device %s", deviceId));
				return null;
			}
			return bundleCtx.getService(serviceRef[0]);
		} catch (InvalidSyntaxException e) {
			LOGGER.severe("Illegal syntax for service filter. This should not happen!");
			throw new IllegalStateException(e);
		}
	}
}
