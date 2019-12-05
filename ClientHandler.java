package ClientServerProject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;




public class ClientHandler extends Thread {
	private boolean go;
	private String name;
	private int id;
	
	// -- the main server (port listener) will supply the socket
	//    the thread (this class) will provide the I/O streams
	//    BufferedReader is used because it handles String objects
	//    whereas DataInputStream does not (primitive types only)
	private BufferedReader datain;
	private DataOutputStream dataout;
	
	// -- this is a reference to the "parent" Server object
	//    it will be set at time of construction
	private Server server;
	



	public ClientHandler (int id, Socket socket, Server server) 
	{
		this.server = server;
		this.id = id;
		this.name = Integer.toString(id);
		go = true;
		
		// -- create the stream I/O objects on top of the socket
		try {
			datain = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			dataout = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public String toString ()
	{
		return name;
	}
	
	public String getname ()
	{
		return name;
	}
	
	public static void LogInUser(String name) throws SQLException
	{
		DBaseConnection dbc = new DBaseConnection();
		Statement stmt = dbc.getStmt();
		ResultSet rset = dbc.getRset();
		//rset = stmt.executeQuery("SELECT * FROM users");
		//dbc.printResultSet(rset);
		//ResultSetMetaData rsmd = rset.getMetaData();
		String update = "update users set logged='1' where username='" + name + "';";
        stmt.executeUpdate(update);
        rset = stmt.executeQuery("SELECT * FROM users");
        dbc.printResultSet(rset);
	}
	
	public static void RegisterUser(String uname, String pword, String emailaddr) throws SQLException
	{
		DBaseConnection dbc = new DBaseConnection();
		Statement stmt = dbc.getStmt();
		ResultSet rset = dbc.getRset();
		//rset = stmt.executeQuery("SELECT * FROM users");
		//dbc.printResultSet(rset);
		//ResultSetMetaData rsmd = rset.getMetaData();
		String insert = "insert into users values('" + uname + "', '" + pword + "', '" + emailaddr + "', " + 0 + ", " + 0 + ");";
//      System.out.println(insert);
        stmt.executeUpdate(insert);   
        rset = stmt.executeQuery("SELECT * FROM users");
        dbc.printResultSet(rset);
	}
	
	public static void Disconnect(String uname) throws SQLException
	{
		DBaseConnection dbc = new DBaseConnection();
		Statement stmt = dbc.getStmt();
		ResultSet rset = dbc.getRset();
		//rset = stmt.executeQuery("SELECT * FROM users");
		//dbc.printResultSet(rset);
		//ResultSetMetaData rsmd = rset.getMetaData();
		String update = "update users set logged='0' where username='" + uname + "';";
        stmt.executeUpdate(update);
        System.out.println("Username " + uname + " is now disconnected");
        rset = stmt.executeQuery("SELECT * FROM users");
        dbc.printResultSet(rset);
	}


	public void run () {
		// -- server thread runs until the client terminates the connection
		while (go) {
			try {
				// -- always receives a String object with a newline (\n)
				//    on the end due to how BufferedReader readLine() works.
				//    The client adds it to the user's string but the BufferedReader
				//    readLine() call strips it off
				String txt = datain.readLine();
				System.out.println("SERVER receive: " + txt);
				// -- if it is not the termination message, send it back adding the
				//    required (by readLine) "\n"

				// -- if the disconnect string is received then 
				//    close the socket, remove this thread object from the
				//    server's active client thread list, and terminate the thread
				if (txt.equals("disconnect")) {
					datain.close();
					server.removeID(id);
					go = false;
				}
				else if (txt.equals("hello")) {
						
					dataout.writeBytes("world!" + "\n");
					dataout.flush();
					
				}
				else {
					System.out.println("unrecognized command >>" + txt + "<<");
					dataout.writeBytes(txt + "\n");
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
				go = false;
			}
			
		}
	}
}
