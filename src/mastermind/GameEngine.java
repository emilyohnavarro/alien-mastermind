package mastermind;

import java.util.ArrayList;

public class GameEngine {
	private Goal target;
	private ArrayList<Peg> currentPegSeq;
	private ArrayList<Rocket> currentRocketSeq;
	private int currentRow;
	private int currentCol;
	private int winOrLose;

	public static final int WIN = 1;
	public static final int LOSE = 2;
	public static final int INPROGRESS = 3;

	public GameEngine(int level) {
		target = new Goal(level);
		currentPegSeq = new ArrayList<>(4);
		currentRocketSeq = new ArrayList<>(4);
		currentRow = 9;
		currentCol = 0;
		winOrLose = INPROGRESS;
	}

	public void addPegToSeq(int color) {
		if (currentPegSeq.size() < 4) {
			currentPegSeq.add(new Peg(color));
			currentCol++;
		}
	}

	public void submitPSeq() {
		int numBlueRockets = 0;
		int numWhiteRockets = 0;

		for (int i = 0; i < 4; i++) // go through each peg in sequence
		{
			if (((Peg) currentPegSeq.get(i)).getColor() == (target.getSequence(i).getColor())) {
				numBlueRockets++;
				target.setPegCount(i, Goal.COUNTEDBLUE);
			}
		}
		for (int i = 0; i < 4; i++) // go through each peg again to test for white rockets
		{
			goThruTarget: for (int j = 0; j < 4; j++) // go through each peg in target until there is a match or target
														// sequence has been exhaustively searched
			{
				if ((((Peg) currentPegSeq.get(i)).getColor() == (target.getSequence(j).getColor()))
						&& (target.getPegCount(i) != Goal.COUNTEDBLUE) && (target.getPegCount(j) == Goal.NOTCOUNTED)) {
					numWhiteRockets++;
					target.setPegCount(j, Goal.COUNTEDWHITE);
					break goThruTarget;
				}
			}
		}
		if (numBlueRockets == 4) {
			winOrLose = WIN;
		}
		if ((numBlueRockets != 4) && currentRow == 0) {
			winOrLose = LOSE;
		}
		for (int i = 0; i < 4; i++) {
			if (numBlueRockets > 0) {
				Rocket r = new Rocket(Rocket.BLUE);
				currentRocketSeq.add(i, r);
				numBlueRockets--;
			} else {
				if (numWhiteRockets > 0) {
					Rocket r = new Rocket(Rocket.WHITE);
					currentRocketSeq.add(i, r);
					numWhiteRockets--;
				} else {
					Rocket r = new Rocket(Rocket.EMPTY);
					currentRocketSeq.add(i, r);
				}
			}
		}
		currentPegSeq.clear();
		currentRow--;
		currentCol = 0;
		target.clearAllCounts();
	}

	public Goal getGoal() {
		return target;
	}

	public int getCurrentRow() {
		return currentRow;
	}

	public int getCurrentCol() {
		return currentCol;
	}

	public Peg getLastPeg() {
		return (Peg) currentPegSeq.get(currentPegSeq.size() - 1);
	}

	public int getCurrentSeqSize() {
		return currentPegSeq.size();
	}

	public Rocket getCurrentRocketSeq(int index) {
		return (Rocket) (currentRocketSeq.get(index));
	}

	public void clearCurrentPegSeq() {
		currentPegSeq.clear();
		currentCol = 0;
	}

	public int getPlayerStatus() {
		return winOrLose;
	}

	public int getLevel() {
		return target.getLevel();
	}
}