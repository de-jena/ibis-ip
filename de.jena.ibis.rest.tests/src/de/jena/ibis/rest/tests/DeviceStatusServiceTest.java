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

import java.util.List;

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

import de.jena.ibis.rest.apis.DeviceStatusService;
import de.jena.model.ibis.rest.DeviceType;
import de.jena.model.ibis.rest.OnlineDevice;

//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;

/**
 * See documentation here: 
 * 	https://github.com/osgi/osgi-test
 * 	https://github.com/osgi/osgi-test/wiki
 * Examples: https://github.com/osgi/osgi-test/tree/main/examples
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
@ExtendWith(ConfigurationExtension.class)
//@ExtendWith(MockitoExtension.class)
public class DeviceStatusServiceTest {
	
//	@Mock
//	TestInterface test;
	
	@BeforeEach
	public void before(@InjectBundleContext BundleContext ctx) {
		System.out.println("Test");
	
		
	}
	
	@Test
	@WithConfiguration(
			pid = "IbisDeviceConfigurator",
			location = "?",
			properties = {
					@Property(key = "deviceId", value = "test1"),
					@Property(key = "deviceName", value = "test1"),
					@Property(key = "deviceType", value = "BUS")
			})
	public void testConfiguratorRegistration(@InjectService ServiceAware<DeviceStatusService> deviceStatusAware) {
		
		assertThat(deviceStatusAware).isNotNull();
		DeviceStatusService deviceStatusService = deviceStatusAware.getService();
		assertThat(deviceStatusService).isNotNull();
		
		List<OnlineDevice> onlineDevice = deviceStatusService.getOnlineDevice();
		assertThat(onlineDevice).hasSize(1);
		
		OnlineDevice result = onlineDevice.get(0);
		assertThat(result.getId()).isEqualTo("test1");
		assertThat(result.getType()).isEqualTo(DeviceType.BUS);
	}
	
	@Test
	@WithFactoryConfiguration(			
			factoryPid = "IbisDeviceConfigurator",
			name = "test1",
			location = "?",
			properties = {
					@Property(key = "deviceId", value = "test1"),
					@Property(key = "deviceName", value = "test1"),
					@Property(key = "deviceType", value = "BUS")
			})
	@WithFactoryConfiguration(
			factoryPid = "IbisDeviceConfigurator",
			name = "test2",
			location = "?",
			properties = {
					@Property(key = "deviceId", value = "test2"),
					@Property(key = "deviceName", value = "test2"),
					@Property(key = "deviceType", value = "TRAM")
			})
	public void testMultiConfiguratorRegistration(@InjectService ServiceAware<DeviceStatusService> deviceStatusAware) {
		
		assertThat(deviceStatusAware).isNotNull();
		DeviceStatusService deviceStatusService = deviceStatusAware.getService();
		assertThat(deviceStatusService).isNotNull();
		
		List<OnlineDevice> onlineDevice = deviceStatusService.getOnlineDevice();
		assertThat(onlineDevice).hasSize(2);
		
		OnlineDevice result1 = null, result2 = null;
		for(OnlineDevice result : onlineDevice) {
			if(result.getId().equals("test1")) {
				result1 = result;
			}
			if(result.getId().equals("test2")) {
				result2 = result;
			}
		}
		assertThat(result1).isNotNull();
		assertThat(result1.getId()).isEqualTo("test1");
		assertThat(result1.getType()).isEqualTo(DeviceType.BUS);
		
		assertThat(result2).isNotNull();
		assertThat(result2.getId()).isEqualTo("test2");
		assertThat(result2.getType()).isEqualTo(DeviceType.TRAM);
	}
	
	@Test
	@WithConfiguration(
			pid = "IbisDeviceConfigurator",
			location = "?",
			properties = {
					@Property(key = "deviceId", value = "test1"),
					@Property(key = "deviceName", value = "test1"),
					@Property(key = "deviceType", value = "BUS")
			})
	public void testIfOnline(@InjectService ServiceAware<DeviceStatusService> deviceStatusAware) {
		
		assertThat(deviceStatusAware).isNotNull();
		DeviceStatusService deviceStatusService = deviceStatusAware.getService();
		assertThat(deviceStatusService).isNotNull();
		
		assertThat(deviceStatusService.isDeviceOnline("test1")).isTrue();
		assertThat(deviceStatusService.isDeviceOnline("test2")).isFalse();
	}
	
	@Test
	@WithConfiguration(
			pid = "IbisDeviceConfigurator",
			location = "?",
			properties = {
					@Property(key = "deviceId", value = "test1"),
					@Property(key = "deviceName", value = "test1"),
					@Property(key = "deviceType", value = "BUS"),
					@Property(key = "refTCPServices", value = {"CustomerInformationService", "TicketValidationService"}, 
						scalar = Property.Scalar.String, type = Property.Type.Array)
			})
	public void testAvailableTCPServices(@InjectService ServiceAware<DeviceStatusService> deviceStatusAware) {
		
		assertThat(deviceStatusAware).isNotNull();
		DeviceStatusService deviceStatusService = deviceStatusAware.getService();
		assertThat(deviceStatusService).isNotNull();
		
		List<String> services = deviceStatusService.getAvailableServicesOnDevice("test1");
		assertThat(services).contains("CustomerInformationService", "TicketValidationService");
		assertThat(services).doesNotContain("PassengerInformationService");
	}
	
	@Test
	@WithConfiguration(
			pid = "IbisDeviceConfigurator",
			location = "?",
			properties = {
					@Property(key = "deviceId", value = "test1"),
					@Property(key = "deviceName", value = "test1"),
					@Property(key = "deviceType", value = "BUS"),
					@Property(key = "refUDPServices", value = {"GNSSLocationService"}, 
						scalar = Property.Scalar.String, type = Property.Type.Array)
			})
	public void testAvailableUDPServices(@InjectService ServiceAware<DeviceStatusService> deviceStatusAware) {
		
		assertThat(deviceStatusAware).isNotNull();
		DeviceStatusService deviceStatusService = deviceStatusAware.getService();
		assertThat(deviceStatusService).isNotNull();
		
		List<String> services = deviceStatusService.getAvailableServicesOnDevice("test1");
		assertThat(services).contains("GNSSLocationService");
		assertThat(services).doesNotContain("PassengerInformationService");
	}
	
	@Test
	@WithConfiguration(
			pid = "IbisDeviceConfigurator",
			location = "?",
			properties = {
					@Property(key = "deviceId", value = "test1"),
					@Property(key = "deviceName", value = "test1"),
					@Property(key = "deviceType", value = "BUS"),
					@Property(key = "refTCPServices", value = {"CustomerInformationService", "TicketValidationService"}, 
						scalar = Property.Scalar.String, type = Property.Type.Array),
					@Property(key = "refUDPServices", value = {"GNSSLocationService"}, 
						scalar = Property.Scalar.String, type = Property.Type.Array)
			})
	public void testAvailableServices(@InjectService ServiceAware<DeviceStatusService> deviceStatusAware) {
		
		assertThat(deviceStatusAware).isNotNull();
		DeviceStatusService deviceStatusService = deviceStatusAware.getService();
		assertThat(deviceStatusService).isNotNull();
		
		List<String> services = deviceStatusService.getAvailableServicesOnDevice("test1");
		assertThat(services).contains("CustomerInformationService", "TicketValidationService", "GNSSLocationService");
		assertThat(services).doesNotContain("PassengerInformationService");
	}
	
	@Test
	@WithConfiguration(
			pid = "IbisDeviceConfigurator",
			location = "?",
			properties = {
					@Property(key = "deviceId", value = "test1"),
					@Property(key = "deviceName", value = "test1"),
					@Property(key = "deviceType", value = "BUS"),
					@Property(key = "refTCPServices", value = {"CustomerInformationService", "TicketValidationService"}, 
						scalar = Property.Scalar.String, type = Property.Type.Array)
			})
	public void testIsTCPServiceAvailable(@InjectService ServiceAware<DeviceStatusService> deviceStatusAware) {
		
		assertThat(deviceStatusAware).isNotNull();
		DeviceStatusService deviceStatusService = deviceStatusAware.getService();
		assertThat(deviceStatusService).isNotNull();
		
		assertThat(deviceStatusService.isServiceAvailableOnDevice("CustomerInformationService", "test1")).isTrue();
		assertThat(deviceStatusService.isServiceAvailableOnDevice("TicketValidationService", "test1")).isTrue();
		assertThat(deviceStatusService.isServiceAvailableOnDevice("PassengerInformationService", "test1")).isFalse();
	}
	
	@Test
	@WithConfiguration(
			pid = "IbisDeviceConfigurator",
			location = "?",
			properties = {
					@Property(key = "deviceId", value = "test1"),
					@Property(key = "deviceName", value = "test1"),
					@Property(key = "deviceType", value = "BUS"),
					@Property(key = "refUDPServices", value = {"GNSSLocationService"}, 
						scalar = Property.Scalar.String, type = Property.Type.Array)
			})
	public void testIsUDPServiceAvailable(@InjectService ServiceAware<DeviceStatusService> deviceStatusAware) {
		
		assertThat(deviceStatusAware).isNotNull();
		DeviceStatusService deviceStatusService = deviceStatusAware.getService();
		assertThat(deviceStatusService).isNotNull();
		
		assertThat(deviceStatusService.isServiceAvailableOnDevice("GNSSLocationService", "test1")).isTrue();
		assertThat(deviceStatusService.isServiceAvailableOnDevice("PassengerInformationService", "test1")).isFalse();
	}
	
	@Test
	@WithConfiguration(
			pid = "IbisDeviceConfigurator",
			location = "?",
			properties = {
					@Property(key = "deviceId", value = "test1"),
					@Property(key = "deviceName", value = "test1"),
					@Property(key = "deviceType", value = "BUS"),
					@Property(key = "refTCPServices", value = {"CustomerInformationService", "TicketValidationService"}, 
						scalar = Property.Scalar.String, type = Property.Type.Array),
					@Property(key = "refUDPServices", value = {"GNSSLocationService"}, 
						scalar = Property.Scalar.String, type = Property.Type.Array)
			})
	public void testIsServiceAvailable(@InjectService ServiceAware<DeviceStatusService> deviceStatusAware) {
		
		assertThat(deviceStatusAware).isNotNull();
		DeviceStatusService deviceStatusService = deviceStatusAware.getService();
		assertThat(deviceStatusService).isNotNull();
		
		assertThat(deviceStatusService.isServiceAvailableOnDevice("CustomerInformationService", "test1")).isTrue();
		assertThat(deviceStatusService.isServiceAvailableOnDevice("TicketValidationService", "test1")).isTrue();
		assertThat(deviceStatusService.isServiceAvailableOnDevice("GNSSLocationService", "test1")).isTrue();
		assertThat(deviceStatusService.isServiceAvailableOnDevice("PassengerInformationService", "test1")).isFalse();
	}

}
