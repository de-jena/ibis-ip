/**
 * Copyright (c) 2012 - 2023 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.rest.application.resource;


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.gecko.osgi.messaging.MessagingService;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jakartars.whiteboard.propertytypes.JakartarsResource;

import de.jena.ibis.apis.helper.IbisMQTTHelper;
import de.jena.ibis.apis.helper.IbisResponseHelper;
import de.jena.ibis.runtime.annotation.RequireRuntime;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

/**
 * 
 * @author ilenia
 * @since Mar 29, 2023
 */
@RequireRuntime
@JakartarsResource
@Component(name = IbisResource.COMPONENT_NAME, service = IbisResource.class, 
scope = ServiceScope.PROTOTYPE, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Path("")
public class IbisResource {
	
	@Reference(target = "(id=full)")
	MessagingService messagingService;

	@Reference
	private ComponentServiceObjects<ResourceSet> rsFactory;

	public static final String COMPONENT_NAME = "IbisJakartarsResource";
	private static final Logger LOGGER = Logger.getLogger(IbisResource.class.getName());

	@GET
	@Path("/{serviceId}/hello")
	public String hello(@PathParam("serviceId") String serviceId) {
		return "Configured for " + serviceId;
	}

	@POST
	@Path("/{deviceId}/{deviceType}/{serviceName}/{operationName}")
	@Consumes
	public Response post(@PathParam("deviceId") String deviceId, @PathParam("deviceType") String deviceType, @PathParam("serviceName") String serviceName, @PathParam("operationName") String operationName, @Context HttpServletRequest request) {

		LOGGER.info(String.format("Received POST request to %s/%s/%s/%s", deviceId, deviceType, serviceName, operationName));
		if(request != null) {
			try {
				String received = new String(request.getInputStream().readAllBytes());
				EClass responseEClass = IbisResponseHelper.getResponseEClass(serviceName, operationName);
				if(responseEClass != null) {
					ResourceSet set = rsFactory.getService();
					set.getPackageRegistry().put(null, responseEClass.getEPackage());
					Resource responseRes = set.createResource(URI.createURI("temp.xml"), "application/xml");
					Map<String, Object> responseOptions = new HashMap<>();
					responseOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
					responseOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
					responseOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
					responseOptions.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, true);
					responseRes.load(new BufferedInputStream(new ByteArrayInputStream(received.getBytes())), responseOptions);
					IbisMQTTHelper.sendToMQTT(String.format("TCPResponse/%s/%s/%s/%s", deviceId, deviceType, serviceName, operationName), responseRes.getContents().get(0), rsFactory, messagingService);
				}
			} catch(IOException e) {
				LOGGER.log(Level.SEVERE, String.format("Cannot load request content for %s/%s/%s/%s", deviceId, deviceType, serviceName, operationName), e);
			}
		}
		else {
			LOGGER.severe(String.format("Request is null for %s/%s/%s/%s", deviceId, deviceType, serviceName, operationName));
		}
		return Response.ok().build();
	}
}
