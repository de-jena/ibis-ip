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
import org.osgi.service.component.annotations.Reference;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.GeneralIbisTCPService;
import de.jena.ibis.apis.IbisDeviceManagementService;
import de.jena.ibis.apis.IbisTCPServiceConfig;
import de.jena.ibis.apis.constants.DeviceManagementServiceConstants;
import de.jena.ibis.components.helper.IbisHttpRequestHelper;
import de.jena.ibis.components.helper.IbisTCPHelper;
import de.jena.model.ibis.common.DataAcceptedResponse;
import de.jena.model.ibis.common.GeneralResponse;
import de.jena.model.ibis.common.GeneralRetrieveRequest;
import de.jena.model.ibis.common.IbisCommonPackage;
import de.jena.model.ibis.devicemanagementservice.AllSubdeviceErrorMessagesResponse;
import de.jena.model.ibis.devicemanagementservice.AllSubdeviceInformationResponse;
import de.jena.model.ibis.devicemanagementservice.DeviceConfigurationResponse;
import de.jena.model.ibis.devicemanagementservice.DeviceErrorMessagesResponse;
import de.jena.model.ibis.devicemanagementservice.DeviceInformationResponse;
import de.jena.model.ibis.devicemanagementservice.DeviceStatusInformationResponse;
import de.jena.model.ibis.devicemanagementservice.DeviceStatusResponse;
import de.jena.model.ibis.devicemanagementservice.FinalizeUpdateRequest;
import de.jena.model.ibis.devicemanagementservice.FinalizeUpdateResponse;
import de.jena.model.ibis.devicemanagementservice.IbisDeviceManagementServicePackage;
import de.jena.model.ibis.devicemanagementservice.InstallUpdateRequest;
import de.jena.model.ibis.devicemanagementservice.InstallUpdateResponse;
import de.jena.model.ibis.devicemanagementservice.RetrieveUpdateStateResponse;
import de.jena.model.ibis.devicemanagementservice.ServiceInformationResponse;
import de.jena.model.ibis.devicemanagementservice.ServiceStatusResponse;
import de.jena.model.ibis.devicemanagementservice.UpdateHistoryResponse;

@Component(immediate = true, name = "IbisDeviceManagementService", 
service = {IbisDeviceManagementService.class, GeneralIbisTCPService.class, GeneralIbisService.class},
configurationPid = "DeviceManagementService", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class IbisDeviceManagementServiceImpl implements IbisDeviceManagementService{
	
	@Reference
	IbisDeviceManagementServicePackage deviceManagementServicePackage;
	
	@Reference 
	IbisCommonPackage ibisCommonPackage;
	
	@Reference(target = "(emf.resource.configurator.name=GeckoXMLResourceFactory)")
	private ComponentServiceObjects<ResourceSet> resourceSetFactory;
	
    private IbisTCPServiceConfig config;


	@Activate
	public void activate(IbisTCPServiceConfig config) throws ConfigurationException{
		IbisTCPHelper.checkTCPServiceConfig(config);
		this.config = config;
	}
	
	@Deactivate() 
	public void deactivate() {
		executeAllUnsubscriptionOperations();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getDeviceInformation()
	 */
	@Override
	public DeviceInformationResponse getDeviceInformation() {
		return executeGetOperation(DeviceManagementServiceConstants.OPERATION_GET_DEVICE_INFO, 
				deviceManagementServicePackage.getDeviceInformationResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getDeviceConfiguration()
	 */
	@Override
	public DeviceConfigurationResponse getDeviceConfiguration() {
		return executeGetOperation(DeviceManagementServiceConstants.OPERATION_GET_DEVICE_CONFIGURATION, 
				deviceManagementServicePackage.getDeviceConfigurationResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getDeviceStatus()
	 */
	@Override
	public DeviceStatusResponse getDeviceStatus() {
		return executeGetOperation(DeviceManagementServiceConstants.OPERATION_GET_DEVICE_STATUS, 
				deviceManagementServicePackage.getDeviceStatusResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getDeviceErrorMessages()
	 */
	@Override
	public DeviceErrorMessagesResponse getDeviceErrorMessages() {
		return executeGetOperation(DeviceManagementServiceConstants.OPERATION_GET_DEVICE_ERR_MSG, 
				deviceManagementServicePackage.getDeviceErrorMessagesResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getServiceInformation()
	 */
	@Override
	public ServiceInformationResponse getServiceInformation() {
		return executeGetOperation(DeviceManagementServiceConstants.OPERATION_GET_SERVICE_INFO, 
				deviceManagementServicePackage.getServiceInformationResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getServiceStatus()
	 */
	@Override
	public ServiceStatusResponse getServiceStatus() {
		return executeGetOperation(DeviceManagementServiceConstants.OPERATION_GET_SERVICE_STATUS,
				deviceManagementServicePackage.getServiceStatusResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getAllSubdeviceInformation()
	 */
	@Override
	public AllSubdeviceInformationResponse getAllSubdeviceInformation() {
		return executeGetOperation(DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_INFO,
				deviceManagementServicePackage.getAllSubdeviceInformationResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getDeviceStatusInformation()
	 */
	@Override
	public DeviceStatusInformationResponse getDeviceStatusInformation() {
		return executeGetOperation(DeviceManagementServiceConstants.OPERATION_GET_DEVICE_STATUS_INFO,
				deviceManagementServicePackage.getDeviceStatusInformationResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getAllSubdeviceStatusInformation()
	 */
	@Override
	public AllSubdeviceInformationResponse getAllSubdeviceStatusInformation() {
		return executeGetOperation(DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_STATUS_INFO,
				deviceManagementServicePackage.getAllSubdeviceStatusInformationResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getAllSubdeviceErrorMessages()
	 */
	@Override
	public AllSubdeviceErrorMessagesResponse getAllSubdeviceErrorMessages() {
		return executeGetOperation(DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_ERR_MSG, 
				deviceManagementServicePackage.getAllSubdeviceErrorMessagesResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeDeviceInformation(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeDeviceInformation() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeDeviceInformation(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeDeviceInformation() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_DEVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeDeviceStatus(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeDeviceStatus() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_STATUS);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeDeviceStatus(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeDeviceStatus() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_DEVICE_STATUS);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeDeviceErrorMessages(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeDeviceErrorMessages() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_ERR_MSG);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeDeviceErrorMessages(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeDeviceErrorMessages() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_DEVICE_ERR_MSG);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeServiceInformation(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeServiceInformation() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_SERVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeServiceInformation(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeServiceInformation() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_SERVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeServiceStatus(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeServiceStatus() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_SERVICE_STATUS);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeServiceStatus(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeServiceStatus() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_SERVICE_STATUS);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeAllSubdeviceInformation(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeAllSubdeviceInformation() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_ALL_SUBDEVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeAllSubdeviceInformation(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeAllSubdeviceInformation() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_ALL_SUBDEVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeDeviceStatusInformation(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeDeviceStatusInformation() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_STATUS_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeDeviceStatusInformation(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeDeviceStatusInformation() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_DEVICE_STATUS_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeAllSubdeviceStatusInformation(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeAllSubdeviceStatusInformation() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_ALL_SUBDEVICE_STATUS_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeAllSubdeviceStatusInformation(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeAllSubdeviceStatusInformation() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_ALL_SUBDEVICE_STATUS_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeAllSubdeviceErrorMessages(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public void subscribeAllSubdeviceErrorMessages() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_ALL_SUBDEVICE_ERR_MSG);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeAllSubdeviceErrorMessages(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public void unsubscribeAllSubdeviceErrorMessages() {
		executeSubscriptionOperation(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_ALL_SUBDEVICE_ERR_MSG);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#installUpdate(de.jena.ibis.devicemanagementservice.DeviceManagementServiceInstallUpdateRequest)
	 */
	@Override
	public InstallUpdateResponse installUpdate(InstallUpdateRequest request) {
		throw new NotImplementedException("Operation not yet implemented!");
	}

	
	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#retrieveUpdateState(GeneralRetrieveRequest)
	 */
	@Override
	public RetrieveUpdateStateResponse retrieveUpdateState(GeneralRetrieveRequest request) {
		return executeRetrieveOperation(DeviceManagementServiceConstants.OPERATION_RETRIEVE_UPDATE_STATE, 
				request, deviceManagementServicePackage.getRetrieveUpdateStateResponse());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getUpdateHistory()
	 */
	@Override
	public UpdateHistoryResponse getUpdateHistory() {
		throw new NotImplementedException("Operation not yet implemented!");
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#finalizeUpdate(de.jena.ibis.devicemanagementservice.DeviceManagementServiceFinalizeUpdateRequest)
	 */
	@Override
	public FinalizeUpdateResponse finalizeUpdate(FinalizeUpdateRequest request) {
		throw new NotImplementedException("Operation not yet implemented!");
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#finalizeAllPendingUpdates()
	 */
	@Override
	public DataAcceptedResponse finalizeAllPendingUpdates() {
		throw new NotImplementedException("Operation not yet implemented!");
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeGetOperation(java.lang.String)
	 */
	@Override
	public GeneralResponse executeGetOperation(String operation) {
		switch(operation) {
		case DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_ERR_MSG:
			return getAllSubdeviceErrorMessages();
		case DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_INFO:
			return getAllSubdeviceInformation();
		case DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_STATUS_INFO:
			return getAllSubdeviceStatusInformation();
		case DeviceManagementServiceConstants.OPERATION_GET_DEVICE_CONFIGURATION:
			return getDeviceConfiguration();
		case DeviceManagementServiceConstants.OPERATION_GET_DEVICE_ERR_MSG:
			return getDeviceErrorMessages();
		case DeviceManagementServiceConstants.OPERATION_GET_DEVICE_INFO:
			return getDeviceInformation();
		case DeviceManagementServiceConstants.OPERATION_GET_DEVICE_STATUS:
			return getDeviceStatus();
		case DeviceManagementServiceConstants.OPERATION_GET_DEVICE_STATUS_INFO:
			return getDeviceStatusInformation();
		case DeviceManagementServiceConstants.OPERATION_GET_SERVICE_INFO:
			return getServiceInformation();
		case DeviceManagementServiceConstants.OPERATION_GET_SERVICE_STATUS:
			return getServiceStatus();
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
		DeviceManagementServiceConstants.getAllGetOperations().forEach(operation -> results.add(executeGetOperation(operation)));
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
		DeviceManagementServiceConstants.getAllSubscriptionOperations().forEach(operation -> executeSubscriptionOperation(operation));
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeAllUnsubscriptionOperations()
	 */
	@Override
	public void executeAllUnsubscriptionOperations() {
		DeviceManagementServiceConstants.getAllUnsubscriptionOperations().forEach(operation -> executeSubscriptionOperation(operation));
	}
	
	private void executeSubscriptionOperation(String operation) {
		IbisTCPHelper.sendSubscriptionRequest(config, operation, ibisCommonPackage, resourceSetFactory);
	}
	
	private <T extends GeneralResponse> T executeGetOperation(String operation, EClass responseType) {
		return IbisHttpRequestHelper.sendHttpRequest(config, operation, null, responseType, resourceSetFactory);
	}
	
	private <T extends GeneralResponse> T executeRetrieveOperation(String operation, GeneralRetrieveRequest request, EClass responseType) {
		return IbisHttpRequestHelper.sendHttpRequest(config, operation, request, responseType, resourceSetFactory);
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
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeRetrieveOperation(java.lang.String, de.jena.model.ibis.common.GeneralRetrieveRequest)
	 */
	@Override
	public GeneralResponse executeRetrieveOperation(String operation, GeneralRetrieveRequest request) {
		switch(operation) {
		case DeviceManagementServiceConstants.OPERATION_RETRIEVE_UPDATE_STATE:
			return retrieveUpdateState(request);
		default:
			throw new IllegalArgumentException(String.format("Retrieve Operation %s not implemented for %s!", operation, config.serviceName()));			
		}
	}
	
}
