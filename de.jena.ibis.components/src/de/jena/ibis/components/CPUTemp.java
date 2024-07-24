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
package de.jena.ibis.components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * 
 * @author ilenia
 * @since May 2, 2023
 */
@Component(name = "CPUTemp", immediate = true)
public class CPUTemp {

	private static final Logger logger = Logger.getLogger(CPUTemp.class.getName());

	private static final String FILE = "/sys/class/thermal/thermal_zone0/temp";
	private static final List<Integer> values = new ArrayList<>();

	private ScheduledExecutorService executor;

	@Activate
	public void activate() {
		executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(this::checkTemp, 0, 2, TimeUnit.MINUTES);
	}

	@Deactivate
	public void deactivate() {
		executor.shutdown();
	}

	private void checkTemp() {

		try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
			int value = Integer.parseInt(br.readLine());
			values.add(value);
			int total = values.stream().mapToInt(Integer::valueOf).sum();
			logger.log(Level.INFO, "Now: "+toCelsius(value)+"°C - Average: "+toCelsius(total / values.size())+"°C - Number of measurements: "+values.size() );
		} catch (Exception e) {
			logger.log(Level.INFO, "Error during temperature check: ", e);
		}

	}

	private double toCelsius(int value) {
		return value / 1000d;
	}

}
