/*
 * Andrew ID: xiaoyuw
 */
package adapter;

import exception.AutoException;
import model.*;
import util.*;

/**
 * Abstract class ProxyAutomobile provides all implementation of methods in the
 * interface
 */
public abstract class ProxyAutomobile {

    private static Automobile a1;
    private static Fleet fleet = new Fleet();// Singleton instance, contain a
					     // LHM of automobiles

    /**
     * create an instance of automobile and add it in the Fleet
     * 
     * @param filename
     *            source data file
     */
    public void buildAuto(String filename) {
	boolean flag = false;
	do {
	    try {
		a1 = new FileIO().readFile(filename);
		fleet.addAuto(a1.getName(), a1);
		flag = true;
	    } catch (AutoException e) {

		int errno = e.getErrno();
		System.out.println(e.getErrMsg());
		filename = e.fix(errno);
	    }
	} while (!flag);
    }

    /**
     * print out an automobile according to the given name
     * 
     * @param autoname
     *            automobile name
     */
    public void printAuto(String autoname) {
	boolean flag = false;
	do {

	    try {
		fleet.printAuto(autoname);
		flag = true;

	    } catch (AutoException e) {

		int errno = e.getErrno();
		System.out.println(e.getErrMsg());
		autoname = e.fix(errno);
	    }
	} while (!flag);
    }

    /**
     * rename an optionset of the automobile
     * 
     * @param modelname
     *            the mobile to be updated
     * @param optionsetname
     *            the name of option set to be rename
     * @param newname
     *            new name of option set
     */
    public void updateOptionSetName(String modelname, String optionsetname,
	    String newname) {
	boolean flag = false;
	do {
	    try {
		fleet.updateOptionSetName(modelname, optionsetname, newname);
		flag = true;

	    } catch (AutoException e) {
		int errno = e.getErrno();
		System.out.println(e.getErrMsg());
		if (errno == 2)
		    optionsetname = e.fix(errno);
		if (errno == 5)
		    modelname = e.fix(errno);
	    }
	} while (!flag);

    }

    /**
     * Update the price of an option in the context of a certain mobile and its
     * option set
     * 
     * @param modelname
     *            name of the mobile
     * @param optionsetname
     *            name of the opiton set
     * @param option
     *            name of the option
     * @param newprice
     *            new price
     */
    public void updateOptionPrice(String modelname, String optionsetname,
	    String option, float newprice) {
	boolean flag = false;
	do {
	    try {
		fleet.updateOptionPrice(modelname, optionsetname, option,
			newprice);
		flag = true;

	    } catch (AutoException e) {
		int errno = e.getErrno();
		System.out.println(e.getErrMsg());
		if (errno == 2)
		    optionsetname = e.fix(errno);
		if (errno == 3)
		    option = e.fix(errno);
		if (errno == 5)
		    modelname = e.fix(errno);
	    }
	} while (!flag);
    }

    /**
     * fix problems based on error number
     * @param errno error number
     */ 
    public void fix(int errno) {
	AutoException ae = new AutoException();
	ae.fix(errno);
    }
}
