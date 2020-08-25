/* Josh DeSieno
 *
 * This file contains an implementation of a canvas which is passed an
 * instance of a model of the game. It then draws the game in its initial
 * state and continually redraws the game based on the ever-changing state of 
 * its model, m. 
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Canvas extends JPanel implements MouseListener {
	private Model m; 
	private boolean started, gameOver;
	private Dimension size;
	private int width, height;

	public Canvas(Model m) {
		addMouseListener(this);
		started = false;
		gameOver = false;
		this.m = m;
	}

	//Continually repaint the game using model, m.
	public void paintComponent (Graphics g) {
		setBackground(Color.BLACK);
		size = getSize();
		width = size.width;
		height = size.height;
		g.setFont(new Font ("Lucida", 1, 40));
		if (!started) {
			g.setColor(Color.RED);
			g.drawString("Click anywhere to begin typing", 
				     width/2 - 400, height/2);
		}
		
		g.setColor(Color.WHITE);
		m.drawChars(g, this);
		
		if (m.gameOver()) gameOver = true;
		if (gameOver) {
			g.setColor(Color.RED);
			g.drawString("Game Over! Click anywhere to restart.", 
					width/2 - 450, height/2);
		}
	}

	//Press the moust to begin playing
	public void mousePressed (MouseEvent event) {
		if (!started) {
			started = true;
			m.start();
		} else if (gameOver) {
			gameOver = false;
			m.restart();
		}
	}

	public void mouseClicked (MouseEvent event) {}
    public void mouseReleased (MouseEvent event) {}
    public void mouseEntered (MouseEvent event) {}
    public void mouseExited (MouseEvent event) {}
}
