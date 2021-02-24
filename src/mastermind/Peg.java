package mastermind;

import javax.swing.*;

public class Peg {
	private int color;
	private ImageIcon img;
	
	public static final int RED = 1;
	public static final int YELLOW = 2;
	public static final int GREEN = 3;
	public static final int PURPLE = 4;
	public static final int BLUE = 5;
	public static final int ORANGE = 6;
	public static final int PINK = 7;
	public static final int AQUA = 8;
	public static final int EMPTY = 9;

	public Peg(int color1) {
		color = color1;
		switch (color) {
		case RED:
			img = new ImageIcon("res/redAlien.gif");
			break;
		case YELLOW:
			img = new ImageIcon("res/yellowAlien.gif");
			break;
		case GREEN:
			img = new ImageIcon("res/greenAlien.gif");
			break;
		case PURPLE:
			img = new ImageIcon("res/purpleAlien.gif");
			break;
		case BLUE:
			img = new ImageIcon("res/blueAlien.gif");
			break;
		case ORANGE:
			img = new ImageIcon("res/orangeAlien.gif");
			break;
		case PINK:
			img = new ImageIcon("res/pinkAlien.gif");
			break;
		case AQUA:
			img = new ImageIcon("res/aquaAlien.gif");
			break;
		default:
			img = new ImageIcon("res/emptyPeg.gif");
			break;
		}
	}

	public int getColor() {
		return color;
	}

	public ImageIcon getImage() {
		return img;
	}
}