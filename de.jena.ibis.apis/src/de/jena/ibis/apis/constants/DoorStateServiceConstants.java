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
 * @since Jun 28, 2023
 */
public interface DoorStateServiceConstants {
	
	public static final String SERVICE_NAME = "DoorStateService";
	
	public static final String OPERATION_GET_DOOR_OPEN_STATES = "GetDoorOpenStates";
	public static final String OPERATION_SUBSCRIBE_DOOR_OPEN_STATES = "SubscribeDoorOpenStates";
	public static final String OPERATION_UNSUBSCRIBE_DOOR_OPEN_STATES = "UnsubscribeDoorOpenStates";

	public static final String OPERATION_GET_DOOR_OPERATION_STATES = "GetDoorOperationStates";
	public static final String OPERATION_SUBSCRIBE_DOOR_OPERATION_STATES = "SubscribeDoorOperationStates";
	public static final String OPERATION_UNSUBSCRIBE_DOOR_OPERATION_STATES = "UnsubscribeDoorOperationStates";

	public static final String OPERATION_RETRIEVE_SPECIFIC_DOOR_OPEN_STATES = "RetrieveSpecificDoorOpenStates";
	public static final String OPERATION_RETRIEVE_SPECIFIC_DOOR_OPERATION_STATES = "RetrieveSpecificDoorOperationStates";

	public static List<String> getAllGetOperations() {
		return List.of(OPERATION_GET_DOOR_OPEN_STATES, OPERATION_GET_DOOR_OPERATION_STATES);
	}
	
	public static List<String> getAllSubscriptionOperations() {
		return List.of(OPERATION_SUBSCRIBE_DOOR_OPEN_STATES, OPERATION_SUBSCRIBE_DOOR_OPERATION_STATES);
	}
	
	public static List<String> getAllUnsubscriptionOperations() {
		return List.of(OPERATION_UNSUBSCRIBE_DOOR_OPEN_STATES, OPERATION_UNSUBSCRIBE_DOOR_OPERATION_STATES);
	}
	
	public static List<String> getAllRetrieveOperations() {
		return List.of(OPERATION_RETRIEVE_SPECIFIC_DOOR_OPEN_STATES, OPERATION_RETRIEVE_SPECIFIC_DOOR_OPERATION_STATES);
	}
	
}
