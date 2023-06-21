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
package de.jena.ibis.apis.constants;

import java.util.List;

/**
 * 
 * @author ilenia
 * @since Jan 18, 2023
 */
public interface TicketValidationServiceConstants {
	
	public static final String SERVICE_NAME = "TicketValidationService";
	
	public static final String OPERATION_GET_CURRENT_TARIFF_STOP = "GetCurrentTariffStop";
	public static final String OPERATION_SUBSCRIBE_CURRENT_TARIFF_STOP = "SubscribeCurrentTariffStop";
	public static final String OPERATION_UNSUBSCRIBE_CURRENT_TARIFF_STOP = "UnsubscribeCurrentTariffStop";
	
	public static final String OPERATION_GET_RAZZIA = "GetRazzia";
	public static final String OPERATION_SUBSCRIBE_RAZZIA = "SubscribeRazzia";
	public static final String OPERATION_UNSUBSCRIBE_RAZZIA = "UnsubscribeRazzia";
	
	public static final String OPERATION_GET_CURRENT_LINE = "GetCurrentLine";
	public static final String OPERATION_SUBSCRIBE_CURRENT_LINE = "SubscribeCurrentLine";
	public static final String OPERATION_UNSUBSCRIBE_CURRENT_LINE = "UnsubscribeCurrentLine";
	
	public static final String OPERATION_GET_VEHICLE_DATA = "GetVehicleData";
	public static final String OPERATION_SUBSCRIBE_VEHICLE_DATA = "SubscribeVehicleData";
	public static final String OPERATION_UNSUBSCRIBE_VEHICLE_DATA = "UnsubscribeVehicleData";
	
	public static final String OPERATION_GET_SHORT_HAUL_STOPS = "GetShortHaulStops";
	public static final String OPERATION_SUBSCRIBE_SHORT_HAUL_STOPS = "SubscribeShortHaulStops";
	public static final String OPERATION_UNSUBSCRIBE_SHORT_HAUL_STOPS = "UnsubscribeShortHaulStops";
	
	public static final String OPERATION_RETRIEVE_TRIP_DATA = "RetrieveTripData";
	
	public static List<String> getAllGetOperations() {
		return List.of(OPERATION_GET_CURRENT_TARIFF_STOP, OPERATION_GET_RAZZIA, OPERATION_GET_CURRENT_LINE,
				OPERATION_GET_VEHICLE_DATA, OPERATION_GET_SHORT_HAUL_STOPS);
	}

	public static List<String> getAllSubscriptionOperations() {
		return List.of(OPERATION_SUBSCRIBE_CURRENT_TARIFF_STOP, OPERATION_SUBSCRIBE_RAZZIA, 
				OPERATION_SUBSCRIBE_CURRENT_LINE, OPERATION_SUBSCRIBE_VEHICLE_DATA, OPERATION_SUBSCRIBE_SHORT_HAUL_STOPS);
	}
	
	public static List<String> getAllUnsubscriptionOperations() {
		return List.of(OPERATION_UNSUBSCRIBE_CURRENT_TARIFF_STOP, OPERATION_UNSUBSCRIBE_RAZZIA,
				OPERATION_UNSUBSCRIBE_CURRENT_LINE, OPERATION_UNSUBSCRIBE_VEHICLE_DATA, OPERATION_UNSUBSCRIBE_SHORT_HAUL_STOPS);
	}
	
	public static List<String> getAllRetrieveOperations() {
		return List.of(OPERATION_RETRIEVE_TRIP_DATA);
	}
 }
