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
package de.jena.ibis.rest.resource;

import java.util.List;

import org.gecko.emf.jakartars.annotations.RequireEMFMessageBodyReaderWriter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jakartars.whiteboard.annotations.RequireJakartarsWhiteboard;
import org.osgi.service.jakartars.whiteboard.propertytypes.JakartarsResource;
import org.osgi.service.servlet.whiteboard.annotations.RequireHttpWhiteboard;

import de.jena.ibis.rest.apis.DeviceStatusService;
import de.jena.ibis.rest.apis.TripInformationService;
import de.jena.model.ibis.rest.DeviceType;
import de.jena.model.ibis.rest.IbisRestFactory;
import de.jena.model.ibis.rest.OnlineDevice;
import de.jena.model.ibis.rest.TripData;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@RequireJakartarsWhiteboard
@RequireHttpWhiteboard
@JakartarsResource
@RequireEMFMessageBodyReaderWriter
@Component(name = "DeviceResource", service = DeviceResource.class, scope = ServiceScope.PROTOTYPE)
@Path("")
public class DeviceResource {

	@Reference
	DeviceStatusService deviceStatusService;
	
	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	TripInformationService tripInfoService;
	
	/**
	 * @return a list of all online devices
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/xmi"})
	@Path("/devices/all")
	public Response getOnlineDevices() {
		List<OnlineDevice> onlineDevices = deviceStatusService.getOnlineDevice();
		de.jena.model.ibis.rest.Response response = IbisRestFactory.eINSTANCE.createResponse();
		response.getData().addAll(onlineDevices);
		return Response.ok(response).build();
	}
	
	/**
	 * Needed for the Mobile Load Management System, which is intended to prevent overloads 
	 * in the tram grid and the peripheral components.
	 * @return a list of online trams
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/xmi"})
	@Path("/devices/tram")
	public Response getOnlineTram() {
		List<OnlineDevice> onlineDevices = deviceStatusService.getOnlineDevice();
		de.jena.model.ibis.rest.Response response = IbisRestFactory.eINSTANCE.createResponse();
		response.getData().addAll(onlineDevices.stream().filter(od -> od.getType().equals(DeviceType.TRAM)).toList());
		return Response.ok(response).build();
	}
	
	/**
	 * Needed for the Stationary Load Management System, which deals with the scheduling
	 * of charging operations of the several electric buses.
	 * @return a list of online e-busses
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/xmi"})
	@Path("/devices/ebus")
	public Response getOnlineEBus() {
		List<OnlineDevice> onlineDevices = deviceStatusService.getOnlineDevice();
		de.jena.model.ibis.rest.Response response = IbisRestFactory.eINSTANCE.createResponse();
		response.getData().addAll(onlineDevices.stream().filter(od -> od.getType().equals(DeviceType.EBUS)).toList());
		return Response.ok(response).build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/xmi"})
	@Path("/trip/{deviceId}")
	public Response getTripInfo(@PathParam("deviceId") String deviceId) {
		if(!deviceStatusService.isServiceAvailableOnDevice("CustomerInformationService", deviceId)) {
			return Response.noContent().entity("No CustomerInformationService available on device " + deviceId).build();
		}
		try {
			TripData tripData = tripInfoService.getTripData(deviceId);
			if(tripData == null) {
				return Response.serverError().entity("Something went wrong and we did not get any response").build();
			}
			de.jena.model.ibis.rest.Response response = IbisRestFactory.eINSTANCE.createResponse();
			response.getData().add(tripData);
			return Response.ok(response).build();
		} catch(IllegalStateException e) {
			return Response.serverError().entity("Something went wrong and we did not get any response").build();
		}
	}
}
