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
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

/**
 * 
 * @author ilenia
 * @since Sep 25, 2023
 */
public class MulticastReceiver extends Thread {

	protected MulticastSocket socket = null;
	protected byte[] buf = new byte[256];
	
	String neteworkInterfaceName = "eth0";
	
	//any address between 224.0.0.0 to 239.255.255.255 can be used as a multicast address. 
	String inetAddressIP = "224.0.0.251"; 
	
//	int listenerPort = 53000;
	int multicastPort = 50976;
	
	public MulticastReceiver() {
		
	}
	
	public MulticastReceiver(String networkInterfaceName, String inetAddressIP) {
		this.neteworkInterfaceName = networkInterfaceName;
		this.inetAddressIP = inetAddressIP;
	}
	
	public void run() {
		System.out.println("Starting Receiver...");
		try {
			socket = new MulticastSocket(multicastPort);
			System.out.println("socket " + socket);
			InetAddress inetAddress = InetAddress.getByName(inetAddressIP);
			System.out.println("inetAddress " + inetAddress);
			InetSocketAddress group = new InetSocketAddress(inetAddress, multicastPort);
			System.out.println("group " + group);
			NetworkInterface networkInterface = NetworkInterface.getByName(neteworkInterfaceName);
			System.out.println("networkInterface " + networkInterface);
			socket.joinGroup(group, networkInterface);
			while (true) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				String received = new String(
						packet.getData(), 0, packet.getLength());
				System.out.println(String.format("Received %s", received));
				if ("end".equals(received)) {
					break;
				}
			}
			socket.leaveGroup(group, networkInterface);
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String ...strings) {
		new MulticastReceiver("wlp0s20f3", "230.0.0.0").run();
	}

}
