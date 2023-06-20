/**
 * Copyright (c) 2012 - 2022 Data In Motion and others.
 * All rights reserved. 
 * 
 * This program and the accompanying materials are made available under the terms of the 
 * Eclipse Public License v2.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Data In Motion - initial API and implementation
 */
package de.jena.ibis.rest.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.osgi.framework.BundleContext;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.common.annotation.InjectService;
import org.osgi.test.common.service.ServiceAware;
import org.osgi.test.junit5.cm.ConfigurationExtension;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;

import de.jena.ibis.apis.IbisCustomerInformationService;

/**
 * See documentation here: 
 * 	https://github.com/osgi/osgi-test
 * 	https://github.com/osgi/osgi-test/wiki
 * Examples: https://github.com/osgi/osgi-test/tree/main/examples
 */
@ExtendWith(BundleContextExtension.class)
@ExtendWith(ServiceExtension.class)
@ExtendWith(ConfigurationExtension.class)
@ExtendWith(MockitoExtension.class)
public class TripInformationServiceTest {
	
	
	@BeforeEach
	public void before(@InjectBundleContext BundleContext ctx) {
		System.out.println("Test");
	
		
	}
	
	@Test
	public void testTripDataNullResponse(@InjectService ServiceAware<IbisCustomerInformationService> customerInfoAware) {
		
		assertThat(customerInfoAware).isNotNull();
		IbisCustomerInformationService customerInfoService = customerInfoAware.getService();
		assertThat(customerInfoService).isNotNull();
		
		IbisCustomerInformationService customerInfoMock = Mockito.spy(customerInfoService);
		Mockito.doReturn(null).when(customerInfoMock).getTripData();
		
		
		
		
	}
	
	
}
