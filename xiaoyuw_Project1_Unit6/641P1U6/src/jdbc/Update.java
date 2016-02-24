/*
 * Andrew ID: xiaoyuw
 */
package jdbc;

import java.sql.*;

/**
 * update data in the database
 */
public class Update extends JDBCConstants {

    private String query;

    /**
     * update option set name in the database
     * 
     * @param modelname
     *            model name
     * @param oldname
     *            old optionset name
     * @param newname
     *            new name for optionset
     */
    public void updateOptsetName(String modelname, String oldname,
	    String newname) {
	try {
	    // load driver
	    Class.forName("com.mysql.jdbc.Driver");
	    // get connection
	    String url = URL + SCHEM;
	    Connection conn = DriverManager.getConnection(url, USER, PSWD);
	    // create statement
	    Statement stat = conn.createStatement();
	    // create and execute query
	    query = command.getProperty("Select_autoid");
	    query = query.replace("%autoname", modelname);
	    ResultSet rs = stat.executeQuery(query);
	    Integer autoid = null;
	    if (rs.next()) {
		autoid = rs.getInt(1);
	    }

	    query = command.getProperty("Update_optsetname");
	    query = query.replace("%newname", newname);
	    query = query.replace("%oldname", oldname);
	    query = query.replace("%autoid", autoid.toString());
	    System.out.println(query);
	    stat.execute(query);
	    // close
	    rs.close();
	    stat.close();
	    conn.close();

	} catch (SQLException e) {
	    e.printStackTrace();
	    System.out.println("No such database!");
	    return;
	} catch (java.lang.Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * update option price in the database
     * 
     * @param modelname
     *            model name
     * @param optionsetname
     *            option set name
     * @param option
     *            option name
     * @param newprice
     *            new price for the option
     */
    public void updateOptionPrice(String modelname, String optionsetname,
	    String option, float newprice) {
	try {
	    // load driver
	    Class.forName("com.mysql.jdbc.Driver");
	    // get connection
	    String url = URL + SCHEM;
	    Connection conn = DriverManager.getConnection(url, USER, PSWD);
	    // create statement
	    Statement stat = conn.createStatement();
	    // create and execute query
	    query = command.getProperty("Select_autoid");
	    query = query.replace("%autoname", modelname);
	    ResultSet rs = stat.executeQuery(query);
	    Integer autoid = null;
	    if (rs.next()) {
		autoid = rs.getInt("AutoID");
	    }
	    rs.close();
	    query = command.getProperty("Select_optsetid");
	    query = query.replace("%optsetname", optionsetname);
	    query = query.replace("%autoid", autoid.toString());
	    rs = stat.executeQuery(query);
	    Integer optsetid = null;
	    if (rs.next()) {
		optsetid = rs.getInt("optsetID");
	    }
	    rs.close();
	    query = command.getProperty("Update_optprice");
	    Float newp = newprice;
	    query = query.replace("%newprice", newp.toString());
	    query = query.replace("%optsetid", optsetid.toString());
	    query = query.replace("%optname", option);

	    System.out.println(query);
	    stat.execute(query);
	    // close
	    stat.close();
	    conn.close();

	} catch (SQLException e) {
	    e.printStackTrace();
	    System.out.println("No such database!");
	    return;
	} catch (java.lang.Exception ex) {
	    ex.printStackTrace();
	}
    }
}
