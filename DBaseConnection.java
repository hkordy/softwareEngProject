package MySQLDemo;

// -- download MySQL from: http://dev.mysql.com/downloads/
//    Community Server version
//    depending on the package installer, you may need to install MySQL Workbench separately
//
// -- Installation instructions are here: http://dev.mysql.com/doc/refman/5.7/en/installing.html
// -- open MySQL Workbench to see the contents of the database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * In MySQL Workbench
 * add user username/password CSC335/SoftwareEngineering
 * grant DBA permissions
 * 
 * create a database called csc335users
 * create a table called users within the csc335users database
 * add fields username, password, email, locked, logged (see below for datatypes)
 * 
 * to use the query engine within the workbench the first command must be
 * 'use csc335users;' -- the name csc335users will turn to bold font
 * 
 */

// -- MAKE SURE THE JDBC CONNECTOR JAR IS IN THE BUILD PATH
//    workspace -> properties -> Java Build Path -> Libraries -> Add External JARs...
// -- C:\Program Files (x86)\MySQL\Connector J 5.1.26\mysql-connector-java-5.1.26-bin.jar
public class DBaseConnection {

	// -- objects to be used for database access
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rset = null;
    
    // -- using the MySQL workbench, create a database called csc335users
    //    within that database create a table called users
    //    add fields for username (varchar(45)), password (varchar(45)), email (varchar(45)), locked (int), loggedin (int)

    // -- connect to the csc335users database (similar to the SQL use csc335users; command)
    // -- this will create a connector to the database, default port is 3306
    private String url = "jdbc:mysql://127.0.0.1:3306/csc335users";
    
    // -- this is the username/password, created during installation and in MySQL Workbench
    //    When you add a user make sure you give them the appropriate Administrative Roles
    //    (DBA sets all which works fine)
    private String DBAuser = "CSC335";
    private String DBApassword = "SoftwareEngineering";

	public DBaseConnection() {
		try {
            
            // -- make the connection to the database define by url, access through
			//    account DBAuser/DBApassword
			conn = DriverManager.getConnection(url, DBAuser, DBApassword);
            
			// -- These will be used to send queries to the database
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT VERSION()");

            if (rset.next()) {
                System.out.println(rset.getString(1));
            }
            
            String uname, pword, emailaddr;
        	Scanner kb = new Scanner(System.in);
            do {
            	System.out.print("Username: ");
            	uname = kb.next();
            	System.out.print("Password: ");
            	pword = kb.next();
            	System.out.print("Email address: ");
            	emailaddr = kb.next();
            	int lock = 0;
            	boolean loggedin = false;
            	if (!uname.equals("999")) {
                    // -- insert record into database
                    String insert = "insert into users values('" + uname + "', '" + pword + "', '" + emailaddr + "', " + lock + ", " + (loggedin ? 1 : 0) + ");";
                    System.out.println(insert);
                    stmt.executeUpdate(insert);            		
            	}
            } while (!uname.equals("999"));
            kb.close();
            
            // -- retrieve record(s) from database (csc335users) table (users)
            String command = "SELECT * FROM users;";
            System.out.println(command);
            // -- the query will return a ResultSet
            rset = stmt.executeQuery(command);
            printResultSet();
            
            // -- change locked to 1 for username username1
            String update = "update users set locked='1' where username='username1';";
            stmt.executeUpdate(update);

            // -- retrieve record(s) from database (csc335users) table (users)
            command = "SELECT * FROM users;";
            System.out.println(command);
            
            // -- the query will return a ResultSet
            rset = stmt.executeQuery(command);
            printResultSet();
            
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}
	
	public void printResultSet()
	{
		try {
	        //    the metadata tells us how many columns in the ResultSet
	        ResultSetMetaData rsmd = rset.getMetaData();
	        int numberOfColumns = rsmd.getColumnCount();
	        System.out.println("columns: " + numberOfColumns);
	        
	        // -- iterate through the ResultSet one row at a time
	        //    Note that the ResultSet starts at index 1
	        while (rset.next()) {
	        	// -- loop through the columns of the ResultSet
	        	for (int i = 1; i < numberOfColumns; ++i) {
	        		System.out.print(rset.getString(i) + " ");
	        	}
	        	System.out.println(rset.getString(numberOfColumns));
	        }
		}
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DBaseConnection dbc = new DBaseConnection();

	}

}
