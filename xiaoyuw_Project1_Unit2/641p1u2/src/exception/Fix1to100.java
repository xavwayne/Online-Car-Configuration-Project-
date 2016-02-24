/*
 * Andrew ID: xiaoyuw
 */
package exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Delegete class to fix exception with number range of 1-100
 */
public class Fix1to100 {

    /**
     * Fix missing price
     * 
     * @param errno
     *            error number
     * @return fix result
     */
    public String fix1(int errno) {
	System.out.println("Fixing...");
	System.out.println("Please enter a price:");
	String price = null;
	BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	try {
	    price = rd.readLine();
	    // Important: System.in should not be closed!! i.e. rd.close() is
	    // fatal!
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return price;
    }

    /**
     * Fix missing option set
     * 
     * @param errno
     *            error number
     * @return fix result
     */
    public String fix2(int errno) {
	System.out.println("Fixing...");
	System.out.println("Please retype option set name:");
	String optset = null;
	BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	try {
	    optset = rd.readLine();

	} catch (IOException e) {
	    e.printStackTrace();
	}
	return optset;
    }

    /**
     * Fix missing option
     * 
     * @param errno
     *            error number
     * @return fix result
     */
    public String fix3(int errno) {
	System.out.println("Fixing...");
	System.out.println("Please retype option  name:");
	String opt = null;
	BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	try {
	    opt = rd.readLine();

	} catch (IOException e) {
	    e.printStackTrace();
	}
	return opt;
    }

    /**
     * Fix invalid file name
     * 
     * @param errno
     *            error number
     * @return fix result
     */
    public String fix4(int errno) {
	System.out.println("Fixing...");
	System.out.println("Please retype file name:");
	String filename = null;
	BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	try {
	    filename = rd.readLine();

	} catch (IOException e) {
	    e.printStackTrace();
	}
	return filename;
    }

    /**
     * Fix wrong model name
     * 
     * @param errno
     *            error number
     * @return fix result
     */
    public String fix5(int errno) {
	System.out.println("Fixing...");
	System.out.println("Please retype model name:");
	String model = null;
	BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	try {
	    model = rd.readLine();

	} catch (IOException e) {
	    e.printStackTrace();
	}
	return model;
    }

    /**
     * Fix file format error
     * 
     * @param errno
     *            error number
     * @return fix result
     */
    public String fix6(int errno) {
	System.out.println("Fixing...");
	System.out.println("Check integrity of file!");
	System.out.println("Please retype file name:");
	String filename = null;
	BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	try {
	    filename = rd.readLine();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return filename;
    }
}
