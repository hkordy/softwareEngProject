package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ClientGUI extends JFrame {

	// -- inherit from JFrame so that we can add custom functionality

	private ControlPanel cp;
	private LoginPanel lp;
	private RegisterPanel rp;
	private ClientGUI parent;
	private ChangePWPanel cpwp;
	private RecoverPWPanel rpwp;
	private LoggedInPanel lip;

	public ClientGUI(int height, int width) {

		// -- set frame title
		setTitle("Client GUI");

		// -- size of the frame: width, height
		setSize(width, height);

		// -- center the frame on the screen
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// -- set the layout manager and add items
		// 5, 5 is the border around the edges of the areas
		setLayout(new BorderLayout(5, 5));

		cp = new ControlPanel();
		this.add(cp, BorderLayout.WEST);

		lp = new LoginPanel();
		this.add(lp, BorderLayout.CENTER);

		rp = new RegisterPanel();
		this.add(rp, BorderLayout.CENTER);

		// -- show the frame on the screen
		setVisible(true);
		cp.setVisible(true);
//		lp.setVisible(false);
		rp.setVisible(false);

		parent = this;
	}

	// -- Inner class for control panel, also inherits from JPanel
	public class ControlPanel extends JPanel {

		public ControlPanel() {
			// -- GridLayout manager
			setLayout(new GridLayout(20, 1, 2, 2));

			JButton loginButton = new JButton("Login");
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					lp.setVisible(true);

					rp.setVisible(false);
					LoginPanel lp = new LoginPanel();
					JFrame jf = new JFrame();
					jf.setSize(512, 300);
					jf.add(lp);
					jf.setVisible(true);

				}
			});
			JButton registerButton = new JButton("Register");
			registerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					lp.setVisible(false);
					rp.setVisible(true);

				}
			});
			JButton disconnectButton = new JButton("Disconnect");
			disconnectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

				}
			});

			// -- add all buttons
			add(loginButton);
			add(registerButton);
			add(disconnectButton);

		}

		public Dimension getPreferredSize() {
			return new Dimension(100, 200);
		}

	}

	public class LoginPanel extends JPanel {

		public LoginPanel() {

			setLayout(new GridLayout(10, 1, 2, 2));

			JLabel user = new JLabel("Username");
			JLabel pw = new JLabel("Password");

			JTextField username = new JTextField();
			JPasswordField password = new JPasswordField();

			JCheckBox seepw = new JCheckBox("See Password");
			seepw.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (seepw.isSelected()) {
						password.setEchoChar((char) 0);
					} else {
						password.setEchoChar('*');
					}
				}
			});

			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Submitted");
					LoggedInPanel lip = new LoggedInPanel();
					JFrame jf = new JFrame();
					jf.setSize(512, 300);
					jf.add(lip);
					jf.setVisible(true);
					jf.setAlwaysOnTop(true);
				}
			});

			JButton recoverButton = new JButton("Recover Password");
			recoverButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					RecoverPWPanel rpwp = new RecoverPWPanel();
					JFrame jf = new JFrame();
					jf.setSize(512, 300);
					jf.add(rpwp);
					jf.setVisible(true);
					jf.setAlwaysOnTop(true);

				}
			});

			this.add(user);
			this.add(username);
			this.add(pw);
			this.add(password);
			this.add(seepw);
			this.add(submitButton);
			this.add(recoverButton);

		}

		public Dimension getPreferredSize() {

			return new Dimension(300, 300);
		}

	}

	public class RegisterPanel extends JPanel {

		public RegisterPanel() {

			setLayout(new GridLayout(20, 1, 2, 2));

			JLabel user = new JLabel("Username");
			JLabel pw = new JLabel("Password");
			JLabel em = new JLabel("Email");
			JTextField username = new JTextField();
			JTextField password = new JTextField();
			JTextField email = new JTextField();
			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Submitted");
				}
			});

			this.add(em);
			this.add(email);
			this.add(user);
			this.add(username);
			this.add(pw);
			this.add(password);
			this.add(submitButton);

		}

		public Dimension getPreferredSize() {

			return new Dimension(300, 300);
		}

	}

	public class LoggedInPanel extends JPanel {

		public LoggedInPanel() {

			JButton changepwButton = new JButton("Change Password");
			changepwButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ChangePWPanel cpwp = new ChangePWPanel();
					JFrame jf = new JFrame();
					jf.setSize(512, 300);
					jf.add(cpwp);
					jf.setVisible(true);
					jf.setAlwaysOnTop(true);

				}
			});

			JButton disconnectButton = new JButton("Disconnect");
			disconnectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.print("Disconnected");

				}
			});

			this.add(changepwButton);
			this.add(disconnectButton);
		}
	}

	public class ChangePWPanel extends JPanel {
		public ChangePWPanel() {

			setLayout(new GridLayout(10, 1, 2, 2));

			JLabel old = new JLabel("Old Password");
			JLabel new1 = new JLabel("New Password");
			JLabel new2 = new JLabel("New Password (Retype)");
			JTextField oldpw = new JTextField();
			JTextField newpw1 = new JTextField();
			JTextField newpw2 = new JTextField();
			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Submitted");
				}
			});

			this.add(old);
			this.add(oldpw);
			this.add(new1);
			this.add(newpw1);
			this.add(new2);
			this.add(newpw2);
			this.add(submitButton);

		}

	}

	public class RecoverPWPanel extends JPanel {
		public RecoverPWPanel() {
			setLayout(new GridLayout(10, 1, 2, 2));

			JLabel user = new JLabel("Username");
			JTextField username = new JTextField();
			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.out.println("Email Has Been Sent");
				}
			});

			this.add(user);
			this.add(username);
			this.add(submitButton);

		}
	}

	public static void main(String[] args) {
		// -- instantiate an anonymous object
		new ClientGUI(512, 300);
	}

}
