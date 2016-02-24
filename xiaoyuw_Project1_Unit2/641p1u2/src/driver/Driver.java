/*
 * Andrew ID: xiaoyuw
 */
package driver;

import exception.*;
import util.*;
import model.*;
import adapter.*;

/**
 * Driver class-- test the methods in interfaces including CreateAuto,
 * UpdateAuto. Additional test for recording option choice
 */
public class Driver {

    public static void main(String args[]) {

	String filename0 = "FordZTW.txt";
	String filename1 = "Porsche.txt";
	// test create
	System.out.println("Test Create:");

	CreateAuto ca = new BuildAuto();
	ca.buildAuto(filename0);
	ca.buildAuto(filename1);
	System.out.println("print out Focus Wagon ZTW...");
	ca.printAuto("Focus Wagon ZTW");
	System.out.println("Porsche 911s...");
	ca.printAuto("Porsche 911s");
	System.out.println();

	// test update
	System.out.println("Test Update:");
	UpdateAuto ua = new BuildAuto();
	ua.updateOptionSetName("Focus Wagon ZTW", "Color", "Vision");
	ua.updateOptionPrice("Porsche 911s", "Transmission", "automatic", 100);
	System.out.println("print out Focus Wagon ZTW...");
	ca.printAuto("Focus Wagon ZTW");
	System.out.println("Porsche 911s...");
	ca.printAuto("Porsche 911s");

	// Test for part B
	System.out.println("----------------------------------------");
	System.out.println("Test for record Option choice....");
	String filename2 = "FordZTW.txt";
	try {
	    Automobile at = new FileIO().readFile(filename2);
	    at.print();
	    System.out.println();
	    // set option choice
	    at.setOptionChoice("Transmission", "manual");
	    System.out.println("Transmission has been set:");
	    // get option choice for chosen optionset
	    System.out.println(at.getOptionChoice("Transmission"));
	    // if the optionset has not been set, return null
	    System.out.println("Brakes/Traction Control has NOT been set:");
	    System.out.println(at.getOptionChoice("Brakes/Traction Control"));
	    System.out.println();
	    at.setOptionChoice("Brakes/Traction Control", "ABS");
	    // get price for a optionset
	    System.out.println("Brakes/Traction Control has been set:");
	    System.out.println(at
		    .getOptionChoicePrice("Brakes/Traction Control"));
	    System.out.println();
	    float total = at.getTotalPrice();
	    System.out.println("Total price:" + total);

	} catch (AutoException e) {

	    System.out.println(e.getErrMsg());
	}

    }
}
