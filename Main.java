/* Josh DeSieno
 *
 * This program runs a simple key-stroke training game, in which characters
 * fly accross the screen at an ever-increasing speed and the user must 
 * type each character on the screen before it completes its journey.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class Main extends JFrame {
	public static void main (String [] args) {
		new Main();
	}

	public Main() {	
		BorderLayout bl = new BorderLayout();
		setLayout(bl);

		setSize(1200, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Model model = new Model();
		addKeyListener(model);

		Canvas canvas = new Canvas(model);
		getContentPane().add(canvas);

		ScoreBoard scores = new ScoreBoard(40, 80, model);
		getContentPane().add(scores, BorderLayout.EAST);

		setVisible(true);	
	}
}
