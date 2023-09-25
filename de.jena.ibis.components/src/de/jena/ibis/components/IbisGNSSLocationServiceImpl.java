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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.gecko.osgi.messaging.MessagingService;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.IbisGNSSLocationService;
import de.jena.ibis.apis.IbisUDPServiceConfig;
import de.jena.ibis.components.helper.IbisUDPHelper;

/**
 * 
 * @author ilenia
 * @since Jan 18, 2023
 */
@Component(immediate=true, name = "IbisGNSSLocationService", 
service = {IbisGNSSLocationService.class, GeneralIbisService.class},
configurationPid = "GNSSLocationService", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class IbisGNSSLocationServiceImpl implements IbisGNSSLocationService {

	@Reference
	private ComponentServiceObjects<ResourceSet> rsFactory;
	
	@Reference(target = "(id=full)")
	MessagingService messagingService;
	
	private final static Logger LOGGER = Logger.getLogger(IbisGNSSLocationServiceImpl.class.getName());
	private IbisUDPServiceConfig config;
	private MulticastSocket socket;
	
	
	@Activate
	public void activate(IbisUDPServiceConfig config) throws ConfigurationException{
		IbisUDPHelper.checkUDPServiceConfig(config);
		this.config = config;		
		LOGGER.info(String.format("GNSSLocationService is up and running for %s!", config.refDeviceId()));
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
	public void connectToGNSSLocationData() {
		doConnectToGNSSLocationData();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisUDPService#executeAllSubscriptionOperations()
	 */
	@Override
	public void executeAllSubscriptionOperations() {
		doConnectToGNSSLocationData();
	}
	
	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#executeAllUnsubscriptionOperations()
	 */
	@Override
	public void executeAllUnsubscriptionOperations() {
		if(socket != null && socket.isConnected()) {
			socket.disconnect();
		} 
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

	private void doConnectToGNSSLocationData() {		
		try {
			socket = new MulticastSocket(config.multiCastGroupPort());
			IbisUDPHelper.setupUDPConnection(socket, config, "GetGNSSLocationData", rsFactory, messagingService);
		} catch(IOException e) {
			LOGGER.log(Level.SEVERE, String.format("Something went wrong when trying to connect to multicast group for %s", config.serviceId(), e));
		}	
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getRefDeviceId()
	 */
	@Override
	public String getRefDeviceId() {
		return config.refDeviceId();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getRefDeviceType()
	 */
	@Override
	public String getRefDeviceType() {
		return config.refDeviceType();
	}
}
