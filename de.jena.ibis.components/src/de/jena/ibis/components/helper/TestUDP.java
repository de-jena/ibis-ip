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
package de.jena.ibis.components.helper;

import java.io.IOException;

/**
 * 
 * @author ilenia
 * @since Sep 25, 2023
 */
public class TestUDP {
	
	public static void main(String...strings) {
		
		System.out.println("----------------------------");
		System.out.println("FakeUDPReceiver active");
		new Thread(() -> new MulticastReceiver("wlp0s20f3", "230.0.0.0").run()).start();
		
		try {
    		MulticastPublisher publisher = new MulticastPublisher("230.0.0.0", "wlp0s20f3");
    		for(int i = 0; i < 10; i++) {
    			Thread.sleep(1000);
    			publisher.multicast("Hello");
    		}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
