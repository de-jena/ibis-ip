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
package de.jena.ibis.rest.tests;

import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.datatype.DatatypeConfigurationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.annotation.Property;
import org.osgi.test.common.annotation.config.WithConfiguration;
import org.osgi.test.common.annotation.config.WithFactoryConfiguration;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.cm.ConfigurationExtension;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

import de.jena.ibis.apis.IbisCustomerInformationService;
import de.jena.ibis.rest.apis.TripInformationService;
import de.jena.model.ibis.rest.TripData;


/**
 * See documentation here: 
 * 	https://github.com/osgi/osgi-test
 * 	https://github.com/osgi/osgi-test/wiki
 * Examples: https://github.com/osgi/osgi-test/tree/main/examples
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
@ExtendWith(ConfigurationExtension.class)
public class TripInformationServiceTest {
	
	
	@BeforeEach
	public void before(@InjectBundleContext BundleContext ctx) {
		
	}
	
	@Test
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "ibisToApiTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=ibisToApiPoolGroup)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "api"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.api.mmt/transforms/IbisToApi.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToApi"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=rest))"),
					@Property(key = "pool.name", value= "ibisToApiPool"),
					@Property(key = "pool.group", value = "ibisToApiPoolGroup"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testTripDataNoService(
			@InjectService ServiceAware<TripInformationService> tripInfoAware) {
		
		assertThat(tripInfoAware).isNotNull();
		TripInformationService tripInfoService = tripInfoAware.getService();
		assertThat(tripInfoService).isNotNull();
		
		TripData result = tripInfoService.getTripData("deviceId");
		assertThat(result).isNull();		
	}
	
	@Test
	@WithFactoryConfiguration(
			factoryPid = "IbisCustomerInformationServiceNoResponse",
			name = "deviceId",
			properties = {
					@Property(key = "serviceId", value = "CustomerInformationService-deviceId"),
					@Property(key = "serviceName", value = "CustomerInformationService"),
					@Property(key = "refDeviceId", value = "deviceId"),
					@Property(key = "refDeviceType", value = "BUS"),
					@Property(key = "serviceType", value = "TCP"),
			})
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "ibisToApiTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=ibisToApiPoolGroup)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "api"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.api.mmt/transforms/IbisToApi.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToApi"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=rest))"),
					@Property(key = "pool.name", value= "ibisToApiPool"),
					@Property(key = "pool.group", value = "ibisToApiPoolGroup"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testTripDataNullResponse(
			@InjectService ServiceAware<IbisCustomerInformationService> customerInfoAware,
			@InjectService ServiceAware<TripInformationService> tripInfoAware) {
		
		assertThat(customerInfoAware).isNotNull();
		IbisCustomerInformationService customerInfoService = customerInfoAware.getService();
		assertThat(customerInfoService).isNotNull();
		
		assertThat(tripInfoAware).isNotNull();
		TripInformationService tripInfoService = tripInfoAware.getService();
		assertThat(tripInfoService).isNotNull();
		
		TripData result = tripInfoService.getTripData("deviceId");
		assertThat(result).isNull();		
	}
	
	@Test
	@WithFactoryConfiguration(
			factoryPid = "IbisCustomerInformationServiceWithResponse",
			name = "deviceId",
			properties = {
					@Property(key = "serviceId", value = "CustomerInformationService-deviceId"),
					@Property(key = "serviceName", value = "CustomerInformationService"),
					@Property(key = "refDeviceId", value = "deviceId"),
					@Property(key = "refDeviceType", value = "BUS"),
					@Property(key = "serviceType", value = "TCP"),
			})
	@WithConfiguration(
			pid = "ConfigurableModelTransformatorPool",
			location = "?",
			properties = {
					@Property(key = "pool.componentName", value = "ibisToApiTransformatorService"),
					@Property(key = "pool.size", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "pool.timeout", value = "100", scalar = Property.Scalar.Integer),
					@Property(key = "poolRef.target", value = "(pool.group=ibisToApiPoolGroup)")

			})
	@WithConfiguration(
			pid = "PrototypeConfigurableTransformationService",
			location = "?",
			properties = {
					@Property(key = "name", value= "api"),
					@Property(key = "qvt.templatePath", value = "de.jena.ibis.api.mmt/transforms/IbisToApi.qvto"),
					@Property(key = "qvt.transformatorName", value = "IbisToApi"),
					@Property(key = "qvt.model.target", value= "(&(emf.model.name=common)(emf.model.name=enumerations)(emf.model.name=customerinformationservice)(emf.model.name=rest))"),
					@Property(key = "pool.name", value= "ibisToApiPool"),
					@Property(key = "pool.group", value = "ibisToApiPoolGroup"),
					@Property(key = "pool.asService", value = "false", scalar = Property.Scalar.Boolean)	
			})
	public void testTripDataValidResponse(
			@InjectService ServiceAware<IbisCustomerInformationService> customerInfoAware,
			@InjectService ServiceAware<TripInformationService> tripInfoAware) throws DatatypeConfigurationException {
		
		assertThat(customerInfoAware).isNotNull();
		IbisCustomerInformationService customerInfoService = customerInfoAware.getService();
		assertThat(customerInfoService).isNotNull();
	
		assertThat(tripInfoAware).isNotNull();
		TripInformationService tripInfoService = tripInfoAware.getService();
		assertThat(tripInfoService).isNotNull();
		
		TripData result = tripInfoService.getTripData("deviceId");
		assertThat(result).isNotNull();			
	}
	
}
