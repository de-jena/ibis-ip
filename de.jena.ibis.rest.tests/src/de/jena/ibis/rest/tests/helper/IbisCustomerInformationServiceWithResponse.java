/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.rest.tests.helper;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.GeneralIbisTCPService;
import de.jena.ibis.apis.IbisCustomerInformationService;
import de.jena.ibis.apis.constants.CustomerInformationServiceConstants;
import de.jena.model.ibis.common.GeneralResponse;
import de.jena.model.ibis.common.IBISIPDateTime;
import de.jena.model.ibis.common.IBISIPInt;
import de.jena.model.ibis.common.IBISIPNMTOKEN;
import de.jena.model.ibis.common.IbisCommonFactory;
import de.jena.model.ibis.customerinformationservice.AllDataResponse;
import de.jena.model.ibis.customerinformationservice.CurrentAnnouncementResponse;
import de.jena.model.ibis.customerinformationservice.CurrentConnectionInformationResponse;
import de.jena.model.ibis.customerinformationservice.CurrentDisplayContentResponse;
import de.jena.model.ibis.customerinformationservice.CurrentStopIndexResponse;
import de.jena.model.ibis.customerinformationservice.CurrentStopPointResponse;
import de.jena.model.ibis.customerinformationservice.IbisCustomerInformationServiceFactory;
import de.jena.model.ibis.customerinformationservice.PartialStopSequenceRequest;
import de.jena.model.ibis.customerinformationservice.PartialStopSequenceResponse;
import de.jena.model.ibis.customerinformationservice.TripDataResponse;
import de.jena.model.ibis.customerinformationservice.TripData;
import de.jena.model.ibis.customerinformationservice.VehicleDataResponse;

/**
 * 
 * @author ilenia
 * @since Jun 20, 2023
 */
@Component(immediate=true, name = "IbisCustomerInformationServiceWithResponse", 
service = {IbisCustomerInformationService.class, GeneralIbisTCPService.class, GeneralIbisService.class},
configurationPid = "IbisCustomerInformationServiceWithResponse", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class IbisCustomerInformationServiceWithResponse implements IbisCustomerInformationService {

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getServiceName()
	 */
	@Override
	public String getServiceName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getServiceId()
	 */
	@Override
	public String getServiceId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getRefDeviceId()
	 */
	@Override
	public String getRefDeviceId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getRefDeviceType()
	 */
	@Override
	public String getRefDeviceType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#executeAllSubscriptionOperations()
	 */
	@Override
	public void executeAllSubscriptionOperations() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#executeAllUnsubscriptionOperations()
	 */
	@Override
	public void executeAllUnsubscriptionOperations() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeAllData()
	 */
	@Override
	public void subscribeAllData() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeAllData()
	 */
	@Override
	public void unsubscribeAllData() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeCurrentAnnouncement()
	 */
	@Override
	public void subscribeCurrentAnnouncement() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeCurrentAnnouncement()
	 */
	@Override
	public void unsubscribeCurrentAnnouncement() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeCurrentConnectionInformation()
	 */
	@Override
	public void subscribeCurrentConnectionInformation() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeCurrentConnectionInformation()
	 */
	@Override
	public void unsubscribeCurrentConnectionInformation() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeCurrentDisplayContent()
	 */
	@Override
	public void subscribeCurrentDisplayContent() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeCurrentDisplayContent()
	 */
	@Override
	public void unsubscribeCurrentDisplayContent() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeCurrentStopPoint()
	 */
	@Override
	public void subscribeCurrentStopPoint() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeCurrentStopPoint()
	 */
	@Override
	public void unsubscribeCurrentStopPoint() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeCurrentStopIndex()
	 */
	@Override
	public void subscribeCurrentStopIndex() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeCurrentStopIndex()
	 */
	@Override
	public void unsubscribeCurrentStopIndex() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeTripData()
	 */
	@Override
	public void subscribeTripData() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeTripData()
	 */
	@Override
	public void unsubscribeTripData() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#subscribeVehicleData()
	 */
	@Override
	public void subscribeVehicleData() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#unsubscribeVehicleData()
	 */
	@Override
	public void unsubscribeVehicleData() {
		// TODO Auto-generated method stub

	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeAllGetOperations()
	 */
	@Override
	public List<GeneralResponse> executeAllGetOperations() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeGetOperation(java.lang.String)
	 */
	@Override
	public GeneralResponse executeGetOperation(String operation) {
		switch(operation) {
		case CustomerInformationServiceConstants.OPERATION_GET_ALL_DATA:
			return getAllData();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_ANNOUNCEMENT:
			return getCurrentAnnouncement();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_CONNECTION_INFO:
			return getCurrentConnectionInformation();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_DISPLAY_CONTENT:
			return getCurrentDisplayContent();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_STOP_INDEX:
			return getCurrentStopIndex();
		case CustomerInformationServiceConstants.OPERATION_GET_CURRENT_STOP_POINT:
			return getCurrentStopPoint();
		case CustomerInformationServiceConstants.OPERATION_GET_TRIP_DATA:
			return getTripData();
		case CustomerInformationServiceConstants.OPERATION_GET_VEHICLE_DATA:
			return getVehicleData();
		default:
			throw new IllegalArgumentException(String.format("Operation %s not implemented for %s!", operation, "CustomerInformationService"));			
		}
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getAllData()
	 */
	@Override
	public AllDataResponse getAllData() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getCurrentAnnouncement()
	 */
	@Override
	public CurrentAnnouncementResponse getCurrentAnnouncement() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getCurrentConnectionInformation()
	 */
	@Override
	public CurrentConnectionInformationResponse getCurrentConnectionInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getCurrentDisplayContent()
	 */
	@Override
	public CurrentDisplayContentResponse getCurrentDisplayContent() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getCurrentStopPoint()
	 */
	@Override
	public CurrentStopPointResponse getCurrentStopPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getCurrentStopIndex()
	 */
	@Override
	public CurrentStopIndexResponse getCurrentStopIndex() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getTripData()
	 */
	@Override
	public TripDataResponse getTripData() {
		TripDataResponse ibisResponse = IbisCustomerInformationServiceFactory.eINSTANCE.createTripDataResponse();
		
		TripData ibisData = IbisCustomerInformationServiceFactory.eINSTANCE.createTripData();
		IBISIPInt stopIndex = IbisCommonFactory.eINSTANCE.createIBISIPInt();
		stopIndex.setValue(7);
		ibisData.setCurrentStopIndex(stopIndex);
		
		IBISIPDateTime timestamp = IbisCommonFactory.eINSTANCE.createIBISIPDateTime();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		XMLGregorianCalendar xCal;
		try {
			xCal = DatatypeFactory.newInstance()
			    .newXMLGregorianCalendar(cal);
			timestamp.setValue(xCal);

		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		ibisData.setTimeStamp(timestamp);
		
		IBISIPNMTOKEN ibisToken = IbisCommonFactory.eINSTANCE.createIBISIPNMTOKEN();
		ibisToken.setValue("vehicleRef");
		ibisData.setVehicleRef(ibisToken);
		
		ibisResponse.setTripData(ibisData);
		return ibisResponse;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#getVehicleData()
	 */
	@Override
	public VehicleDataResponse getVehicleData() {
		// TODO Auto-generated method stub
		return null;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#retrievePartialStopSequence(de.jena.model.ibis.customerinformationservice.PartialStopSequenceRequest)
	 */
	@Override
	public PartialStopSequenceResponse retrievePartialStopSequence(PartialStopSequenceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
