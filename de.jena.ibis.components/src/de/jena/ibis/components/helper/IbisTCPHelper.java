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
import de.jena.ibis.ibis_common.GeneralSubscribeRequest;
import de.jena.ibis.ibis_common.IBISIPInt;
import de.jena.ibis.ibis_common.IBISIPString;
import de.jena.ibis.ibis_common.IbisCommonPackage;

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
	
	public static GeneralSubscribeRequest createSubscriptionRequest(IbisTCPServiceConfig serviceConfig, String operation, IbisCommonPackage ibisCommonPackage) {
		GeneralSubscribeRequest subscriptionRequest = ibisCommonPackage.getIbisCommonFactory().createGeneralSubscribeRequest();
		
		IBISIPString ibisClientIP = ibisCommonPackage.getIbisCommonFactory().createIBISIPString();
		ibisClientIP.setValue(serviceConfig.serviceClientSubscriptionIP());
		
		IBISIPInt ibisClientPort = ibisCommonPackage.getIbisCommonFactory().createIBISIPInt();
		ibisClientPort.setValue(serviceConfig.serviceClientSubscriptionPort());
		
		IBISIPString ibisClientPath = ibisCommonPackage.getIbisCommonFactory().createIBISIPString();
		String path = "ibis/rest/" + serviceConfig.serviceId()+"/"+operation;
		ibisClientPath.setValue(path);
		
		subscriptionRequest.setClientIPAddress(ibisClientIP);
		subscriptionRequest.setReplyPort(ibisClientPort);
		subscriptionRequest.setReplyPath(ibisClientPath);
		
		return subscriptionRequest;
	}
	
	public static Integer sendSubscriptionRequest(IbisTCPServiceConfig serviceConfig, String operation, 
			IbisCommonPackage ibisCommonPackage, ComponentServiceObjects<ResourceSet> rsFactory) {
		GeneralSubscribeRequest subscribeRequest = 
				IbisTCPHelper.createSubscriptionRequest(serviceConfig, operation, ibisCommonPackage);
		
		return IbisHttpRequestHelper.sendHttpSubscriptionRequest(serviceConfig.serviceIP(), serviceConfig.servicePort(),
				serviceConfig.serviceName(), operation, subscribeRequest, rsFactory);
	}
}
