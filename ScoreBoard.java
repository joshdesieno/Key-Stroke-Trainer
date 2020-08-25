/* Josh DeSieno
 *
 * This file contains a simple implementation to keep track of the 
 * number of key-strokes successfully hit in a given turn of the game, 
 * as well as a High-score.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScoreBoard extends JPanel {
	private Model m;
	private JLabel current, best;
	private ActionListener l1;
	private Timer t1;

	public ScoreBoard(int x, int y, Model model) {
		BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
		setBackground(Color.BLACK);
		setLayout(bl);
		m = model;

		current = new JLabel("Current score: " + 
									   Integer.toString(m.getCurrentScore()));
		current.setForeground(Color.WHITE);
		add(current);

		best = new JLabel("High score: " + Integer.toString(m.getMaxScore()));
		best.setForeground(Color.WHITE);
		add(best);

		l1 = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.setText("Current score: " + 
									   Integer.toString(m.getCurrentScore()));
				best.setText("High score: " + 
								           Integer.toString(m.getMaxScore()));
			}
		};

		t1 = new Timer(100, l1);
		t1.start();
	}


}