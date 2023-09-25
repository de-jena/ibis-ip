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
package de.jena.ibis.components;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.GeneralIbisTCPService;
import de.jena.ibis.apis.IbisPassengerCountingService;
import de.jena.ibis.apis.IbisTCPServiceConfig;
import de.jena.ibis.apis.constants.PassengerCountingServiceConstants;
import de.jena.ibis.components.helper.IbisTCPHelper;
import de.jena.model.ibis.common.DataAcceptedResponse;
import de.jena.model.ibis.common.GeneralResponse;
import de.jena.model.ibis.common.GeneralRetrieveRequest;
import de.jena.model.ibis.common.IbisCommonPackage;
import de.jena.model.ibis.passengercountingservice.AllDataResponse;
import de.jena.model.ibis.passengercountingservice.CountingStateResponse;
import de.jena.model.ibis.passengercountingservice.IbisPassengerCountingServicePackage;
import de.jena.model.ibis.passengercountingservice.RetrieveSpecificDoorDataResponse;
import de.jena.model.ibis.passengercountingservice.SetCounterDataRequest;
import de.jena.model.ibis.passengercountingservice.StartCountingRequest;
import de.jena.model.ibis.passengercountingservice.StopCountingRequest;

/**
 * 
 * @author ilenia
 * @since Jun 22, 2023
 */
@Component(immediate=true, name = "IbisPassengerCountingService", 
service = {IbisPassengerCountingService.class, GeneralIbisTCPService.class, GeneralIbisService.class},
configurationPid = "IbisPassengerCountingService", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class IbisPassengerCountingServiceImpl implements IbisPassengerCountingService {
	
	@Reference
	IbisPassengerCountingServicePackage passengerCountingServicePackage;
	
	@Reference 
	IbisCommonPackage ibisCommonPackage;
	
	@Reference(target = "(emf.resource.configurator.name=GeckoXMLResourceFactory)")
	private ComponentServiceObjects<ResourceSet> resourceSetFactory;
	
	private IbisTCPServiceConfig config;


	@Activate
	@Modified
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
	 * @see de.jena.ibis.apis.IbisPassengerCountingService#subscribeAllData()
	 */
	@Override
	public void subscribeAllData() {
		IbisTCPHelper.executeSubscriptionOperation(config,
				PassengerCountingServiceConstants.OPERATION_SUBSCRIBE_ALL_DATA, 
				ibisCommonPackage, resourceSetFactory, true);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisPassengerCountingService#unsubscribeAllData()
	 */
	@Override
	public void unsubscribeAllData() {
		IbisTCPHelper.executeSubscriptionOperation(config,
				PassengerCountingServiceConstants.OPERATION_UNSUBSCRIBE_ALL_DATA, 
				ibisCommonPackage, resourceSetFactory, false);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisPassengerCountingService#subscribeCountingState()
	 */
	@Override
	public void subscribeCountingState() {
		IbisTCPHelper.executeSubscriptionOperation(config,
				PassengerCountingServiceConstants.OPERATION_SUBSCRIBE_COUNTING_STATE, 
				ibisCommonPackage, resourceSetFactory, true);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisPassengerCountingService#unsubscribeCountingState()
	 */
	@Override
	public void unsubscribeCountingState() {
		IbisTCPHelper.executeSubscriptionOperation(config,
				PassengerCountingServiceConstants.OPERATION_UNSUBSCRIBE_COUNTING_STATE, 
				ibisCommonPackage, resourceSetFactory, false);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisPassengerCountingService#getAllData()
	 */
	@Override
	public AllDataResponse getAllData() {
		return IbisTCPHelper.executeGetOperation(config, 
				PassengerCountingServiceConstants.OPERATION_GET_ALL_DATA, 
				passengerCountingServicePackage.getAllDataResponse(),
				resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisPassengerCountingService#getCountingState()
	 */
	@Override
	public CountingStateResponse getCountingState() {
		return IbisTCPHelper.executeGetOperation(config, 
				PassengerCountingServiceConstants.OPERATION_GET_COUNTING_STATE, 
				passengerCountingServicePackage.getCountingStateResponse(),
				resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisPassengerCountingService#retrieveSpecificDoorData(de.jena.model.ibis.common.GeneralRetrieveRequest)
	 */
	@Override
	public RetrieveSpecificDoorDataResponse retrieveSpecificDoorData(GeneralRetrieveRequest request) {
		return IbisTCPHelper.executeRetrieveOperation(config, 
				PassengerCountingServiceConstants.OPERATION_RETRIEVE_SPECIFIC_DOOR_DATA, 
				request, passengerCountingServicePackage.getRetrieveSpecificDoorDataResponse(), resourceSetFactory);		
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisPassengerCountingService#setCounterData(de.jena.ibis.apis.SetCounterDataRequest)
	 */
	@Override
	public DataAcceptedResponse setCounterData(SetCounterDataRequest request) {
		throw new NotImplementedException("Operation not yet implemented!");
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisPassengerCountingService#startCounting(de.jena.ibis.apis.StartCountingRequest)
	 */
	@Override
	public DataAcceptedResponse startCounting(StartCountingRequest request) {
		throw new NotImplementedException("Operation not yet implemented!");
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisPassengerCountingService#stopCounting(de.jena.ibis.apis.StopCountingRequest)
	 */
	@Override
	public DataAcceptedResponse stopCounting(StopCountingRequest request) {
		throw new NotImplementedException("Operation not yet implemented!");
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeAllGetOperations()
	 */
	@Override
	public List<GeneralResponse> executeAllGetOperations() {
		List<GeneralResponse> results = new ArrayList<>();
		PassengerCountingServiceConstants.getAllGetOperations().forEach(operation -> results.add(executeGetOperation(operation)));
		return results;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeGetOperation(java.lang.String)
	 */
	@Override
	public GeneralResponse executeGetOperation(String operation) {
		switch(operation) {
		case PassengerCountingServiceConstants.OPERATION_GET_ALL_DATA:
			return getAllData();
		case PassengerCountingServiceConstants.OPERATION_GET_COUNTING_STATE:
			return getCountingState();
		default:
			throw new IllegalArgumentException(String.format("Operation %s not implemented for %s!", operation, config.serviceName()));			
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeRetrieveOperation(java.lang.String, de.jena.model.ibis.common.GeneralRetrieveRequest)
	 */
	@Override
	public GeneralResponse executeRetrieveOperation(String operation, GeneralRetrieveRequest request) {
		switch(operation) {
		case PassengerCountingServiceConstants.OPERATION_RETRIEVE_SPECIFIC_DOOR_DATA:
			return retrieveSpecificDoorData(request);
		default:
			throw new IllegalArgumentException(String.format("Retrieve Operation %s not implemented for %s!", operation, config.serviceName()));			
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getServiceName()
	 */
	@Override
	public String getServiceName() {
		return config.serviceName();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getServiceId()
	 */
	@Override
	public String getServiceId() {
		return config.serviceId();
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

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#executeAllSubscriptionOperations()
	 */
	@Override
	public void executeAllSubscriptionOperations() {
		PassengerCountingServiceConstants.getAllSubscriptionOperations()
		.forEach(operation -> IbisTCPHelper.executeSubscriptionOperation(config, operation, ibisCommonPackage, resourceSetFactory, true));
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#executeAllUnsubscriptionOperations()
	 */
	@Override
	public void executeAllUnsubscriptionOperations() {
		PassengerCountingServiceConstants.getAllUnsubscriptionOperations()
		.forEach(operation -> IbisTCPHelper.executeSubscriptionOperation(config, operation, ibisCommonPackage, resourceSetFactory, false));
	}

}
