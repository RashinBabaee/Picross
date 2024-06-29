package server;
/*
 * Name: Rashin Babaee, Sewmini Jayasinghe
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 9 April 2023
 */

import java.io.*;
import java.net.*;

/**
 * Class ThreadServer.
 * 
 * @author Rashin Babaee, Sewmini Jayasinghe
 *
 */
public class ThreadServer implements Runnable {

	/**
	 * Socket variable.
	 */
	Socket sock;

	/**
	 * Variables for number clients.
	 */
	int nclient = 0, nclients = 0;

	/**
	 * Server socket.
	 */
	ServerSocket servsock;

	/**
	 * Number of port.
	 */
	int portNumber = 0;

	/**
	 * Default constructor.
	 */

	ViewServer viewServer;;

	public ThreadServer(ViewServer viewServer) {
		this.viewServer = viewServer;
	}

	/**
	 * Main method.
	 * 
	 * @param args Param arguments.
	 */
	public void startServer(int args) {
		portNumber = args;

		System.out.println("Starting Server Thread on port " + portNumber);
		try {
			servsock = new ServerSocket(portNumber);
			Thread servDaemon = new Thread(this);
			servDaemon.start();
			System.out.println("Server running on " + InetAddress.getLocalHost() + " at port " + portNumber + "!");
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
	}

	/**
	 * this function is for disconnecting
	 */
	public void disconnect() {
		try {
			servsock.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Run method.
	 */
	public void run() {
		for (;;) {
			try {
				sock = servsock.accept();
				nclient += 1;
				nclients += 1;
				System.out.println("Connecting " + sock.getInetAddress() + " at port " + sock.getPort() + ".");
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
			Worked w = new Worked(sock, nclient);
			w.start();
		}
	}

	/**
	 * Inner class for Theads.
	 * 
	 * @author Rashin Babaee, Sewmini Jayasinghe
	 *
	 */
	class Worked extends Thread {

		/**
		 * Socket variable.
		 */
		Socket sock;

		/**
		 * Integers for client and positions.
		 */
		int clientid, poscerq;

		/**
		 * String for data.
		 */
		String strcliid, dadosCliente;

		/**
		 * Default constructor.
		 * 
		 * @param s       Socket
		 * @param nclient Number of client.
		 */
		public Worked(Socket s, int nclient) {
			sock = s;
			clientid = nclient;
		}

		private String savedBoard;
		private PrintStream out = null;
		private BufferedReader in;

		private void receiveBoard(String board) {
			savedBoard = board;
			System.out.println("receive Board : " + board);
		}

		private void sendBoard() {
			if (savedBoard != null) {
				out.println(savedBoard);
				System.out.println("sending board " + savedBoard);
				out.flush();
			}
		}

		/**
		 * Run method.
		 */
		public void run() {
			String data;

			try {
				out = new PrintStream(sock.getOutputStream());
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				out.println(clientid);
				data = in.readLine();
				// poscerq = data.indexOf("#");
				dadosCliente = data;
				while (!dadosCliente.equals("end")) {

					System.out.println("Cli[" + strcliid + "]: " + data);
					out.println("String \"" + data + "\" received.");
					out.flush();
					data = in.readLine();
					// poscerq = data.indexOf("#");
					dadosCliente = data;
					if (dadosCliente == null) {
						break;
					}
					if (dadosCliente.charAt(0) == '[') {
						// dadosCliente = dadosCliente.substring(1);
						receiveBoard(dadosCliente);

					} else if (dadosCliente.charAt(0) == ']') {
						sendBoard();
					} else if (dadosCliente.charAt(0) == '{') {
						viewServer.receiveResult(dadosCliente);
					}
				}
				System.out.println("Disconecting " + sock.getInetAddress() + "!");
				nclients -= 1;
				System.out.println("Current client number: " + nclients);
				if (nclients == 0) {
					System.out.println("Ending server...");
					sock.close();
					// System.exit(0);
				}
			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		}
	}
}
