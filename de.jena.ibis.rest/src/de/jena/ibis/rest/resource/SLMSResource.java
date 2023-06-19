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
package de.jena.ibis.rest.resource;

import java.util.List;

import org.gecko.emf.jakartars.annotations.RequireEMFMessageBodyReaderWriter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jakartars.whiteboard.annotations.RequireJakartarsWhiteboard;
import org.osgi.service.jakartars.whiteboard.propertytypes.JakartarsResource;
import org.osgi.service.servlet.whiteboard.annotations.RequireHttpWhiteboard;

import de.jena.ibis.rest.apis.DeviceStatusService;
import de.jena.model.ibis.rest.DeviceType;
import de.jena.model.ibis.rest.IbisRestFactory;
import de.jena.model.ibis.rest.OnlineDevice;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * 
 * @author ilenia
 * @since Jun 19, 2023
 * 
 * This REST Resource is responsible to handle the requests from the Stationary Load Management System,
 * which deals with the scheduling of charging operations of the several electric buses.
 */
@RequireJakartarsWhiteboard
@RequireHttpWhiteboard
@JakartarsResource
@RequireEMFMessageBodyReaderWriter
@Component(immediate=true, name = "SLMSResource", service = SLMSResource.class)
@Path("slms")
public class SLMSResource {
	
	@Reference
	DeviceStatusService deviceStatusService;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/xmi"})
	@Path("/devices")
	public Response getOnlineDevices() {
		List<OnlineDevice> onlineDevices = deviceStatusService.getOnlineDevice();
		de.jena.model.ibis.rest.Response response = IbisRestFactory.eINSTANCE.createResponse();
		response.getData().addAll(onlineDevices.stream().filter(od -> od.getType().equals(DeviceType.EBUS)).toList());
		return Response.ok(response).build();
	}

}
