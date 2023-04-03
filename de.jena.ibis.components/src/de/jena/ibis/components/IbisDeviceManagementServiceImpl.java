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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.GeneralIbisTCPService;
import de.jena.ibis.apis.IbisDeviceManagementService;
import de.jena.ibis.apis.IbisTCPServiceConfig;
import de.jena.ibis.components.helper.DeviceManagementServiceConstants;
import de.jena.ibis.components.helper.IbisHttpRequestHelper;
import de.jena.ibis.components.helper.IbisTCPHelper;
import de.jena.ibis.ibis_common.DataAcceptedResponse;
import de.jena.ibis.ibis_common.GeneralResponse;
import de.jena.ibis.ibis_common.IbisCommonPackage;
import de.jena.ibis.ibis_devicemanagementservice.AllSubdeviceErrorMessagesResponse;
import de.jena.ibis.ibis_devicemanagementservice.AllSubdeviceInformationResponse;
import de.jena.ibis.ibis_devicemanagementservice.DeviceConfigurationResponse;
import de.jena.ibis.ibis_devicemanagementservice.DeviceErrorMessagesResponse;
import de.jena.ibis.ibis_devicemanagementservice.DeviceInformationResponse;
import de.jena.ibis.ibis_devicemanagementservice.DeviceStatusInformationResponse;
import de.jena.ibis.ibis_devicemanagementservice.DeviceStatusResponse;
import de.jena.ibis.ibis_devicemanagementservice.FinalizeUpdateRequest;
import de.jena.ibis.ibis_devicemanagementservice.FinalizeUpdateResponse;
import de.jena.ibis.ibis_devicemanagementservice.IbisDeviceManagementServicePackage;
import de.jena.ibis.ibis_devicemanagementservice.InstallUpdateRequest;
import de.jena.ibis.ibis_devicemanagementservice.InstallUpdateResponse;
import de.jena.ibis.ibis_devicemanagementservice.RetrieveUpdateStateRequest;
import de.jena.ibis.ibis_devicemanagementservice.RetrieveUpdateStateResponse;
import de.jena.ibis.ibis_devicemanagementservice.ServiceInformationResponse;
import de.jena.ibis.ibis_devicemanagementservice.ServiceStatusResponse;
import de.jena.ibis.ibis_devicemanagementservice.UpdateHistoryResponse;

@Component(name = "IbisDeviceManagementService", 
scope = ServiceScope.PROTOTYPE, service = {IbisDeviceManagementService.class, GeneralIbisTCPService.class, GeneralIbisService.class},
configurationPid = "DeviceManagementService", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class IbisDeviceManagementServiceImpl implements IbisDeviceManagementService{
	
	@Reference
	IbisDeviceManagementServicePackage deviceManagementServicePackage;
	
	@Reference 
	IbisCommonPackage ibisCommonPackage;
	
	@Reference
	private ComponentServiceObjects<ResourceSet> resourceSetFactory;
	
    private IbisTCPServiceConfig config;


	@Activate
	public void activate(IbisTCPServiceConfig config) throws ConfigurationException{
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
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getDeviceInformation()
	 */
	@Override
	public DeviceInformationResponse getDeviceInformation() {
		return IbisHttpRequestHelper.sendHttpRequest(config.serviceIP(), config.servicePort(), DeviceManagementServiceConstants.SERVICE_NAME, 
				DeviceManagementServiceConstants.OPERATION_GET_DEVICE_INFO, null, 
				deviceManagementServicePackage.getDeviceInformationResponse(), resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getDeviceConfiguration()
	 */
	@Override
	public DeviceConfigurationResponse getDeviceConfiguration() {
		return IbisHttpRequestHelper.sendHttpRequest(config.serviceIP(), config.servicePort(), DeviceManagementServiceConstants.SERVICE_NAME, 
				DeviceManagementServiceConstants.OPERATION_GET_DEVICE_CONFIGURATION, null, 
				deviceManagementServicePackage.getDeviceConfigurationResponse(), resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getDeviceStatus()
	 */
	@Override
	public DeviceStatusResponse getDeviceStatus() {
		return IbisHttpRequestHelper.sendHttpRequest(config.serviceIP(), config.servicePort(), DeviceManagementServiceConstants.SERVICE_NAME, 
				DeviceManagementServiceConstants.OPERATION_GET_DEVICE_STATUS, null, 
				deviceManagementServicePackage.getDeviceStatusResponse(), resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getDeviceErrorMessages()
	 */
	@Override
	public DeviceErrorMessagesResponse getDeviceErrorMessages() {
		return IbisHttpRequestHelper.sendHttpRequest(config.serviceIP(), config.servicePort(), DeviceManagementServiceConstants.SERVICE_NAME, 
				DeviceManagementServiceConstants.OPERATION_GET_DEVICE_ERR_MSG, null, 
				deviceManagementServicePackage.getDeviceErrorMessagesResponse(), resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getServiceInformation()
	 */
	@Override
	public ServiceInformationResponse getServiceInformation() {
		return IbisHttpRequestHelper.sendHttpRequest(config.serviceIP(), config.servicePort(), DeviceManagementServiceConstants.SERVICE_NAME, 
				DeviceManagementServiceConstants.OPERATION_GET_SERVICE_INFO, null, 
				deviceManagementServicePackage.getServiceInformationResponse(), resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getServiceStatus()
	 */
	@Override
	public ServiceStatusResponse getServiceStatus() {
		return IbisHttpRequestHelper.sendHttpRequest(config.serviceIP(), config.servicePort(), DeviceManagementServiceConstants.SERVICE_NAME, 
				DeviceManagementServiceConstants.OPERATION_GET_SERVICE_STATUS, null, 
				deviceManagementServicePackage.getServiceStatusResponse(), resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getAllSubdeviceInformation()
	 */
	@Override
	public AllSubdeviceInformationResponse getAllSubdeviceInformation() {
		return IbisHttpRequestHelper.sendHttpRequest(config.serviceIP(), config.servicePort(), DeviceManagementServiceConstants.SERVICE_NAME, 
				DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_INFO, null, 
				deviceManagementServicePackage.getAllSubdeviceInformationResponse(), resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getDeviceStatusInformation()
	 */
	@Override
	public DeviceStatusInformationResponse getDeviceStatusInformation() {
		return IbisHttpRequestHelper.sendHttpRequest(config.serviceIP(), config.servicePort(), DeviceManagementServiceConstants.SERVICE_NAME, 
				DeviceManagementServiceConstants.OPERATION_GET_DEVICE_STATUS_INFO, null, 
				deviceManagementServicePackage.getDeviceStatusInformationResponse(), resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getAllSubdeviceStatusInformation()
	 */
	@Override
	public AllSubdeviceInformationResponse getAllSubdeviceStatusInformation() {
		return IbisHttpRequestHelper.sendHttpRequest(config.serviceIP(), config.servicePort(), DeviceManagementServiceConstants.SERVICE_NAME, 
				DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_STATUS_INFO, null, 
				deviceManagementServicePackage.getAllSubdeviceStatusInformationResponse(), resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#getAllSubdeviceErrorMessages()
	 */
	@Override
	public AllSubdeviceErrorMessagesResponse getAllSubdeviceErrorMessages() {
		return IbisHttpRequestHelper.sendHttpRequest(config.serviceIP(), config.servicePort(), DeviceManagementServiceConstants.SERVICE_NAME, 
				DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_ERR_MSG, null, 
				deviceManagementServicePackage.getAllSubdeviceErrorMessagesResponse(), resourceSetFactory);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeDeviceInformation(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public Integer subscribeDeviceInformation() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeDeviceInformation(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public Integer unsubscribeDeviceInformation() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_DEVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeDeviceStatus(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public Integer subscribeDeviceStatus() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_STATUS);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeDeviceStatus(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public Integer unsubscribeDeviceStatus() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_DEVICE_STATUS);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeDeviceErrorMessages(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public Integer subscribeDeviceErrorMessages() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_ERR_MSG);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeDeviceErrorMessages(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public Integer unsubscribeDeviceErrorMessages() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_DEVICE_ERR_MSG);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeServiceInformation(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public Integer subscribeServiceInformation() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_SERVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeServiceInformation(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public Integer unsubscribeServiceInformation() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_SERVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeServiceStatus(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public Integer subscribeServiceStatus() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_SERVICE_STATUS);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeServiceStatus(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public Integer unsubscribeServiceStatus() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_SERVICE_STATUS);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeAllSubdeviceInformation(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public Integer subscribeAllSubdeviceInformation() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_ALL_SUBDEVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeAllSubdeviceInformation(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public Integer unsubscribeAllSubdeviceInformation() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_ALL_SUBDEVICE_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeDeviceStatusInformation(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public Integer subscribeDeviceStatusInformation() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_STATUS_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeDeviceStatusInformation(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public Integer unsubscribeDeviceStatusInformation() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_DEVICE_STATUS_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeAllSubdeviceStatusInformation(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public Integer subscribeAllSubdeviceStatusInformation() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_ALL_SUBDEVICE_STATUS_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeAllSubdeviceStatusInformation(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public Integer unsubscribeAllSubdeviceStatusInformation() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_ALL_SUBDEVICE_STATUS_INFO);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#subscribeAllSubdeviceErrorMessages(de.jena.ibis.common.SubscribeRequest)
	 */
	@Override
	public Integer subscribeAllSubdeviceErrorMessages() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_ALL_SUBDEVICE_ERR_MSG);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#unsubscribeAllSubdeviceErrorMessages(de.jena.ibis.common.UnsubscribeRequest)
	 */
	@Override
	public Integer unsubscribeAllSubdeviceErrorMessages() {
		return doSendSubscriptionRequest(DeviceManagementServiceConstants.OPERATION_UNSUBSCRIBE_ALL_SUBDEVICE_ERR_MSG);
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
	 * @see de.jena.ibis.apis.IbisDeviceManagementService#retrieveUpdateState(de.jena.ibis.devicemanagementservice.DeviceManagementServiceRetrieveUpdateStateRequest)
	 */
	@Override
	public RetrieveUpdateStateResponse retrieveUpdateState(RetrieveUpdateStateRequest request) {
		throw new NotImplementedException("Operation not yet implemented!");
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
		results.add(getAllSubdeviceErrorMessages());
		results.add(getAllSubdeviceInformation());
		results.add(getAllSubdeviceStatusInformation());
		results.add(getDeviceConfiguration());
		results.add(getDeviceErrorMessages());
		results.add(getDeviceInformation());
		results.add(getDeviceStatus());
		results.add(getDeviceStatusInformation());
		results.add(getServiceInformation());
		results.add(getServiceStatus());
		return results;
	}

	private Integer executeSubscriptionOperation(String operation) {
		return doSendSubscriptionRequest(operation);
	}

	private Integer doSendSubscriptionRequest(String operation) {
		return IbisTCPHelper.sendSubscriptionRequest(config, operation, ibisCommonPackage, resourceSetFactory);
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
	public List<Integer> executeAllSubscriptionOperations() {
		List<Integer> results = new ArrayList<>();
		DeviceManagementServiceConstants.getAllSubscriptionOperations().forEach(operation -> {
			results.add(executeSubscriptionOperation(operation));
		});
		return results;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeAllUnsubscriptionOperations()
	 */
	@Override
	public List<Integer> executeAllUnsubscriptionOperations() {
		List<Integer> results = new ArrayList<>();
		DeviceManagementServiceConstants.getAllUnsubscriptionOperations().forEach(operation -> {
			results.add(executeSubscriptionOperation(operation));
		});
		return results;
	}

	
}
