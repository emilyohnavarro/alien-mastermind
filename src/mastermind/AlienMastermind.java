package mastermind;

public class AlienMastermind {
	private GameBoard board;
	private static final int DEFAULT_LEVEL = 1;

	public void execute() {
		board = new GameBoard(DEFAULT_LEVEL);
	}
	
	public static void main(String args[]) {
		AlienMastermind game = new AlienMastermind();
		game.execute();
	}
}