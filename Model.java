/* Josh DeSieno
 * 
 * This file contains the model of an instance of the key-stroke training 
 * game. It maintains a list of the DrawChar class to continually update 
 * the state of the game.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

public class Model implements KeyListener {
	private Random rand = new Random();
	private Timer timer;
	private int timerMS = 1000, currentScore = 0, maxScore = 0;

	private ArrayList<DrawChar> onScreen = new ArrayList<DrawChar>(); 
	private char [] alphanums = 
				{'a','b','c','d','e','f','g','h','i','j','k','l','m', 
				 'n','o','p','q','r','s','t','u','v','w','x','y','z',	 
				 'A','B','C','D','E','F','G','H','I','J','K','L','M', 
				 'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',    
				 '0','1','2','3','4','5','6','7','8','9'};

	public Model() {
		timer = new Timer(timerMS, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nextY = rand.nextInt(400) + 60;
				onScreen.add(new DrawChar(randChar(), 1200, nextY));
				if (nextY % 2 == 0) {
					onScreen.add(new DrawChar(randChar(), 1200, nextY - 50));
				}
				if (timerMS > 100) {
					timerMS -= 10;
				}
			}
		});
	}

	//  For debugging purposes only  //
	public void printScreen() {
		System.out.print("[");
		for (DrawChar x : onScreen) {
			System.out.print(x.getValue() + ", ");
		}
		System.out.println("]");
	}

	//generate a random alphanumeric character
	private char randChar() {
		int index = rand.nextInt(62);
		return alphanums[index];
	}

	//Logic for removing characters as the keys are stroked.
	private void removeChar(char c) {
		int index = 0;
		boolean remove = false;
		for (DrawChar x : onScreen) {
			if (c == x.getValue()) {
				remove = true;
				break;
			}
			index++;
		}
		if (remove) {
			currentScore++;
			if (currentScore > maxScore) {
				maxScore++;
			}
			onScreen.remove(index);
		}
	}

	public int getCurrentScore () { return currentScore; }
	public int getMaxScore     () { return maxScore; }

	//Paint each individual character
	public void drawChars(Graphics g, Canvas c) {
		for (DrawChar x : onScreen) {
			x.DrawChar(g, c);
		}
		c.repaint();
	}

	public void start() {
		timer.start(); 
	}

	public boolean gameOver() {
		for (DrawChar d : onScreen) {
			if (d.getX() < 0) {
				timer.stop();
				currentScore = 0;
				onScreen.clear();
				timerMS = 1000;
				return true;
			}
		}
		return false;
	}

	public void restart() {
		for (DrawChar x : onScreen) {
			onScreen.remove(0);
		}
		DrawChar.reset();
		timer.restart();
	}

	public void keyTyped(KeyEvent e) {
		char typed = e.getKeyChar();
		removeChar(typed);
	}

	public void keyPressed (KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
}
