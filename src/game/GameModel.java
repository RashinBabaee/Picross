package game;
/*
 * Name: Rashin Babaee, Sewmini Jayasinghe
 * Professor: Daniel Cormier
 * Course: Java Application Programming
 * Due Date: 9 April 2023
 */

import java.io.PrintWriter;
import java.util.Random;

/**
 * 
 * @author Rashin Babaee, Sewmini Jayasinghe
 *
 */
public class GameModel {
	/**
	 * x and y are int
	 */
	private int x, y;
	/**
	 * array[][] is int
	 */
	private int array[][];

	/**
	 * this function can call createBoard function
	 * 
	 * @param x is int
	 * @param y is int
	 */
	public GameModel(int x, int y) {
		this.x = x;
		this.y = y;
		createBoard();
	}

	/**
	 * this function can return array[x][y]
	 * 
	 * @param x is int
	 * @param y is int
	 * @return array[x][y]
	 */
	public int getModel(int x, int y) {
		return array[x][y];
	}

	/**
	 * this function can check the button
	 * 
	 * @param x is int
	 * @param y is int
	 */
	public void setCheck(int x, int y) {
		if (array[x][y] != 1) {
			array[x][y] = 1;
		} else {
			array[x][y] = 0;
		}

	}

	/**
	 * this function can set value to array[][]
	 * 
	 * @param x     is int
	 * @param y     is int
	 * @param value sets to array
	 */
	public void set(int x, int y, int value) {
		array[x][y] = value;
	}

	/**
	 * this funnction can set checked mark
	 * 
	 * @param x is int
	 * @param y is int
	 */
	public void setMark(int x, int y) {
		if (array[x][y] != 2) {
			array[x][y] = 2;
		} else {
			array[x][y] = 0;
		}
	}

	/**
	 * this function can unchecked the button
	 * 
	 * @param x is int
	 * @param y is int
	 */
	public void setUnchecked(int x, int y) {
		array[x][y] = 0;
	}

	/**
	 * this function can call create board function
	 * 
	 * @param x is int
	 * @param y is int
	 */
	public void changeSize(int x, int y) {
		this.x = x;
		this.y = y;
		createBoard();
	}

	/**
	 * this function can create an unchecked board
	 */
	public void createBoard() {
		points = 0;
		array = new int[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				setUnchecked(i, j);
			}
		}
	}

	/**
	 * points is int
	 */
	private int points;

	/**
	 * this function can return points
	 * 
	 * @return points
	 */
	public int getPoints() {
		return points;

	}

	/**
	 * this function can set p to points
	 * 
	 * @param p is int
	 */
	public void setPoints(int p) {
		points = p;
	}

	/**
	 * this function can print the board
	 */
	public void printBoard() {

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				System.out.print(array[j][i] + " ");
			}

			System.out.println();
		}
	}

	/**
	 * this function can reset the board
	 */
	public void resetBoard() {

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				array[i][j] = 0;
			}
		}
	}

	/**
	 * this function can write the board
	 * 
	 * @param printwriter is PrentWriter
	 */
	public void writeBoard(PrintWriter printwriter) {

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				printwriter.print(array[j][i] + " ");
			}

			printwriter.println();
		}
	}
	/**
	 * this function is for getting String
	 * @return a
	 */
	public String getString() {
		
		String a = "";

		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				a += (array[j][i] + " ");
			}

			a += "=";
		}
		return a;
	}

}
