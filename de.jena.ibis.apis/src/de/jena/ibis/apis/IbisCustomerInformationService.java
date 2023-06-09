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

import de.jena.model.ibis.customerinformationservice.AllDataResponse;
import de.jena.model.ibis.customerinformationservice.CurrentAnnouncementResponse;
import de.jena.model.ibis.customerinformationservice.CurrentConnectionInformationResponse;
import de.jena.model.ibis.customerinformationservice.CurrentDisplayContentResponse;
import de.jena.model.ibis.customerinformationservice.CurrentStopIndexResponse;
import de.jena.model.ibis.customerinformationservice.CurrentStopPointResponse;
import de.jena.model.ibis.customerinformationservice.PartialStopSequenceRequest;
import de.jena.model.ibis.customerinformationservice.PartialStopSequenceResponse;
import de.jena.model.ibis.customerinformationservice.TripDataResponse;
import de.jena.model.ibis.customerinformationservice.VehicleDataResponse;



@ProviderType
public interface IbisCustomerInformationService extends GeneralIbisTCPService{
		
//	GET Operations

	AllDataResponse getAllData();
	
	CurrentAnnouncementResponse getCurrentAnnouncement();
	
	CurrentConnectionInformationResponse getCurrentConnectionInformation();
	
	CurrentDisplayContentResponse getCurrentDisplayContent();
	
	CurrentStopPointResponse getCurrentStopPoint();
	
	CurrentStopIndexResponse getCurrentStopIndex();
	
	TripDataResponse getTripData();
	
	VehicleDataResponse getVehicleData();
	
	
//	SUBSCRIBE/UNSUBSCRIBE Operations
	
	void subscribeAllData();
	
	void unsubscribeAllData();
	
	void subscribeCurrentAnnouncement();
	
	void unsubscribeCurrentAnnouncement();
	
	void subscribeCurrentConnectionInformation();
	
	void unsubscribeCurrentConnectionInformation();
	
	void subscribeCurrentDisplayContent();
	
	void unsubscribeCurrentDisplayContent();
	
	void subscribeCurrentStopPoint();
	
	void unsubscribeCurrentStopPoint();
	
	void subscribeCurrentStopIndex();
	
	void unsubscribeCurrentStopIndex();
	
	void subscribeTripData();
	
	void unsubscribeTripData();
	
	void subscribeVehicleData();
	
	void unsubscribeVehicleData();
	
	
//	RETRIEVE Operations
	
	PartialStopSequenceResponse 
	retrievePartialStopSequence(PartialStopSequenceRequest request);
	
}
