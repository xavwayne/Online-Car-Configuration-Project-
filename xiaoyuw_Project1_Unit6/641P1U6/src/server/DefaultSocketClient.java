/*
 * Andrew ID: xiaoyuw
 */
package server;

import java.io.*;
import java.net.*;
import java.util.*;

import util.FileIO;
import model.*;

/**
 * DefaultSocketClient--serve the request from clients on its own thread
 */
public class DefaultSocketClient extends Thread implements
	SocketClientInterface, SocketClientConstants {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket clientsock;
    private int method;
    private AutoServer server;

    /*
     * constructor
     */
    public DefaultSocketClient(Socket clientsock, AutoServer server,
	    ObjectInputStream in, ObjectOutputStream out, int method) {
	this.clientsock = clientsock;
	this.server = server;
	this.in = in;
	this.out = out;
	this.method = method;
    }

    public void run() {
	if (openConnection()) {
	    handleSession();
	    closeSession();
	    System.out.println("Connection end normally!");
	}
    }

    /**
     * check connection
     * 
     * @return true when connected; false otherwise
     */
    @Override
    public boolean openConnection() {
	if (this.in != null && this.out != null)
	    return true;
	else
	    return false;
    }

    /**
     * build read data from client and build an automobile
     * 
     * @throws Exception
     */
    @Override
    public void handleSession() {
	try {
	    switch (method) {
	    case 1: // build an auto and add it to LHM
		Automobile at = null;
		int filetype = (Integer) in.readObject();
		if (filetype == 2) {// properties object
		    Properties prop = (Properties) ((ObjectInputStream) in)
			    .readObject();
		    at = server.buildModel(prop); // create a model
		}
		if (filetype == 1) {// String object of the old txt file
		    String txt = (String) in.readObject();
		    byte[] buf = txt.getBytes();
		    File tmp = new File("tmp.txt");
		    FileOutputStream fos = new FileOutputStream(tmp);
		    fos.write(buf);
		    fos.close();
		    at = new FileIO().readFileT("tmp.txt", 1);
		    tmp.delete();
		}
		server.addAuto(at);// add model to the LHM
		out.writeObject("Srever: Car Model " + at.getName()
			+ " Created Successfully!");
		break;
	    case 2:// show a list of available cars
		String list = server.showAvailable();
		out.writeObject(list);
		break;
	    case 3:// send a instance of the selected car to the client
		server.sendCar(in, out);
		break;
	    default:
		System.out.println("No such method!");
	    }
	} catch (Exception e) {
	    try {
		out.writeObject("Handle failed!");
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }

	}

    }

    /**
     * close connection to a client
     */
    @Override
    public void closeSession() {
	try {

	    in = null;
	    out = null;
	    clientsock.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
