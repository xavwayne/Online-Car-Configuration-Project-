/*
 * Andrew ID: xiaoyuw
 */
package model;

import java.io.*;

/**
 * Automotive class is the model of a car. It has name and base price, contains
 * a set of option set. It also provides some methods to set, update and delete
 * option sets and options.
 *
 */
public class Automotive implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private float baseprice;
    private OptionSet optset[];

    /**
     * constructor
     * 
     * @param n
     *            name of the car
     * @param baseprice
     *            base price
     * @param size
     *            number of option sets
     */
    public Automotive(String n, float baseprice, int size) {
	this.optset = new OptionSet[size];
	this.setName(n);
	this.setBaseprice(baseprice);
    }

    /*
     * getters
     */

    public String getName() {
	return name;
    }

    public float getBaseprice() {
	return baseprice;
    }

    public OptionSet getOptset(int index) {
	return optset[index];
    }

    public OptionSet[] getOptset() {
	return this.optset;
    }

    /*
     * Find
     */

    /**
     * Find option set by given a name
     * 
     * @param name
     *            option set name
     * @return the optionset if found, null otherwise
     */
    protected OptionSet findOptset(String name) {
	for (OptionSet x : optset) {
	    if (x != null && x.getName().equals(name)) {

		return x;
	    }
	}
	System.out.println("Optionset " + name + " not found");
	return null;
    }

    /**
     * Find option by given a name
     * 
     * @param name
     *            option name
     * @param optset
     *            in the context of a option set
     * @return the option if found, null otherwise
     */
    protected OptionSet.Option findOpt(String name, String optset) {
	OptionSet os = findOptset(optset);
	if (os != null) {
	    OptionSet.Option[] opts = os.getOpt();
	    for (OptionSet.Option x : opts) {
		if (x != null && x.getName().equals(name)) {

		    return x;
		}
	    }

	}
	System.out.println("Option " + name + " not found");
	return null;
    }

    /*
     * setters
     */
    public void setBaseprice(float baseprice) {
	this.baseprice = baseprice;
    }

    public void setName(String name) {
	this.name = name;
    }

    /**
     * Set an optionset
     * 
     * @param index
     *            the index of optset array
     * @param name
     *            the name of the oprionset
     * @param optsize
     *            num of options in the option set
     */
    public void setOptset(int index, String name, int optsize) {
	this.optset[index] = new OptionSet(name, optsize);

    }

    /**
     * Set an option
     * 
     * @param optset
     *            in the context of optionset
     * @param index
     *            the index of opt array in optionset
     * @param name
     *            name of option
     * @param price
     *            price of the option
     */
    public void setOpt(String optset, int index, String name, float price) {
	OptionSet os = findOptset(optset);
	if (os != null) {
	    os.setOpt(index, name, price);
	}
    }

    /*
     * Delete
     */

    /**
     * Delete an option by name
     * 
     * @param opt
     *            name of the option to be deleted
     * @param optset
     *            int the context of an optionset
     */
    public void delOpt(String opt, String optset) {
	OptionSet os = this.findOptset(optset);
	for (int i = 0; i < os.getOpt().length; i++) {
	    if (os.getOpt()[i].getName().equals(opt)) {
		os.getOpt()[i] = null;
		break;
	    }
	}
    }

    /**
     * Delete an option by index
     * 
     * @param index
     *            index of the opt in optionset
     * @param optset
     *            in the context of an optionset
     */
    public void delOpt(int index, String optset) {
	OptionSet os = this.findOptset(optset);
	os.getOpt()[index] = null;
    }

    /**
     * Delete an option set by name
     * 
     * @param optset
     *            name of the option set
     */
    public void delOptset(String optset) {
	for (int i = 0; i < this.optset.length; i++) {
	    if (this.optset[i].getName().equals(optset)) {
		this.optset[i] = null;
		break;
	    }
	}
    }

    /**
     * Delete an option set by index
     * 
     * @param index
     *            the index of the optset
     */
    public void delOptset(int index) {
	this.optset[index] = null;
    }

    /*
     * Update
     */
    /**
     * Update an option set. For example, when misspelling a name of an option
     * in a option set, we should modify the name
     * 
     * @param optset
     *            the name of the option set
     * @param optname
     *            the name of the option to be modified
     * @param newname
     *            the new name of the option
     */
    public void upDateOptset(String optset, String optname, String newname) {
	OptionSet os = this.findOptset(optset);
	if (os != null) {
	    for (OptionSet.Option x : os.getOpt()) {
		if (x.getName().equals(optname)) {
		    x.setName(newname);
		    break;
		}
	    }
	}
    }

    /**
     * Update an option. For example, when the price of a certain option has
     * changed, we should modify the price of the option
     * 
     * @param optset
     *            in the context of an option set
     * @param optname
     *            the name of the option
     * @param newprice
     *            new price for the option
     */
    public void upDateOpt(String optset, String optname, float newprice) {
	OptionSet.Option op = this.findOpt(optname, optset);
	if (op != null) {
	    op.setPrice(newprice);
	}
    }

    /**
     * Print information of the class
     */
    public void print() {
	StringBuffer s = new StringBuffer();
	s.append(this.name);
	s.append("\t");
	s.append(this.baseprice);
	System.out.println(s.toString());
	for (OptionSet x : this.optset) {
	    if (x != null)
		x.print();
	}
    }
}
