/*
 * Andrew ID: xiaoyuw
 */
package model;

import java.util.*;

import exception.AutoException;

/**
 * Fleet class contains a LHM of automobiles. Complete with CRUD methods.
 */
public class Fleet {
    private LinkedHashMap<String, Automobile> autos;

    /**
     * Constructor
     */
    public Fleet() {
	this.autos = new LinkedHashMap<String, Automobile>();
    }

    /**
     * Add an automobile in the LHM
     * 
     * @param modelname
     *            automobile name
     * @param auto
     *            instance of an automobile
     */
    public void addAuto(String modelname, Automobile auto) {

	this.autos.put(modelname, auto);
    }

    /**
     * print out an automobile
     * 
     * @param modelname
     *            automobile name
     * @throws AutoException
     */
    public void printAuto(String modelname) throws AutoException {
	if (autos.containsKey(modelname)) {
	    Automobile at = autos.get(modelname);
	    at.print();
	} else {
	    throw new AutoException("NoSuchModel");
	}
    }

    /**
     * update Option set name in the context of an automobile and its optionset
     * 
     * @param modelname
     *            name of the automobile
     * @param optionsetname
     *            name of the optionset
     * @param newname
     *            new name
     * @throws AutoException
     */
    public Automobile updateOptionSetName(String modelname, String optionsetname,
	    String newname) throws AutoException {
	if (autos.containsKey(modelname)) {
	    Automobile at = autos.get(modelname);
	    at.updateOptsetName(optionsetname, newname);
	    return at;
	} else {
	    throw new AutoException("NoSuchModel");
	}
    }

    /**
     * Update option price in the context of an automobile and its optionset and
     * option
     * 
     * @param modelname
     *            mobile name
     * @param optionsetname
     *            option set name
     * @param option
     *            option name
     * @param newprice
     *            new price
     * @throws AutoException
     */
    public void updateOptionPrice(String modelname, String optionsetname,
	    String option, float newprice) throws AutoException {
	if (autos.containsKey(modelname)) {
	    Automobile at = autos.get(modelname);
	    at.upDateOpt(optionsetname, option, newprice);
	} else {
	    throw new AutoException("NoSuchModel");
	}

    }

    /**
     * Delete an automobile from the fleet
     * 
     * @param modelname
     *            mobile name
     * @throws AutoException
     */
    public Automobile deleteAutomobile(String modelname) throws AutoException {
	if (autos.containsKey(modelname)) {
	    return autos.remove(modelname);
	} else {
	    throw new AutoException("NoSuchModel");
	}

    }

    /**
     * get the reference of the automobile
     * 
     * @param modelname
     *            mobile name
     * @return reference of the mobile
     * @throws AutoException
     */
    public Automobile getAutomobile(String modelname) throws AutoException {
	if (autos.containsKey(modelname)) {
	    return autos.get(modelname);
	} else {
	    throw new AutoException("NoSuchModel");
	}
    }

    /**
     * show all automobiles in the LHM
     * 
     * @return a name list of automobiles
     */
    public String showAll() {
	String list = "";
	Set<String> all =  autos.keySet();
	for (String x : all) {
	    list += "\t" + x;
	}
	return list;
    }
}
