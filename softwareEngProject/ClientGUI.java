package softwareEngProject;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ClientGUI extends JFrame {

	// -- inherit from JFrame so that we can add custom functionality

	private ControlPanel cp;
	private LoginPanel lp;
	private RegisterPanel rp;
	private Client client;

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
		lp.setVisible(false);
		rp.setVisible(false);

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


				}
			});
			JButton registerButton = new JButton("Register");
			registerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					lp.setEnabled(false);
					rp.setEnabled(true);

				}
			});
			JButton disconnectButton = new JButton("Disconnect");
			disconnectButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					client.disconnect();

				}
			});
			JButton connectButton = new JButton("Connect");
			add(connectButton);
			connectButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					client = new Client();
					connectButton.setEnabled(false);
					disconnectButton.setEnabled(true);
					loginButton.setEnabled(true);
					registerButton.setEnabled(true);

				}
			});

			// -- add all buttons
			add(loginButton);
			add(registerButton);
			add(disconnectButton);
			disconnectButton.setEnabled(false);
			loginButton.setEnabled(false);
			registerButton.setEnabled(false);
			add(connectButton);


		}

		public Dimension getPreferredSize() {
			return new Dimension(100, 200);
		}

	}

	public class LoginPanel extends JPanel {

		public LoginPanel() {

			setLayout(new GridLayout(20, 1, 2, 2));

			JLabel user = new JLabel("Username");
			JLabel pw = new JLabel("Password");

			JTextField username = new JTextField();
			JTextField password = new JTextField();

			this.add(user);
			this.add(username);
			this.add(pw);
			this.add(password);

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


			this.add(em);
			this.add(email);
			this.add(user);
			this.add(username);
			this.add(pw);
			this.add(password);

		}

		public Dimension getPreferredSize() {

			return new Dimension(300, 300);
		}

	}

	public static void main(String[] args) {
		// -- instantiate an anonymous object
		new ClientGUI(512, 300);
	}

}
