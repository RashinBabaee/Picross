package game;

/*
 * Name: Rashin Babaee, Sewmini Jayasinghe
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 9 April 2023
 */
import static java.lang.System.out;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a class for actionperformed to show if each action is equal to
 * command, then that function what to do
 * 
 * @author Rashin Babaee, Sewmini Jayasinghe
 *
 */
public class ClientController implements ActionListener {

	private ViewClient viewClient;

	/**
	 * This is a constructor
	 * 
	 * @param viewClient is type ViewClient
	 */
	public ClientController(ViewClient viewClient) {
		this.viewClient = viewClient;
	}

	/**
	 * this is a actionPerformed function
	 */
	public void actionPerformed(ActionEvent e) {
		String actionString[] = e.getActionCommand().split(" ");
		String action = actionString[0];
		out.println(action);

		if (action.equals("Connect")) {
			viewClient.connect();
		} else if (action.equals("End")) {
			viewClient.endClient();
		} else if (action.equals("NewGame")) {
			viewClient.newGame();
		} else if (action.equals("SendGame")) {
			viewClient.sendGame();
		} else if (action.equals("ReceiveGame")) {
			viewClient.receiveGame();

		} else if (action.equals("SendData")) {
			viewClient.sendData();
		} else if (action.equals("Play")) {
			viewClient.play();
		}
	}

}
