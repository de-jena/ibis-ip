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

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jakartars.whiteboard.annotations.RequireJakartarsWhiteboard;
import org.osgi.service.jakartars.whiteboard.propertytypes.JakartarsResource;
import org.osgi.service.servlet.whiteboard.annotations.RequireHttpWhiteboard;

import de.jena.ibis.rest.apis.DeviceStatusService;
import de.jena.model.ibis.rest.IbisRestFactory;
import de.jena.model.ibis.rest.OnlineDevice;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.gecko.emf.jakartars.annotations.RequireEMFMessageBodyReaderWriter;


@RequireJakartarsWhiteboard
@RequireHttpWhiteboard
@JakartarsResource
@RequireEMFMessageBodyReaderWriter
@Component(immediate=true, name = "DeviceResource", service = DeviceResource.class)
@Path("")
public class DeviceResource {

	@Reference
	DeviceStatusService deviceStatusService;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/xmi"})
	@Path("/devices")
	public Response getOnlineDevices() {
		List<OnlineDevice> onlineDevices = deviceStatusService.getOnlineDevice();
		de.jena.model.ibis.rest.Response response = IbisRestFactory.eINSTANCE.createResponse();
		response.getData().addAll(onlineDevices);
		return Response.ok(response).build();
	}
}
