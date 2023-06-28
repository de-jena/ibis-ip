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
package de.jena.ibis.apis.constants;

import java.util.List;

/**
 * 
 * @author ilenia
 * @since Jun 22, 2023
 */
public interface PassengerCountingServiceConstants {
	
	public static final String SERVICE_NAME = "PassengerCountingService";
	
	public static final String OPERATION_GET_ALL_DATA = "GetAllData";
	public static final String OPERATION_SUBSCRIBE_ALL_DATA = "SubscribeAllData";
	public static final String OPERATION_UNSUBSCRIBE_ALL_DATA = "UnsubscribeAllData";
	
	public static final String OPERATION_GET_COUNTING_STATE = "GetCountingState";
	public static final String OPERATION_SUBSCRIBE_COUNTING_STATE = "SubscribeCountingState";
	public static final String OPERATION_UNSUBSCRIBE_COUNTING_STATE = "UnsubscribeCountingState";
	
	public static final String OPERATION_RETRIEVE_SPECIFIC_DOOR_DATA = "RetrieveSpecificDoorData";
	
	public static final String OPERATION_SET_COUNTER_DATA = "SetCounterData";	
	public static final String OPERATION_START_COUNTING = "StartCounting";
	public static final String OPERATION_STOP_COUNTING = "StopCounting";
	
	public static List<String> getAllGetOperations() {
		return List.of(OPERATION_GET_ALL_DATA, OPERATION_GET_COUNTING_STATE);
	}
	
	public static List<String> getAllSubscriptionOperations() {
		return List.of(OPERATION_SUBSCRIBE_ALL_DATA, OPERATION_SUBSCRIBE_COUNTING_STATE);
	}

	public static List<String> getAllUnsubscriptionOperations() {
		return List.of(OPERATION_UNSUBSCRIBE_ALL_DATA, OPERATION_UNSUBSCRIBE_COUNTING_STATE);
	}

	public static List<String> getAllRetrieveOperations() {
		return List.of(OPERATION_RETRIEVE_SPECIFIC_DOOR_DATA);
	}
	

}
