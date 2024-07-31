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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.gecko.emf.json.annotation.RequireEMFJson;
import org.gecko.emf.json.constants.EMFJs;
import org.gecko.osgi.messaging.MessagingService;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jakartars.whiteboard.propertytypes.JakartarsResource;

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
@RequireEMFJson
@JakartarsResource
@Component(name = IbisResource.COMPONENT_NAME, service = IbisResource.class, scope = ServiceScope.PROTOTYPE, configurationPolicy = ConfigurationPolicy.REQUIRE)
@Path("")
public class IbisResource {
	private static final Logger LOGGER = Logger.getLogger(IbisResource.class.getName());

	private static final String TOPIC = "5g/ibis/";

	@Reference(target = "(id=full)")
	private MessagingService messaging;

	@Reference
	private ComponentServiceObjects<ResourceSet> serviceObject;

	public static final String COMPONENT_NAME = "IbisJakartarsResource";

	@GET
	@Path("/{serviceId}/hello")
	public String hello(@PathParam("serviceId") String serviceId) {
		return "Configured for " + serviceId;
	}

	@POST
	@Path("/{deviceId}/{deviceType}/{serviceName}/{operationName}")
	@Consumes
	public Response post(@PathParam("deviceType") String deviceType, @PathParam("deviceId") String deviceId,
			@PathParam("serviceName") String serviceName, @PathParam("operationName") String operationName,
			@Context HttpServletRequest request) {

		LOGGER.info(String.format("Received POST request to %s/%s/%s/%s", deviceType, deviceId, serviceName,
				operationName));
		if (request != null) {
			EClass responseEClass = IbisResponseHelper.getResponseEClass(serviceName, operationName);
			if (responseEClass != null) {
				ResourceSet set = serviceObject.getService();
				try {
					set.getPackageRegistry().put(null, responseEClass.getEPackage());
					Optional<EObject> data = load(request, set);
					if (data.isPresent()) {
						String topic = TOPIC + deviceType + "/" + deviceId + "/" + serviceName + "/" + operationName;
						publish(set, topic, data.get());
					}
				} finally {
					serviceObject.ungetService(set);
				}
			}

			LOGGER.finer("Resource loaded successfully!");
		} else {
			LOGGER.finer("Request is null!");
		}
		return Response.ok().build();
	}

	private Optional<EObject> load(HttpServletRequest request, ResourceSet set) {
		try {
			Resource responseRes = set.createResource(URI.createURI(UUID.randomUUID().toString() + "-load.xml"),
					"application/xml");
			Map<String, Object> responseOptions = new HashMap<>();
			responseOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
			responseOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
			responseOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
			responseOptions.put(XMLResource.OPTION_KEEP_DEFAULT_CONTENT, true);
			responseRes.load(request.getInputStream(), responseOptions);
			return Optional.of(responseRes.getContents().get(0));
		} catch (IOException e) {
			LOGGER.severe("Error while loading ibis resource." + e.getLocalizedMessage());
			e.printStackTrace();
			return Optional.empty();
		}
	}

	private void publish(ResourceSet set, String topic, EObject data) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			Resource resource = set.createResource(URI.createFileURI(UUID.randomUUID().toString() + "-mqtt.json"));
			resource.getContents().add(data);
			resource.save(baos, Collections.singletonMap(EMFJs.OPTION_SERIALIZE_DEFAULT_VALUE, true));
			messaging.publish(topic, ByteBuffer.wrap(baos.toByteArray()));
		} catch (Exception e) {
			LOGGER.severe("Error while publishing ibis resource." + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
