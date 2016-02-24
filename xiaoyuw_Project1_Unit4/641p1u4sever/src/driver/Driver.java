/*
 * Andrew ID: xiaoyuw
 */
package driver;

import server.*;

/**
 * Driver class-- start a server
 */
public class Driver {

    public static void main(String args[]) {
    	
    	BuildCarModelOptions bcmo=new BuildCarModelOptions();
    	
    	while(true){
    		bcmo.process();
    	}

	
    }
}
