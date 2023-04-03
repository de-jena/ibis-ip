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
package de.jena.ibis.rest.application;

import jakarta.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

import de.jena.ibis.runtime.annotation.RequireRuntime;

@Component(name = IbisApplication.COMPONENT_NAME, service = Application.class, configurationPolicy = ConfigurationPolicy.REQUIRE)
@RequireRuntime
public class IbisApplication extends Application {

	public static final String COMPONENT_NAME = "IbisApplication";
	
}

