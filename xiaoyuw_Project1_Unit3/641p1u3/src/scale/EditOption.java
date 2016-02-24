/*
 * Andrew ID: xiaoyuw
 */
package scale;

import adapter.*;

/**
 * An application thread definition. When it runs, it either edits option set
 * name or edit option price.
 */
public class EditOption extends Thread {
    private ProxyAutomobile pa;
    private int method;
    private String modelname;
    private String optionsetname;
    private String option;
    private String newname;
    private float newprice;
    private static int count = 0;
    private int num;

    /**
     * constructor for edit option price
     * 
     * @param pa
     *            a reference to ProxyAutomobile
     * @param method
     *            determine which method to run
     * @param modelname
     *            model name
     * @param optionsetname
     *            option set name
     * @param option
     *            option name
     * @param newprice
     *            new price
     */
    public EditOption(ProxyAutomobile pa, int method, String modelname,
	    String optionsetname, String option, float newprice) {
	this.pa = pa;
	this.method = method;
	this.modelname = modelname;
	this.optionsetname = optionsetname;
	this.option = option;
	this.newprice = newprice;
	count++;
	this.num = count;
    }

    /**
     * consructor for edit option set name
     * 
     * @param pa
     *            a reference to ProxyAutomobile
     * @param method
     *            determine which method to run
     * @param modelname
     *            model name
     * @param optionsetname
     *            option set name
     * @param newname
     *            new name
     */
    public EditOption(ProxyAutomobile pa, int method, String modelname,
	    String optionsetname, String newname) {
	this.pa = pa;
	this.method = method;
	this.modelname = modelname;
	this.optionsetname = optionsetname;
	this.newname = newname;
	count++;
	this.num = count;
    }

    @Override
    public void run() {
	switch (this.method) {
	case 1:
	    System.out.println("Thread " + num + " begins edit option set.");
	    editOptionSet(modelname, optionsetname, newname);
	    System.out.println("Thread " + num + " ends edit option set.");
	    break;
	case 2:
	    System.out.println("Thread " + num + " begins edit option price.");
	    editOption(modelname, optionsetname, option, newprice);
	    System.out.println("Thread " + num + " ends edit option price.");
	    break;
	default:
	    System.out.println("No such method!");
	    System.out.println("Thread " + num + " ends.");

	}

    }

    private void editOptionSet(String modelname, String optionsetname,
	    String newname) {

	pa.updateOptionSetName(modelname, optionsetname, newname);

    }

    private void editOption(String modelname, String optionsetname,
	    String option, float newprice) {

	pa.updateOptionPrice(modelname, optionsetname, option, newprice);

    }

}
