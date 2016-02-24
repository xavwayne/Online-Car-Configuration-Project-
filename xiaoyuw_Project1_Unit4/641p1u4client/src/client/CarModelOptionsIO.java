/*
 * Andrew ID: xiaoyuw
 */
package client;

import java.io.*;
import java.net.*;
import java.util.*;

import adapter.*;
import util.*;

/**
 * CarModelOptionsIO-- Upload a properties file from client to server and build
 * an automobile at server side, receiving a verification of success
 */
public class CarModelOptionsIO {


    /**
     * Create a new thread to transfer file from client to server
     */
    public void process() {
	String HOST = null;
	try {
	    HOST = InetAddress.getLocalHost().getHostName();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	}
	DefaultSocketClient dsc = new DefaultSocketClient(HOST, SocketClientConstants.TEST_PORT, this,
		1);
	dsc.start();

	try {
	    dsc.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    /**
     * transfer the object
     * 
     * @param out
     *            outputStream
     * @param in
     *            inputStream
     * @throws Exception
     */
    public void transfer(ObjectOutputStream out, ObjectInputStream in)
	    throws Exception {
	boolean flag = false;
	String filename = null;
	int filetype = 0;
	Properties prop = null;
	do {
	    System.out.println("Please provide a file/filetype:  ");
	    BufferedReader rd = new BufferedReader(new InputStreamReader(
		    System.in));
	    String nametype = rd.readLine();
	    filename = nametype.split("/")[0];
	    filetype = Integer.parseInt(nametype.split("/")[1]);

	    if (filetype == 2) {// properties file
		System.out.println(filename + " loading...");
		try {
		    prop = new FileIO().loadFile(filename);
		} catch (Exception e) {
		    System.out.println("No such file!");
		    continue;
		}
		System.out.println(filename + " loaded");

		out.writeObject(new Integer(1)); // request head
		out.writeObject(new Integer(2)); //request body:filetype
		out.writeObject(prop); // request body: file
	    }
	    if (filetype == 1) {//old txt file
	        out.writeObject(new Integer(1));//head
	        out.writeObject(new Integer(1));//body:type
	        File file=new File(filename);
	        long len=file.length();
	        byte[] buf=new byte[4096];
	        FileInputStream fis=new FileInputStream(file);
	        fis.read(buf, 0, (int) len);	        
	        byte[] buft=Arrays.copyOf(buf, (int)len);	        
	        String s=new String(buft);
	        out.writeObject(s);//body:file
	        fis.close();

	    }
	    System.out.println(filename + " sent");
	    flag = true;
	} while (!flag);

	this.verify(in);
    }

    /**
     * get a message from server to verify that the car model is created at
     * server
     * 
     * @param in
     *            inputStream
     * @throws Exception
     */
    private void verify(ObjectInputStream in) throws Exception {
	String msg = (String) in.readObject();
	System.out.println(msg);
    }

    /**
     * build an automobile based on given file
     * 
     * @param filename
     *            filename
     * @param filetype
     *            tiletype
     */
    public void buildAuto(String filename, int filetype) {
	CreateAuto ca = new BuildAuto();
	ca.buildAuto(filename, filetype);

    }
}
