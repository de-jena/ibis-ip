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
package de.jena.ibis.apis.helper;

import java.util.logging.Logger;

import org.eclipse.emf.ecore.EClass;

import de.jena.ibis.apis.constants.CustomerInformationServiceConstants;
import de.jena.ibis.apis.constants.DeviceManagementServiceConstants;
import de.jena.ibis.apis.constants.TicketValidationServiceConstants;
import de.jena.ibis.ibis_customerinformationservice.IbisCustomerInformationServicePackage;
import de.jena.ibis.ibis_devicemanagementservice.IbisDeviceManagementServicePackage;
import de.jena.ibis.ibis_ticketvalidationservice.IbisTicketValidationServicePackage;

/**
 * 
 * @author ilenia
 * @since Apr 17, 2023
 */
public class IbisResponseHelper {
	
	
	private static final Logger LOGGER = Logger.getLogger(IbisResponseHelper.class.getName());

	public static EClass getResponseEClass(String serviceName, String operation) {
		switch(serviceName) {
		case "CustomerInformationService":
			return getCustomerInfoServiceResponseEClass(operation);
		case "TicketValidationService":
			return getTicketValidationServiceResponseEClass(operation);
		case "DeviceManagementService":
			return getDeviceManagementServiceResponseEClass(operation);
		default:
			LOGGER.severe(() -> String.format("No supported service %s", serviceName));
			return null;
		}		
	}
	
	private static EClass getCustomerInfoServiceResponseEClass(String operation) {
		switch(operation) {
		case CustomerInformationServiceConstants.OPERATION_GET_ALL_DATA, CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_ALL_DATA:
			return IbisCustomerInformationServicePackage.eINSTANCE.getAllDataResponse();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_ANNOUNCEMENT, CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_ANNOUNCEMENT:
			return IbisCustomerInformationServicePackage.eINSTANCE.getCurrentAnnouncementResponse();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_CONNECTION_INFO, CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_CONNECTION_INFO:
			return IbisCustomerInformationServicePackage.eINSTANCE.getCurrentConnectionInformationResponse();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_DISPLAY_CONTENT, CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_DISPLAY_CONTENT:
			return IbisCustomerInformationServicePackage.eINSTANCE.getCurrentDisplayContentResponse();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_STOP_INDEX, CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_STOP_INDEX:
			return IbisCustomerInformationServicePackage.eINSTANCE.getCurrentStopIndexResponse();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_STOP_POINT, CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_STOP_POINT:
			return IbisCustomerInformationServicePackage.eINSTANCE.getCurrentStopPointResponse();
		case CustomerInformationServiceConstants.OPERATION_GET_TRIP_DATA, CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_TRIP_DATA:
			return IbisCustomerInformationServicePackage.eINSTANCE.getTripDataResponse();
		case CustomerInformationServiceConstants.OPERATION_GET_VEHICLE_DATA, CustomerInformationServiceConstants.OPERATION_SUBSCRIBE_VEHICLE_DATA:
			return IbisCustomerInformationServicePackage.eINSTANCE.getVehicleDataResponse();
		default:
			LOGGER.severe(() -> String.format("No operation %s for service CustomerInformationService", operation));
			return null;
		}
	}
	
	private static EClass getTicketValidationServiceResponseEClass(String operation) {
		switch(operation) {
		case TicketValidationServiceConstants.OPERATION_GET_CURRENT_LINE, TicketValidationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_LINE:
			return IbisTicketValidationServicePackage.eINSTANCE.getCurrentLineResponse();
		case TicketValidationServiceConstants.OPERATION_GET_CURRENT_TARIFF_STOP, TicketValidationServiceConstants.OPERATION_SUBSCRIBE_CURRENT_TARIFF_STOP:
			return IbisTicketValidationServicePackage.eINSTANCE.getCurrentTariffStopResponse();
		case TicketValidationServiceConstants.OPERATION_GET_RAZZIA, TicketValidationServiceConstants.OPERATION_SUBSCRIBE_RAZZIA:
			return IbisTicketValidationServicePackage.eINSTANCE.getRazziaResponse();
		case TicketValidationServiceConstants.OPERATION_GET_SHORT_HAUL_STOPS, TicketValidationServiceConstants.OPERATION_SUBSCRIBE_SHORT_HAUL_STOPS:
			return IbisTicketValidationServicePackage.eINSTANCE.getCurrentShortHaulStopsResponse();
		case TicketValidationServiceConstants.OPERATION_GET_VEHICLE_DATA, TicketValidationServiceConstants.OPERATION_SUBSCRIBE_VEHICLE_DATA:
			return IbisTicketValidationServicePackage.eINSTANCE.getVehicleDataResponse();
		default:
			LOGGER.severe(() -> String.format("No operation %s for service TicketValidationService", operation));
			return null;
		}
	}
	
	
	private static EClass getDeviceManagementServiceResponseEClass(String operation) {
		switch(operation) {
		case DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_ERR_MSG, DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_ALL_SUBDEVICE_ERR_MSG:
			return IbisDeviceManagementServicePackage.eINSTANCE.getAllSubdeviceErrorMessagesResponse();
		case DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_INFO, DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_ALL_SUBDEVICE_INFO:
			return IbisDeviceManagementServicePackage.eINSTANCE.getAllSubdeviceInformationResponse();
		case DeviceManagementServiceConstants.OPERATION_GET_ALL_SUBDEVICE_STATUS_INFO, DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_ALL_SUBDEVICE_STATUS_INFO:
			return IbisDeviceManagementServicePackage.eINSTANCE.getAllSubdeviceStatusInformationResponse();
		case DeviceManagementServiceConstants.OPERATION_GET_DEVICE_CONFIGURATION:
			return IbisDeviceManagementServicePackage.eINSTANCE.getDeviceConfigurationResponse();
		case DeviceManagementServiceConstants.OPERATION_GET_DEVICE_ERR_MSG, DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_ERR_MSG:
			return IbisDeviceManagementServicePackage.eINSTANCE.getDeviceErrorMessagesResponse();
		case DeviceManagementServiceConstants.OPERATION_GET_DEVICE_INFO, DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_INFO:
			return IbisDeviceManagementServicePackage.eINSTANCE.getDeviceInformationResponse();
		case DeviceManagementServiceConstants.OPERATION_GET_DEVICE_STATUS, DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_STATUS:
			return IbisDeviceManagementServicePackage.eINSTANCE.getDeviceStatusResponse();
		case DeviceManagementServiceConstants.OPERATION_GET_DEVICE_STATUS_INFO, DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_DEVICE_STATUS_INFO:
			return IbisDeviceManagementServicePackage.eINSTANCE.getDeviceStatusInformationResponse();
		case DeviceManagementServiceConstants.OPERATION_GET_SERVICE_INFO, DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_SERVICE_INFO:
			return IbisDeviceManagementServicePackage.eINSTANCE.getServiceInformationResponse();
		case DeviceManagementServiceConstants.OPERATION_GET_SERVICE_STATUS, DeviceManagementServiceConstants.OPERATION_SUBSCRIBE_SERVICE_STATUS:
			return IbisDeviceManagementServicePackage.eINSTANCE.getServiceStatusResponse();
		default:
			LOGGER.severe(() -> String.format("No operation %s for service DeviceManagementService", operation));
			return null;
		}
	}

}
