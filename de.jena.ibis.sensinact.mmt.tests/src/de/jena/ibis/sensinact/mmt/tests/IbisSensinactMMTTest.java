/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.sensinact.mmt.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.gecko.core.pool.Pool;
import org.gecko.qvt.osgi.api.ConfigurableModelTransformatorPool;
import org.gecko.qvt.osgi.api.ModelTransformator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.config.WithConfiguration;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.cm.ConfigurationExtension;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

import de.jena.model.ibis.common.Announcement;
import de.jena.model.ibis.common.IBISIPBoolean;
import de.jena.model.ibis.common.IBISIPDateTime;
import de.jena.model.ibis.common.IBISIPInt;
import de.jena.model.ibis.common.IBISIPLanguage;
import de.jena.model.ibis.common.IBISIPNMTOKEN;
import de.jena.model.ibis.common.IBISIPString;
import de.jena.model.ibis.common.IbisCommonFactory;
import de.jena.model.ibis.common.InternationalTextType;
import de.jena.model.ibis.common.StopInformation;
import de.jena.model.ibis.common.TripInformation;
import de.jena.model.ibis.customerinformationservice.AllData;
import de.jena.model.ibis.customerinformationservice.AllDataResponse;
import de.jena.model.ibis.customerinformationservice.CurrentAnnouncementData;
import de.jena.model.ibis.customerinformationservice.CurrentAnnouncementResponse;
import de.jena.model.ibis.customerinformationservice.CurrentConnectionInformationData;
import de.jena.model.ibis.customerinformationservice.CurrentConnectionInformationResponse;
import de.jena.model.ibis.customerinformationservice.CurrentDisplayContentData;
import de.jena.model.ibis.customerinformationservice.CurrentDisplayContentResponse;
import de.jena.model.ibis.customerinformationservice.CurrentStopIndexData;
import de.jena.model.ibis.customerinformationservice.CurrentStopIndexResponse;
import de.jena.model.ibis.customerinformationservice.CurrentStopPointData;
import de.jena.model.ibis.customerinformationservice.CurrentStopPointResponse;
import de.jena.model.ibis.customerinformationservice.IbisCustomerInformationServiceFactory;
import de.jena.model.ibis.customerinformationservice.TripData;
import de.jena.model.ibis.customerinformationservice.TripDataResponse;
import de.jena.model.ibis.customerinformationservice.VehicleData;
import de.jena.model.ibis.customerinformationservice.VehicleDataResponse;
import de.jena.model.ibis.enumerations.DoorOpenStateEnumeration;
import de.jena.model.ibis.enumerations.ExitSideEnumeration;
import de.jena.model.ibis.enumerations.LocationStateEnumeration;
import de.jena.model.ibis.enumerations.RouteDeviationEnumeration;
import de.jena.model.ibis.enumerations.RouteDirectionEnumeration;
import de.jena.model.ibis.enumerations.TripStateEnumeration;
import de.jena.model.ibis.enumerations.VehicleModeEnumeration;
import de.jena.model.sensinact.ibis.CustomerInfoAllData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentAnnouncementData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentConnectionData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentDisplayContentData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentStopIndexData;
import de.jena.model.sensinact.ibis.CustomerInfoCurrentStopPointData;
import de.jena.model.sensinact.ibis.CustomerInfoTripData;
import de.jena.model.sensinact.ibis.CustomerInfoVehicleData;
import de.jena.model.sensinact.ibis.IbisDevice;


/**
 * See documentation here: 
 * 	https://github.com/osgi/osgi-test
 * 	https://github.com/osgi/osgi-test/wiki
 * Examples: https://github.com/osgi/osgi-test/tree/main/examples
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
@ExtendWith(ConfigurationExtension.class)
public class IbisSensinactMMTTest {


	@BeforeEach
	public void before(@InjectBundleContext BundleContext ctx) {
		System.out.println("Test");
	}

	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=sensinactPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "ibis"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.sensinact.mmt/transformations/ibisCustomerInfoToSensinact.qvto"),
					@Property(key = "qvt.transformatorName", value = "ibisCustomerInfoToSensinact"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=ibis)(emf.model.name=customerinformationservice))"),
					@Property(key = "pool.name", value= "ibisPool"),
					@Property(key = "pool.group", value = "sensinactPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testAllData(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
		ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
		assertThat(poolAware).isNotNull();
		ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
		assertThat(poolComponent).isNotNull();
				
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-ibisPool");
		assertThat(pool).isNotNull();
		ModelTransformator transformator = pool.poll();
		assertThat(transformator).isNotNull();
		
		AllDataResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createAllDataResponse();
		AllData data = IbisCustomerInformationServiceFactory.eINSTANCE.createAllData();
	
		data.setTimeStamp(createIBisDateTime(new Date()));
		data.setDefaultLanguage(createIbisLanguage("en"));
		data.setVehicleRef(createIbisToken("vehicleRefTest"));
		data.setCurrentStopIndex(createIbisInt(7));
		data.setRouteDeviation(RouteDeviationEnumeration.ONROUTE);
		data.setDoorState(DoorOpenStateEnumeration.SINGLE_DOOR_OPEN);
		data.setExitSide(ExitSideEnumeration.LEFT);
		data.setInPanic(createIbisBoolean(false));
		data.setMovingDirectionForward(createIbisBoolean(true));
		data.setSpeakerActive(createIbisBoolean(false));
		data.setStopInformationActive(createIbisBoolean(true));
		data.setVehicleStopRequested(createIbisBoolean(false));
		data.setVehicleMode(VehicleModeEnumeration.BUS);		
		data.setTripState(TripStateEnumeration.OFF_DUTY);
		response.setAllData(data);
		
		IbisDevice sensinactDevice = (IbisDevice) transformator.startTransformation(response);
		assertThat(sensinactDevice).isNotNull();
		
		CustomerInfoAllData sensinactData = sensinactDevice.getCustomerInfoAllData();
		assertThat(sensinactData).isNotNull();
		assertThat(sensinactData.getTimestamp()).isNotNull();
		assertThat(sensinactData.getDefaultLanguage()).isEqualTo("en");
		assertThat(sensinactData.getVehicleRef()).isEqualTo("vehicleRefTest");
		assertThat(sensinactData.getCurrentStopIndex()).isEqualTo(7);
		assertThat(sensinactData.getRouteDeviation()).isEqualTo(RouteDeviationEnumeration.ONROUTE.getLiteral());
		assertThat(sensinactData.getDoorState()).isEqualTo(DoorOpenStateEnumeration.SINGLE_DOOR_OPEN.getLiteral());
		assertThat(sensinactData.getExitSide()).isEqualTo(ExitSideEnumeration.LEFT.getLiteral());
		assertThat(sensinactData.isInPanic()).isFalse();
		assertThat(sensinactData.isMovingDirectionForward()).isTrue();
		assertThat(sensinactData.isSpeakerActive()).isFalse();
		assertThat(sensinactData.isStopInformationActive()).isTrue();
		assertThat(sensinactData.isVehicleStopRequested()).isFalse();
		assertThat(sensinactData.getVehicleMode()).isEqualTo(VehicleModeEnumeration.BUS.getLiteral());
		assertThat(sensinactData.getTripState()).isEqualTo(TripStateEnumeration.OFF_DUTY.getLiteral());
		
		assertThat(sensinactData.getMetadata()).isNotEmpty();
		assertThat(sensinactData.getMetadata()).hasSize(13);		
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=sensinactPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "ibis"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.sensinact.mmt/transformations/ibisCustomerInfoToSensinact.qvto"),
					@Property(key = "qvt.transformatorName", value = "ibisCustomerInfoToSensinact"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=ibis)(emf.model.name=customerinformationservice))"),
					@Property(key = "pool.name", value= "ibisPool"),
					@Property(key = "pool.group", value = "sensinactPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testCurrentConnectionData(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
		ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
		assertThat(poolAware).isNotNull();
		ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
		assertThat(poolComponent).isNotNull();
				
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-ibisPool");
		assertThat(pool).isNotNull();
		ModelTransformator transformator = pool.poll();
		assertThat(transformator).isNotNull();
		
		CurrentConnectionInformationResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentConnectionInformationResponse();
		CurrentConnectionInformationData data = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentConnectionInformationData();
	
		data.setTimeStamp(createIBisDateTime(new Date()));		
		
		response.setCurrentConnectionData(data);
		
		IbisDevice sensinactDevice = (IbisDevice) transformator.startTransformation(response);
		assertThat(sensinactDevice).isNotNull();
		
		CustomerInfoCurrentConnectionData sensinactData = sensinactDevice.getCustomerInfoCurrentConnectionData();
		assertThat(sensinactData).isNotNull();
		assertThat(sensinactData.getTimestamp()).isNotNull();
		assertThat(sensinactData.getMetadata()).isEmpty();
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=sensinactPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "ibis"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.sensinact.mmt/transformations/ibisCustomerInfoToSensinact.qvto"),
					@Property(key = "qvt.transformatorName", value = "ibisCustomerInfoToSensinact"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=ibis)(emf.model.name=customerinformationservice))"),
					@Property(key = "pool.name", value= "ibisPool"),
					@Property(key = "pool.group", value = "sensinactPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testCurrentDisplayContentData(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
		ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
		assertThat(poolAware).isNotNull();
		ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
		assertThat(poolComponent).isNotNull();
				
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-ibisPool");
		assertThat(pool).isNotNull();
		ModelTransformator transformator = pool.poll();
		assertThat(transformator).isNotNull();
		
		CurrentDisplayContentResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentDisplayContentResponse();
		CurrentDisplayContentData data = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentDisplayContentData();
		
		data.setTimeStamp(createIBisDateTime(new Date()));		
		response.setCurrentDisplayContentData(data);
		
		IbisDevice sensinactDevice = (IbisDevice) transformator.startTransformation(response);
		assertThat(sensinactDevice).isNotNull();
		
		CustomerInfoCurrentDisplayContentData sensinactData = sensinactDevice.getCustomerInfoCurrentDisplayContentData();
		assertThat(sensinactData).isNotNull();
		assertThat(sensinactData.getTimestamp()).isNotNull();
		assertThat(sensinactData.getMetadata()).isEmpty();		
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=sensinactPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "ibis"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.sensinact.mmt/transformations/ibisCustomerInfoToSensinact.qvto"),
					@Property(key = "qvt.transformatorName", value = "ibisCustomerInfoToSensinact"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=ibis)(emf.model.name=customerinformationservice))"),
					@Property(key = "pool.name", value= "ibisPool"),
					@Property(key = "pool.group", value = "sensinactPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testCurrentAnnouncement(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
		ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
		assertThat(poolAware).isNotNull();
		ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
		assertThat(poolComponent).isNotNull();
				
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-ibisPool");
		assertThat(pool).isNotNull();
		ModelTransformator transformator = pool.poll();
		assertThat(transformator).isNotNull();
		
		CurrentAnnouncementResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentAnnouncementResponse();
		CurrentAnnouncementData data = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentAnnouncementData();
	
		data.setTimeStamp(createIBisDateTime(new Date()));
		Announcement announcement = IbisCommonFactory.eINSTANCE.createAnnouncement();
		announcement.setAnnouncementRef(createIbisToken("annRefTest"));
		announcement.getAnnouncementText().add(createIbisTextType("Ann 1"));
		announcement.getAnnouncementText().add(createIbisTextType("Ann 2"));
		announcement.getAnnouncementText().add(createIbisTextType("Ann 3"));
		announcement.getAnnouncementTTSText().add(createIbisTextType("TTS Ann 1"));
		announcement.getAnnouncementTTSText().add(createIbisTextType("TTS Ann 2"));
		data.setCurrentAnnouncement(announcement);		
		
		response.setCurrentAnnouncementData(data);
		
		IbisDevice sensinactDevice = (IbisDevice) transformator.startTransformation(response);
		assertThat(sensinactDevice).isNotNull();
		
		CustomerInfoCurrentAnnouncementData sensinactData = sensinactDevice.getCustomerInfoCurrentAnnouncementData();
		assertThat(sensinactData).isNotNull();
		assertThat(sensinactData.getTimestamp()).isNotNull();
		assertThat(sensinactData.getAnnouncementRef()).isEqualTo("annRefTest");
		assertThat(sensinactData.getAnnouncementText()).hasSize(3);
		assertThat(sensinactData.getAnnouncementText()).contains("Ann 1", "Ann 2", "Ann 3");
		assertThat(sensinactData.getAnnouncementTTSText()).hasSize(2);
		assertThat(sensinactData.getAnnouncementTTSText()).contains("TTS Ann 1", "TTS Ann 2");
		
		assertThat(sensinactData.getMetadata()).isNotEmpty();
		assertThat(sensinactData.getMetadata()).hasSize(3);		
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=sensinactPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "ibis"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.sensinact.mmt/transformations/ibisCustomerInfoToSensinact.qvto"),
					@Property(key = "qvt.transformatorName", value = "ibisCustomerInfoToSensinact"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=ibis)(emf.model.name=customerinformationservice))"),
					@Property(key = "pool.name", value= "ibisPool"),
					@Property(key = "pool.group", value = "sensinactPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testCurrentStopIndex(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
		ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
		assertThat(poolAware).isNotNull();
		ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
		assertThat(poolComponent).isNotNull();
				
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-ibisPool");
		assertThat(pool).isNotNull();
		ModelTransformator transformator = pool.poll();
		assertThat(transformator).isNotNull();
		
		CurrentStopIndexResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentStopIndexResponse();
		CurrentStopIndexData data = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentStopIndexData();
	
		data.setTimeStamp(createIBisDateTime(new Date()));
		data.setCurrentStopIndex(createIbisInt(7));
		
		response.setCurrentStopIndexData(data);
		
		IbisDevice sensinactDevice = (IbisDevice) transformator.startTransformation(response);
		assertThat(sensinactDevice).isNotNull();
		
		CustomerInfoCurrentStopIndexData sensinactData = sensinactDevice.getCustomerInfoCurrentStopIndexData();
		assertThat(sensinactData).isNotNull();
		assertThat(sensinactData.getTimestamp()).isNotNull();
		assertThat(sensinactData.getCurrentStopIndex()).isEqualTo(7);
	
		assertThat(sensinactData.getMetadata()).isNotEmpty();
		assertThat(sensinactData.getMetadata()).hasSize(1);		
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=sensinactPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "ibis"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.sensinact.mmt/transformations/ibisCustomerInfoToSensinact.qvto"),
					@Property(key = "qvt.transformatorName", value = "ibisCustomerInfoToSensinact"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=ibis)(emf.model.name=customerinformationservice))"),
					@Property(key = "pool.name", value= "ibisPool"),
					@Property(key = "pool.group", value = "sensinactPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testCurrentStopPoint(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
		ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
		assertThat(poolAware).isNotNull();
		ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
		assertThat(poolComponent).isNotNull();
				
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-ibisPool");
		assertThat(pool).isNotNull();
		ModelTransformator transformator = pool.poll();
		assertThat(transformator).isNotNull();
		
		CurrentStopPointResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentStopPointResponse();
		CurrentStopPointData data = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentStopPointData();
	
		data.setTimeStamp(createIBisDateTime(new Date()));
		StopInformation stopInfo = IbisCommonFactory.eINSTANCE.createStopInformation();
		stopInfo.setArrivalExpected(createIBisDateTime(new Date()));
		stopInfo.setArrivalScheduled(createIBisDateTime(new Date()));
		stopInfo.setDepartureExpected(createIBisDateTime(new Date()));
		stopInfo.setDepartureScheduled(createIBisDateTime(new Date()));
		stopInfo.setDistanceToNextStop(createIbisInt(3));
		stopInfo.setPlatform(createIbisString("platformTest"));
		stopInfo.setRecordedArrivalTime(createIBisDateTime(new Date()));
		stopInfo.setStopIndex(createIbisInt(7));
		stopInfo.setStopRef(createIbisToken("stopRefTest"));
		stopInfo.getStopName().add(createIbisTextType("Stop Name 1"));
		stopInfo.getStopName().add(createIbisTextType("Stop Name 2"));
		stopInfo.getStopAlternativeName().add(createIbisTextType("Stop Alternative Name 1"));
		stopInfo.getStopAlternativeName().add(createIbisTextType("Stop Alternative Name 2"));
		stopInfo.getFareZone().add(createIbisToken("Fare Zone 1"));
		stopInfo.getFareZone().add(createIbisToken("Fare Zone 2"));

		data.setCurrentStopPoint(stopInfo);
		response.setCurrentStopPointData(data);
		
		IbisDevice sensinactDevice = (IbisDevice) transformator.startTransformation(response);
		assertThat(sensinactDevice).isNotNull();
		
		CustomerInfoCurrentStopPointData sensinactData = sensinactDevice.getCustomerInfoCurrentStopPointData();
		assertThat(sensinactData).isNotNull();
		assertThat(sensinactData.getTimestamp()).isNotNull();
		assertThat(sensinactData.getArrivalExpected()).isNotNull();
		assertThat(sensinactData.getArrivalScheduled()).isNotNull();
		assertThat(sensinactData.getDepartureExpected()).isNotNull();
		assertThat(sensinactData.getDepartureScheduled()).isNotNull();
		assertThat(sensinactData.getRecordedArrivalTime()).isNotNull();
		assertThat(sensinactData.getPlatform()).isEqualTo("platformTest");
		assertThat(sensinactData.getStopRef()).isEqualTo("stopRefTest");
		assertThat(sensinactData.getStopIndex()).isEqualTo(7);
		assertThat(sensinactData.getDistanceToNextStop()).isEqualTo(3);
		assertThat(sensinactData.getStopName()).hasSize(2);
		assertThat(sensinactData.getStopName()).contains("Stop Name 1", "Stop Name 2");
		assertThat(sensinactData.getStopAlternativeName()).hasSize(2);
		assertThat(sensinactData.getStopAlternativeName()).contains("Stop Alternative Name 1", "Stop Alternative Name 2");
		assertThat(sensinactData.getFareZone()).hasSize(2);
		assertThat(sensinactData.getFareZone()).contains("Fare Zone 1", "Fare Zone 2");
		
		assertThat(sensinactData.getMetadata()).isNotEmpty();
		assertThat(sensinactData.getMetadata()).hasSize(12);		
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=sensinactPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "ibis"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.sensinact.mmt/transformations/ibisCustomerInfoToSensinact.qvto"),
					@Property(key = "qvt.transformatorName", value = "ibisCustomerInfoToSensinact"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=ibis)(emf.model.name=customerinformationservice))"),
					@Property(key = "pool.name", value= "ibisPool"),
					@Property(key = "pool.group", value = "sensinactPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testTripData(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
		ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
		assertThat(poolAware).isNotNull();
		ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
		assertThat(poolComponent).isNotNull();
				
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-ibisPool");
		assertThat(pool).isNotNull();
		ModelTransformator transformator = pool.poll();
		assertThat(transformator).isNotNull();
		
		TripDataResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createTripDataResponse();
		TripData data = IbisCustomerInformationServiceFactory.eINSTANCE.createTripData();
		
		TripInformation tripInfo = IbisCommonFactory.eINSTANCE.createTripInformation();
		tripInfo.setLocationState(LocationStateEnumeration.AT_STOP);
		tripInfo.setPathDestinationNumber(createIbisInt(7));
		tripInfo.setPatternNumber(createIbisInt(4));
		tripInfo.setRouteDirection(RouteDirectionEnumeration.CLOCKWISE);
		tripInfo.setRunNumber(createIbisInt(23));
		tripInfo.setTimetableDelay(createIbisInt(3));
		tripInfo.setTripRef(createIbisToken("tripRefTest"));
		tripInfo.setAdditionalTextMessage(createIbisTextType("Msg"));
		tripInfo.setAdditionalTextMessage1(createIbisTextType("Msg1"));
		tripInfo.setAdditionalTextMessage2(createIbisTextType("Msg2"));
		tripInfo.setAdditionalTextMessage3(createIbisTextType("Msg3"));
		tripInfo.setAdditionalTextMessage4(createIbisTextType("Msg4"));
		tripInfo.setAdditionalTextMessage5(createIbisTextType("Msg5"));
		tripInfo.setAdditionalTextMessage6(createIbisTextType("Msg6"));
		tripInfo.setAdditionalTextMessage7(createIbisTextType("Msg7"));
		tripInfo.setAdditionalTextMessage8(createIbisTextType("Msg8"));
		tripInfo.setAdditionalTextMessage9(createIbisTextType("Msg9"));

		
		data.setTimeStamp(createIBisDateTime(new Date()));
		data.setDefaultLanguage(createIbisLanguage("en"));
		data.setVehicleRef(createIbisToken("vehicleRefTest"));
		data.setCurrentStopIndex(createIbisInt(7));
		data.setTripInformation(tripInfo);
		response.setTripData(data);
		
		IbisDevice sensinactDevice = (IbisDevice) transformator.startTransformation(response);
		assertThat(sensinactDevice).isNotNull();
		
		CustomerInfoTripData sensinactData = sensinactDevice.getCustomerInfoTripData();
		assertThat(sensinactData).isNotNull();
		assertThat(sensinactData.getTimestamp()).isNotNull();
		assertThat(sensinactData.getDefaultLanguage()).isEqualTo("en");
		assertThat(sensinactData.getVehicleRef()).isEqualTo("vehicleRefTest");
		assertThat(sensinactData.getCurrentStopIndex()).isEqualTo(7);
		assertThat(sensinactData.getPathDestinationNumber()).isEqualTo(7);
		assertThat(sensinactData.getPatternNumber()).isEqualTo(4);
		assertThat(sensinactData.getRunNumber()).isEqualTo(23);
		assertThat(sensinactData.getTimetableDelay()).isEqualTo(3);
		assertThat(sensinactData.getTripRef()).isEqualTo("tripRefTest");
		assertThat(sensinactData.getLocationState()).isEqualTo(LocationStateEnumeration.AT_STOP.getLiteral());
		assertThat(sensinactData.getRouteDirection()).isEqualTo(RouteDirectionEnumeration.CLOCKWISE.getLiteral());
		assertThat(sensinactData.getAdditionalTextMsg()).isEqualTo("Msg");
		assertThat(sensinactData.getAdditionalTextMsg1()).isEqualTo("Msg1");
		assertThat(sensinactData.getAdditionalTextMsg2()).isEqualTo("Msg2");
		assertThat(sensinactData.getAdditionalTextMsg3()).isEqualTo("Msg3");
		assertThat(sensinactData.getAdditionalTextMsg4()).isEqualTo("Msg4");
		assertThat(sensinactData.getAdditionalTextMsg5()).isEqualTo("Msg5");
		assertThat(sensinactData.getAdditionalTextMsg6()).isEqualTo("Msg6");
		assertThat(sensinactData.getAdditionalTextMsg7()).isEqualTo("Msg7");
		assertThat(sensinactData.getAdditionalTextMsg8()).isEqualTo("Msg8");
		assertThat(sensinactData.getAdditionalTextMsg9()).isEqualTo("Msg9");
		
		assertThat(sensinactData.getMetadata()).isNotEmpty();
		assertThat(sensinactData.getMetadata()).hasSize(20);		
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=sensinactPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "ibis"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.sensinact.mmt/transformations/ibisCustomerInfoToSensinact.qvto"),
					@Property(key = "qvt.transformatorName", value = "ibisCustomerInfoToSensinact"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=ibis)(emf.model.name=customerinformationservice))"),
					@Property(key = "pool.name", value= "ibisPool"),
					@Property(key = "pool.group", value = "sensinactPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testVehicleData(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
		ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
		assertThat(poolAware).isNotNull();
		ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
		assertThat(poolComponent).isNotNull();
				
		Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
		Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-ibisPool");
		assertThat(pool).isNotNull();
		ModelTransformator transformator = pool.poll();
		assertThat(transformator).isNotNull();
		
		VehicleDataResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createVehicleDataResponse();
		VehicleData data = IbisCustomerInformationServiceFactory.eINSTANCE.createVehicleData();
	
		data.setTimeStamp(createIBisDateTime(new Date()));
		data.setVehicleRef(createIbisToken("vehicleRefTest"));
		data.setRouteDeviation(RouteDeviationEnumeration.ONROUTE);
		data.setDoorState(DoorOpenStateEnumeration.SINGLE_DOOR_OPEN);
		data.setExitSide(ExitSideEnumeration.LEFT);
		data.setInPanic(createIbisBoolean(false));
		data.setMovingDirectionForward(createIbisBoolean(true));
		data.setSpeakerActive(createIbisBoolean(false));
		data.setStopInformationActive(createIbisBoolean(true));
		data.setVehicleStopRequested(createIbisBoolean(false));
		data.setVehicleMode(VehicleModeEnumeration.BUS);		
		data.setTripState(TripStateEnumeration.OFF_DUTY);
		
		response.setVehicleData(data);
		
		IbisDevice sensinactDevice = (IbisDevice) transformator.startTransformation(response);
		assertThat(sensinactDevice).isNotNull();
		
		CustomerInfoVehicleData sensinactData = sensinactDevice.getCustomerInfoVehicleData();
		assertThat(sensinactData).isNotNull();
		assertThat(sensinactData.getTimestamp()).isNotNull();
		assertThat(sensinactData.getVehicleRef()).isEqualTo("vehicleRefTest");
		assertThat(sensinactData.getRouteDeviation()).isEqualTo(RouteDeviationEnumeration.ONROUTE.getLiteral());
		assertThat(sensinactData.getDoorState()).isEqualTo(DoorOpenStateEnumeration.SINGLE_DOOR_OPEN.getLiteral());
		assertThat(sensinactData.getExitSide()).isEqualTo(ExitSideEnumeration.LEFT.getLiteral());
		assertThat(sensinactData.isInPanic()).isFalse();
		assertThat(sensinactData.isMovingDirectionForward()).isTrue();
		assertThat(sensinactData.isSpeakerActive()).isFalse();
		assertThat(sensinactData.isStopInformationActive()).isTrue();
		assertThat(sensinactData.isVehicleStopRequested()).isFalse();
		assertThat(sensinactData.getVehicleMode()).isEqualTo(VehicleModeEnumeration.BUS.getLiteral());
		assertThat(sensinactData.getTripState()).isEqualTo(TripStateEnumeration.OFF_DUTY.getLiteral());
		
		assertThat(sensinactData.getMetadata()).isNotEmpty();
		assertThat(sensinactData.getMetadata()).hasSize(11);		
	}
	
	private IBISIPString createIbisString(String value) {
		IBISIPString ibisStr = IbisCommonFactory.eINSTANCE.createIBISIPString();
		ibisStr.setValue(value);
		return ibisStr;
	}
	
	private InternationalTextType createIbisTextType(String value) {
		InternationalTextType text = IbisCommonFactory.eINSTANCE.createInternationalTextType();
		text.setValue(value);
		return text;
	}
	
	private IBISIPBoolean createIbisBoolean(boolean value) {
		IBISIPBoolean ibisBoolean = IbisCommonFactory.eINSTANCE.createIBISIPBoolean();
		ibisBoolean.setValue(value);
		return ibisBoolean;
	}
	
	private IBISIPNMTOKEN createIbisToken(String value) {
		IBISIPNMTOKEN ibisToken = IbisCommonFactory.eINSTANCE.createIBISIPNMTOKEN();
		ibisToken.setValue(value);
		return ibisToken;
	}
	
	private IBISIPInt createIbisInt(int value) {
		IBISIPInt ibisInt = IbisCommonFactory.eINSTANCE.createIBISIPInt();
		ibisInt.setValue(value);
		return ibisInt;
	}
	
	private IBISIPLanguage createIbisLanguage(String value) {
		IBISIPLanguage language = IbisCommonFactory.eINSTANCE.createIBISIPLanguage();
		language.setValue(value);
		return language;
	}
	
	private IBISIPDateTime createIBisDateTime(Date date) throws DatatypeConfigurationException {
		IBISIPDateTime timestamp = IbisCommonFactory.eINSTANCE.createIBISIPDateTime();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar xCal = DatatypeFactory.newInstance()
		    .newXMLGregorianCalendar(cal);
		timestamp.setValue(xCal);
		return timestamp;
	}

}
