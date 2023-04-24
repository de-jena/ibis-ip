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
package de.jena.ibis.components.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLResource.MissingPackageHandler;
import org.gecko.emf.osgi.EMFUriHandlerConstants;
import org.osgi.service.component.ComponentServiceObjects;

import de.jena.ibis.apis.IbisTCPServiceConfig;
import de.jena.model.ibis.common.DocumentRoot;
import de.jena.model.ibis.common.GeneralResponse;

/**
 * 
 * @author ilenia
 * @since Jan 18, 2023
 */
public class IbisHttpRequestHelper {

	public static final Logger LOGGER = Logger.getLogger(IbisHttpRequestHelper.class.getName());

//	public static Integer sendHttpSubscriptionRequest(String host, String port, String serviceName, String operationName,
//			EObject request,
//			ComponentServiceObjects<ResourceSet> rsFactory) {
//		URI uri = calculateURI(host, port, serviceName, operationName);
//		try {
//			return doSendHttpSubscriptionRequest(uri, "POST", request, rsFactory);	
//		} catch (Exception e) {
//			LOGGER.severe(String.format("Something went wrong during request %s. Returning null!", uri.toString()));
//			return null;
//		}
//	}
//	
//	public static <T extends GeneralResponse> T sendHttpSubscriptionRequest2(String host, String port, String serviceName, String operationName,
//			EObject request,
//			ComponentServiceObjects<ResourceSet> rsFactory) {
//		URI uri = calculateURI(host, port, serviceName, operationName);
//		try {
//			Optional<GeneralResponse> responseOpt =  doSendHttpRequest(uri, "POST", request, IbisCommonPackage.eINSTANCE.getSubscribeResponse(), rsFactory);	
//			if(isResponseValid(responseOpt, operationName)) {
//				return (T) responseOpt.get();
//			}
//			LOGGER.severe(String.format("Response for URI %s is not valid. Returning null!", uri.toString()));
//			return null;
//		} catch (Throwable e) {
//			LOGGER.severe(String.format("Something went wrong during request %s. Returning null!", uri.toString()));
//			return null;
//		}
//	}
	
	@SuppressWarnings("unchecked")
	public static <T extends GeneralResponse> T sendHttpRequest(IbisTCPServiceConfig serviceConfig, String operationName, EObject request, EClass responseClass,
			ComponentServiceObjects<ResourceSet> rsFactory) {
		URI uri = calculateURI(serviceConfig, operationName);
		try {
			Optional<GeneralResponse> responseOpt = doSendHttpRequest(uri, request == null ? "GET" : "POST", request, responseClass, rsFactory);
			if(isResponseValid(responseOpt, operationName)) {
				return (T) responseOpt.get();
			}
			LOGGER.severe(String.format("Response for URI %s is not valid. Returning null!", uri.toString()));
			return null;
		} catch (Throwable e) {
			LOGGER.severe(String.format("Something went wrong during request %s. Returning null!", uri.toString()));
			e.printStackTrace();
			return null;
		}
	}

	private static URI calculateURI(IbisTCPServiceConfig serviceConfig, String operationName) {
		return URI.createURI(calculateURIString(serviceConfig.serviceIP(), serviceConfig.servicePort(), serviceConfig.serviceName(), operationName));
	}

	private static String calculateURIString(String host, String port, String serviceName, String operationName) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		sb.append(host);
		sb.append(":");
		sb.append(port);
		sb.append("/");
		sb.append(serviceName);
		sb.append("/");
		sb.append(operationName);
		return sb.toString();
	}

//	private static int doSendHttpSubscriptionRequest(URI uri, String method, EObject request,
//			ComponentServiceObjects<ResourceSet> rsFactory) throws Exception {
//
//		ResourceSet set = rsFactory.getService();
//
//		try {
//			Map<String, Object> options = new HashMap<>();			
//			IbisResource requestRes = new IbisResource(uri);
//			set.getResources().add(requestRes);
//
//			//			Add the request parameters to the request resource
//			Map<String, Object> headers = new HashMap<>();
//			if(request != null) {
//				requestRes.getContents().add(request);			
//				headers.put("Content-Type", "application/xml; charset=utf-8");
//			}			
//			headers.put("Accept", "application/xml; charset=utf-8");
//			headers.put("Method", method);
//			options.put(EMFUriHandlerConstants.OPTION_HTTP_HEADERS, headers);
//			options.put(EMFUriHandlerConstants.OPTION_HTTP_METHOD, method);				
//			options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
//			options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
//			options.put(XMLResource.OPTION_ENCODING, "UTF-8");
//			requestRes.save(System.out, options);
//			requestRes.save(options);
//			return requestRes.getResponseCode();
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			rsFactory.ungetService(set);
//		}
//	}


	@SuppressWarnings("unchecked")
	private static <T extends GeneralResponse> Optional<T> doSendHttpRequest(URI uri, String method, EObject request, EClass responseClass,
			ComponentServiceObjects<ResourceSet> rsFactory) throws Throwable {

		ResourceSet set = rsFactory.getService();
		set.getPackageRegistry().put(null, responseClass.getEPackage());

		try {
			Map<String, Object> options = new HashMap<>();

			Resource requestRes = set.createResource(uri, "application/xml");

			//			Add the request parameters to the request resource
			Map<String, Object> headers = new HashMap<>();
			if(request != null) {
				requestRes.getContents().add(request);			
				headers.put("Content-Type", "application/xml; charset=utf-8");
			}			
			headers.put("Accept", "application/xml; charset=utf-8");
			headers.put("Method", method);
			options.put(EMFUriHandlerConstants.OPTION_HTTP_HEADERS, headers);
			options.put(EMFUriHandlerConstants.OPTION_HTTP_METHOD, method);				
			options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
			options.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
			options.put(XMLResource.OPTION_ENCODING, "UTF-8");

			if("POST".equals(method)) {
				Resource responseRes = set.createResource(URI.createURI("temp.xml"), "application/xml");
				Map<String, Object> responseOptions = new HashMap<>();
				responseOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
				responseOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
				responseOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
				responseOptions.put(XMLResource.OPTION_MISSING_PACKAGE_HANDLER, new MissingPackageHandler() {

					@Override
					public EPackage getPackage(String nsURI) {
						return responseClass.getEPackage();
					}}
				);
				options.put(EMFUriHandlerConstants.OPTIONS_EXPECTED_RESPONSE_RESOURCE, responseRes);
				options.put(EMFUriHandlerConstants.OPTIONS_EXPECTED_RESPONSE_RESOURCE_OPTIONS, responseOptions);
				requestRes.save(System.out, options);
				requestRes.save(options);
				if(responseRes.getContents().isEmpty()) return Optional.empty();
				else if(responseRes.getContents().get(0) instanceof DocumentRoot) {
					DocumentRoot root = (DocumentRoot) responseRes.getContents().get(0);
					return (Optional<T>) Optional.of(root.getSubscribeResponse());
				} 
				return Optional.empty();
			} else {
				requestRes.load(options);		
				return requestRes.getContents().isEmpty() ? Optional.empty() : Optional.of((T) requestRes.getContents().get(0));
			}
		} catch (Throwable e) {
			throw e;
		} finally {
			rsFactory.ungetService(set);
		}
	}

	private static boolean isResponseValid(Optional<? extends GeneralResponse> responseOptional, String operationName) {
		if(responseOptional.isEmpty()) {
			LOGGER.severe(String.format("Response for operation %s is empty!", operationName));
			return false;
		}
		GeneralResponse response = responseOptional.get();
		if(response.getOperationErrorMessage() != null) {
			LOGGER.severe(String.format("Response for operation %s contains error with code %s and value %s", 
					operationName, response.getOperationErrorMessage().getErrorCode(), response.getOperationErrorMessage().getValue()));
			return false;
		}		
		return true;
	}

}
