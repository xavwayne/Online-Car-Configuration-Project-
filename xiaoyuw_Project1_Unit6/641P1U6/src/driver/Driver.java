/*
 * Andrew ID: xiaoyuw
 */
package driver;

import jdbc.*;
import adapter.*;

/**
 * Driver class-- test the Create, Update and Delete functionality with mysql
 * database
 */
public class Driver {

    public static void main(String args[]) {

	ProxyAutomobile ba = new BuildAuto();
	// test Create and Insert
	ba.buildAuto("FordZTW.txt", 1);
	System.out.println();
	Info info = new Info();
	info.showTable("automobile");
	System.out.println();
	info.showTable("optionset");
	System.out.println();
	info.showTable("options");
	System.out.println();

	// test Update
	// update option set name
	ba.updateOptionSetName("Focus Wagon ZTW", "Color", "Vision");
	System.out.println();
	info.showTable("optionset");
	System.out.println();

	// update option price
	ba.updateOptionPrice("Focus Wagon ZTW", "Transmission", "manual", 100);
	System.out.println();
	info.showTable("options");
	System.out.println();

	// test Delete
	ba.deleteAuto("Focus Wagon ZTW");
	System.out.println();
	info.showTable("automobile");
	System.out.println();
	info.showTable("optionset");
	System.out.println();
	info.showTable("options");
	System.out.println();

    }
}
