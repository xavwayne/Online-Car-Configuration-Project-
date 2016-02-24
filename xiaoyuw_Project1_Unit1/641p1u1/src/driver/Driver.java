/*
 * Andrew ID: xiaoyuw
 */
package driver;

import util.FileIO;
import model.*;

/**
 * Driver class for all tests, including tests for IO, serialization and
 * deserialization, delete, update and find by both index and name
 *
 */
public class Driver {

    public static void main(String[] args) {
	try {
	    // test for file IO, including serialization and deserialization
	    Automotive FordZTW = new FileIO().readFile("FordZTW.txt");
	    System.out.println("Test File IO:");
	    System.out.println("Befor serialization: ");
	    FordZTW.print();
	    System.out.println();
	    new FileIO().serializeAuto(FordZTW);
	    Automotive newFordZTW = new FileIO().deserializeAuto("auto.ser");
	    System.out.println("After deserialization:");
	    newFordZTW.print();
	    System.out.println();

	    // test for functions in Automotive. Since the Find functionality is
	    // wrapped in the Update function and it is protected, we can only
	    // test update and delete
	    // 

	    // test Update
	    System.out.println("Test update option set:");
	    FordZTW.upDateOptset("Transmission", "manual", "standard");
	    FordZTW.print();
	    System.out.println();

	    System.out.println("Test update option:");
	    FordZTW.upDateOpt("Transmission", "standard", 100);
	    FordZTW.print();
	    System.out.println();

	    // test Delete
	    System.out.println("Test delete option set by name:");
	    FordZTW.delOptset("Brakes/Traction Control");
	    FordZTW.print();
	    System.out.println();

	    System.out.println("Test delete option set by index:");
	    FordZTW.delOptset(0);
	    FordZTW.print();
	    System.out.println();

	    System.out.println("Test delete option by name:");
	    FordZTW.delOpt("automatic", "Transmission");
	    FordZTW.print();
	    System.out.println();

	    System.out.println("Test delete option by index:");
	    FordZTW.delOpt(0, "Power Moonroof");
	    FordZTW.print();
	    System.out.println();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
