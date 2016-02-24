/*
 * Andrew ID: xiaoyuw
 */
package driver;

import scale.Scalable;
import adapter.*;

/**
 * Driver class-- test multithreading and synchronization for editing optionset
 * and option
 */
public class Driver {

    public static void main(String args[]) {

	String filename = "FordZTW.txt";
	CreateAuto ca = new BuildAuto();
	ca.buildAuto(filename);
	System.out.println("Print out Ford");
	ca.printAuto("Focus Wagon ZTW");
	// test multithreading
	System.out.println();
	System.out.println("test edit option in multithread");
	Scalable sc1 = new BuildAuto();
	Scalable sc2 = new BuildAuto();

	System.out.println("Editing option price...");

	sc1.editOption("Focus Wagon ZTW", "Transmission", "automatic", 100);
	sc2.editOption("Focus Wagon ZTW", "Transmission", "automatic", 50);

	// enough delay to finish the threads above
	try {
	    Thread.sleep(2000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	System.out.println("Editing option set name...");

	sc1.editOptionSet("Focus Wagon ZTW", "Transmission", "Gear");
	sc2.editOptionSet("Focus Wagon ZTW", "Gear", "Shift");

	// enough delay to finish the threads above
	try {
	    Thread.sleep(2000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	ca.printAuto("Focus Wagon ZTW");

    }
}
