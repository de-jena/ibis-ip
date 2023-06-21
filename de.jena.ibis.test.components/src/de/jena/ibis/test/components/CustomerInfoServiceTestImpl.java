/**
 * Copyright (c) 2012 - 2018 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.test.components;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.GeneralIbisTCPService;
import de.jena.ibis.apis.IbisCustomerInformationService;
import de.jena.ibis.apis.IbisTCPServiceConfig;
import de.jena.ibis.apis.constants.CustomerInformationServiceConstants;
import de.jena.ibis.test.components.helper.IbisToApiHelper;
import de.jena.model.ibis.common.GeneralResponse;
import de.jena.model.ibis.common.GeneralRetrieveRequest;
import de.jena.model.ibis.common.IBISIPDateTime;
import de.jena.model.ibis.common.IBISIPInt;
import de.jena.model.ibis.common.IBISIPNMTOKEN;
import de.jena.model.ibis.common.IbisCommonFactory;
import de.jena.model.ibis.common.StopInformation;
import de.jena.model.ibis.common.StopSequence;
import de.jena.model.ibis.customerinformationservice.AllDataResponse;
import de.jena.model.ibis.customerinformationservice.CurrentAnnouncementResponse;
import de.jena.model.ibis.customerinformationservice.CurrentConnectionInformationResponse;
import de.jena.model.ibis.customerinformationservice.CurrentDisplayContentResponse;
import de.jena.model.ibis.customerinformationservice.CurrentStopIndexData;
import de.jena.model.ibis.customerinformationservice.CurrentStopIndexResponse;
import de.jena.model.ibis.customerinformationservice.CurrentStopPointResponse;
import de.jena.model.ibis.customerinformationservice.IbisCustomerInformationServiceFactory;
import de.jena.model.ibis.customerinformationservice.PartialStopSequenceData;
import de.jena.model.ibis.customerinformationservice.PartialStopSequenceResponse;
import de.jena.model.ibis.customerinformationservice.TripData;
import de.jena.model.ibis.customerinformationservice.TripDataResponse;
import de.jena.model.ibis.customerinformationservice.VehicleDataResponse;

@Component(immediate=true, name = "FakeCustomerInfoService", 
service = {IbisCustomerInformationService.class, GeneralIbisTCPService.class, GeneralIbisService.class},
configurationPid = "CustomerInformationService", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class CustomerInfoServiceTestImpl implements  IbisCustomerInformationService {
	
	private IbisTCPServiceConfig config;
	
	@Activate
	@Modified
	public void activate(IbisTCPServiceConfig config) {
		this.config = config;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getServiceName()
	 */
	@Override
	public String getServiceName() {
		return config.serviceName();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getServiceId()
	 */
	@Override
	public String getServiceId() {
		return config.serviceId();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getRefDeviceId()
	 */
	@Override
	public String getRefDeviceId() {
		return config.refDeviceId();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getRefDeviceType()
	 */
	@Override
	public String getRefDeviceType() {
		return config.refDeviceType();
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
		CurrentStopIndexResponse ibisResponse = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentStopIndexResponse();
		CurrentStopIndexData ibisData = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentStopIndexData();
		IBISIPInt stopIndex = IbisCommonFactory.eINSTANCE.createIBISIPInt();
		stopIndex.setValue(7);
		ibisData.setCurrentStopIndex(stopIndex);
		ibisResponse.setCurrentStopIndexData(ibisData);
		return ibisResponse;
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
	 * @see de.jena.ibis.apis.GeneralIbisTCPService#executeRetrieveOperation(java.lang.String, de.jena.model.ibis.common.GeneralRetrieveRequest)
	 */
	@Override
	public GeneralResponse executeRetrieveOperation(String operation, GeneralRetrieveRequest request) {
		return retrievePartialStopSequence(request);
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisCustomerInformationService#retrievePartialStopSequence(de.jena.model.ibis.common.GeneralRetrieveRequest)
	 */
	@Override
	public PartialStopSequenceResponse retrievePartialStopSequence(GeneralRetrieveRequest request) {
		PartialStopSequenceResponse ibisResponse = IbisCustomerInformationServiceFactory.eINSTANCE.createPartialStopSequenceResponse();
		PartialStopSequenceData ibisData = IbisCustomerInformationServiceFactory.eINSTANCE.createPartialStopSequenceData();
		StopSequence stopSequence = IbisCommonFactory.eINSTANCE.createStopSequence();
		StopInformation stopInfo1 = IbisCommonFactory.eINSTANCE.createStopInformation();
		stopInfo1.setStopIndex(IbisToApiHelper.createIbisInt(7));
		stopInfo1.setStopRef(IbisToApiHelper.createIbisToken("stop1"));
		stopInfo1.getStopName().add(IbisToApiHelper.createIbisTextType("stop 1 Name"));
		stopInfo1.getStopName().add(IbisToApiHelper.createIbisTextType("stop 1 Other Name"));
		stopInfo1.setDistanceToNextStop(IbisToApiHelper.createIbisInt(1500));
		
		StopInformation stopInfo2 = IbisCommonFactory.eINSTANCE.createStopInformation();
		stopInfo2.setStopIndex(IbisToApiHelper.createIbisInt(8));
		stopInfo2.setStopRef(IbisToApiHelper.createIbisToken("stop2"));
		stopInfo2.getStopName().add(IbisToApiHelper.createIbisTextType("stop 2 Name"));
		stopInfo2.getStopName().add(IbisToApiHelper.createIbisTextType("stop 2 Other Name"));
		stopInfo2.setDistanceToNextStop(IbisToApiHelper.createIbisInt(1300));
		
		stopSequence.getStopPoint().add(stopInfo1);
		stopSequence.getStopPoint().add(stopInfo2);
		ibisData.setStopSequence(stopSequence);
		ibisResponse.setPartialStopSequenceData(ibisData);
		
		return ibisResponse;
	}

}
