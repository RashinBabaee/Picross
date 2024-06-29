package server;
/*
 * Name: Rashin Babaee, Sewmini Jayasinghe
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 9 April 2023
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * this is class for interface of server
 * 
 * @author Rashin Babaee, Sewmini Jayasinghe
 *
 */
public class ViewServer {
	/**
	 * this is an object
	 */
	ServerController serverController = new ServerController(this);
	/**
	 * this is an object
	 */
	ThreadServer threadServer;
	/**
	 * this is an object
	 */
	JTextField portText = new JTextField();
	/**
	 * this is an object
	 */
	JTextArea textArea = new JTextArea(10, 10);

	// GameServer gameServer;
	/**
	 * this is function for port
	 */
	public void portServer() {

	}

	/**
	 * this is a function for executing
	 */
	public void execute() {
		textArea.append("Pressed Execute button\n");

		try {
			int port = Integer.parseInt(portText.getText());
			if (port < 10000 || port > 65000) {
				System.out.println("port number should be between 10000 and 65000");

			} else {

				threadServer = new ThreadServer(this);
				threadServer.startServer(port);
				// textArea.append(threadServer.startServer(port));
				// new Thread(() -> threadServer.startServer()).start();

				System.out.println("rash");
			}
		} catch (NumberFormatException e) {
			System.out.println("invalid port number");
		}
		System.out.println("execute");

	}

	private String result = "";

	/**
	 * this function is for receiving result
	 * 
	 * @param res is String
	 */
	public void receiveResult(String res) {
		textArea.append("Receiving Result\n");
		if (res != null) {
			result = result + res + "\n";
			System.out.println(result);
		}

	}

	private JFrame splashWindow = new JFrame("Message");
	private JPanel panelcenter = new JPanel();
	private JButton ok = new JButton("Ok");
	private JTextArea area = new JTextArea();

	/**
	 * this function is for showing screen splash
	 */
	private void resultWindow() {

		splashWindow.setSize(400, 300);
		splashWindow.setLocationRelativeTo(null);

		panelcenter.setLayout(new BorderLayout());

		ok.addActionListener(a -> {
			splashWindow.setVisible(false);
		});
		panelcenter.add(area, BorderLayout.CENTER);
		panelcenter.add(ok, BorderLayout.SOUTH);
		area.setEditable(false);
		splashWindow.add(panelcenter);

		splashWindow.setVisible(false);

	}

	/**
	 * this is a function for results
	 */
	public void results() {
		textArea.append("Pressed result button\n");

		System.out.println(result);
		area.setText(result);
		splashWindow.setVisible(true);

	}

	/**
	 * this is a function for finalizing
	 */
	public void finalize() {
		textArea.append("Pressed Finalize\n");

		// gameServer.finilize();

	}

	/**
	 * this is a function for ending server
	 */
	public void endServer() {
		textArea.append("Pressed End button\n");
		if (threadServer != null) {
			threadServer.disconnect();

		}

	}

	/**
	 * this is a function for receiving game
	 */
	public void receiveGame() {
//		gameServer.receiveBoard();

	}

	/**
	 * thi is main function for running the code
	 * 
	 * @param args is for running the code
	 */
	public static void main(String[] args) {
		ViewServer view = new ViewServer();

	}

	ViewServer() {
		JFrame splashWindow = new JFrame("Picross Server - JAP");
		splashWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		splashWindow.setSize(700, 600);
		splashWindow.setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		splashWindow.add(mainPanel, BorderLayout.CENTER);

		JLabel logo = new JLabel(new ImageIcon("images1\\picross.png"));
		mainPanel.add(logo, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1, 5));
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		JLabel portServer = new JLabel("Port");

		centerPanel.add(portServer);
		centerPanel.add(portText);

		JButton execute = new JButton("Execute");
		centerPanel.add(execute);

		JButton results = new JButton("Results");
		centerPanel.add(results);

		JCheckBox checkbox = new JCheckBox("Finalize"); // check box for mark
		centerPanel.add(checkbox);

		JButton endServer = new JButton("End");
		centerPanel.add(endServer);

		execute.setActionCommand("Execute");
		execute.addActionListener(serverController);

		results.setActionCommand("Results");
		results.addActionListener(serverController);

		checkbox.setActionCommand("Mark");
		checkbox.addActionListener(serverController);

		endServer.setActionCommand("End");
		endServer.addActionListener(serverController);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		mainPanel.add(southPanel, BorderLayout.SOUTH);

		JScrollPane scroll = new JScrollPane(textArea);

		southPanel.add(scroll);
		resultWindow();
		splashWindow.setVisible(true);

	}

}
