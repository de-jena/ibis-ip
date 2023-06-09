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
package de.jena.ibis.apis;

import de.jena.model.ibis.common.DataAcceptedResponse;
import de.jena.model.ibis.devicemanagementservice.AllSubdeviceErrorMessagesResponse;
import de.jena.model.ibis.devicemanagementservice.AllSubdeviceInformationResponse;
import de.jena.model.ibis.devicemanagementservice.DeviceConfigurationResponse;
import de.jena.model.ibis.devicemanagementservice.DeviceErrorMessagesResponse;
import de.jena.model.ibis.devicemanagementservice.DeviceInformationResponse;
import de.jena.model.ibis.devicemanagementservice.DeviceStatusInformationResponse;
import de.jena.model.ibis.devicemanagementservice.DeviceStatusResponse;
import de.jena.model.ibis.devicemanagementservice.FinalizeUpdateRequest;
import de.jena.model.ibis.devicemanagementservice.FinalizeUpdateResponse;
import de.jena.model.ibis.devicemanagementservice.InstallUpdateRequest;
import de.jena.model.ibis.devicemanagementservice.InstallUpdateResponse;
import de.jena.model.ibis.devicemanagementservice.RetrieveUpdateStateRequest;
import de.jena.model.ibis.devicemanagementservice.RetrieveUpdateStateResponse;
import de.jena.model.ibis.devicemanagementservice.ServiceInformationResponse;
import de.jena.model.ibis.devicemanagementservice.ServiceStatusResponse;
import de.jena.model.ibis.devicemanagementservice.UpdateHistoryResponse;

/**
 * 
 * @author ilenia
 * @since Jan 18, 2023
 */
public interface IbisDeviceManagementService extends GeneralIbisTCPService {
	
//	GET Operations
	DeviceInformationResponse getDeviceInformation();
	
	DeviceConfigurationResponse getDeviceConfiguration();
	
	DeviceStatusResponse getDeviceStatus();
	
	DeviceErrorMessagesResponse getDeviceErrorMessages();
	
	ServiceInformationResponse getServiceInformation();
	
	ServiceStatusResponse getServiceStatus();
	
	AllSubdeviceInformationResponse getAllSubdeviceInformation();
	
	DeviceStatusInformationResponse getDeviceStatusInformation();
	
	AllSubdeviceInformationResponse getAllSubdeviceStatusInformation();
	
	AllSubdeviceErrorMessagesResponse getAllSubdeviceErrorMessages();
		
	
//	SUBSCRIBE/UNSUBSCRIBE Operations
	void subscribeDeviceInformation();	
	void unsubscribeDeviceInformation();
	
	void subscribeDeviceStatus();	
	void unsubscribeDeviceStatus();
	
	void subscribeDeviceErrorMessages();	
	void unsubscribeDeviceErrorMessages();
	
	void subscribeServiceInformation();	
	void unsubscribeServiceInformation();
	
	void subscribeServiceStatus();	
	void unsubscribeServiceStatus();
	
	void subscribeAllSubdeviceInformation();	
	void unsubscribeAllSubdeviceInformation();
	
	void subscribeDeviceStatusInformation();	
	void unsubscribeDeviceStatusInformation();
	
	void subscribeAllSubdeviceStatusInformation();	
	void unsubscribeAllSubdeviceStatusInformation();
	
	void subscribeAllSubdeviceErrorMessages();	
	void unsubscribeAllSubdeviceErrorMessages();
	
	
//	UPDATE Operations (they are optional)
	InstallUpdateResponse installUpdate(InstallUpdateRequest request);
	
	RetrieveUpdateStateResponse retrieveUpdateState(RetrieveUpdateStateRequest request);
	
	UpdateHistoryResponse getUpdateHistory();
	
	FinalizeUpdateResponse finalizeUpdate(FinalizeUpdateRequest request);

	DataAcceptedResponse finalizeAllPendingUpdates();
}
