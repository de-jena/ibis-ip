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

import java.io.IOException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import de.jena.ibis.components.helper.MulticastPublisher;
import de.jena.ibis.components.helper.MulticastReceiver;

/**
 * Test component to check UDP connection
 * @author ilenia
 * @since Sep 25, 2023
 */
//@Component(name = "FakeUDPComponent", immediate=true)
public class FakeUDPComponent {
	
	@Activate
	public void activate() {
		System.out.println("----------------------------");
		System.out.println("FakeUDPComponent active");
		new Thread(() -> new MulticastReceiver("eth0", "224.0.0.251").run()).start();
		
		try {
    		MulticastPublisher publisher = new MulticastPublisher("224.0.0.251", "eth0");
    		for(int i = 0; i < 10; i++) {
    			Thread.sleep(1000);
    			publisher.multicast("Hello");
    		}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
