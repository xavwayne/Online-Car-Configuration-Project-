/*
 * Andrew ID: xiaoyuw
 */
package model;

import java.io.Serializable;
import java.util.*;

import exception.AutoException;

/**
 * Optionset class definition, contains a number of options.
 *
 */
public class OptionSet implements Serializable {

    private static final long serialVersionUID = 2L;
    private String name;
    private Option choice = new Option();
    private ArrayList<Option> opt;

    /**
     * constructor
     * 
     * @param n
     *            name of the option set
     * @param size
     *            number of options
     */
    protected OptionSet(String n, int size) {
	this.name = n;
	this.opt = new ArrayList<Option>(size);
    }

    /**
     * constructor
     * 
     * @param n
     *            name of the option
     */
    protected OptionSet(String n) {
	this.name = n;
	this.opt = new ArrayList<Option>();
    }

    /*
     * getters and setters
     */
    protected String getName() {
	return name;
    }

    protected void setName(String name) {
	this.name = name;
    }

    protected ArrayList<Option> getOpt() {
	return opt;
    }

    protected Option getOpt(int index) throws AutoException {
	if (index < this.opt.size()) {
	    return opt.get(index);
	} else
	    throw new AutoException("IndexOutofBound");
    }

    protected Option getOpt(String opt){
	for(Option op:this.opt){
	    if(op.getName().equals(opt))
		return op;
	}
	return null;
    }
    
    protected void setOpt(ArrayList<Option> opt) {
	this.opt = opt;
    }

    protected void setOpt(int index, String name, float price) {

	if (index < this.opt.size())
	    this.opt.set(index, new Option(name, price));
	else
	    this.opt.add(new Option(name, price));
    }

    protected void setOpt(String name, float price) {
	this.opt.add(new Option(name, price));
    }

    protected void setOpt(String name) {
	this.opt.add(new Option(name));
    }

    protected Option getChoice() {
	return this.choice;
    }

    protected void setChoice(String choice) throws AutoException {
	for (Option x : this.opt) {
	    if (x.getName().equals(choice)) {
		this.choice = x;
		return;
	    }
	}
	throw new AutoException("MissingOption");
    }

    /**
     * Print information of this class
     */
    protected void print() {
	StringBuffer s = new StringBuffer();
	s.append(this.name);
	System.out.print(s);
	for (Option x : this.opt) {
	    if (x != null)
		x.print();
	}
	System.out.println();

    }

    /*
     * inner class Option for OptionSet
     */
    protected class Option implements Serializable {

	private static final long serialVersionUID = 3L;
	private String name;
	private float price;
	private float DEFAULTPRICE=0;

	protected Option(String name, float price) {
	    this.name = name;
	    this.price = price;
	}

	protected Option() {

	}

	protected Option(String name) {
	    this.name = name;
	    this.price=DEFAULTPRICE;
	}

	protected String getName() {
	    return name;
	}

	protected void setName(String name) {
	    this.name = name;
	}

	protected float getPrice() {
	    return price;
	}

	protected void setPrice(float price) {
	    this.price = price;
	}

	protected void print() {
	    StringBuffer s = new StringBuffer();
	    s.append("\t");
	    s.append(this.name);
	    s.append(",");
	    s.append(this.price);
	    System.out.print(s);
	}
    }

}
