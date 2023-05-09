/**
 * Copyright (c) 2012 - 2018 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.simulator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import de.dim.trafficos.simulator.apis.PublicTransportSimulator;

@Component(name = "IbisSimulatorCmd", service = IbisSimulatorCmd.class, property = {
		"osgi.command.scope=simulator", //
		"osgi.command.function=start", 
		"osgi.command.function=stop"
})
public class IbisSimulatorCmd {

	@Reference
	PublicTransportSimulator simulator;
	
	
	public void start() {
		simulator.startSimulation();
	}
	
	public void stop() {
		simulator.stopSimulation();
	}

}
