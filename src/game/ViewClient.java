package game;
/*
 * Name: Rashin Babaee, Sewmini Jayasinghe
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 9 April 2023
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * this is a class for interface of client
 * 
 * @author Rashin Babaee, Sewmini Jayasinghe
 *
 */
public class ViewClient {
	/**
	 * this is an object
	 */
	ClientController clientController = new ClientController(this);
	/**
	 * this is an object
	 */
	GameClient gameClient;
	/**
	 * this is an object
	 */
	GameView gameView = new GameView();

	private JTextField serverText = new JTextField();
	private JTextField portText = new JTextField();
	private JTextField userText = new JTextField();
	private JTextArea textArea = new JTextArea(10, 10);

	/**
	 * this is a function for user
	 * 
	 * @return user
	 */
	public String user() {
		return userText.getText();

	}

	/**
	 * this is a function for server
	 */
	public void server() {

	}

	/**
	 * this is a function for port
	 */
	public void portClient() {

	}

	/**
	 * this is a function to connect to server
	 */
	public void connect() {
		textArea.append("Connecting to server\n");
		try {
			int port = Integer.parseInt(portText.getText());
			if (port < 10000 && port > 65000) {
				System.out.println("port number should be between 10000 and 65000");

			} else {

				gameClient = new GameClient(port, serverText.getText(), gameView, this);

				gameClient.connectToServer();
				System.out.println("rash");
			}
		} catch (NumberFormatException e) {
			System.out.println("invalid port number");
		} catch (ConnectException e) {
			System.out.println("connect exception");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * this is function for disconnecting
	 */
	public void endClient() {
		textArea.append("Ending the program\n");
		if (gameClient != null) {
			gameClient.disconnect();
		}

	}

	/**
	 * this is function for showing new game
	 */
	public void newGame() {
		textArea.append("pressed New game\n");

		gameView.random();

	}

	/**
	 * this is function for sending game board
	 */
	public void sendGame() {
		textArea.append("Sendig board game to server\n");

		if (gameClient != null) {
			gameClient.sendBoard();
		}

	}

	/**
	 * this is function for receiving game board
	 */
	public void receiveGame() {

		textArea.append("Receiving board game from server\n");

		if (gameClient != null) {
			// gameClient.sendBoard();

			String board = gameClient.receiveGame();
			if (board != null) {
				gameView.loadBoard(board);

			}
		}
	}

	/**
	 * this is a function for sending data to server
	 */
	public void sendData() {
		textArea.append("Sending user name, points and time to server\n");
		if (gameClient != null) {

			gameClient.sendDataPoints();

		}
	}

	/**
	 * this is a function to play the game
	 */
	public void play() {
		textArea.append("Pressed Play button\n");
		// gameView.random();
	}

	/**
	 * this is a main function for running game
	 * 
	 * @param args is for running game
	 */
	public static void main(String[] args) {
		ViewClient view = new ViewClient();
	}

	ViewClient() {
		JFrame splashWindow = new JFrame("Picross Client - JAP");

		splashWindow.setSize(700, 600);
		splashWindow.setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		splashWindow.add(mainPanel, BorderLayout.CENTER);

		JLabel logo = new JLabel(new ImageIcon("images1\\picross.png"));
		mainPanel.add(logo, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 5));
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		JLabel user = new JLabel("User");

		centerPanel.add(user);
		centerPanel.add(userText);

		JLabel server = new JLabel("Server");

		centerPanel.add(server);
		centerPanel.add(serverText);

		JLabel port = new JLabel("Port");

		centerPanel.add(port);
		centerPanel.add(portText);

		JButton connect = new JButton("Connect");
		connect.setSize(100, 50);
		JButton end = new JButton("End");

		JButton newGame = new JButton("New Game");

		JButton sendGame = new JButton("Send Game");

		JButton receiveGame = new JButton("Receive Game");

		JButton sendData = new JButton("Send Data");

		JButton play = new JButton("Play");

		centerPanel.add(connect);
		centerPanel.add(end);
		centerPanel.add(newGame);
		centerPanel.add(sendGame);
		centerPanel.add(receiveGame);
		centerPanel.add(sendData);
		centerPanel.add(play);

		connect.setActionCommand("Connect");
		connect.addActionListener(clientController);

		end.setActionCommand("End");
		end.addActionListener(clientController);

		newGame.setActionCommand("NewGame");
		newGame.addActionListener(clientController);

		sendGame.setActionCommand("SendGame");
		sendGame.addActionListener(clientController);

		receiveGame.setActionCommand("ReceiveGame");
		receiveGame.addActionListener(clientController);

		sendData.setActionCommand("SendData");
		sendData.addActionListener(clientController);

		play.setActionCommand("Play");
		play.addActionListener(clientController);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		JScrollPane scroll = new JScrollPane(textArea);
		southPanel.add(scroll);

		splashWindow.setVisible(true);
	}

}
