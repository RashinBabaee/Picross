package game;

import static java.lang.System.out;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

/*
 * Name: Rashin Babaee, Sewmini Jayasinghe
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 9 April 2023
 */

/**
 * This class shows JFrame, BorderLayout, Panel, GridLayout and Buttons for
 * creating an interface and functions of Picross game.
 * 
 * @author Rashin Babaee, Sewmini Jayasinghe
 *
 */
public class GameView {
	/**
	 * number of square`s row
	 */
	private static int x = 5;
	/**
	 * number of square`s column
	 */
	private static int y = 5;

	private String userActionHistory = "";
	private long startTime;

	private JFrame f = new JFrame("Picross");
	private JFileChooser fileChooser = new JFileChooser(); // for choosing files
	private JColorChooser colorChooser = new JColorChooser(); // for choosing colors

	private GameModel gamemodel = new GameModel(x, y);

	private JButton savebutton = new JButton("Save"); // save button

	private JButton resetbutton = new JButton("Reset"); // reset button

	private JLabel language3 = new JLabel("Language"); // language label

	private JLabel points = new JLabel("Points"); // points label

	private JTextField pointsfield = new JTextField();

	private JLabel time = new JLabel("Time"); // time label

	private JCheckBox checkbox = new JCheckBox("Mark"); // check box for mark

	private JMenu file = new JMenu("File");

	private JMenuItem openItem = new JMenuItem("Open", new ImageIcon("images1\\piciconnew.gif")); // open the file

	private JMenuItem newItem = new JMenuItem("New", new ImageIcon("images1\\new.png")); // reset the file

	private JMenuItem saveItem = new JMenuItem("Save", new ImageIcon("images1\\save.png")); // save the file

	private JMenu settings = new JMenu("Settings"); // setting for colors and about

	private JMenuItem colors = new JMenuItem("Colors", new ImageIcon("images1\\piciconabt.gif")); // changing the color

	private JMenuItem solutionItem = new JMenuItem("Solution", new ImageIcon("images1\\piciconsol.gif")); // solution of
																											// game

	private JMenuItem exitItem = new JMenuItem("Exit", new ImageIcon("images1\\piciconext.gif")); // exit from game

	private JMenuItem randomItem = new JMenuItem("Random", new ImageIcon("images\\piciconnew.gif")); // random game

	private JMenuItem aboutItem = new JMenuItem("About", new ImageIcon("about.gif"));

	private JLabel toplabel[] = new JLabel[x]; // the top of center panel

	private JLabel westlabel[] = new JLabel[y]; // the west of center panel

	private GameController gamecontroller = new GameController(this);

	private JPanel centerpanel = new JPanel(); // left side of interface is center panel

	private JPanel mainpanel = new JPanel();

	private JLabel centerlabel = new JLabel();

	private JTextField timefield = new JTextField();

	private Color newcolor = new Color(123, 193, 234); // color of board game

	private int[][] defaultPattern = { { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 0 }, { 1, 1, 1, 1, 1 }, { 0, 1, 1, 1, 0 },
			{ 0, 1, 0, 1, 0 } }; // the default pattern

	private int[][] defaultPatternGrid = new int[x][y];
	{
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {

				defaultPatternGrid[i][j] = defaultPattern[j][i];
			}
		}
	}

	/**
	 * this function is for getting String
	 * 
	 * @return a
	 */
	public String getString() {

		String a = "[" + x + ", " + y + "] ";

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				a += (defaultPatternGrid[j][i] + " ");
			}

			a += "=";
		}
		return a;
	}

	/**
	 * this function is for sending String
	 * 
	 * @return a
	 */
	public String sendString() {

		String a = "";

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				a += (defaultPatternGrid[j][i] + " ");
			}

			a += "=";
		}
		return a;
	}

	private JButton buttons[][] = new JButton[x][y]; // buttons of center panel

	private Locale currentLocale;
	private static ResourceBundle texts;

	/**
	 * this function updates languages
	 * 
	 * @param lang is String
	 */
	public void updateInterface(String lang) {
		String language = null;
		String country = null;
		switch (lang) {
		case "English": // if it is English
			language = "en";
			country = "CA";
			break;
		case "Persian": // if it is persian
			language = "pe";
			country = "IR";
			break;
		case "Sinhala": // if it is Sinhala

			language = "si";
			country = "SR";
			break;
		}
		try {

			currentLocale = new Locale.Builder().setLanguage(language).setRegion(country).build();
			texts = ResourceBundle.getBundle("resources/texts", currentLocale);
			savebutton.setText(texts.getString("BUTSAVE")); // get String and change it to text for BUTSAVE
			resetbutton.setText(texts.getString("BUTRESET")); // get String and change it to text for BUTRESET
			language3.setText(texts.getString("BUTLANGUAGE")); // get String and change it to text for BUTLANGUAGE
			points.setText(texts.getString("BUTPOINTS")); // get String and change it to text for BUTPOINTS
			time.setText(texts.getString("BUTTIME")); // get String and change it to text for BUTTIME
			checkbox.setText(texts.getString("BUTMARK")); // get String and change it to text for BUTMARK
			file.setText(texts.getString("BUTFILE")); // get String and change it to text for BUTFILE
			openItem.setText(texts.getString("BUTOPENITEM")); // get String and change it to text for BUTOPENITEM
			newItem.setText(texts.getString("BUTNEWITEM")); // get String and change it to text for BUTNEWITEM
			saveItem.setText(texts.getString("BUTSAVEITEM")); // get String and change it to text for BUTSAVEITEM
			settings.setText(texts.getString("BUTSETTING")); // get String and change it to text for BUTSETTING
			colors.setText(texts.getString("BUTCOLORS")); // get String and change it to text for BUTCOLORS
			solutionItem.setText(texts.getString("BUTSOLUTION")); // get String and change it to text for BUTSOLUTION
			exitItem.setText(texts.getString("BUTEXIT")); // get String and change it to text for BUTEXIT
			randomItem.setText(texts.getString("BUTRANDOM")); // get String and change it to text for BUTRANDOM

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JTextArea area = new JTextArea(); // center of east panel

	/**
	 * for new option
	 */
	public void chooseNew() {
		area.append("Choosing new\n");
		gamemodel.changeSize(x, y);
	}

	/**
	 * for reseting game
	 */
	public void resetGame() {
		gamemodel.resetBoard();
		resetView();
	}

	/**
	 * reseting the interface
	 */
	public void resetView() {
		for (int i = 0; i < x; i++) {

			for (int j = 0; j < x; j++) {
				buttons[i][j].setBackground(newcolor); // set the color to background color
			}
		}
	}

	/**
	 * opening the file
	 */
	public void openFile() {
		area.append("Opening\n");
		resetGame();
		int response = fileChooser.showOpenDialog(f);
		if (response == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();

			try {
				Scanner scanner = new Scanner(selectedFile);
				int lineNumber = 0;
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					updateBoard(lineNumber, line);

					System.out.println("Read line: " + line);
					lineNumber++;
				}
				scanner.close();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		}

	}

	/**
	 * thsi function is for loading board game
	 * 
	 * @param board is String
	 */
	public void loadBoard(String board) {
		area.append("Loading board\n");
		String lines[] = board.split("]");
		String lines2[] = lines[0].split(",");
		int x = Integer.parseInt(lines2[0].trim());
		int y = Integer.parseInt(lines2[1].trim());
		random(x, y);
		resetGame();

		lines = lines[1].split("=");
		int lineNumber = 0;
		while (lineNumber < lines.length) {
			if (lines[lineNumber].length() > 1) {
				System.out.println("Read line: " + lines[lineNumber]);
				updateBoard(lineNumber, lines[lineNumber]);
			}

			lineNumber++;
		}

	}

	/**
	 * this function is for updating board
	 * @param lineNumber is int
	 * @param line       is String
	 */
	public void updateBoard(int lineNumber, String line) {
		String values[] = line.trim().split(" ");
		for (int j = 0; j < x; j++) {
			System.out.println("*" + values[j] + "*");
			int value = Integer.parseInt(values[j]);
			defaultPatternGrid[j][lineNumber] = value;

		}
	}

	/**
	 * for updating board
	 */
	public void updateBoard() {
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (defaultPatternGrid[i][j] == 1) { // if it is 1, it is green
					buttons[i][j].setBackground(Color.green);

				} else {
					buttons[i][j].setBackground(Color.red); // if it is 0, it is red
				}

			}
		}
	}

	/**
	 * for saving file
	 */
	public void saveFile() {
		area.append("Saving\n");
		int result = fileChooser.showSaveDialog(f); // showing save dialog
		if (result == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			try {
				PrintWriter printer = new PrintWriter(fileToSave);
				gamemodel.writeBoard(printer);
				printer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Game saved successfully.");

		}
	}
	/**
	 * this function is for getting game model
	 * @return gamemodel
	 */
	public GameModel getGameModel() {
		return gamemodel;
	}

	/**
	 * for solution of game
	 */
	public void solutionFile() {
		area.append("solution\n");
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {

				if (defaultPatternGrid[i][j] == 1) { // if it is one, the button will be green
					buttons[i][j].setBackground(Color.green);

				} else {
					buttons[i][j].setBackground(Color.yellow); // if it is 0, the button should be red

				}
			}
		}
	}

	/**
	 * for exiting from game
	 */
	public void exitFile() {
		area.append("exiting\n");
		System.exit(x);
	}

	/**
	 * choosing another color for board game
	 */
	public void chooseColor() {
		area.append("Choosing color\n");
		Color color = JColorChooser.showDialog(null, "Colors", newcolor); // showing dialog
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				buttons[i][j].setBackground(color);
			}
		}
	}

	/**
	 * for pressing button for being yellow, green or red
	 * 
	 * @param x is int
	 * @param y is int
	 */
	public void pressButton(int x, int y) {

		area.append("button " + x + " " + y + "\n");
		if (checkbox.isSelected()) {
			gamemodel.setMark(x, y);
			gamemodel.printBoard();
			if (defaultPatternGrid[x][y] == 0 && gamemodel.getModel(x, y) == 2) {
				buttons[x][y].setBackground(Color.yellow); // the button is yellow
				point(1);

			} else {
				buttons[x][y].setBackground(Color.red);
				point(-1);
			}
		} else {

			gamemodel.setCheck(x, y);
			gamemodel.printBoard();
			if (defaultPatternGrid[x][y] == gamemodel.getModel(x, y) && defaultPatternGrid[x][y] == 1) {
				buttons[x][y].setBackground(Color.green); // the button is green
				point(1);

			} else {
				buttons[x][y].setBackground(Color.red); // the button is red
				point(-1);
			}
		}
		checkWin(); // the splash screen comes

	}

	/**
	 * if the user can win
	 */
	private void checkWin() {
		boolean win = true;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (!(defaultPatternGrid[i][j] == gamemodel.getModel(i, j)
						|| (defaultPatternGrid[i][j] == 0 && gamemodel.getModel(i, j) == 2))) {
					win = false;
				}
			}
		}
		if (win) {
			win();
		}

	}

	/**
	 * this function is increasing and decreasing points
	 * 
	 * @param change is int
	 * @return point
	 */
	public int point(int change) {

		int point = gamemodel.getPoints();

		point = point + change;
		if (point < 0) {

			point = 0;
		}
		gamemodel.setPoints(point);
		pointsfield.setText(Integer.toString(point));
		return point;
	}

	/**
	 * for reseting the board
	 */
	public void reset() {
		gamemodel.changeSize(x, y);
		gamemodel.createBoard();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				buttons[i][j].setBackground(newcolor);
			}
		}
		point(0);

	}

	/**
	 * this function is for choosing random game
	 * 
	 * @param x is int
	 * @param y is int
	 */
	public void random(int x, int y) {
		System.out.println("random");
		area.append("Random\n");
		// Random random = new Random();
//		x = Math.abs(random.nextInt()) % 5 + 3;
//		y = Math.abs(random.nextInt()) % 5 + 3;

		this.x = x;
		this.y = y;
		randomGrid();

		centerpanel();
		reset();
		labels();

	}

	/**
	 * random function for random board
	 */
	public void random() {
		System.out.println("random");
		area.append("Random\n");
		Random random = new Random();
		x = Math.abs(random.nextInt()) % 5 + 3;
		y = Math.abs(random.nextInt()) % 5 + 3;

		randomGrid();

		centerpanel();
		reset();
		labels();

	}

	/**
	 * function for getting random grid
	 */
	public void randomGrid() {

		defaultPatternGrid = new int[x][y];
		Random random = new Random();
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				defaultPatternGrid[i][j] = Math.abs(random.nextInt()) % 2;

			}
		}
	}

	/**
	 * this function is for labels in top and west of board
	 */
	public void labels() {
		int count = 0;
		int count1 = 0;

		for (int c = 0; c < x; c++) {
			String label = "";
			count = 0;
			for (int r = 0; r < y; r++) {
				if (defaultPatternGrid[c][r] == 1) {
					count = count + 1;

				} else {
					if (count != 0) {
						label += Integer.toString(count) + " ";
						count = 0;
					}
				}

			}
			if (count != 0) {
				label += Integer.toString(count);

			}
			toplabel[c].setText(label);
		}

		for (int r = 0; r < y; r++) {
			String label = "";
			count1 = 0;
			for (int c = 0; c < x; c++) {
				if (defaultPatternGrid[c][r] == 1) {
					count1 = count1 + 1;

				} else {
					if (count1 != 0) {
						label += Integer.toString(count1) + " ";
						count1 = 0;
					}
				}

			}
			if (count1 != 0) {
				label += Integer.toString(count1);

			}
			westlabel[r].setText(label);
		}
	}

	/**
	 * showing panel of labels, and board game
	 */
	public void centerpanel() {

		if (centerpanel != null) {
			mainpanel.remove(centerpanel);
			centerpanel = new JPanel();

		}
		toplabel = new JLabel[x]; // the top of center panel

		westlabel = new JLabel[y]; // the west of center panel

		buttons = new JButton[x][y]; // buttons of center panel
		centerpanel.setBackground(Color.lightGray); // the color is light gray
		centerpanel.setLayout(new GridLayout(y + 1, x + 1));
		centerpanel.add(centerlabel);
		System.out.println(x + " " + y);

		for (int i = 0; i < x; i++) {
			toplabel[i] = new JLabel("" + i); // 5 section of top label
			centerpanel.add(toplabel[i]);
		}

		for (int i = 0; i < y; i++) {
			westlabel[i] = new JLabel("" + i); // 5 section of west label
			centerpanel.add(westlabel[i]);

			for (int j = 0; j < x; j++) {
				JButton button = buttons[j][i] = new JButton("" + j + " " + i); // creating buttons

				button.setBackground(newcolor);

				button.setOpaque(true);

				button.setActionCommand("Button " + j + " " + i);
				button.addActionListener(gamecontroller);
				button.setPreferredSize(new Dimension(50, 50)); // size of buttons
				centerpanel.add(buttons[j][i]);

			}
		}

		mainpanel.add(centerpanel, BorderLayout.CENTER);
		redraw();

	}

	/**
	 * this function is for redrawing the board
	 */
	public void redraw() {
		f.setVisible(false);
		f.setVisible(true);
	}

	/**
	 * this function is for showing time
	 */
	public void time() {
		startTime = System.currentTimeMillis();
		Timer timer = new Timer(1000, e -> {
			long elapsedTime = System.currentTimeMillis() - startTime;
			String formattedTime = String.format(" %02d:%02d", TimeUnit.MILLISECONDS.toMinutes(elapsedTime),
					TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % 60);
			timefield.setText(formattedTime);
		});

		timer.start();
		// return timefield;

	}

	/**
	 * this function is for getting time
	 * 
	 * @return timefield
	 */
	public String getTime() {

		// time();
		return timefield.getText();

	}

	/**
	 * this function is for about in setting to pup up the splash window
	 */
	public void about() {

		JFrame splashWindow3 = new JFrame("Splash Screen");
		splashWindow3.setSize(600, 500);
		splashWindow3.setLocationRelativeTo(null);

		JPanel splashpanelcenter3 = new JPanel();
		splashpanelcenter3.setLayout(new BorderLayout());

		JLabel logo3 = new JLabel(new ImageIcon("images1\\picross.png"));

		splashpanelcenter3.add(logo3, BorderLayout.CENTER);
		JButton ok = new JButton("Ok");

		ok.addActionListener(a -> {
			splashWindow3.setVisible(false);
		});
		splashpanelcenter3.add(ok, BorderLayout.SOUTH);

		splashWindow3.add(splashpanelcenter3);
		splashWindow3.setVisible(true);
	}

	/**
	 * this function is for when user wins and splash screen pops up
	 */
	public void win() {

		JFrame splashWindow2 = new JFrame("Splash Screen");
		splashWindow2.setSize(600, 500);
		splashWindow2.setLocationRelativeTo(null);

		JPanel splashpanelcenter2 = new JPanel();
		splashpanelcenter2.setLayout(new BorderLayout());

		JLabel logo2 = new JLabel(new ImageIcon("images1\\gameend.png"));

		splashpanelcenter2.add(logo2, BorderLayout.CENTER);
		JLabel ending = new JLabel("Ending");

		splashpanelcenter2.add(ending, BorderLayout.SOUTH);

		splashWindow2.add(splashpanelcenter2);
		splashWindow2.setVisible(true);

	}

	/**
	 * This is a constructor
	 */
	GameView() {

		JFrame splashWindow1 = new JFrame("Splash Screen");
		splashWindow1.setSize(600, 500);
		splashWindow1.setLocationRelativeTo(null);

		JPanel splashpanelcenter1 = new JPanel();
		splashpanelcenter1.setLayout(new BorderLayout());

		JLabel logo1 = new JLabel(new ImageIcon("images1\\picross.png"));

		splashpanelcenter1.add(logo1, BorderLayout.CENTER);
		JLabel loading = new JLabel("Loading");

		splashpanelcenter1.add(loading, BorderLayout.SOUTH);

		splashWindow1.add(splashpanelcenter1);
		splashWindow1.setVisible(true);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		splashWindow1.setVisible(false);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Color blue = new Color(10, 10, 100); // creating color of border

		JPanel gamepanel = new JPanel(); // panel of whole game
		gamepanel.setBackground(blue);
		Border blueline = BorderFactory.createEmptyBorder(30, 30, 30, 30);

		gamepanel.setBorder(blueline);
		gamepanel.setLayout(new BorderLayout());

		ImageIcon imageicon = new ImageIcon("images\\logo.png"); // showing
																	// Picross
																	// icon

		imageicon.getIconWidth();
		centerlabel.setIcon(imageicon);

		JMenuBar menuBar = new JMenuBar();

		file.setMnemonic(KeyEvent.VK_F);
		file.getAccessibleContext().setAccessibleDescription("File menu");
		menuBar.add(file);

		newItem.setMnemonic(KeyEvent.VK_N);
		newItem.setActionCommand("New");
		newItem.addActionListener(gamecontroller);
		file.add(newItem);

		randomItem.setMnemonic(KeyEvent.VK_R);
		randomItem.setActionCommand("Random");
		randomItem.addActionListener(gamecontroller);
		file.add(randomItem);

		openItem.setMnemonic(KeyEvent.VK_O);
		openItem.setActionCommand("Open");
		openItem.addActionListener(gamecontroller);
		file.add(openItem);

		// solutionItem.setMnemonic(KeyEvent.VK_S);
		solutionItem.setActionCommand("Solution");
		solutionItem.addActionListener(gamecontroller);
		file.add(solutionItem);

		saveItem.setMnemonic(KeyEvent.VK_S);
		saveItem.setActionCommand("Save");
		saveItem.addActionListener(gamecontroller);
		file.add(saveItem);

		exitItem.setMnemonic(KeyEvent.VK_E);
		exitItem.setActionCommand("Exit");
		exitItem.addActionListener(gamecontroller);
		file.add(exitItem);

		colors.setActionCommand("Colors");
		colors.addActionListener(gamecontroller);
		settings.add(colors);

		aboutItem.setActionCommand("About");
		aboutItem.addActionListener(gamecontroller);
		settings.add(aboutItem);

		menuBar.add(settings);
		f.setJMenuBar(menuBar);

		mainpanel.setLayout(new BorderLayout());

		mainpanel.add(checkbox, BorderLayout.NORTH);
		centerpanel();

		gamepanel.add(mainpanel, BorderLayout.CENTER);
		labels();
		JPanel eastpanel = new JPanel();
		eastpanel.setBackground(blue); // blue border of east panel
		Border blueline1 = BorderFactory.createEmptyBorder(0, 50, 0, 0); // size of blue border of east panel

		eastpanel.setBorder(blueline1);

		eastpanel.setLayout(new BorderLayout());

		JPanel eastsouthpanel = new JPanel(); // south of east panel
		eastsouthpanel.setLayout(new GridLayout(1, 2)); // this is for 2 buttons

		checkbox.setActionCommand("Mark");
		checkbox.addActionListener(gamecontroller);

		savebutton.setActionCommand("Save");
		savebutton.addActionListener(gamecontroller);

		resetbutton.addActionListener(gamecontroller);
		eastsouthpanel.add(savebutton);
		eastsouthpanel.add(resetbutton);

		JPanel eastnorthpanel = new JPanel(); // north of east panel
		eastnorthpanel.setLayout(new GridLayout(3, 2)); // for creating language, points and time

		timefield.setEditable(false);
		timefield.setText("0"); // 0 in time section

		time();

		String[] languages = { "English", "Persian", "Sinhala" }; // 3 different languages

		JComboBox<String> languagefield = new JComboBox<>(languages); // for creating drop down list

		languagefield.setActionCommand("Language");
		languagefield.addActionListener(gamecontroller);
		languagefield.setSelectedIndex(0);

		pointsfield.setEditable(false);
		pointsfield.setText(Integer.toString(gamemodel.getPoints())); // text in point section
		pointsfield.setActionCommand("Point");
		pointsfield.addActionListener(gamecontroller);

		eastnorthpanel.add(language3); // adding language to east north panel
		eastnorthpanel.add(languagefield);

		eastnorthpanel.add(points); // adding points to east north panel
		eastnorthpanel.add(pointsfield);

		eastnorthpanel.add(time); // adding time to east north panel
		eastnorthpanel.add(timefield);

		eastpanel.add(eastnorthpanel, BorderLayout.NORTH); // the location of east north panel
		eastpanel.add(area, BorderLayout.CENTER); // the location of east east center panel
		eastpanel.add(eastsouthpanel, BorderLayout.SOUTH); // the location of east south panel

		gamepanel.add(eastpanel, BorderLayout.EAST);

		f.add(gamepanel); // adding game panel to JFrame

		f.setSize(700, 600); // size of frame
		f.setVisible(true); // it should be visible

	}

}
