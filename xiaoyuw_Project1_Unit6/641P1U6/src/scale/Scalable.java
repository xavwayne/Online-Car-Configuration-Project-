/*
 * Andrew ID: xiaoyuw
 */
package scale;

/**
 * interface that expose the methods in ProxyAutomobile to edit option set name
 * or option price in a thread
 */
public interface Scalable {

    public void editOptionSet(String modelname, String optionsetname,
	    String newname);

    public void editOption(String modelname, String optionsetname,
	    String option, float newprice);
}
