/*
 * Andrew ID: xiaoyuw
 */
package driver;

import java.io.*;
import client.*;

/**
 * Driver class-- start a client
 */
public class Driver {

    public static void main(String args[]) {
	CarModelOptionsIO cmo = new CarModelOptionsIO();
	SelectCarOption sco = new SelectCarOption();
	while (true) {
	    System.out.println("==============================");
	    System.out.println("1.Upload Properties file");
	    System.out.println("2.Configure a car");
	    BufferedReader rd = new BufferedReader(new InputStreamReader(
		    System.in));
	    String choice = null;
	    try {
		choice = rd.readLine();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    if (choice.equalsIgnoreCase("1")) {
		// create a thread to upload prop file
		cmo.process();
	    }
	    if (choice.equalsIgnoreCase("2")) {
		sco.showCars();
		sco.select();
		sco.printOptions();
		try {
		    sco.configure();
		} catch (Exception e) {
		    e.printStackTrace();
		}

	    }

	}

    }
}
