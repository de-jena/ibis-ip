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
package de.jena.ibis.rest.application.configurator;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Logger;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.annotations.RequireConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jakartars.whiteboard.JakartarsWhiteboardConstants;

import de.jena.ibis.rest.application.IbisApplication;
import de.jena.ibis.rest.application.resource.IbisResource;


/**
 * This component is responsible for activating a rest resource, in such a way we can listen to subscriptions for TCP services 
 * 
 * @author ilenia
 * @since Mar 29, 2023
 */
@Component(name="IbisApplicationConfigurator", immediate=true)
@RequireConfigurationAdmin
public class IbisApplicationConfigurator {

	private static final Logger LOGGER = Logger.getLogger(IbisApplicationConfigurator.class.getName());
	private ConfigurationAdmin configAdmin;
	private Configuration applicationConfig;

	@Activate
	public IbisApplicationConfigurator(@Reference ConfigurationAdmin configAdmin) throws IOException{
		this.configAdmin = configAdmin;
		configureRestResource();
	}
	
	@Deactivate
	public void deactivate() {
		if(applicationConfig != null) {
			try {
				applicationConfig.delete();
			} catch(IOException e) {
				LOGGER.severe(() -> String.format("Error while deleting Config for Jakarta RS Application %s", applicationConfig.getPid()));
				e.printStackTrace();
			}
		}
	}
	
	private void configureRestResource() throws IOException {

		applicationConfig = configAdmin.createFactoryConfiguration(IbisApplication.COMPONENT_NAME, "?");
		Dictionary<String, Object> props = new Hashtable<String, Object>();
		props.put(JakartarsWhiteboardConstants.JAKARTA_RS_APPLICATION_BASE, "/");
		props.put(JakartarsWhiteboardConstants.JAKARTA_RS_NAME, "IbisJaxRsApplication");
		props.put("id",  "IbisJaxRsApplication");
		applicationConfig.update(props);
		LOGGER.fine(()->"Registering JaxRs application  IbisJaxRsApplication");
		
		Configuration resourceConfig = configAdmin.createFactoryConfiguration(IbisResource.COMPONENT_NAME, "?");
		props = new Hashtable<String, Object>();
		props.put(JakartarsWhiteboardConstants.JAKARTA_RS_NAME, "IbisJaxRsResource");
		props.put(JakartarsWhiteboardConstants.JAKARTA_RS_APPLICATION_SELECT, "(id=IbisJaxRsApplication)");
		resourceConfig.update(props);
		LOGGER.fine(()->"Registering JaxRs resource IbisJaxRsResource");
	}
}
