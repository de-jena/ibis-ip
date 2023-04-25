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

import org.apache.commons.lang3.NotImplementedException;
import org.eclipse.emf.ecore.EClass;
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
import de.jena.ibis.apis.IbisCustomerInformationService;
import de.jena.ibis.apis.IbisTCPServiceConfig;
import de.jena.ibis.apis.constants.CustomerInformationServiceConstants;
import de.jena.ibis.components.helper.IbisHttpRequestHelper;
import de.jena.ibis.components.helper.IbisTCPHelper;
import de.jena.model.ibis.common.GeneralResponse;
import de.jena.model.ibis.common.IbisCommonPackage;
import de.jena.model.ibis.customerinformationservice.AllDataResponse;
import de.jena.model.ibis.customerinformationservice.CurrentAnnouncementResponse;
import de.jena.model.ibis.customerinformationservice.CurrentConnectionInformationResponse;
import de.jena.model.ibis.customerinformationservice.CurrentDisplayContentResponse;
import de.jena.model.ibis.customerinformationservice.CurrentStopIndexResponse;
import de.jena.model.ibis.customerinformationservice.CurrentStopPointResponse;
import de.jena.model.ibis.customerinformationservice.IbisCustomerInformationServicePackage;
import de.jena.model.ibis.customerinformationservice.PartialStopSequenceRequest;
import de.jena.model.ibis.customerinformationservice.PartialStopSequenceResponse;
import de.jena.model.ibis.customerinformationservice.TripDataResponse;
import de.jena.model.ibis.customerinformationservice.VehicleDataResponse;


/**
 * 
 * @author ilenia
 * @since Jan 18, 2023
 */
@Component(immediate=true, name = "IbisCustomerInformationService", 
service = {IbisCustomerInformationService.class, GeneralIbisTCPService.class, GeneralIbisService.class},
configurationPid = "CustomerInformationService", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class IbisCustomerInformationServiceImpl implements IbisCustomerInformationService {

	@Reference
	IbisCustomerInformationServicePackage customerInfoServicePackage;
	
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
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getAllData()
	 */
	@Override
	public AllDataResponse getAllData() {
		return executeGetOperation(CustomerInformationServiceConstants.OPERATION_GET_ALL_DATA, 
				customerInfoServicePackage.getAllDataResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getCurrentAnnouncement()
	 */
	@Override
	public CurrentAnnouncementResponse getCurrentAnnouncement() {
		return executeGetOperation(CustomerInformationServiceConstants.OPERATION_GET_CURRENT_ANNOUNCEMENT, 
				customerInfoServicePackage.getCurrentAnnouncementResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getCurrentConnectionInformation()
	 */
	@Override
	public CurrentConnectionInformationResponse getCurrentConnectionInformation() {
		return executeGetOperation(CustomerInformationServiceConstants.OPERATION_GET_CURRENT_CONNECTION_INFO, 
				customerInfoServicePackage.getCurrentConnectionInformationResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getCurrentDisplayContent()
	 */
	@Override
	public CurrentDisplayContentResponse getCurrentDisplayContent() {
		return executeGetOperation(CustomerInformationServiceConstants.OPERATION_GET_CURRENT_DISPLAY_CONTENT,
				customerInfoServicePackage.getCurrentDisplayContentResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getCurrentStopPoint()
	 */
	@Override
	public CurrentStopPointResponse getCurrentStopPoint() {
		return executeGetOperation(CustomerInformationServiceConstants.OPERATION_GET_CURRENT_STOP_POINT,
				customerInfoServicePackage.getCurrentStopPointResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getCurrentStopIndex()
	 */
	@Override
	public CurrentStopIndexResponse getCurrentStopIndex() {
		return executeGetOperation(CustomerInformationServiceConstants.OPERATION_GET_CURRENT_STOP_INDEX, 
				customerInfoServicePackage.getCurrentStopIndexResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getTripData()
	 */
	@Override
	public TripDataResponse getTripData() {
		return executeGetOperation(CustomerInformationServiceConstants.OPERATION_GET_TRIP_DATA,
				customerInfoServicePackage.getTripDataResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getVehicleData()
	 */
	@Override
	public VehicleDataResponse getVehicleData() {
		return executeGetOperation(CustomerInformationServiceConstants.OPERATION_GET_VEHICLE_DATA, 
				customerInfoServicePackage.getVehicleDataResponse());
	}

	
	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeAllData()
	 */
	@Override
	public void subscribeAllData() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_ALL_DATA);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeAllData(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeAllData() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_UNSUBSCRIBE_ALL_DATA);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeCurrentAnnouncement(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeCurrentAnnouncement() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_ANNOUNCEMENT);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeCurrentAnnouncement(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeCurrentAnnouncement() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_UNSUBSCRIBE_CURRENT_ANNOUNCEMENT);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeCurrentConnectionInformation(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeCurrentConnectionInformation() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_CONNECTION_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeCurrentConnectionInformation(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeCurrentConnectionInformation() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_UNSUBSCRIBE_CURRENT_CONNECTION_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeCurrentDisplayContent(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeCurrentDisplayContent() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_DISPLAY_CONTENT);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeCurrentDisplayContent(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeCurrentDisplayContent() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_UNSUBSCRIBE_CURRENT_DISPLAY_CONTENT);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeCurrentStopPoint(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeCurrentStopPoint() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_STOP_POINT);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeCurrentStopPoint(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeCurrentStopPoint() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_UNSUBSCRIBE_CURRENT_STOP_POINT);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeCurrentStopIndex(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeCurrentStopIndex() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_STOP_INDEX);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeCurrentStopIndex(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeCurrentStopIndex() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_UNSUBSCRIBE_CURRENT_STOP_INDEX);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeTripData(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeTripData() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_TRIP_DATA);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeTripData(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeTripData() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_UNSUBSCRIBE_TRIP_DATA);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeVehicleData(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeVehicleData() {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_VEHICLE_DATA);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeVehicleData(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeVehicleData(	) {
		executeSubscriptionOperation(CustomerInformationServiceConstants.OPERATION_UNSUBSCRIBE_VEHICLE_DATA);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#retrievePartialStopSequence(de.jena.ibis.customerinformationservice.CustomerInformationServiceRetrievePartialStopSequenceRequest)
	 */
	@Override
	public PartialStopSequenceResponse retrievePartialStopSequence(PartialStopSequenceRequest request) {
		throw new NotImplementedException("Operation not supported yet!");
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#executeGetOperation(java.lang.String)
	 */
	@Override
	public GeneralResponse executeGetOperation(String operation) {
		switch(operation) {
		case CustomerInformationServiceConstants.OPERATION_GET_ALL_DATA:
			return getAllData();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_ANNOUNCEMENT:
			return getCurrentAnnouncement();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_CONNECTION_INFO:
			return getCurrentConnectionInformation();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_DISPLAY_CONTENT:
			return getCurrentDisplayContent();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_STOP_INDEX:
			return getCurrentStopIndex();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_STOP_POINT:
			return getCurrentStopPoint();
		case CustomerInformationServiceConstants.OPERATION_GET_TRIP_DATA:
			return getTripData();
		case CustomerInformationServiceConstants.OPERATION_GET_VEHICLE_DATA:
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
		CustomerInformationServiceConstants.getAllGetOperations().forEach(operation -> results.add(executeGetOperation(operation)));
		return results;
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
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeAllSubscriptionOperations()
	 */
	@Override
	public void executeAllSubscriptionOperations() {
		CustomerInformationServiceConstants.getAllSubscriptionOperations()
		.forEach(operation -> executeSubscriptionOperation(operation));
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeAllUnsubscriptionOperations()
	 */
	@Override
	public void executeAllUnsubscriptionOperations() {
		CustomerInformationServiceConstants.getAllUnsubscriptionOperations()
		.forEach(operation -> executeSubscriptionOperation(operation));
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
