/*
 * Andrew ID: xiaoyuw
 */
package jdbc;

import java.sql.*;
import java.util.*;

import exception.*;
import util.FileIO;

/**
 * Constants for JDBC
 */
public abstract class JDBCConstants {

    protected static final String URL = "jdbc:mysql://127.0.0.1:3306/";
    protected static final String SCHEM = "car";
    protected static final String USER = "root";
    protected static final String PSWD = "wxywxywxy";    

    protected static Integer autoID = 1;
    protected static Integer optsetID = 1;
    protected static Integer optID = 1;

    protected Properties command;

    /**
     * constructor
     */
    public JDBCConstants() {
	// connect to database to deternine the initial value of the primary key

	try {
	    command = new FileIO().loadFile("sql_command.txt");
	} catch (AutoException e1) {
	    e1.printStackTrace();
	}
	String query;
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    String url = URL + SCHEM;
	    Connection conn = null;

	    try {
		conn = DriverManager.getConnection(url, USER, PSWD);

	    } catch (SQLException e) {
		return;// if the database does not exist, then do nothing;else,
		       // determine all the pk
	    }
	    Statement stat = conn.createStatement();
	    query = command.getProperty("Select_max_autoid");
	    ResultSet rs = stat.executeQuery(query);
	    if (rs.next()) {
		autoID = rs.getInt(1) + 1;
	    }
	    rs.close();

	    query = command.getProperty("Select_max_optsetid");
	    rs = stat.executeQuery(query);
	    if (rs.next()) {
		optsetID = rs.getInt(1) + 1;
	    }
	    rs.close();

	    query = command.getProperty("Select_max_optid");
	    rs = stat.executeQuery(query);
	    if (rs.next()) {
		optID = rs.getInt(1) + 1;
	    }
	    rs.close();

	    stat.close();
	    conn.close();

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
