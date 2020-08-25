/* Josh DeSieno
 * 
 * This file contains a simple character class in which each instance keeps
 * track of its value, its x and y coordinates, as well as its current velocity.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DrawChar {
	private char value;
	private double x;
	private double y;
	static private double motion = 2.0;

	public DrawChar(char c, int x, int y) {
		this.value = c;
		this.x = x;
		this.y = y;
	}

	public static void reset() {
		motion = 2.0;
	}

	public char getValue () {return value;}
	public double getX   () {return x;}

	public void DrawChar(Graphics g, Canvas c) {
		String v = String.valueOf(value);
		g.drawString(v, (int)x, (int)y);
		tick();
	}

	public void tick() {
		if (motion < 8) {
			motion += 0.0002;
		}
		x = x - motion;
	 
	}
}