/*
 * Andrew ID: xiaoyuw
 */
package jdbc;

import java.sql.*;

import model.*;

/**
 * Create-- Create a database or insert data in the database
 */
public class Create extends JDBCConstants {

    private String query = null;
    private  boolean flag = false;
    /**
     * constructor
     */
    public Create() {
	super();
    }

    /**
     * create the database for car configuration
     */
    public void createSchema() {

	try {
	    // load driver
	    Class.forName("com.mysql.jdbc.Driver");
	    // get connection
	    Connection conn = DriverManager.getConnection(URL, USER, PSWD);
	    // create statement
	    Statement stat = conn.createStatement();
	    // create and execute query
	    query = command.getProperty("CreateSCH");
	    try {
		stat.execute(query);
	    } catch (Exception e) {
		System.out.println("car database already exist!");
		return;
	    }

	    query = command.getProperty("CreateTab_automobile");
	    stat.execute(query);
	    query = command.getProperty("CreateTab_optset");
	    stat.execute(query);
	    query = command.getProperty("CreateTab_opt");
	    stat.execute(query);

	    // close
	    stat.close();
	    conn.close();

	} catch (java.lang.Exception ex) {
	    ex.printStackTrace();
	}

    }

    /**
     * insert the data of an automobile in the database
     * 
     * @param at
     *            the automobile to be inserted
     */
    public void insert(Automobile at) {
	/*
	 * if (flag == false) { this.createSchema(); }
	 */

	try {
	    // load driver
	    Class.forName("com.mysql.jdbc.Driver");
	    // get connection
	    String url = URL + SCHEM;
	    Connection conn = null;
	    flag = false;
	    do {
		try {
		    conn = DriverManager.getConnection(url, USER, PSWD);
		    flag = true;
		} catch (SQLException e) {
		    System.out.println("Create database " + SCHEM + "...");
		    this.createSchema();
		}
	    } while (!flag);

	    // create statement
	    Statement stat = conn.createStatement();
	    // create and execute query

	    // insert in automobile table

	    String autoname = at.getName();
	    Float price = at.getBaseprice();
	    query = command.getProperty("Insert_to_automobile");
	    query = query.replace("%id", autoID.toString());
	    query = query.replace("%name", autoname);
	    query = query.replace("%price", price.toString());
	    stat.execute(query);

	    // insert in optionset table
	    String[] optionset = at.getOptsets();
	    String[] option;
	    for (String optset : optionset) {

		query = command.getProperty("Insert_to_optset");
		query = query.replace("%id", optsetID.toString());
		query = query.replace("%name", optset);
		query = query.replace("%autoid", autoID.toString());
		stat.execute(query);

		// insert in option table
		option = at.getOpts(optset);
		for (String opt : option) {
		    Float optprice = at.getOptPrice(optset, opt);
		    query = command.getProperty("Insert_to_opt");
		    query = query.replace("%id", optID.toString());
		    query = query.replace("%name", opt);
		    query = query.replace("%price", optprice.toString());
		    query = query.replace("%optsetid", optsetID.toString());
		    stat.execute(query);
		    optID++;
		}

		optsetID++;
	    }

	    autoID++;

	    // close
	    stat.close();
	    conn.close();

	} catch (Exception ex) {
	    ex.printStackTrace();
	}

    }
}
