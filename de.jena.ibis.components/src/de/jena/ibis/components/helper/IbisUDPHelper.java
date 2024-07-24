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
package de.jena.ibis.components.helper;

import java.util.logging.Logger;

import org.osgi.service.cm.ConfigurationException;

import de.jena.ibis.apis.IbisUDPServiceConfig;

/**
 * 
 * @author ilenia
 * @since Apr 3, 2023
 */
public class IbisUDPHelper {

	private final static Logger LOGGER = Logger.getLogger(IbisUDPHelper.class.getName());

	public static void checkUDPServiceConfig(IbisUDPServiceConfig serviceConfig) throws ConfigurationException {
		if (serviceConfig.multiCastGroupIP().isEmpty()) {
			String msg = String.format("Multicast Group for UDP Communication is not properly set for %s",
					serviceConfig.serviceId());
			LOGGER.severe(() -> msg);
			throw new ConfigurationException("multiCastGroupIP", msg);
		} else if (serviceConfig.listenerNetworkInterface().isEmpty()) {
			String msg = String.format("Listener Network Interface for UDP Communication is not properly set for %s",
					serviceConfig.serviceId());
			LOGGER.severe(() -> msg);
			throw new ConfigurationException("listenerNetworkInterface", msg);
		}
	}


}
