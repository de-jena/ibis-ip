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
package de.jena.ibis.rest.components;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import de.jena.ibis.apis.IbisDeviceConfigurator;
import de.jena.ibis.rest.apis.DeviceStatusService;
import de.jena.model.ibis.rest.DeviceType;
import de.jena.model.ibis.rest.IbisRestFactory;
import de.jena.model.ibis.rest.OnlineDevice;

/**
 * 
 * @author ilenia
 * @since May 30, 2023
 */
@Component(immediate=true, name="DeviceStatusService", service=DeviceStatusService.class)
public class DeviceStatusServiceImpl implements DeviceStatusService {
	
	private static final Logger LOGGER = Logger.getLogger(DeviceStatusServiceImpl.class.getName());
	
	Map<String, IbisDeviceConfigurator> devices = new HashMap<>();

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisDeviceStatusService#isDeviceOnline(java.lang.String)
	 */
	@Override
	public boolean isDeviceOnline(String deviceId) {
		return devices.containsKey(deviceId);
	}
	
	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY, unbind = "removeDevice")
	protected void addDevice(IbisDeviceConfigurator deviceConfigurator, Map<String, Object> properties) {
		devices.put(deviceConfigurator.getConfig().deviceId(), deviceConfigurator);
		LOGGER.info("Added IbisDeviceConfigurator for device " + deviceConfigurator.getConfig().deviceId());
	}
	
	protected void removeDevice(IbisDeviceConfigurator deviceConfigurator) {
		devices.remove(deviceConfigurator.getConfig().deviceId());
		LOGGER.info("Removed IbisDeviceConfigurator for device " + deviceConfigurator.getConfig().deviceId());
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.rest.apis.IbisDeviceStatusService#getOnlineDevice()
	 */
	@Override
	public List<OnlineDevice> getOnlineDevice() {
		return devices.entrySet().stream().map(e -> {
			OnlineDevice onlineDevice = IbisRestFactory.eINSTANCE.createOnlineDevice();
			onlineDevice.setId(e.getKey());
			onlineDevice.setType(DeviceType.getByName(e.getValue().getConfig().deviceType()));
			return onlineDevice;
		}).toList();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.rest.apis.DeviceStatusService#isServiceAvailableOnDevice(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isServiceAvailableOnDevice(String serviceName, String deviceId) {
		if(!isDeviceOnline(deviceId)) return false;
		if(List.of(devices.get(deviceId).getConfig().refTCPServices()).contains(serviceName)) return true;
		if(List.of(devices.get(deviceId).getConfig().refUDPServices()).contains(serviceName)) return true;
		return false;
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.rest.apis.DeviceStatusService#getAvailableServicesOnDevice(java.lang.String)
	 */
	@Override
	public List<String> getAvailableServicesOnDevice(String deviceId) {
		if(!isDeviceOnline(deviceId)) return Collections.emptyList();	
		return Stream.concat(List.of(devices.get(deviceId).getConfig().refTCPServices()).stream(), 
				List.of(devices.get(deviceId).getConfig().refUDPServices()).stream())
	      		.toList(); 
	}

	
}
