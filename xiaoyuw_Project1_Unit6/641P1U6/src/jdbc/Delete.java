/*
 * Andrew ID: xiaoyuw
 */
package jdbc;

import java.sql.*;

import model.*;

/**
 * Delete -- delete an automobile from the database
 */
public class Delete extends JDBCConstants {

    private String query;

    /**
     * delete an automobile from the database
     * 
     * @param at
     *            the automobile to be deleted
     */
    public void delete(Automobile at) {
	try {
	    // load driver
	    Class.forName("com.mysql.jdbc.Driver");
	    // get connection
	    String url = URL + SCHEM;
	    Connection conn = DriverManager.getConnection(url, USER, PSWD);
	    // create statement
	    Statement stat = conn.createStatement();
	    // create and execute query
	    String autoname = at.getName();
	    query = command.getProperty("Del_auto");
	    query = query.replace("%autoname", autoname);
	    System.out.println(query);
	    stat.execute(query);
	    // close
	    stat.close();
	    conn.close();

	} catch (SQLException e) {
	    System.out.println("No such database!");
	    return;
	} catch (java.lang.Exception ex) {
	    ex.printStackTrace();
	}
    }
}
