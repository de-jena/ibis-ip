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
package de.jena.ibis.api.mmt.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Map;

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

import de.jena.ibis.api.mmt.tests.helper.IbisToApiHelper;
import de.jena.model.ibis.common.IbisCommonFactory;
import de.jena.model.ibis.common.StopInformation;
import de.jena.model.ibis.common.StopSequence;
import de.jena.model.ibis.common.TripInformation;
import de.jena.model.ibis.customerinformationservice.IbisCustomerInformationServiceFactory;
import de.jena.model.ibis.customerinformationservice.TripData;
import de.jena.model.ibis.customerinformationservice.TripDataResponse;
import de.jena.model.ibis.enumerations.LocationStateEnumeration;
import de.jena.model.ibis.enumerations.RouteDirectionEnumeration;
import de.jena.model.ibis.rest.LocationStateType;
import de.jena.model.ibis.rest.RouteDirectionType;
import de.jena.model.ibis.rest.Timetable;


/**
 * See documentation here: 
 * 	https://github.com/osgi/osgi-test
 * 	https://github.com/osgi/osgi-test/wiki
 * Examples: https://github.com/osgi/osgi-test/tree/main/examples
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
@ExtendWith(ConfigurationExtension.class)
public class IbisToApiMMTTest {
	
	
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
					@Property(key = "poolRef.target", value = "(pool.group=apiPoolGroup)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "api"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.api.mmt/transforms/IbisToApi.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToApi"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=rest))"),
					@Property(key = "pool.name", value= "apiPool"),
					@Property(key = "pool.group", value = "apiPoolGroup"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testGeneralTripData(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
	ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
	assertThat(poolAware).isNotNull();
	ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
	assertThat(poolComponent).isNotNull();
			
	Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
	Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-apiPool");
	assertThat(pool).isNotNull();
	ModelTransformator transformator = pool.poll();
	assertThat(transformator).isNotNull();
	
	TripDataResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createTripDataResponse();
	TripData data = IbisCustomerInformationServiceFactory.eINSTANCE.createTripData();	

	data.setTimeStamp(IbisToApiHelper.createIbisDateTime(new Date()));
	data.setVehicleRef(IbisToApiHelper.createIbisToken("vehicleRefTest"));
	data.setCurrentStopIndex(IbisToApiHelper.createIbisInt(7));
	response.setTripData(data);
	
	de.jena.model.ibis.rest.TripData result = (de.jena.model.ibis.rest.TripData) transformator.startTransformation(response);
	assertThat(result).isNotNull();
	
	assertThat(result.getTimestamp()).isNotNull();
	assertThat(result.getVehicleRef()).isEqualTo("vehicleRefTest");
	assertThat(result.getCurrentStopIndex()).isEqualTo(7);
	}

	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=apiPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "api"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.api.mmt/transforms/IbisToApi.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToApi"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=rest))"),
					@Property(key = "pool.name", value= "apiPool"),
					@Property(key = "pool.group", value = "apiPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testTripDataTimetable(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
	ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
	assertThat(poolAware).isNotNull();
	ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
	assertThat(poolComponent).isNotNull();
			
	Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
	Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-apiPool");
	assertThat(pool).isNotNull();
	ModelTransformator transformator = pool.poll();
	assertThat(transformator).isNotNull();
	
	TripDataResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createTripDataResponse();
	TripData data = IbisCustomerInformationServiceFactory.eINSTANCE.createTripData();
	
	TripInformation tripInfo = IbisCommonFactory.eINSTANCE.createTripInformation();
	
	tripInfo.setLocationState(LocationStateEnumeration.AT_STOP);
	tripInfo.setRouteDirection(RouteDirectionEnumeration.CLOCKWISE);
	tripInfo.setRunNumber(IbisToApiHelper.createIbisInt(23));
	tripInfo.setTimetableDelay(IbisToApiHelper.createIbisInt(3));
	tripInfo.setTripRef(IbisToApiHelper.createIbisToken("tripRefTest"));

	data.setTripInformation(tripInfo);
	response.setTripData(data);
	
	de.jena.model.ibis.rest.TripData result = (de.jena.model.ibis.rest.TripData) transformator.startTransformation(response);
	assertThat(result).isNotNull();
	
	Timetable apiTimetable = result.getTimetable();
	assertThat(apiTimetable).isNotNull();
	assertThat(apiTimetable.getRunNumber()).isEqualTo(23);
	assertThat(apiTimetable.getTimetableDelay()).isEqualTo(3);
	assertThat(apiTimetable.getTimetableRef()).isEqualTo("tripRefTest");
	assertThat(apiTimetable.getLocationState()).isEqualTo(LocationStateType.AT_STOP);
	assertThat(apiTimetable.getRouteDirection()).isEqualTo(RouteDirectionType.CLOCKWISE);
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=apiPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "api"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.api.mmt/transforms/IbisToApi.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToApi"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=rest))"),
					@Property(key = "pool.name", value= "apiPool"),
					@Property(key = "pool.group", value = "apiPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testTripDataStopSequence(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
	ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
	assertThat(poolAware).isNotNull();
	ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
	assertThat(poolComponent).isNotNull();
			
	Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
	Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-apiPool");
	assertThat(pool).isNotNull();
	ModelTransformator transformator = pool.poll();
	assertThat(transformator).isNotNull();
	
	TripDataResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createTripDataResponse();
	TripData data = IbisCustomerInformationServiceFactory.eINSTANCE.createTripData();
	
	TripInformation tripInfo = IbisCommonFactory.eINSTANCE.createTripInformation();
	
	StopSequence stopSequence = IbisCommonFactory.eINSTANCE.createStopSequence();
	StopInformation stopInfo1 = IbisCommonFactory.eINSTANCE.createStopInformation();
	stopInfo1.setStopIndex(IbisToApiHelper.createIbisInt(7));
	stopInfo1.setStopRef(IbisToApiHelper.createIbisToken("stop1"));
	stopInfo1.getStopName().add(IbisToApiHelper.createIbisTextType("stop 1 Name"));
	stopInfo1.getStopName().add(IbisToApiHelper.createIbisTextType("stop 1 Other Name"));
	stopInfo1.setDistanceToNextStop(IbisToApiHelper.createIbisInt(1500));
	stopInfo1.setArrivalExpected(IbisToApiHelper.createIbisDateTime(new Date()));
	stopInfo1.setArrivalScheduled(IbisToApiHelper.createIbisDateTime(new Date()));
	stopInfo1.setRecordedArrivalTime(IbisToApiHelper.createIbisDateTime(new Date()));
	stopInfo1.setDepartureExpected(IbisToApiHelper.createIbisDateTime(new Date()));
	stopInfo1.setDepartureScheduled(IbisToApiHelper.createIbisDateTime(new Date()));
	
	StopInformation stopInfo2 = IbisCommonFactory.eINSTANCE.createStopInformation();
	stopInfo2.setStopIndex(IbisToApiHelper.createIbisInt(8));
	stopInfo2.setStopRef(IbisToApiHelper.createIbisToken("stop2"));
	stopInfo2.getStopName().add(IbisToApiHelper.createIbisTextType("stop 2 Name"));
	stopInfo2.getStopName().add(IbisToApiHelper.createIbisTextType("stop 2 Other Name"));
	stopInfo2.setDistanceToNextStop(IbisToApiHelper.createIbisInt(1300));
	stopInfo2.setArrivalExpected(IbisToApiHelper.createIbisDateTime(new Date()));
	stopInfo2.setArrivalScheduled(IbisToApiHelper.createIbisDateTime(new Date()));
	stopInfo2.setRecordedArrivalTime(IbisToApiHelper.createIbisDateTime(new Date()));
	stopInfo2.setDepartureExpected(IbisToApiHelper.createIbisDateTime(new Date()));
	stopInfo2.setDepartureScheduled(IbisToApiHelper.createIbisDateTime(new Date()));
	
	stopSequence.getStopPoint().add(stopInfo1);
	stopSequence.getStopPoint().add(stopInfo2);
	tripInfo.setStopSequence(stopSequence);

	data.setTripInformation(tripInfo);
	response.setTripData(data);
	
	de.jena.model.ibis.rest.TripData result = (de.jena.model.ibis.rest.TripData) transformator.startTransformation(response);
	assertThat(result).isNotNull();
	
	Timetable apiTimetable = result.getTimetable();
	assertThat(apiTimetable).isNotNull();

	de.jena.model.ibis.rest.StopSequence apiStopSequence = apiTimetable.getStopSequence();
	assertThat(apiStopSequence).isNotNull();
	assertThat(apiStopSequence.getStopPoint()).hasSize(2);
	
	de.jena.model.ibis.rest.StopInformation apiStopInfo1 = apiStopSequence.getStopPoint().get(0);
	assertThat(apiStopInfo1.getArrivalExpected()).isNotNull();
	assertThat(apiStopInfo1.getArrivalScheduled()).isNotNull();
	assertThat(apiStopInfo1.getDepartureExpected()).isNotNull();
	assertThat(apiStopInfo1.getDepartureScheduled()).isNotNull();
	assertThat(apiStopInfo1.getRecordedArrivalTime()).isNotNull();
	assertThat(apiStopInfo1.getStopName()).contains("stop 1 Name", "stop 1 Other Name");
	assertThat(apiStopInfo1.getDistanceToNextStop()).isEqualTo(1500);
	assertThat(apiStopInfo1.getStopIndex()).isEqualTo(7);
	assertThat(apiStopInfo1.getStopRef()).isEqualTo("stop1");
	
	de.jena.model.ibis.rest.StopInformation apiStopInfo2 = apiStopSequence.getStopPoint().get(1);
	assertThat(apiStopInfo2.getArrivalExpected()).isNotNull();
	assertThat(apiStopInfo2.getArrivalScheduled()).isNotNull();
	assertThat(apiStopInfo2.getDepartureExpected()).isNotNull();
	assertThat(apiStopInfo2.getDepartureScheduled()).isNotNull();
	assertThat(apiStopInfo2.getRecordedArrivalTime()).isNotNull();
	assertThat(apiStopInfo2.getStopName()).contains("stop 2 Name", "stop 2 Other Name");
	assertThat(apiStopInfo2.getDistanceToNextStop()).isEqualTo(1300);
	assertThat(apiStopInfo2.getStopIndex()).isEqualTo(8);
	assertThat(apiStopInfo2.getStopRef()).isEqualTo("stop2");
	}
}
