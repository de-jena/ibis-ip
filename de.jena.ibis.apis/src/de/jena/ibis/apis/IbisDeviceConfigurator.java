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
package de.jena.ibis.apis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * 
 * @author ilenia
 * @since Mar 29, 2023
 */
@Component(name = "IbisDeviceConfigurator", service = IbisDeviceConfigurator.class, configurationPid = "IbisDeviceConfigurator", configurationPolicy = ConfigurationPolicy.REQUIRE, scope = ServiceScope.PROTOTYPE)
@RequireConfigurationAdmin
public class IbisDeviceConfigurator {

	private static final Logger LOGGER = Logger.getLogger(IbisDeviceConfigurator.class.getName());

	private ConfigurationAdmin configAdmin;
	private IbisDeviceConfiguratorConfig config;
	private List<Configuration> serviceConfigs = new ArrayList<>();

	@Activate
	public IbisDeviceConfigurator(IbisDeviceConfiguratorConfig config, @Reference ConfigurationAdmin configAdmin) throws IOException  {
		this.config = config;
		this.configAdmin = configAdmin;
		String[] tcpServices = config.refTCPServices();
		for(String service : tcpServices) {
			updateServiceConfig(service);
		}
		String[] udpServices = config.refUDPServices();
		for(String service : udpServices) {
			updateServiceConfig(service);
		}
	}
	
	@Deactivate
	public void deactivate() {	
		serviceConfigs.forEach(c -> {
			try {
				c.delete();
			} catch (IOException e) {
				LOGGER.severe(() -> "Exception when trying to delete Configuration " + c.getFactoryPid());
				e.printStackTrace();
			}
		});
		serviceConfigs.clear();
	}
	
	public IbisDeviceConfiguratorConfig getConfig() {
		return config;
	}
	
	private void updateServiceConfig(String service) throws IOException {		
		String factoryPid = service;
		Configuration serviceConfig = configAdmin.getFactoryConfiguration(factoryPid, config.deviceId(), "?");		
		if(isSupportedTCPService(service)) {
			String servicePort = determineServicePort(service);
			serviceConfig.update(createTCPServiceProperties(service, servicePort));
			LOGGER.info(() -> "Updated Config for IbisTCPService " + service + " in Configurator " + config.deviceId());
		} else if(isSupportedUDPService(service)) {
			serviceConfig.update(createUDPServiceProperties(service));
			LOGGER.info(() -> "Updated Config for IbisUDPService " + service + " in Configurator " + config.deviceId());
		} else {
			throw new IllegalArgumentException(String.format("Unsupported Ibis Service %s", service));
		}	
		serviceConfigs.add(serviceConfig);
	}
	
	private String determineServicePort(String service) {
		switch(service) {
		case "CustomerInformationService":
			return config.customerInfoServicePort();
		case "TicketValidationService":
			return config.ticketValidationServicePort();
		default:
			LOGGER.severe(() -> String.format("Service %s not supported!", service));
			return null;
		}
	}
	
	private boolean isSupportedTCPService(String service) {
		if("CustomerInformationService".equals(service) ||
				"TicketValidationService".equals(service) ||
				"DeviceManagementService".equals(service)) {
			return true;
		}
		return false;
	}
	
	private boolean isSupportedUDPService(String service) {
		if("GNSSLocationService".equals(service)) return true;
		return false;
	}
	
	private Dictionary<String, Object> createTCPServiceProperties(String service, String servicePort) {
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put("serviceType", "TCP");
		props.put("refDeviceId", config.deviceId());
		props.put("refDeviceType", config.deviceType());
		props.put("serviceName", service);
		props.put("serviceId", service+"-"+config.deviceId());
		props.put("serviceIP", config.deviceIP());
		props.put("servicePort", servicePort);
		props.put("serviceClientSubscriptionPort", config.clientSubscriptionPort());
		props.put("serviceClientSubscriptionIP", config.clientSubscriptionIP());
		return props;
	}
	
	private Dictionary<String, Object> createUDPServiceProperties(String service) {
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put("serviceType", "UDP");
		props.put("refDeviceId", config.deviceId());
		props.put("refDeviceType", config.deviceType());
		props.put("serviceName", service);
		props.put("serviceId", service+"-"+config.deviceId());
		props.put("serviceIP", config.deviceIP());
		props.put("listenerNetworkInterface", config.updListenerNetworkInterface());
		props.put("multiCastGroupPort", config.udpMultiCastGroupPort());
		props.put("multiCastGroupIP", config.udpMultiCastGroupIP());
		props.put("listenerPort", config.udpListenerPort());
		return props;
	}
}
