/*
 * Andrew ID: xiaoyuw
 */
package server;

import java.io.*;
import java.net.*;
import java.util.*;
import exception.*;
import util.*;
import adapter.*;
import model.*;

/**
 * BuildCarModelOptions--Act like a server, taking requests from clients and
 * serving to clients
 */
public class BuildCarModelOptions implements AutoServer {

    
    private ServerSocket serversock = null;
    private DefaultSocketClient dsc = null;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ProxyAutomobile pa;

    /**
     * constructor--instantiate a server socket
     */
    public BuildCarModelOptions() {
	this.pa = new BuildAuto();
	try {
	    this.serversock = new ServerSocket(SocketClientConstants.TEST_PORT);
	} catch (IOException e) {
	    e.printStackTrace();
	    System.err.println("Could not listen on port: " + SocketClientConstants.TEST_PORT);
	    System.exit(1);
	}
    }

    /**
     * server take request from clients and handle it on a dependent thread
     */
    public void process() {
	try {
	    Socket clientsock = serversock.accept();
	    System.out.println("new connection!");
	    this.init(clientsock);
	    int method = 0;
	    try {
		method = (Integer) in.readObject();// parsing request head
	    } catch (ClassNotFoundException e) {
		e.printStackTrace();
	    }

	    dsc = new DefaultSocketClient(clientsock, this, in, out, method);
	    dsc.start();// start a new thread to process request
	} catch (IOException e) {
	    System.err.println("Accept failed.");
	    System.exit(1);
	}
    }

    public void init(Socket clientsock) throws IOException {

	out = new ObjectOutputStream(clientsock.getOutputStream());
	in = new ObjectInputStream(clientsock.getInputStream());

    }

    @Override
    public Automobile buildModel(Properties prop) {
	FileIO fio = new FileIO();
	Automobile at;
	try {
	    at = fio.readObj(prop);// create an mobile
	    return at;
	} catch (AutoException e) {
	    e.printStackTrace();
	    return null;
	}

    }

    @Override
    public void addAuto(Automobile at) {

	pa.addAuto(at);
    }

    @Override
    public String showAvailable() {
	return pa.showAvailable();
    }

    @Override
    public void sendCar(ObjectInputStream in, ObjectOutputStream out) {
	pa.sendCar(in, out);
    }
}
