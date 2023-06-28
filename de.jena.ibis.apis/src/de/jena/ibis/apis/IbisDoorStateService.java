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
package de.jena.ibis.apis;

import org.osgi.annotation.versioning.ProviderType;

import de.jena.ibis.model.doorstateservice.DoorOpenStatesResponse;
import de.jena.ibis.model.doorstateservice.DoorOperationStatesResponse;
import de.jena.ibis.model.doorstateservice.RetrieveSpecificDoorOpenStateResponse;
import de.jena.ibis.model.doorstateservice.RetrieveSpecificDoorOperationStateResponse;
import de.jena.model.ibis.common.GeneralRetrieveRequest;

/**
 * 
 * @author ilenia
 * @since Jun 28, 2023
 */
@ProviderType
public interface IbisDoorStateService extends GeneralIbisTCPService {
	
//	GET Operations
	
	DoorOpenStatesResponse getDoorOpenStates();
	
	DoorOperationStatesResponse getDoorOperationStates();
	
	
//	Subscribe/Unsubscribe Operations
	
	void subscribeDoorOpenStates();
	void unsubscribeDoorOpenStates();
	
	void subscribeDoorOperationStates();
	void unsubscribeDoorOperationStates();
	
//	Retrieve Operations
	
	RetrieveSpecificDoorOpenStateResponse retrieveSpecificDoorOpenState(GeneralRetrieveRequest request);
	
	RetrieveSpecificDoorOperationStateResponse retrieveSpecificDoorOperationState(GeneralRetrieveRequest request);

}
