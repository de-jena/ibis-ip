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
package de.jena.ibis.test.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.util.promise.PromiseFactory;

import de.jena.ibis.apis.GeneralIbisService;
import de.jena.ibis.apis.IbisGNSSLocationService;
import de.jena.ibis.apis.IbisUDPServiceConfig;
import de.jena.ibis.gnsslocationservice.GNSSLSFactory;
import de.jena.ibis.gnsslocationservice.GNSSLocationServiceDataStructure;
import de.jena.ibis.test.components.helper.IbisToApiHelper;

/**
 * 
 * @author ilenia
 * @since Jun 28, 2023
 */
@Component(immediate=true, name = "FakeGNSSLocationService", 
service = {IbisGNSSLocationService.class, GeneralIbisService.class},
configurationPid = "GNSSLocationService", configurationPolicy = ConfigurationPolicy.REQUIRE)
public class GNSSLocationServiceTestImpl implements IbisGNSSLocationService {
	
	@Reference
	EventAdmin eventAdmin;
	
	private static final Logger LOGGER = Logger.getLogger(GNSSLocationServiceTestImpl.class.getName());
	
	private IbisUDPServiceConfig config;
	
	ExecutorService executors = Executors.newCachedThreadPool();
	PromiseFactory promiseFactory = new PromiseFactory(executors);
	private final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
	private ScheduledFuture<?> simulationFuture;
	private ReentrantLock simLock = new ReentrantLock(true);

	
	@Activate
	@Modified
	public void activate(IbisUDPServiceConfig config) {
		this.config = config;		
		startSimulation();
	}
	
	@Deactivate
	public void deactivate() {
		if(simulationFuture == null) {
			stopSimulation();
			ses.shutdown(); 
			try {
				if (!ses.awaitTermination(60, TimeUnit.SECONDS)) {
					ses.shutdownNow(); 
				}
			} catch (InterruptedException ie) {
				ses.shutdownNow();
				Thread.currentThread().interrupt();
			}	finally {
				simulationFuture = null;
			}
		}	
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getServiceName()
	 */
	@Override
	public String getServiceName() {
		return config.serviceName();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#getServiceId()
	 */
	@Override
	public String getServiceId() {
		return config.serviceId();
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

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#executeAllSubscriptionOperations()
	 */
	@Override
	public void executeAllSubscriptionOperations() {
		connectToGNSSLocationData();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.GeneralIbisService#executeAllUnsubscriptionOperations()
	 */
	@Override
	public void executeAllUnsubscriptionOperations() {
		stopSimulation();
	}

	/* 
	 * (non-Javadoc)
	 * @see de.jena.ibis.apis.IbisGNSSLocationService#connectToGNSSLocationData()
	 */
	@Override
	public void connectToGNSSLocationData() {
		startSimulation();
	}
	
	private void startSimulation() {
		promiseFactory.submit(() -> {
			LOGGER.info("Scheduling Simluation");
			simulationFuture = ses.scheduleAtFixedRate(this::simulate, 0, 15, TimeUnit.SECONDS);
			return true;
		});
	}
	
	private void simulate() {
		if (simLock.tryLock()) {
			try {
				doSimulate();
			} finally {
				simLock.unlock();
			}
		} else {
			LOGGER.warning(String.format("Simulation step is currently in progress, waiting"));
		}		
	}
	
	private void doSimulate() {
		Map<String, Object> properties = new HashMap<>();
		properties.put("serviceId", config.serviceId());
		properties.put("operation", "GNSSLocationData");
		properties.put("deviceId", config.refDeviceId());
		properties.put("deviceType", config.refDeviceType());
		GNSSLocationServiceDataStructure data = GNSSLSFactory.eINSTANCE.createGNSSLocationServiceDataStructure();
//		data.setTime(IbisToApiHelper.createIbisDateTime(new Date()));
		Random r = new Random();
		double randomLat = 50. + (51. - 50.) * r.nextDouble();
		r = new Random();
		double randomLng = 11. + (12. - 11.) * r.nextDouble();
		data.setLatitude(IbisToApiHelper.createIbisGNSSCoordinates(randomLat, null));
		data.setLongitude(IbisToApiHelper.createIbisGNSSCoordinates(randomLng, null));
		properties.put("data", data);
		Event evt = new Event(String.format("UDPPacket/%s/%s/%s/%s", config.refDeviceId(), config.refDeviceType(), config.serviceName(), "GNSSLocationData"), properties);	
		eventAdmin.postEvent(evt);
	}
	
	private void stopSimulation() {
		simulationFuture.cancel(true);
		while(!simulationFuture.isDone()) {
			try {
				Thread.sleep(50l);
			} catch (InterruptedException e) {
				LOGGER.severe(String.format("Simulation stopping was interrupted"));
			}
		}
		LOGGER.info("Simulation stopped");
	}

}
