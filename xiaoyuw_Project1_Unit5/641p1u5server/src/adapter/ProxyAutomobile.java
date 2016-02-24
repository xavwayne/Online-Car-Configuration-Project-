/*
 * Andrew ID: xiaoyuw
 */
package adapter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import exception.AutoException;
import model.*;
import scale.EditOption;
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
    public void buildAuto(String filename, int filetype) {
	boolean flag = false;
	do {
	    try {
		// String suffix = filename.split("\\.")[1];
		a1 = new FileIO().readFileT(filename, filetype);
		fleet.addAuto(a1.getName(), a1);
		flag = true;
	    } catch (AutoException e) {

		int errno = e.getErrno();
		System.out.println(e.getErrMsg());
		e.log();
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
		e.log();
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
		e.log();
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
		e.log();
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
     * 
     * @param errno
     *            error number
     */
    public void fix(int errno) {
	AutoException ae = new AutoException();
	ae.fix(errno);
    }

    /**
     * Edit option set name in a thread
     * 
     * @param modelname
     *            model name
     * @param optionsetname
     *            option set name
     * @param newname
     *            new option set name
     */
    public void editOptionSet(String modelname, String optionsetname,
	    String newname) {
	Thread t = new EditOption(this, 1, modelname, optionsetname, newname);
	t.start();

    }

    /**
     * Edit option price in a thread
     * 
     * @param modelname
     *            model name
     * @param optionsetname
     *            option set name
     * @param option
     *            option name
     * @param newprice
     *            new price
     */
    public void editOption(String modelname, String optionsetname,
	    String option, float newprice) {
	Thread t = new EditOption(this, 2, modelname, optionsetname, option,
		newprice);
	t.start();

    }

    /**
     * add an automobile in the LHM
     * 
     * @param at
     *            automobile to be added
     */
    public void addAuto(Automobile at) {
	String autoname = at.getName();
	fleet.addAuto(autoname, at);
    }

    /**
     * build an automobile based on a properties object
     * 
     * @param prop
     *            properties object
     * @return an Automobile instance
     */
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

    /**
     * show all available autos in the fleet
     * 
     * @return a name list of autos
     */
    public String showAvailable() {
	String list;
	list = fleet.showAll();
	return list;
    }

    public void sendCar(ObjectInputStream in, ObjectOutputStream out) {
	try {
	    String choice = (String) in.readObject();
	    Automobile thecar = fleet.getAutomobile(choice);
	    out.writeObject(thecar);
	} catch (Exception e) {
	    Automobile thecar = new Automobile("No such car!", 0);
	    try {
		out.writeObject(thecar);
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
	}
    }
}
