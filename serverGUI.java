package ClientServerProject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.Statement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class serverGUI extends JFrame
{
	private titlePanel tp;
	private valuePanel vp;
	private request rb;
	public int reg = 0;
	public int logged = 0;
	public int locked = 0;
	public ArrayList<String> users = new ArrayList<String>();
	public int counter = 1;

	public serverGUI()
	{

		setTitle("Server GUI");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// setLayout(new BorderLayout(20,25));

		tp = new titlePanel();
		vp = new valuePanel();
		rb = new request();

		this.add(tp, BorderLayout.WEST);
		this.add(vp, BorderLayout.CENTER);
		this.add(rb, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	public class valuePanel extends JPanel
	{

		private JTextField registered, loggedIn, lockedOut, users;

		public valuePanel()
		{
			super();
			Color lightBlue = new Color(31, 190, 214);
			this.setBackground(lightBlue);
			setLayout(new GridLayout(5, 1, 20, 20));

			registered = new JTextField(String.valueOf(reg));
			loggedIn = new JTextField(String.valueOf(logged));
			lockedOut = new JTextField(String.valueOf(locked));
			users = new JTextField("");

			add(registered);
			add(loggedIn);
			add(lockedOut);
			add(users);
		}
	}

	public class titlePanel extends JPanel
	{

		public titlePanel()
		{
			super();
			Color lightYellow = new Color(255, 255, 204);
			this.setBackground(lightYellow);
			setLayout(new GridLayout(5, 1, 20, 20));
			JLabel registered = new JLabel("Number of Registered Users \t:");
			JLabel loggedIn = new JLabel("Number of Logged In Users \t:");
			JLabel locked = new JLabel("Number of Locked out Users \t:");
			JLabel users = new JLabel("Logged in users  \t:");
			add(registered);
			add(loggedIn);
			add(locked);
			add(users);
		}
	}

	public class request extends JPanel
	{
		public request()
		{
			super();

			JButton submit = new JButton("Request Queries");
			submit.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					System.out.println("Queries requested");
					DBaseConnection dbc = new DBaseConnection();
					Statement stmt = dbc.getStmt();
					ResultSet rset = dbc.getRset();
					try
					{
						rset = stmt.executeQuery("SELECT * FROM users");
						dbc.printResultSet(rset);
						rset = stmt.executeQuery("SELECT * FROM users");
						ResultSetMetaData rsmd = rset.getMetaData();
						int numberOfColumns = rsmd.getColumnCount();
						String loggedInUsers = "";
						
						while (rset.next()) {
				        	// -- loop through the columns of the ResultSet
				        	if(rset.getString(5).equals("1"))
				        	{
				        		logged++; // increment logged in users 
				        		loggedInUsers += (rset.getString(1) + " "); // add username to user list
				        	}
				        	if(rset.getString(4).equals("1"))
				        	{
				        		locked++;
				        	}
				        }
						
						users.add("User #" + String.valueOf(counter++));
						vp.registered.setText(Integer.toString(numberOfColumns+1));
						vp.loggedIn.setText(String.valueOf(logged));
						vp.lockedOut.setText(String.valueOf(locked));
						vp.users.setText(loggedInUsers);
					
					} catch (SQLException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			add(submit);
		}
	}

	public static void main(String[] args)
	{
		new serverGUI();
	}
}
