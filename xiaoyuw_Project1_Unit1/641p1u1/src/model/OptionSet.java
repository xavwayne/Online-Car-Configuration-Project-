/*
 * Andrew ID: xiaoyuw
 */
package model;

import java.io.Serializable;

/**
 * Optionset class definition, contains a number of options.
 *
 */
public class OptionSet implements Serializable {

    private static final long serialVersionUID = 2L;
    private String name;
    private Option opt[];

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
	this.opt = new Option[size];
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

    protected Option[] getOpt() {
	return opt;
    }

    protected void setOpt(Option opt[]) {
	this.opt = opt;
    }

    protected void setOpt(int index, String name, float price) {
	this.opt[index] = new Option(name, price);
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

	protected Option(String name, float price) {
	    this.name = name;
	    this.price = price;
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
