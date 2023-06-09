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

/**
 * 
 * @author ilenia
 * @since Mar 28, 2023
 */
public @interface IbisUDPServiceConfig {
	
	public String serviceId() default "";
	
	public String serviceName() default "";
	
	public String refDeviceId() default "";
	
	public String refDeviceType() default "";
	
	public int listenerPort() default 53000;
	
	public String listenerNetworkInterface() default "";
	
	public String multiCastGroupIP() default "224.0.0.251";
	
	public int multiCastGroupPort() default 54000;

}
