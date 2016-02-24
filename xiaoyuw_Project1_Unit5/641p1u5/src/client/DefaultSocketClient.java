/*
 * Andrew ID: xiaoyuw
 */
package client;

import java.io.*;
import java.net.*;

/**
 * DefaultSocketClient--start a client-server connection on its own thread
 */
public class DefaultSocketClient extends Thread implements
	SocketClientInterface, SocketClientConstants {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket sock;
    private String host;
    private int port;
    private CarModelOptionsIO cmo;
    private SelectCarOption sco;
    private int method;

    /**
     * constructor
     * 
     * @param host
     *            host name
     * @param port
     *            port number
     * @param cmo
     *            CarModelOptionsIO instance
     * @param method
     *            method number
     */
    public DefaultSocketClient(String host, int port, CarModelOptionsIO cmo,
	    int method) {
	this.host = host;
	this.port = port;
	this.cmo = cmo;
	this.method = method;
    }

    /**
     * constructor
     * 
     * @param host
     *            host name
     * @param port
     *            port number
     * @param sco
     *            SelectCarOption instance
     * @param method
     *            method number
     */
    public DefaultSocketClient(String host, int port, SelectCarOption sco,
	    int method) {
	this.host = host;
	this.port = port;
	this.sco = sco;
	this.method = method;
    }

    public void run() {
	if (openConnection()) {
	    handleSession();
	    closeSession();
	}
    }

    /**
     * open connection to server
     * 
     * @return true when ok;false otherwise
     */
    @Override
    public boolean openConnection() {
	try {
	    sock = new Socket(this.host, this.port);
	    out = new ObjectOutputStream(sock.getOutputStream());
	    in = new ObjectInputStream(sock.getInputStream());
	    return true;
	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println("Can not connect to server!");
	    return false;
	}
    }

    /**
     * handle request at client side
     */
    @Override
    public void handleSession() {
	switch (method) {
	case 1:
	    try {
		transfer();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    break;
	case 2:
	    try {
		out.writeObject(new Integer(2));
		String models = (String) in.readObject();
		sco.setCars(models);
		System.out.println(models);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    break;
	case 3:
	    try {
		sco.select(in, out);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    break;

	default:
	    System.out.println("No such method!");
	}

    }

    /**
     * close connection
     */
    @Override
    public void closeSession() {
	try {
	    out = null;
	    in = null;
	    sock.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void transfer() throws Exception {
	cmo.transfer(out, in);
    }

}
