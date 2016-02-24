/*
 * Andrew ID: xiaoyuw
 */
package jdbc;

import java.sql.*;

/**
 * Info -- show the data in a table
 */

public class Info extends JDBCConstants {
    private String query;

    /**
     * show the data in the table
     * 
     * @param tablename
     *            the required table
     */
    public void showTable(String tablename) {

	try {
	    // load driver
	    Class.forName("com.mysql.jdbc.Driver");
	    // get connection
	    String url = URL + SCHEM;
	    Connection conn = DriverManager.getConnection(url, USER, PSWD);
	    // create statement
	    Statement stat = conn.createStatement();
	    // create and execute query
	    query = command.getProperty("Select_all") + tablename;
	    System.out.println(query);
	    ResultSet rs = stat.executeQuery(query);
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnCount = rsmd.getColumnCount();
	    while (rs.next()) {
		for (int i = 1; i <= columnCount; i++) {
		    if (i > 1)
			System.out.print(", ");
		    System.out.print(rs.getString(i));
		}
		System.out.println();
	    }
	    rs.close();
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
