package server;
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
public class ServerController implements ActionListener {

	private ViewServer viewServer;

	/**
	 * this is a constructor
	 * 
	 * @param viewServer is type ViewServer
	 */
	public ServerController(ViewServer viewServer) {
		this.viewServer = viewServer;
	}

	/**
	 * this is a actionPerformed function
	 */
	public void actionPerformed(ActionEvent e) {
		String actionString[] = e.getActionCommand().split(" ");
		String action = actionString[0];
		out.println(action);

		if (action.equals("Port")) {
			viewServer.portServer();

		} else if (action.equals("Execute")) {
			viewServer.execute();
		} else if (action.equals("Results")) {
			viewServer.results();
		} else if (action.equals("Finalize")) {
			viewServer.finalize();
		} else if (action.equals("End")) {
			viewServer.endServer();
		}

	}

}
