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
package de.jena.ibis.rest.apis;

import org.osgi.annotation.versioning.ProviderType;

import de.jena.model.ibis.rest.TripData;

/**
 * 
 * @author ilenia
 * @since Jun 20, 2023
 * 
 * This service should be responsible of retrieving information about the current trip run by 
 * a certain device (tram, bus, etc). It should then handle the communication with the corresponding
 * IBIS services, sending the appropriate requests and transforming the results into the api model
 */
@ProviderType
public interface TripInformationService {
	
	TripData getTripData(String deviceId);
}
