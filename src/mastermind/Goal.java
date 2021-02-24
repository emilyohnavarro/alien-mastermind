package mastermind;

import java.util.Random;

public class Goal {
	private Peg[] sequence;
	private int[] beenCounted;
	private int level;
	private Random rand = new Random();
	private static final int NUM_PEGS = 4;
	public static final int COUNTEDBLUE = 1;
	public static final int COUNTEDWHITE = 2;
	public static final int NOTCOUNTED = 3;
	

	public Goal(int currentLevel) {
		level = currentLevel;
		sequence = new Peg[NUM_PEGS];
		beenCounted = new int[NUM_PEGS];
		for (int i : beenCounted) {
			beenCounted[i] = NOTCOUNTED;
		}

		switch (level) {
		case 1:
			for (int i = 0; i < NUM_PEGS; i++) {
				double randDouble = rand.nextDouble();
				if (randDouble < 0.25) {
					sequence[i] = new Peg(Peg.RED);
				} else if ((randDouble >= 0.25) && (randDouble < 0.5)) {
					sequence[i] = new Peg(Peg.YELLOW);
				} else if ((randDouble >= 0.5) && (randDouble < 0.75)) {
					sequence[i] = new Peg(Peg.GREEN);
				} else { // (randDouble >= 0.75)
					sequence[i] = new Peg(Peg.PURPLE);
				}
			}
			break;
		case 2:
			for (int i = 0; i < NUM_PEGS; i++) {
				double randDouble = rand.nextDouble();
				if (randDouble < 0.2) {
					sequence[i] = new Peg(Peg.RED);
				} else if ((randDouble >= 0.2) && (randDouble < 0.4)) {
					sequence[i] = new Peg(Peg.YELLOW);
				} else if ((randDouble >= 0.4) && (randDouble < 0.6)) {
					sequence[i] = new Peg(Peg.GREEN);
				} else if ((randDouble >= 0.6) && (randDouble < 0.8)) {
					sequence[i] = new Peg(Peg.PURPLE);
				} else { // (randDouble >= 0.8)
					sequence[i] = new Peg(Peg.BLUE);
				}
			}
			break;
		case 3:
			for (int i = 0; i < NUM_PEGS; i++) {
				double randDouble = rand.nextDouble();
				if (randDouble < 0.167) {
					sequence[i] = new Peg(Peg.RED);
				} else if ((randDouble >= 0.167) && (randDouble < 0.333)) {
					sequence[i] = new Peg(Peg.YELLOW);
				} else if ((randDouble >= 0.333) && (randDouble < 0.5)) {
					sequence[i] = new Peg(Peg.GREEN);
				} else if ((randDouble >= 0.5) && (randDouble < 0.667)) {
					sequence[i] = new Peg(Peg.PURPLE);
				} else if ((randDouble >= 0.667) && (randDouble < 0.833)) {
					sequence[i] = new Peg(Peg.BLUE);
				} else { // (randDouble >= 0.833)
					sequence[i] = new Peg(Peg.ORANGE);
				}
			}
			break;
		case 4:
			for (int i = 0; i < NUM_PEGS; i++) {
				double randDouble = rand.nextDouble();
				if (randDouble < 0.143) {
					sequence[i] = new Peg(Peg.RED);
				} else if ((randDouble >= 0.143) && (randDouble < 0.286)) {
					sequence[i] = new Peg(Peg.YELLOW);
				} else if ((randDouble >= 0.286) && (randDouble < 0.429)) {
					sequence[i] = new Peg(Peg.GREEN);
				} else if ((randDouble >= 0.429) && (randDouble < 0.572)) {
					sequence[i] = new Peg(Peg.PURPLE);
				} else if ((randDouble >= 0.572) && (randDouble < 0.715)) {
					sequence[i] = new Peg(Peg.BLUE);
				} else if ((randDouble >= 0.715) && (randDouble < 0.858)) {
					sequence[i] = new Peg(Peg.ORANGE);
				} else { // (randDouble >= 0.858)
					sequence[i] = new Peg(Peg.PINK);
				}
			}
			break;
		default: // case 5:
			for (int i = 0; i < NUM_PEGS; i++) {
				double randDouble = rand.nextDouble();
				if (randDouble < 0.125) {
					sequence[i] = new Peg(Peg.RED);
				} else if ((randDouble >= 0.125) && (randDouble < 0.25)) {
					sequence[i] = new Peg(Peg.YELLOW);
				} else if ((randDouble >= 0.25) && (randDouble < 0.375)) {
					sequence[i] = new Peg(Peg.GREEN);
				} else if ((randDouble >= 0.375) && (randDouble < 0.5)) {
					sequence[i] = new Peg(Peg.PURPLE);
				} else if ((randDouble >= 0.5) && (randDouble < 0.625)) {
					sequence[i] = new Peg(Peg.BLUE);
				} else if ((randDouble >= 0.625) && (randDouble < 0.75)) {
					sequence[i] = new Peg(Peg.ORANGE);
				} else if ((randDouble >= 0.75) && (randDouble < 0.875)) {
					sequence[i] = new Peg(Peg.PINK);
				} else { // (randDouble >= 0.875)
					sequence[i] = new Peg(Peg.AQUA);
				}
			}
			break;
		}

	}

	public Peg getSequence(int position) {
		return sequence[position];
	}

	public void setPegCount(int index, int color) {
		beenCounted[index] = color;
	}

	public void clearAllCounts() {
		for (int i = 0; i < 4; i++) {
			beenCounted[i] = NOTCOUNTED;
		}
	}

	public int getPegCount(int index) {
		return beenCounted[index];
	}

	public int getLevel() {
		return level;
	}
}