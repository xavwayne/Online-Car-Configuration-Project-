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
 * The CRUD methods in this class should be synchronized, because an automobile
 * object is shared by multiple threads. Modifying shared data in multiple
 * threads can cause data corruption.
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

	/**
	 * constructor
	 * 
	 * @param n
	 *            name of the car
	 * @param baseprice
	 *            base price
	 */
	public Automobile(String n, float baseprice) {
		this.optset = new ArrayList<OptionSet>();
		this.setName(n);
		this.setBaseprice(baseprice);
	}

	/**
	 * constructor
	 * 
	 * @param n
	 *            name of the car
	 */
	public Automobile(String n) {
		this.optset = new ArrayList<OptionSet>();
		this.setName(n);
	}

	/*
	 * getters
	 */

	public synchronized String getName() {
		return name;
	}

	public synchronized String getMake() {
		return make;
	}

	public synchronized String getModel() {
		return model;
	}

	public synchronized float getBaseprice() {
		return baseprice;
	}

	public synchronized OptionSet getOptset(int index) {
		return optset.get(index);
	}

	public synchronized ArrayList<OptionSet> getOptset() {
		return this.optset;
	}

	public String[] getOptsets() {
		String[] sets = new String[this.optset.size()];
		for (int i = 0; i < sets.length; i++) {
			sets[i] = this.optset.get(i).getName();
		}
		return sets;

	}

	public String[] getOpts(String optset) throws AutoException {
		OptionSet os = this.findOptset(optset);

		if (os != null) {
			String[] opts = new String[os.getOpt().size()];
			for (int i = 0; i < opts.length; i++) {
				opts[i] = os.getOpt().get(i).getName();
			}
			return opts;
		}
		return null;
	}

	public synchronized String getOptionChoice(String setname) throws AutoException {
		OptionSet os = this.findOptset(setname);
		Option op = os.getChoice();
		if (op != null)
			return op.getName();
		else
			return null;
	}

	public synchronized float getOptionChoicePrice(String setname) throws AutoException {
		OptionSet os = this.findOptset(setname);
		Option choice = os.getChoice();
		return choice.getPrice();
	}

	public synchronized float getTotalPrice() {
		float total = this.baseprice;
		for (Option x : this.opt) {
			total += x.getPrice();
		}
		return total;
	}

	public synchronized void displayOptionChoice() {
		System.out.println(this.name);
		for (Option x : this.opt) {
			System.out.println(x.getName() + " " + x.getPrice());
		}
		System.out.println("Total price: " + this.getTotalPrice());
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
	protected OptionSet.Option findOpt(String name, String optset) throws AutoException {
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
	public synchronized void setBaseprice(float baseprice) {
		this.baseprice = baseprice;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	public synchronized void setMake(String make) {
		this.make = make;
	}

	public synchronized void setModel(String model) {
		this.model = model;
	}

	public synchronized void setOptionChoice(String setname, String optname) throws AutoException {

		boolean mark = false;
		String oldchoice = null;
		if ((oldchoice = this.getOptionChoice(setname)) == null)
			mark = false;// not yet set
		else if (oldchoice.equals(optname))
			mark = true; // have been set

		OptionSet os = this.findOptset(setname);
		os.setChoice(optname);
		Option choice = os.getChoice();

		if (mark == false) {

			this.opt.add(choice);
		} else {

		}
	}

	/**
	 * Set an optionset
	 * 
	 * @param index
	 *            the index of optset array
	 * @param name
	 *            the name of the optionset
	 * @param optsize
	 *            num of options in the option set
	 */
	public synchronized void setOptset(int index, String name, int optsize) {

		if (index < this.optset.size())
			this.optset.set(index, new OptionSet(name, optsize));
		else
			this.optset.add(new OptionSet(name, optsize));

	}

	/**
	 * Set an Optionset
	 * 
	 * @param name
	 *            the name of the optionset
	 */
	public synchronized void setOptset(String name) {
		this.optset.add(new OptionSet(name));
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
	public synchronized void setOpt(String optset, int index, String name, float price) throws AutoException {
		OptionSet os = findOptset(optset);
		if (os != null) {
			os.setOpt(index, name, price);
		}
	}

	/**
	 * Set an option
	 * 
	 * @param optset
	 *            in the context of optionset
	 * @param name
	 *            name of option
	 * @param price
	 *            price of the option
	 * @throws AutoException
	 */
	public synchronized void setOpt(String optset, String name, float price) throws AutoException {
		OptionSet os = findOptset(optset);
		if (os != null) {
			os.setOpt(name, price);
		}
	}

	/**
	 * Set an option
	 * 
	 * @param optset
	 *            in the context of optionset
	 * @param name
	 *            name of option
	 * 
	 * @throws AutoException
	 */
	public synchronized void setOpt(String optset, String name) throws AutoException {
		OptionSet os = findOptset(optset);
		if (os != null) {
			os.setOpt(name);
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
	public synchronized void delOpt(String opt, String optset) throws AutoException {
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
	public synchronized void delOpt(int index, String optset) throws AutoException {
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
	public synchronized void delOptset(String optset) throws AutoException {
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
	public synchronized void delOptset(int index) throws AutoException {
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
	public synchronized void upDateOptset(String optset, String optname, String newname) throws AutoException {
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
	public synchronized void updateOptsetName(String optset, String newname) throws AutoException {
		this.randomWait();
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
	public synchronized void upDateOpt(String optset, String optname, float newprice) throws AutoException {
		this.randomWait();
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
	public synchronized void upDateOpt(String optset, int index, float newprice) throws AutoException {
		OptionSet os = this.findOptset(optset);
		os.getOpt(index).setPrice(newprice);
	}

	/**
	 * Print information of the class
	 */
	public synchronized void print() {
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

	/**
	 * helper function to simulate the small and random delay in manipulating
	 * data
	 */
	private void randomWait() {
		try {
			Thread.sleep((long) (Math.random() * 10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
