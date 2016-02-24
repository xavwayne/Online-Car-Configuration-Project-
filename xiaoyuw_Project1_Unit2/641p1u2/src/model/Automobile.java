/*
 * Andrew ID: xiaoyuw
 */
package model;

import java.io.*;
import java.util.*;

import model.OptionSet.Option;
import exception.*;

/**
 * Automotive class is the model of a car. It has name and base price, contains
 * a set of option set. It also provides some methods to set, update and delete
 * option sets and options.
 *
 */
public class Automobile implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String make;
    private String model;
    private float baseprice;
    private ArrayList<OptionSet> optset;
    private ArrayList<Option> opt = new ArrayList<Option>();

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
    public Automobile(String n, float baseprice, int size) {
	this.optset = new ArrayList<OptionSet>(size);
	this.setName(n);
	this.setBaseprice(baseprice);
    }

    /*
     * getters
     */

    public String getName() {
	return name;
    }

    public String getMake() {
	return make;
    }

    public String getModel() {
	return model;
    }

    public float getBaseprice() {
	return baseprice;
    }

    public OptionSet getOptset(int index) {
	return optset.get(index);
    }

    public ArrayList<OptionSet> getOptset() {
	return this.optset;
    }

    public String getOptionChoice(String setname) throws AutoException {
	OptionSet os = this.findOptset(setname);
	Option op = os.getChoice();
	if (op != null)
	    return op.getName();
	else
	    return null;
    }

    public float getOptionChoicePrice(String setname) throws AutoException {
	OptionSet os = this.findOptset(setname);
	Option choice = os.getChoice();
	return choice.getPrice();
    }

    public float getTotalPrice() {
	float total = this.baseprice;
	for (Option x : this.opt) {
	    total += x.getPrice();
	}
	return total;
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
     * @throws AutoException
     */
    protected OptionSet findOptset(String name) throws AutoException {

	for (OptionSet x : optset) {
	    if (x != null && x.getName().equals(name)) {

		return x;
	    }
	}

	throw new AutoException("MissingOptionSet");

    }

    /**
     * Find option by given a name
     * 
     * @param name
     *            option name
     * @param optset
     *            in the context of a option set
     * @return the option if found, null otherwise
     * @throws AutoException
     */
    protected OptionSet.Option findOpt(String name, String optset)
	    throws AutoException {
	OptionSet os = findOptset(optset);
	if (os != null) {
	    ArrayList<Option> opts = os.getOpt();
	    for (OptionSet.Option x : opts) {
		if (x != null && x.getName().equals(name)) {
		    return x;
		}
	    }

	}
	throw new AutoException("MissingOption");
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

    public void setMake(String make) {
	this.make = make;
    }

    public void setModel(String model) {
	this.model = model;
    }

    public void setOptionChoice(String setname, String optname)
	    throws AutoException {
	OptionSet os = this.findOptset(setname);
	os.setChoice(optname);
	Option choice = os.getChoice();
	this.opt.add(choice);
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

	if (index < this.optset.size())
	    this.optset.set(index, new OptionSet(name, optsize));
	else
	    this.optset.add(new OptionSet(name, optsize));

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
     * @throws AutoException
     */
    public void setOpt(String optset, int index, String name, float price)
	    throws AutoException {
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
     * @throws AutoException
     */
    public void delOpt(String opt, String optset) throws AutoException {
	OptionSet os = this.findOptset(optset);
	for (Option x : os.getOpt()) {
	    if (x.getName().equals(opt)) {
		os.getOpt().remove(x);
		return;
	    }
	}
	throw new AutoException("MissingOption");
    }

    /**
     * Delete an option by index
     * 
     * @param index
     *            index of the opt in optionset
     * @param optset
     *            in the context of an optionset
     * @throws AutoException
     */
    public void delOpt(int index, String optset) throws AutoException {
	OptionSet os = this.findOptset(optset);
	if (index < os.getOpt().size()) {
	    os.getOpt().remove(index);
	} else {
	    throw new AutoException("IndexOutofBound");
	}
    }

    /**
     * Delete an option set by name
     * 
     * @param optset
     *            name of the option set
     * @throws AutoException
     */
    public void delOptset(String optset) throws AutoException {
	for (OptionSet x : this.optset) {
	    if (x.getName().equals(optset)) {
		this.optset.remove(x);
		return;
	    }
	}
	throw new AutoException("MissingOptionSet");
    }

    /**
     * Delete an option set by index
     * 
     * @param index
     *            the index of the optset
     * @throws AutoException
     */
    public void delOptset(int index) throws AutoException {
	if (index < this.optset.size()) {
	    this.optset.remove(index);
	} else {
	    throw new AutoException("IndexOutofBound");
	}
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
     * @throws AutoException
     */
    public void upDateOptset(String optset, String optname, String newname)
	    throws AutoException {
	OptionSet os = this.findOptset(optset);
	if (os != null) {
	    for (OptionSet.Option x : os.getOpt()) {
		if (x.getName().equals(optname)) {
		    x.setName(newname);
		    return;
		}
	    }
	}
	throw new AutoException("MissingOption");
    }

    /**
     * Update the name of an optionset,i.e. rename the optionset
     * 
     * @param optset
     *            the optionset to be rename
     * @param newname
     *            the new name of the optionset
     * @throws AutoException
     */
    public void updateOptsetName(String optset, String newname)
	    throws AutoException {
	OptionSet os = this.findOptset(optset);
	if (os != null) {
	    os.setName(newname);
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
     * @throws AutoException
     */
    public void upDateOpt(String optset, String optname, float newprice)
	    throws AutoException {
	OptionSet.Option op = this.findOpt(optname, optset);
	if (op != null) {
	    op.setPrice(newprice);
	}
    }

    /**
     * Update an option by index
     * 
     * @param optset
     *            in the contex of option set
     * @param index
     *            index of option
     * @param newprice
     *            new price of the opiton
     * @throws AutoException
     */
    public void upDateOpt(String optset, int index, float newprice)
	    throws AutoException {
	OptionSet os = this.findOptset(optset);
	os.getOpt(index).setPrice(newprice);
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
