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

import org.osgi.annotation.versioning.ProviderType;

import de.jena.model.ibis.common.GeneralRetrieveRequest;
import de.jena.model.ibis.ticketvalidationservice.CurrentLineResponse;
import de.jena.model.ibis.ticketvalidationservice.CurrentShortHaulStopsResponse;
import de.jena.model.ibis.ticketvalidationservice.CurrentTariffStopResponse;
import de.jena.model.ibis.ticketvalidationservice.RazziaResponse;
import de.jena.model.ibis.ticketvalidationservice.TripDataResponse;
import de.jena.model.ibis.ticketvalidationservice.VehicleDataResponse;

/**
 * 
 * @author ilenia
 * @since Mar 30, 2023
 */
@ProviderType
public interface IbisTicketValidationService extends GeneralIbisTCPService{
	
//	GET Operations
	
	CurrentTariffStopResponse getCurrentTariffStop();
	
	RazziaResponse getRazzia();
	
	CurrentLineResponse getCurrentLine();
	
	VehicleDataResponse getVehicleData();
	
	CurrentShortHaulStopsResponse getShortHaulsStops();
	
//	Subscribe/Unsubscribe Operations
	
	void subscribeCurrentTariffStop();
	void unsubscribeCurrentTariffStop();
	
	void subscribeRazzia();
	void unsubscribeRazzia();
	
	void subscribeCurrentLine();
	void unsubscribeCurrentLine();
	
	void subscribeVehicleData();
	void unsubscribeVehicleData();
	
	void subscribeShortHaulStops();
	void unsubscribeShortHaulStops();
	
//	Retrieve Operations
	
	TripDataResponse retrieveTripData(GeneralRetrieveRequest request);
}
