/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.components;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.GeneralIbisTCPService;
import de.jena.ibis.apis.IbisTCPServiceConfig;
import de.jena.ibis.apis.IbisTicketValidationService;
import de.jena.ibis.apis.constants.TicketValidationServiceConstants;
import de.jena.ibis.components.helper.IbisHttpRequestHelper;
import de.jena.ibis.components.helper.IbisTCPHelper;
import de.jena.model.ibis.common.GeneralResponse;
import de.jena.model.ibis.common.IbisCommonPackage;
import de.jena.model.ibis.ticketvalidationservice.CurrentLineResponse;
import de.jena.model.ibis.ticketvalidationservice.CurrentShortHaulStopsResponse;
import de.jena.model.ibis.ticketvalidationservice.CurrentTariffStopResponse;
import de.jena.model.ibis.ticketvalidationservice.IbisTicketValidationServicePackage;
import de.jena.model.ibis.ticketvalidationservice.RazziaResponse;
import de.jena.model.ibis.ticketvalidationservice.VehicleDataResponse;

/**
 * 
 * @author ilenia
 * @since Mar 30, 2023
 */
@Component(immediate=true, name = "IbisTicketValidationService", 
service = {IbisTicketValidationService.class, GeneralIbisTCPService.class, GeneralIbisService.class},
configurationPid = "TicketValidationService", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class IbisTicketValidationServiceImpl implements IbisTicketValidationService {
	
	@Reference
	IbisTicketValidationServicePackage ticketValidationServicePackage;
	
	@Reference 
	IbisCommonPackage ibisCommonPackage;
	
	@Reference(target = "(emf.resource.configurator.name=GeckoXMLResourceFactory)")
	private ComponentServiceObjects<ResourceSet> resourceSetFactory;
	
	private IbisTCPServiceConfig config;

	@Activate
	public void activate(IbisTCPServiceConfig config) throws ConfigurationException {
		IbisTCPHelper.checkTCPServiceConfig(config);
		this.config = config;
		executeAllSubscriptionOperations();
	}
	
	@Deactivate() 
	public void deactivate() {
		executeAllUnsubscriptionOperations();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#getServiceName()
	 */
	@Override
	public String getServiceName() {
		return config.serviceName();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#getServiceId()
	 */
	@Override
	public String getServiceId() {
		return config.serviceId();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeGetOperation(java.lang.String)
	 */
	@Override
	public GeneralResponse executeGetOperation(String operation) {
		switch(operation) {
		case TicketValidationServiceConstants.OPERATION_GET_CURRENT_LINE:
			return getCurrentLine();
		case TicketValidationServiceConstants.OPERATION_GET_CURRENT_TARIFF_STOP:
			return getCurrentTariffStop();
		case TicketValidationServiceConstants.OPERATION_GET_RAZZIA:
			return getRazzia();
		case TicketValidationServiceConstants.OPERATION_GET_VEHICLE_DATA:
			return getVehicleData();
		default:
			throw new IllegalArgumentException(String.format("Operation %s not implemented for %s!", operation, config.serviceName()));			
		}
	}
	
	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeAllGetOperations()
	 */
	@Override
	public List<GeneralResponse> executeAllGetOperations() {
		List<GeneralResponse> results = new ArrayList<>();
		TicketValidationServiceConstants.getAllGetOperations().forEach(operation -> results.add(executeGetOperation(operation)));
		return results;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeAllSubscriptionOperations()
	 */
	@Override
	public void executeAllSubscriptionOperations() {
		TicketValidationServiceConstants.getAllSubscriptionOperations().forEach(operation -> executeSubscriptionOperation(operation));
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeAllUnsubscriptionOperations()
	 */
	@Override
	public void executeAllUnsubscriptionOperations() {
		TicketValidationServiceConstants.getAllUnsubscriptionOperations().forEach(operation -> executeSubscriptionOperation(operation));
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#subscribeCurrentTariffStop()
	 */
	@Override
	public void subscribeCurrentTariffStop() {
		executeSubscriptionOperation(TicketValidationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_TARIFF_STOP);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#unsubscribeCurrentTariffStop()
	 */
	@Override
	public void unsubscribeCurrentTariffStop() {
		executeSubscriptionOperation(TicketValidationServiceConstants.OPERATION_UNSUBSCRIBE_CURRENT_TARIFF_STOP);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#subscribeRazzia()
	 */
	@Override
	public void subscribeRazzia() {
		executeSubscriptionOperation(TicketValidationServiceConstants.OPERATION_SUBSCRIBE_RAZZIA);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#unsubscribeRazzia()
	 */
	@Override
	public void unsubscribeRazzia() {
		executeSubscriptionOperation(TicketValidationServiceConstants.OPERATION_UNSUBSCRIBE_RAZZIA);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#subscribeCurrentLine()
	 */
	@Override
	public void subscribeCurrentLine() {
		executeSubscriptionOperation(TicketValidationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_LINE);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#unsubscribeCurrentLine()
	 */
	@Override
	public void unsubscribeCurrentLine() {
		executeSubscriptionOperation(TicketValidationServiceConstants.OPERATION_UNSUBSCRIBE_CURRENT_LINE);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#subscribeVehicleData()
	 */
	@Override
	public void subscribeVehicleData() {
		executeSubscriptionOperation(TicketValidationServiceConstants.OPERATION_SUBSCRIBE_VEHICLE_DATA);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#unsubscribeVehicleData()
	 */
	@Override
	public void unsubscribeVehicleData() {
		executeSubscriptionOperation(TicketValidationServiceConstants.OPERATION_UNSUBSCRIBE_VEHICLE_DATA);
	}
	
	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#subscribeShortHaulStops()
	 */
	@Override
	public void subscribeShortHaulStops() {
		executeSubscriptionOperation(TicketValidationServiceConstants.OPERATION_SUBSCRIBE_SHORT_HAUL_STOPS);		
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#unsubscribeShortHaulStops()
	 */
	@Override
	public void unsubscribeShortHaulStops() {
		executeSubscriptionOperation(TicketValidationServiceConstants.OPERATION_UNSUBSCRIBE_SHORT_HAUL_STOPS);	
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#getCurrentTariffStop()
	 */
	@Override
	public CurrentTariffStopResponse getCurrentTariffStop() {
		return executeGetOperation(TicketValidationServiceConstants.OPERATION_GET_CURRENT_TARIFF_STOP,
				ticketValidationServicePackage.getCurrentTariffStopResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#getRazzia()
	 */
	@Override
	public RazziaResponse getRazzia() {
		return executeGetOperation(TicketValidationServiceConstants.OPERATION_GET_RAZZIA,
				ticketValidationServicePackage.getRazziaResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#getCurrentLine()
	 */
	@Override
	public CurrentLineResponse getCurrentLine() {
		return executeGetOperation(TicketValidationServiceConstants.OPERATION_GET_CURRENT_LINE,
				ticketValidationServicePackage.getCurrentLineResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#getVehicleData()
	 */
	@Override
	public VehicleDataResponse getVehicleData() {
		return executeGetOperation(TicketValidationServiceConstants.OPERATION_GET_VEHICLE_DATA,
				ticketValidationServicePackage.getVehicleDataResponse());
	}
	
	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisTicketValidationService#getShortHaulsStops()
	 */
	@Override
	public CurrentShortHaulStopsResponse getShortHaulsStops() {
		return executeGetOperation(TicketValidationServiceConstants.OPERATION_GET_SHORT_HAUL_STOPS,
				ticketValidationServicePackage.getCurrentShortHaulStopsResponse());
	}
	
	private void executeSubscriptionOperation(String operation) {
		IbisTCPHelper.sendSubscriptionRequest(config, operation, ibisCommonPackage, resourceSetFactory);
	}
	
	private <T extends GeneralResponse> T executeGetOperation(String operation, EClass responseType) {
		return IbisHttpRequestHelper.sendHttpRequest(config, operation, null, responseType, resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getRefDeviceId()
	 */
	@Override
	public String getRefDeviceId() {
		return config.refDeviceId();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getRefDeviceType()
	 */
	@Override
	public String getRefDeviceType() {
		return config.refDeviceType();
	}

}
