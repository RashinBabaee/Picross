package game;
/*
 * Name: Rashin Babaee, Sewmini Jayasinghe
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 9 April 2023
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import static java.lang.System.out;

/**
 * this is a class for actionperformed to show if each action is equal to
 * command, then that function what to do
 * 
 * @author Rashin Babaee, Sewmini Jayasinghe
 *
 */
public class GameController implements ActionListener {
	/**
	 * game is from GameView class
	 */
	private GameView game;

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * this is a actionPerformed function
	 */
	public void actionPerformed(ActionEvent e) {
		/**
		 * number of square`s row
		 */
		int m = 5;
		/**
		 * number of square`s column
		 */
		int n = 5;

		GameModel gamemodel = new GameModel(m, n);

		String actionString[] = e.getActionCommand().split(" ");
		String action = actionString[0];
		out.println(action);

		if (action.equals("Language")) {
			JComboBox<String> language = (JComboBox<String>) e.getSource();
			String selectlanguage = (String) language.getSelectedItem();
			game.updateInterface(selectlanguage);
			out.println(selectlanguage);
		} else if (action.equals("New")) {
			game.reset();
		} else if (action.equals("Open")) {
			game.openFile();
		} else if (action.equals("Save")) {
			game.saveFile();
		} else if (action.equals("Solution")) {
			game.solutionFile();
		} else if (action.equals("Exit")) {
			game.exitFile();
		} else if (action.equals("Colors")) {
			game.chooseColor();
		}

		else if (action.equals("Reset")) {
			game.reset();
		}

		else if (action.equals("Button")) {
			int x = Integer.parseInt(actionString[1]);
			int y = Integer.parseInt(actionString[2]);
			game.pressButton(x, y);

		} else if (action.equals("Solution")) {
			game.solutionFile();
		}

		else if (action.equals("Random")) {
			game.random();
		}

		else if (action.equals("About")) {
			game.about();
		}

	}

	/**
	 * this is a constructor
	 * 
	 * @param game is from GameView
	 */
	public GameController(GameView game) {
		this.game = game;
	}

}
