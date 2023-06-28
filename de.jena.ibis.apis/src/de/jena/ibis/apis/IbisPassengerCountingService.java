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

import de.jena.model.ibis.common.DataAcceptedResponse;
import de.jena.model.ibis.common.GeneralRetrieveRequest;
import de.jena.model.ibis.passengercountingservice.AllDataResponse;
import de.jena.model.ibis.passengercountingservice.CountingStateResponse;
import de.jena.model.ibis.passengercountingservice.RetrieveSpecificDoorDataResponse;
import de.jena.model.ibis.passengercountingservice.SetCounterDataRequest;
import de.jena.model.ibis.passengercountingservice.StartCountingRequest;
import de.jena.model.ibis.passengercountingservice.StopCountingRequest;

/**
 * 
 * @author ilenia
 * @since Jun 22, 2023
 */
@ProviderType
public interface IbisPassengerCountingService extends GeneralIbisTCPService {

//	GET Operations
	
	AllDataResponse getAllData();
	
	CountingStateResponse getCountingState();
	
	
//	Subscribe/Unsubscribe Operations
	
	void subscribeAllData();
	void unsubscribeAllData();
	
	void subscribeCountingState();
	void unsubscribeCountingState();
	
	
//	Retrieve Operations
	
	RetrieveSpecificDoorDataResponse retrieveSpecificDoorData(GeneralRetrieveRequest request);
	
	
//	Set/Start/Stop Operations
	
	DataAcceptedResponse setCounterData(SetCounterDataRequest request);
	
	DataAcceptedResponse startCounting(StartCountingRequest request);
	
	DataAcceptedResponse stopCounting(StopCountingRequest request);
	
}
