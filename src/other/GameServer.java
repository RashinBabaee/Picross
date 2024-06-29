package other;
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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * this class shows some functions like disconnecting, receiving board and so on.
 * @author Rashin Babaee, Sewmini Jayasinghe
 *
 */
public class GameServer {
	/**
	 * it is a constructor
	 * @param portNum is int 
	 */
	public GameServer(int portNum) {
		this.portNum = portNum;
	}
	/**
	 * it is a port number
	 */
	int portNum = 55555;
	/**
	 * object of serverSocket
	 */
	ServerSocket serverSocket;
	/**
	 * object of clientSocket
	 */
	Socket clientSocket;
	/**
	 * object of in
	 */
	BufferedReader in;
	/**
	 * this function is for starting the server
	 */
	public void startServer() {
		try {
			serverSocket = new ServerSocket(portNum);
			System.out.println("waiting for clienet");
			clientSocket = serverSocket.accept();
			System.out.println("client connected");

			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String input;
			while ((input = in.readLine()) != null) {
				System.out.println(input);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * this function is for disconnecting the server
	 */
	public void disconnect() {
		try {
			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * this function is for finalizing
	 */
	public void finilize() {
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}
	
	/**
	 * this function is for receiving the board from client
	 */
	public void recieveBoard() {
//		String b = gameView.sendString();
//		printWrite.println(b);
	}
	
	
	/**
	 * this is a main function for running the code
	 * @param args for running code
	 */
	public static void main(String[] args) {

	}
}
