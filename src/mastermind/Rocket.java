package mastermind;

import javax.swing.*;

public class Rocket {
	private int fill;
	private ImageIcon img;
	public static final int BLUE = 1;
	public static final int WHITE = 2;
	public static final int EMPTY = 3;

	public Rocket(int fill1) {
		fill = fill1;
		switch (fill) {
		case BLUE:
			img = new ImageIcon("res/blueRocket.gif");
			break;
		case WHITE:
			img = new ImageIcon("res/whiteRocket.gif");
			break;
		default:
			img = new ImageIcon("res/emptyRocket.gif");
			break;
		}
	}

	public ImageIcon getImage() {
		return img;
	}
}