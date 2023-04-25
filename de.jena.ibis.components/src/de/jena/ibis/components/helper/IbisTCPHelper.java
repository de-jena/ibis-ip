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

import java.util.logging.Logger;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentServiceObjects;

import de.jena.ibis.apis.IbisTCPServiceConfig;
import de.jena.model.ibis.common.IBISIPInt;
import de.jena.model.ibis.common.IBISIPString;
import de.jena.model.ibis.common.IbisCommonPackage;
import de.jena.model.ibis.common.SubscribeRequest;
import de.jena.model.ibis.common.SubscribeResponse;

/**
 * 
 * @author ilenia
 * @since Mar 15, 2023
 */
public class IbisTCPHelper {
	
	private final static Logger LOGGER = Logger.getLogger(IbisTCPHelper.class.getName());

	
	public static void checkTCPServiceConfig(IbisTCPServiceConfig serviceConfig) throws ConfigurationException {
		if(serviceConfig.serviceIP().isEmpty()) {
			String msg = String.format("Service IP is not properly set for %s", serviceConfig.serviceId());
			LOGGER.severe(() -> msg);
			throw new ConfigurationException("serviceIP", msg);
		}
		if(serviceConfig.servicePort().isEmpty()) {
			String msg = String.format("Service Port is not properly set for %s", serviceConfig.serviceId());
			LOGGER.severe(() -> msg);
			throw new ConfigurationException("servicePort", msg);
		}
	}
	
	public static SubscribeRequest createSubscriptionRequest(IbisTCPServiceConfig serviceConfig, String operation, IbisCommonPackage ibisCommonPackage) {
		SubscribeRequest subscriptionRequest = ibisCommonPackage.getIbisCommonFactory().createSubscribeRequest();
		
		IBISIPString ibisClientIP = ibisCommonPackage.getIbisCommonFactory().createIBISIPString();
		ibisClientIP.setValue(serviceConfig.serviceClientSubscriptionIP());
		
		IBISIPInt ibisClientPort = ibisCommonPackage.getIbisCommonFactory().createIBISIPInt();
		ibisClientPort.setValue(serviceConfig.serviceClientSubscriptionPort());
		
		IBISIPString ibisClientPath = ibisCommonPackage.getIbisCommonFactory().createIBISIPString();
//		String path = "/ibis/rest/" + serviceConfig.serviceId()+"/"+operation;
		String path = "/ibis/rest/" + serviceConfig.refDeviceId() + "/" + serviceConfig.refDeviceType() + "/" + serviceConfig.serviceName()+"/"+operation;
		ibisClientPath.setValue(path);
		
		subscriptionRequest.setClientIPAddress(ibisClientIP);
		subscriptionRequest.setReplyPort(ibisClientPort);
		subscriptionRequest.setReplyPath(ibisClientPath);
		
		return subscriptionRequest;
	}
	
	public static SubscribeResponse sendSubscriptionRequest(IbisTCPServiceConfig serviceConfig, String operation, 
			IbisCommonPackage ibisCommonPackage, ComponentServiceObjects<ResourceSet> rsFactory) {
		SubscribeRequest subscribeRequest = 
				IbisTCPHelper.createSubscriptionRequest(serviceConfig, operation, ibisCommonPackage);
		
		SubscribeResponse response = IbisHttpRequestHelper.sendHttpRequest(serviceConfig, operation, subscribeRequest, ibisCommonPackage.getSubscribeResponse(), rsFactory);
		if(response != null) {
			if(!isSubscribeResponseValid(response, operation)) {
				LOGGER.warning(() -> String.format("Subscription response for service %s and operation %s is not valid. Returning null!", serviceConfig.serviceId(), operation));
				return null;
			} else {
				LOGGER.info(() -> String.format("Subscription for service %s and operation %s was successfull! Starting listening to it.", serviceConfig.serviceId(), operation));
			}
		}		
		return response;
	}
	
	public static boolean isSubscribeResponseValid(SubscribeResponse subscribeResponse, String operation) {
		if(!subscribeResponse.getActive().isValue()) {
			LOGGER.warning(() -> String.format("Subscription for operation %s is not active!", operation));
			return false;
		}
		return true;
	}
}
