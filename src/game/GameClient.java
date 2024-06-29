package game;

/*
 * Name: Rashin Babaee, Sewmini Jayasinghe
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 9 April 2023
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Timer;

/**
 * this class shows functions like connecting, sending board game, disconnecting
 * and so on.
 * 
 * @author Rashin Babaee, Sewmini Jayasinghe
 *
 */
public class GameClient {
	/**
	 * it is a port num
	 */
	int portNum = 55555;
	/**
	 * variable hostName
	 */
	String hostName;
	/**
	 * object of clientSocket
	 */
	Socket clientSocket;
	/**
	 * object of printwriter
	 */
	PrintWriter printwriter;
	/**
	 * object of dis
	 */
	BufferedReader dis;
	/**
	 * object of gameView
	 */
	GameView gameView;
	/**
	 * object of gameModel
	 */
	GameModel gameModel;
	/**
	 * object of viewClient
	 */
	ViewClient viewClient;

	/**
	 * this is a constructor
	 * 
	 * @param portNum    is int
	 * @param hostName   is String
	 * @param gameView   is GameView
	 * @param viewClient is ViewClient
	 */
	public GameClient(int portNum, String hostName, GameView gameView, ViewClient viewClient) {
		this.portNum = portNum;
		this.hostName = hostName;
		this.gameView = gameView;
		gameModel = gameView.getGameModel();
		this.viewClient = viewClient;

	}

	/**
	 * this function can connect to server
	 * 
	 * @throws IOException is an exception
	 */
	public void connectToServer() throws IOException {
		System.out.println("connect to server");

		try {

			clientSocket = new Socket(hostName, portNum);
			printwriter = new PrintWriter(clientSocket.getOutputStream(), true);
			dis = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			printwriter.println("text");
			System.out.println("connected to server");
		} catch (UnknownHostException e) {
			e.printStackTrace();

		}

	}

	/**
	 * this function can send the board game to server
	 */
	public void sendBoard() {
		String b = gameView.getString();
		printwriter.println(b);
	}

	/**
	 * thsi function can receive the board game from server
	 * 
	 * @return message
	 */
	public String receiveGame() {

		printwriter.println(']');
		try {

			String message = "message";
			while (message.charAt(0) != '[') {

				message = dis.readLine();

				System.out.println("receive: " + message);
			}
			System.out.println("receive board: " + message);
			return message.substring(1);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * this function can send data to server
	 */
	public void sendDataPoints() {
		int c = gameView.point(0);
		String d = gameView.getTime();
		printwriter.println("{" + viewClient.user() + ";" + c + ";" + d);
		printwriter.flush();
		// printwriter.println(d);
	}

	/**
	 * this function can disconnect from server
	 */
	public void disconnect() {
		try {
			OutputStream outPutStream = clientSocket.getOutputStream();
			outPutStream.write("disconnected".getBytes());
			outPutStream.flush();
			clientSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this is a main function
	 * 
	 * @param args is for running the program
	 */
	public static void main(String[] args) {

	}
}
