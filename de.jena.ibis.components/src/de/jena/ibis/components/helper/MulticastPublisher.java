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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.StandardSocketOptions;

/**
 * 
 * @author ilenia
 * @since Sep 25, 2023
 */
public class MulticastPublisher {
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buf;
    
	int multicastPort = 50976;
	String inetAddressIP = "224.0.0.251";
	String networkInterface = "eth0";

	public MulticastPublisher() {
		
	}
	
	public MulticastPublisher(String inetAddressIP, String networkInteface) {
		this.inetAddressIP = inetAddressIP;
		this.networkInterface = networkInteface;
	}

    public void multicast(String multicastMessage) throws IOException {
        group = InetAddress.getByName(inetAddressIP);
        socket = new DatagramSocket();
        socket.setOption(StandardSocketOptions.IP_MULTICAST_IF, NetworkInterface.getByName(networkInterface));
        System.out.println("Publisher Interface: " + socket.getOption(StandardSocketOptions.IP_MULTICAST_IF));

        buf = multicastMessage.getBytes();
        DatagramPacket packet 
          = new DatagramPacket(buf, buf.length, group, multicastPort);
        socket.send(packet);
		System.out.println(String.format("Published %s", multicastMessage));
        socket.close();
    }
    
    public static void main(String...strings) {
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