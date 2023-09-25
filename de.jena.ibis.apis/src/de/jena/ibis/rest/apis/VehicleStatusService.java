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
package de.jena.ibis.rest.apis;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import de.jena.udp.model.trafficos.publictransport_api.OnlineVehicle;

/**
 * 
 * @author ilenia
 * @since May 30, 2023
 * 
 * This service should be responsible of monitoring which vehicles are online
 * and which services are available on each vehicle.
 */
@ProviderType
public interface VehicleStatusService {
	
	boolean isVehicleOnline(String vehicleId);
	
	List<OnlineVehicle> getOnlineVehicles();
	
	boolean isServiceAvailableOnVehicle(String serviceName, String vehicleId);
	
	List<String> getAvailableServicesOnVehicle(String vehicleId);

}
