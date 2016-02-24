/*
 * Andrew ID: xiaoyuw
 */
package adapter;

/**
 * Interface to update information of an automobile
 */
public interface UpdateAuto {

    public void updateOptionSetName(String modelname, String optionsetname,
	    String newname);

    public void updateOptionPrice(String modelname, String optionname,
	    String option, float newprice);
}
