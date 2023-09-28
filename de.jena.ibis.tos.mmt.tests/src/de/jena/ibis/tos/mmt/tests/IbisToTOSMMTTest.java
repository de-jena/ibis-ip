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
package de.jena.ibis.tos.mmt.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.gecko.core.pool.Pool;
import org.gecko.qvt.osgi.api.ConfigurableModelTransformatorPool;
import org.gecko.qvt.osgi.api.ModelTransformator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.config.WithConfiguration;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.cm.ConfigurationExtension;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

import de.jena.ibis.gnsslocationservice.DocumentRoot;
import de.jena.ibis.gnsslocationservice.GNSSLSFactory;
import de.jena.ibis.gnsslocationservice.GNSSLSPackage;
import de.jena.ibis.gnsslocationservice.GNSSLocationServiceDataStructure;
import de.jena.ibis.gnsslocationservice.util.GNSSLSResourceFactoryImpl;
import de.jena.ibis.tos.mmt.tests.helper.IbisToTOSHelper;
import de.jena.model.ibis.common.Announcement;
import de.jena.model.ibis.common.IbisCommonFactory;
import de.jena.model.ibis.common.StopInformation;
import de.jena.model.ibis.common.StopSequence;
import de.jena.model.ibis.common.TripInformation;
import de.jena.model.ibis.customerinformationservice.CurrentAnnouncementData;
import de.jena.model.ibis.customerinformationservice.CurrentAnnouncementResponse;
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
import de.jena.udp.model.trafficos.publictransport.PTAnnouncementUpdate;
import de.jena.udp.model.trafficos.publictransport.PTDoorOpenStateType;
import de.jena.udp.model.trafficos.publictransport.PTExitSideType;
import de.jena.udp.model.trafficos.publictransport.PTLocationStateType;
import de.jena.udp.model.trafficos.publictransport.PTPositionUpdate;
import de.jena.udp.model.trafficos.publictransport.PTRouteDeviationType;
import de.jena.udp.model.trafficos.publictransport.PTStopIndexUpdate;
import de.jena.udp.model.trafficos.publictransport.PTStopUpdate;
import de.jena.udp.model.trafficos.publictransport.PTTripStateType;
import de.jena.udp.model.trafficos.publictransport.PTTripUpdate;
import de.jena.udp.model.trafficos.publictransport.PTUpdate;
import de.jena.udp.model.trafficos.publictransport.PTUpdateValueType;
import de.jena.udp.model.trafficos.publictransport.PTVehicleUpdate;


/**
 * See documentation here: 
 * 	https://github.com/osgi/osgi-test
 * 	https://github.com/osgi/osgi-test/wiki
 * Examples: https://github.com/osgi/osgi-test/tree/main/examples
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
@ExtendWith(ConfigurationExtension.class)
public class IbisToTOSMMTTest {
	
	@Test
	public void test(@InjectService(filter = "(emf.model.name=gnsslocationservice)") ServiceAware<ResourceSet> resSetAware) {
		assertThat(resSetAware).isNotNull();
		ResourceSet set = resSetAware.getService();
		assertThat(set).isNotNull();
		
		EClass responseEClass = GNSSLSPackage.eINSTANCE.getGNSSLocationServiceDataStructure();
//		EClass responseEClass = IbisCustomerInformationServicePackage.eINSTANCE.getAllDataResponse();
		set.getPackageRegistry().put(null, responseEClass.getEPackage());
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("gnss", new GNSSLSResourceFactoryImpl());
		
		Resource res = set.createResource(URI.createURI("temp.gnss"));
		Map<String, Object> options = new HashMap<>();
//		options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
//		options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
//		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
//		options.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, true);
		try {
			res.load(new FileInputStream(new File("data/test.xml")), options);
//			res.load(new FileInputStream(new File("data/response.xml")), options);
			if(res instanceof XMLResource xmlRes) {
				Map<EObject, AnyType> unknownFeature = xmlRes.getEObjectToExtensionMap();
				System.out.println("Test");
			}
			if(res.getContents() != null && !res.getContents().isEmpty()) {
				EObject content = res.getContents().get(0);
				System.out.println("Test ");
				System.out.println(content.eClass().getName());
				System.out.println(((DocumentRoot)content).getGNSSLocationServiceData());
			}
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	@Test
	public void testSave(@InjectService(filter = "(emf.resource.configurator.name=GeckoXMLResourceFactory)") ServiceAware<ResourceSet> resSetAware) {
		assertThat(resSetAware).isNotNull();
		ResourceSet set = resSetAware.getService();
		assertThat(set).isNotNull();
		
		try {
		EClass responseEClass = GNSSLSPackage.eINSTANCE.getGNSSLocationServiceDataStructure();
		set.getPackageRegistry().put(null, responseEClass.getEPackage());
		} catch (Exception e) {
			e.printStackTrace();
		}
//		EClass responseEClass = IbisCustomerInformationServicePackage.eINSTANCE.getAllDataResponse();
		set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("gns", new GNSSLSResourceFactoryImpl());
		
		Resource res = set.createResource(URI.createURI("temp_save.gns"));
		Map<String, Object> options = new HashMap<>();
//		options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
//		options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
//		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
//		options.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, true);
		DocumentRoot root = GNSSLSFactory.eINSTANCE.createDocumentRoot();
		GNSSLocationServiceDataStructure data = GNSSLSFactory.eINSTANCE.createGNSSLocationServiceDataStructure();
		root.setGNSSLocationServiceData(data);
		res.getContents().add(root);
		try {
			res.save(new FileOutputStream(new File("data/test2.xml")), options);
//			res.load(new FileInputStream(new File("data/response.xml")), options);
//			if(res instanceof XMLResource xmlRes) {
//				Map<EObject, AnyType> unknownFeature = xmlRes.getEObjectToExtensionMap();
//				System.out.println("Test");
//			}
//			if(res.getContents() != null && !res.getContents().isEmpty()) {
//				EObject content = res.getContents().get(0);
//				System.out.println("Test ");
//				System.out.println(content.eClass().getName());
//			}
		} catch(Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=tosPoolGroup)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "tos"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.tos.mmt/transforms/IbisToTOS.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToTOS"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=gnsslocationservice)(emf.model.name=publictransport))"),
					@Property(key = "pool.name", value= "tosPool"),
					@Property(key = "pool.group", value = "tosPoolGroup"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testGeneralTripData(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
	ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
	assertThat(poolAware).isNotNull();
	ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
	assertThat(poolComponent).isNotNull();
			
	Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
	Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-tosPool");
	assertThat(pool).isNotNull();
	ModelTransformator transformator = pool.poll();
	assertThat(transformator).isNotNull();
	
	TripDataResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createTripDataResponse();
	TripData data = IbisCustomerInformationServiceFactory.eINSTANCE.createTripData();	

	data.setTimeStamp(IbisToTOSHelper.createIbisDateTime(new Date()));
	data.setVehicleRef(IbisToTOSHelper.createIbisToken("vehicleRefTest"));
	data.setCurrentStopIndex(IbisToTOSHelper.createIbisInt(7));
	response.setTripData(data);
	
	PTUpdate result = (PTUpdate) transformator.startTransformation(response);
	assertThat(result).isNotNull();
	assertThat(result.getType()).isEqualTo(PTUpdateValueType.TRIP_DATA);
	assertThat(result.getValue()).isNotNull();
	assertThat(result.getValue()).isInstanceOf(PTTripUpdate.class);
	
	PTTripUpdate value = (PTTripUpdate) result.getValue();	
	assertThat(value.getCurrentStopIndex()).isEqualTo(7);
	}

	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=tosPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "tos"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.tos.mmt/transforms/IbisToTOS.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToTOS"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=gnsslocationservice)(emf.model.name=publictransport))"),
					@Property(key = "pool.name", value= "tosPool"),
					@Property(key = "pool.group", value = "tosPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testTripDataTimetable(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
	ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
	assertThat(poolAware).isNotNull();
	ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
	assertThat(poolComponent).isNotNull();
			
	Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
	Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-tosPool");
	assertThat(pool).isNotNull();
	ModelTransformator transformator = pool.poll();
	assertThat(transformator).isNotNull();
	
	TripDataResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createTripDataResponse();
	TripData data = IbisCustomerInformationServiceFactory.eINSTANCE.createTripData();
	
	TripInformation tripInfo = IbisCommonFactory.eINSTANCE.createTripInformation();
	
	tripInfo.setLocationState(LocationStateEnumeration.AT_STOP);
	tripInfo.setRouteDirection(RouteDirectionEnumeration.CLOCKWISE);
	tripInfo.setRunNumber(IbisToTOSHelper.createIbisInt(23));
	tripInfo.setPatternNumber(IbisToTOSHelper.createIbisInt(44));
	tripInfo.setPathDestinationNumber(IbisToTOSHelper.createIbisInt(77));
	tripInfo.setTimetableDelay(IbisToTOSHelper.createIbisInt(3));
	tripInfo.setTripRef(IbisToTOSHelper.createIbisToken("tripRefTest"));

	data.setTripInformation(tripInfo);
	response.setTripData(data);
	
	PTUpdate result = (PTUpdate) transformator.startTransformation(response);
	assertThat(result).isNotNull();
	assertThat(result.getType()).isEqualTo(PTUpdateValueType.TRIP_DATA);
	assertThat(result.getValue()).isNotNull();
	assertThat(result.getValue()).isInstanceOf(PTTripUpdate.class);
	
	PTTripUpdate value = (PTTripUpdate) result.getValue();
	
	assertThat(value.getRunNumber()).isEqualTo(23);
	assertThat(value.getPatternNumber()).isEqualTo(44);
	assertThat(value.getPathDestinationNumber()).isEqualTo(77);
	assertThat(value.getTimetableDelay()).isEqualTo(3);
	assertThat(value.getRefScheduleId()).isEqualTo("tripRefTest");
	assertThat(value.getLocationState()).isEqualTo(PTLocationStateType.AT_STOP);
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=tosPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "tos"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.tos.mmt/transforms/IbisToTOS.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToTOS"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=gnsslocationservice)(emf.model.name=publictransport))"),
					@Property(key = "pool.name", value= "tosPool"),
					@Property(key = "pool.group", value = "tosPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testTripDataStopSequence(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
	ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
	assertThat(poolAware).isNotNull();
	ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
	assertThat(poolComponent).isNotNull();
			
	Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
	Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-tosPool");
	assertThat(pool).isNotNull();
	ModelTransformator transformator = pool.poll();
	assertThat(transformator).isNotNull();
	
	TripDataResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createTripDataResponse();
	TripData data = IbisCustomerInformationServiceFactory.eINSTANCE.createTripData();
	
	TripInformation tripInfo = IbisCommonFactory.eINSTANCE.createTripInformation();
	
	StopSequence stopSequence = IbisCommonFactory.eINSTANCE.createStopSequence();
	StopInformation stopInfo1 = IbisCommonFactory.eINSTANCE.createStopInformation();
	stopInfo1.setStopIndex(IbisToTOSHelper.createIbisInt(7));
	stopInfo1.setStopRef(IbisToTOSHelper.createIbisToken("stop1"));
	stopInfo1.getStopName().add(IbisToTOSHelper.createIbisTextType("stop 1 Name"));
	stopInfo1.getStopName().add(IbisToTOSHelper.createIbisTextType("stop 1 Other Name"));
	stopInfo1.setDistanceToNextStop(IbisToTOSHelper.createIbisInt(1500));
	stopInfo1.setArrivalExpected(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo1.setArrivalScheduled(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo1.setRecordedArrivalTime(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo1.setDepartureExpected(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo1.setDepartureScheduled(IbisToTOSHelper.createIbisDateTime(new Date()));
	
	StopInformation stopInfo2 = IbisCommonFactory.eINSTANCE.createStopInformation();
	stopInfo2.setStopIndex(IbisToTOSHelper.createIbisInt(8));
	stopInfo2.setStopRef(IbisToTOSHelper.createIbisToken("stop2"));
	stopInfo2.getStopName().add(IbisToTOSHelper.createIbisTextType("stop 2 Name"));
	stopInfo2.getStopName().add(IbisToTOSHelper.createIbisTextType("stop 2 Other Name"));
	stopInfo2.setDistanceToNextStop(IbisToTOSHelper.createIbisInt(1300));
	stopInfo2.setArrivalExpected(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo2.setArrivalScheduled(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo2.setRecordedArrivalTime(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo2.setDepartureExpected(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo2.setDepartureScheduled(IbisToTOSHelper.createIbisDateTime(new Date()));
	
	stopSequence.getStopPoint().add(stopInfo1);
	stopSequence.getStopPoint().add(stopInfo2);
	tripInfo.setStopSequence(stopSequence);

	data.setTripInformation(tripInfo);
	response.setTripData(data);
	
	PTUpdate result = (PTUpdate) transformator.startTransformation(response);
	assertThat(result).isNotNull();
	assertThat(result.getType()).isEqualTo(PTUpdateValueType.TRIP_DATA);
	assertThat(result.getValue()).isNotNull();
	assertThat(result.getValue()).isInstanceOf(PTTripUpdate.class);
	
	PTTripUpdate value = (PTTripUpdate) result.getValue();
	

	List<PTStopUpdate> stopUpdates = value.getStopUpdate();
	assertThat(stopUpdates).isNotNull();
	assertThat(stopUpdates).hasSize(2);
	
	PTStopUpdate stopUpdate1 = stopUpdates.get(0);
	assertThat(stopUpdate1.getExpectedArrivalTime()).isNotNull();
	assertThat(stopUpdate1.getExpectedDepartureTime()).isNotNull();
	assertThat(stopUpdate1.getRecordedArrivalTime()).isNotNull();
	assertThat(stopUpdate1.getDistanceFromNextStop()).isEqualTo(1500);

	
	PTStopUpdate stopUpdate2 = stopUpdates.get(1);
	assertThat(stopUpdate2.getExpectedArrivalTime()).isNotNull();
	assertThat(stopUpdate2.getExpectedDepartureTime()).isNotNull();
	assertThat(stopUpdate2.getRecordedArrivalTime()).isNotNull();
	assertThat(stopUpdate2.getDistanceFromNextStop()).isEqualTo(1300);
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=tosPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "tos"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.tos.mmt/transforms/IbisToTOS.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToTOS"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=gnsslocationservice)(emf.model.name=publictransport))"),
					@Property(key = "pool.name", value= "tosPool"),
					@Property(key = "pool.group", value = "tosPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testCurrentStopPoint(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
	ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
	assertThat(poolAware).isNotNull();
	ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
	assertThat(poolComponent).isNotNull();
			
	Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
	Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-tosPool");
	assertThat(pool).isNotNull();
	ModelTransformator transformator = pool.poll();
	assertThat(transformator).isNotNull();
	
	CurrentStopPointResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentStopPointResponse();
	CurrentStopPointData data = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentStopPointData();
	
	StopInformation stopInfo1 = IbisCommonFactory.eINSTANCE.createStopInformation();
	stopInfo1.setStopIndex(IbisToTOSHelper.createIbisInt(7));
	stopInfo1.setStopRef(IbisToTOSHelper.createIbisToken("stop1"));
	stopInfo1.getStopName().add(IbisToTOSHelper.createIbisTextType("stop 1 Name"));
	stopInfo1.getStopName().add(IbisToTOSHelper.createIbisTextType("stop 1 Other Name"));
	stopInfo1.setDistanceToNextStop(IbisToTOSHelper.createIbisInt(1500));
	stopInfo1.setArrivalExpected(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo1.setArrivalScheduled(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo1.setRecordedArrivalTime(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo1.setDepartureExpected(IbisToTOSHelper.createIbisDateTime(new Date()));
	stopInfo1.setDepartureScheduled(IbisToTOSHelper.createIbisDateTime(new Date()));
	
	data.setCurrentStopPoint(stopInfo1);
	response.setCurrentStopPointData(data);
	
	PTUpdate result = (PTUpdate) transformator.startTransformation(response);
	assertThat(result).isNotNull();
	assertThat(result.getType()).isEqualTo(PTUpdateValueType.CURRENT_STOP_POINT);
	assertThat(result.getValue()).isNotNull();
	assertThat(result.getValue()).isInstanceOf(PTStopUpdate.class);
	
	PTStopUpdate value = (PTStopUpdate) result.getValue();
	assertThat(value.getRefStopId()).isEqualTo("stop1");
	assertThat(value.getExpectedArrivalTime()).isNotNull();
	assertThat(value.getExpectedDepartureTime()).isNotNull();
	assertThat(value.getRecordedArrivalTime()).isNotNull();
	assertThat(value.getDistanceFromNextStop()).isEqualTo(1500);
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=tosPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "tos"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.tos.mmt/transforms/IbisToTOS.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToTOS"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=gnsslocationservice)(emf.model.name=publictransport))"),
					@Property(key = "pool.name", value= "tosPool"),
					@Property(key = "pool.group", value = "tosPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testCurrentStopIndex(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
	ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
	assertThat(poolAware).isNotNull();
	ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
	assertThat(poolComponent).isNotNull();
			
	Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
	Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-tosPool");
	assertThat(pool).isNotNull();
	ModelTransformator transformator = pool.poll();
	assertThat(transformator).isNotNull();
	
	CurrentStopIndexResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentStopIndexResponse();
	CurrentStopIndexData data = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentStopIndexData();
	data.setCurrentStopIndex(IbisToTOSHelper.createIbisInt(7));
	response.setCurrentStopIndexData(data);
	
	PTUpdate result = (PTUpdate) transformator.startTransformation(response);
	assertThat(result).isNotNull();
	assertThat(result.getType()).isEqualTo(PTUpdateValueType.CURRENT_STOP_INDEX);
	assertThat(result.getValue()).isNotNull();
	assertThat(result.getValue()).isInstanceOf(PTStopIndexUpdate.class);
	
	PTStopIndexUpdate value = (PTStopIndexUpdate) result.getValue();
	
	assertThat(value.getCurrentStopIndex()).isEqualTo(7);
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=tosPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "tos"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.tos.mmt/transforms/IbisToTOS.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToTOS"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=gnsslocationservice)(emf.model.name=publictransport))"),
					@Property(key = "pool.name", value= "tosPool"),
					@Property(key = "pool.group", value = "tosPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testCurrentAnnouncement(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
	ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
	assertThat(poolAware).isNotNull();
	ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
	assertThat(poolComponent).isNotNull();
			
	Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
	Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-tosPool");
	assertThat(pool).isNotNull();
	ModelTransformator transformator = pool.poll();
	assertThat(transformator).isNotNull();
	
	CurrentAnnouncementResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentAnnouncementResponse();
	CurrentAnnouncementData data = IbisCustomerInformationServiceFactory.eINSTANCE.createCurrentAnnouncementData();
	Announcement announcement = IbisCommonFactory.eINSTANCE.createAnnouncement();
	announcement.setAnnouncementRef(IbisToTOSHelper.createIbisToken("annRef"));
	announcement.getAnnouncementText().add(IbisToTOSHelper.createIbisTextType("This is an announcement"));
	announcement.getAnnouncementText().add(IbisToTOSHelper.createIbisTextType("This is a second announcement"));
	announcement.getAnnouncementTTSText().add(IbisToTOSHelper.createIbisTextType("This is a TTS announcement"));
	announcement.getAnnouncementTTSText().add(IbisToTOSHelper.createIbisTextType("This is a second TTS announcement"));
	
	data.setCurrentAnnouncement(announcement);
	response.setCurrentAnnouncementData(data);
	
	PTUpdate result = (PTUpdate) transformator.startTransformation(response);
	assertThat(result).isNotNull();
	assertThat(result.getType()).isEqualTo(PTUpdateValueType.CURRENT_ANNOUNCEMENT);
	assertThat(result.getValue()).isNotNull();
	assertThat(result.getValue()).isInstanceOf(PTAnnouncementUpdate.class);
	
	PTAnnouncementUpdate value = (PTAnnouncementUpdate) result.getValue();
	
	assertThat(value.getAnnoucementRef()).isEqualTo("annRef");
	assertThat(value.getAnnoucementText()).contains("This is an announcement", "This is a second announcement");
	assertThat(value.getAnnoucementTTSText()).contains("This is a TTS announcement", "This is a second TTS announcement");
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=tosPool)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "tos"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.tos.mmt/transforms/IbisToTOS.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToTOS"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=gnsslocationservice)(emf.model.name=publictransport))"),
					@Property(key = "pool.name", value= "tosPool"),
					@Property(key = "pool.group", value = "tosPool"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testVehicleData(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
	ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
	assertThat(poolAware).isNotNull();
	ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
	assertThat(poolComponent).isNotNull();
			
	Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
	Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-tosPool");
	assertThat(pool).isNotNull();
	ModelTransformator transformator = pool.poll();
	assertThat(transformator).isNotNull();
	
	VehicleDataResponse response = IbisCustomerInformationServiceFactory.eINSTANCE.createVehicleDataResponse();
	VehicleData data = IbisCustomerInformationServiceFactory.eINSTANCE.createVehicleData();
	data.setDoorState(DoorOpenStateEnumeration.ALL_DOORS_CLOSED);
	data.setExitSide(ExitSideEnumeration.RIGHT);
	data.setTripState(TripStateEnumeration.TRIP_BREAK);
	data.setRouteDeviation(RouteDeviationEnumeration.OFFROUTE);
	data.setSpeakerActive(IbisToTOSHelper.createIbisBoolean(true));
	data.setStopInformationActive(IbisToTOSHelper.createIbisBoolean(false));
	data.setVehicleRef(IbisToTOSHelper.createIbisToken("vehicleRef"));
	data.setInPanic(IbisToTOSHelper.createIbisBoolean(false));
	data.setVehicleStopRequested(IbisToTOSHelper.createIbisBoolean(true));
	data.setMovingDirectionForward(IbisToTOSHelper.createIbisBoolean(false));
	
	response.setVehicleData(data);
	
	PTUpdate result = (PTUpdate) transformator.startTransformation(response);
	assertThat(result).isNotNull();
	assertThat(result.getType()).isEqualTo(PTUpdateValueType.VEHICLE_DATA);
	assertThat(result.getValue()).isNotNull();
	assertThat(result.getValue()).isInstanceOf(PTVehicleUpdate.class);
	
	PTVehicleUpdate value = (PTVehicleUpdate) result.getValue();
	
	assertThat(value.getVehicleRef()).isEqualTo("vehicleRef");
	assertTrue(value.isSpeakerActive());
	assertTrue(value.isVehicleStopRequested());
	assertFalse(value.isStopInformationActive());
	assertFalse(value.isMovingForward());
	assertFalse(value.isInPanic());
	assertThat(value.getDoorState()).isEqualTo(PTDoorOpenStateType.ALL_DOORS_CLOSED);
	assertThat(value.getExitSide()).isEqualTo(PTExitSideType.RIGHT);
	assertThat(value.getTripState()).isEqualTo(PTTripStateType.TRIP_BREAK);
	assertThat(value.getRouteDeviation()).isEqualTo(PTRouteDeviationType.OFF_ROUTE);
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "modelTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=tosPoolGroup)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "tos"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.tos.mmt/transforms/IbisToTOS.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToTOS"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=gnsslocationservice)(emf.model.name=publictransport))"),
					@Property(key = "pool.name", value= "tosPool"),
					@Property(key = "pool.group", value = "tosPoolGroup"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testPositionData(@InjectService(timeout = 5000l, filter="(pool.componentName=modelTransformatorService)") 
	ServiceAware<ConfigurableModelTransformatorPool> poolAware) throws Exception{
		
	assertThat(poolAware).isNotNull();
	ConfigurableModelTransformatorPool poolComponent = poolAware.getService();
	assertThat(poolComponent).isNotNull();
			
	Map<String,Pool<ModelTransformator>> poolMap = poolComponent.getPoolMap();
	Pool<ModelTransformator> pool = poolMap.get("modelTransformatorService-tosPool");
	assertThat(pool).isNotNull();
	ModelTransformator transformator = pool.poll();
	assertThat(transformator).isNotNull();
	
	GNSSLocationServiceDataStructure response = GNSSLSFactory.eINSTANCE.createGNSSLocationServiceDataStructure();
	response.setTime(IbisToTOSHelper.createIbisTime(new Date()));
	response.setLatitude(IbisToTOSHelper.createIbisGNSSCoordinates(50.76283, null));
	response.setLongitude(IbisToTOSHelper.createIbisGNSSCoordinates(11.35262, null));

	PTUpdate result = (PTUpdate) transformator.startTransformation(response);
	assertThat(result).isNotNull();
	assertThat(result.getType()).isEqualTo(PTUpdateValueType.GEO_INFO);
	assertThat(result.getValue()).isNotNull();
	assertThat(result.getValue()).isInstanceOf(PTPositionUpdate.class);
	
	PTPositionUpdate value = (PTPositionUpdate) result.getValue();
	
	assertThat(value.getPosition()).isNotNull();
	assertThat(value.getPosition().getLatitude()).isEqualTo(50.76283);
	assertThat(value.getPosition().getLongitude()).isEqualTo(11.35262);
	}

}
