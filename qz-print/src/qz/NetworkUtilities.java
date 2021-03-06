/**
 * @author Tres Finocchiaro
 *
 * Copyright (C) 2013 Tres Finocchiaro, QZ Industries
 *
 * IMPORTANT: This software is dual-licensed
 *
 * LGPL 2.1 This is free software. This software and source code are released
 * under the "LGPL 2.1 License". A copy of this license should be distributed
 * with this software. http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * QZ INDUSTRIES SOURCE CODE LICENSE This software and source code *may* instead
 * be distributed under the "QZ Industries Source Code License", available by
 * request ONLY. If source code for this project is to be made proprietary for
 * an individual and/or a commercial entity, written permission via a copy of
 * the "QZ Industries Source Code License" must be obtained first. If you've
 * obtained a copy of the proprietary license, the terms and conditions of the
 * license apply only to the licensee identified in the agreement. Only THEN may
 * the LGPL 2.1 license be voided.
 *
 */
package qz;

import java.net.SocketException;
import java.net.UnknownHostException;
import qz.reflection.ReflectException;

/**
 *
 * @author Tres
 */
public class NetworkUtilities {

    private String ipAddress;
    private String macAddress;
    private String hostname = "www.google.com";
    private int port = 80;
    PrintSpooler spooler;

    public NetworkUtilities(PrintSpooler spooler) throws SocketException, ReflectException, UnknownHostException {
        this.spooler = spooler;
    }
    
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    
    public void setPort(int port) {
        this.port = port;
    }

    public void gatherNetworkInfo() {
        LogIt.log("Initiating a temporary connection to \"" + hostname + ":" + 
                port + "\" to determine main Network Interface");
        
        NetworkInfoFinder finder = new NetworkInfoFinder(this, hostname, port);
        Thread finderThread = new Thread(finder);
        finderThread.start();
        
    }
    
    public void doneFindingNetworkInfo() {
        spooler.doneFindingNetworkInfo();
    }

    public String getHardwareAddress() {
        return this.macAddress;
    }

    public String getInetAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
