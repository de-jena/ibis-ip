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
package de.jena.ibis.components;

import java.io.IOException;
import java.net.MulticastSocket;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.event.EventAdmin;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.IbisGNSSLocationService;
import de.jena.ibis.apis.IbisUDPServiceConfig;
import de.jena.ibis.components.helper.IbisUDPHelper;

/**
 * 
 * @author ilenia
 * @since Jan 18, 2023
 */
@Component(name = "IbisGNSSLocationService", 
scope = ServiceScope.PROTOTYPE, service = {IbisGNSSLocationService.class, GeneralIbisService.class},
configurationPid = "GNSSLocationService", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class IbisGNSSLocationServiceImpl implements IbisGNSSLocationService {

	@Reference
	private ComponentServiceObjects<ResourceSet> rsFactory;
	
	@Reference
	EventAdmin eventAdmin;

	private final static Logger LOGGER = Logger.getLogger(IbisGNSSLocationServiceImpl.class.getName());
	private IbisUDPServiceConfig config;
	private MulticastSocket socket;
	
	
	@Activate
	public void activate(IbisUDPServiceConfig config) throws ConfigurationException{
		IbisUDPHelper.checkUDPServiceConfig(config);
		this.config = config;		
		executeAllSubscriptionOperations();
	}
	
	@Deactivate() 
	public void deactivate() {
		executeAllUnsubscriptionOperations();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisGNSSLocationService#connectToGNSSLocationData()
	 */
	@Override
	public Integer connectToGNSSLocationData() {
		return doConnectToGNSSLocationData();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisUDPService#executeAllSubscriptionOperations()
	 */
	@Override
	public List<Integer> executeAllSubscriptionOperations() {
//		return List.of(connectToGNSSLocationData());
		return Collections.emptyList();
	}
	
	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#executeAllUnsubscriptionOperations()
	 */
	@Override
	public List<Integer> executeAllUnsubscriptionOperations() {
		if(socket != null && socket.isConnected()) {
			socket.disconnect();
			return List.of(200);
		} 
		return Collections.emptyList();		
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisUDPService#getServiceName()
	 */
	@Override
	public String getServiceName() {
		return config.serviceName();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisUDPService#getServiceId()
	 */
	@Override
	public String getServiceId() {
		return config.serviceId();
	}

	private Integer doConnectToGNSSLocationData() {
		
		try {
			socket = new MulticastSocket(config.listenerPort());
			return IbisUDPHelper.setupUDPConnection(socket, config, "GetGNSSLocationData", rsFactory, eventAdmin);
		} catch(IOException e) {
			LOGGER.severe(() -> String.format("Something went wrong when trying to connect to multicast group for %s", config.serviceId()));
			e.printStackTrace();
			return -1;
		}	
	}
}
