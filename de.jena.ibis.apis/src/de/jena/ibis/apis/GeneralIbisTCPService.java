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

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

import de.jena.model.ibis.common.GeneralResponse;

/**
 * 
 * @author ilenia
 * @since Mar 27, 2023
 */
@ProviderType
public interface GeneralIbisTCPService extends GeneralIbisService {
	
	List<GeneralResponse> executeAllGetOperations();
		
	GeneralResponse executeGetOperation(String operation);	
		
}
