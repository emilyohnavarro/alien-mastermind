package mastermind;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameBoard extends JFrame implements ActionListener {
	private GameEngine engine;
	private JPanel mainPane, mainBoard, goalPane, buttonPane, rocketPaneMain;
	private JPanel[] rocketPanes;
	private JLabel[][] rocketSpaces; // md array of rocket spaces
	private JLabel[][] spaces; // md array of spaces
	private JLabel g, o, a, l, levelLabel;
	private JButton redButton, yellowButton, greenButton, purpleButton, blueButton, orangeButton, pinkButton,
			aquaButton, okButton, clearButton, instructionsButton, newGameButton, levelButton;
	private ArrayList<JButton> buttons;

	public GameBoard(int level) { // constructor
		super("Alien Mastermind"); // sets the title for the window
		constructBoard(level);
	}

	public void actionPerformed(ActionEvent evt) { // handles user actions
		Object source = evt.getSource(); // get which component the action came from
		if (source == okButton) {
			if (engine.getCurrentSeqSize() == 4) {
				engine.submitPSeq();
				for (int i = 0; i < 4; i++) {
					rocketSpaces[engine.getCurrentRow() + 1][i].setIcon(engine.getCurrentRocketSeq(i).getImage());
				}
			} else {
				JOptionPane.showMessageDialog(null, "Each guess must contain 4 aliens", "Invalid Input",
						JOptionPane.ERROR_MESSAGE);
			}
			if (engine.getPlayerStatus() == GameEngine.WIN) {
				win();
			}
			if (engine.getPlayerStatus() == GameEngine.LOSE) {
				lose();
			}
		} else if (source == clearButton) {
			if (engine.getCurrentSeqSize() != 0) {
				engine.clearCurrentPegSeq();
				for (int i = 0; i < 4; i++) {
					spaces[engine.getCurrentRow()][i].setIcon(new Peg(Peg.EMPTY).getImage());
				}
			}
		} else if (source == instructionsButton) {
			JOptionPane.showMessageDialog(null,
					("Four aliens have arranged themselves in a secret order and are hiding behind the sign marked GOAL."
							+ '\n' + "There are " + (engine.getLevel() + 3) + " different possible colors of aliens."
							+ '\n'
							+ "There may be more than one alien of the same color, and there may be no alien of a particular color."
							+ '\n'
							+ "Try to guess the order in which the four aliens are arranged before you reach the end of the board."
							+ '\n'
							+ "After each guess, zero to four rockets will appear on the rocket panel on the left."
							+ '\n'
							+ "Each blue rocket means that you have placed an alien of the right color in the right position."
							+ '\n'
							+ "Each white rocket means that you have placed an alien of the right color in the wrong position."
							+ '\n' + '\n' + "You are currently playing level " + engine.getLevel()));
		} else if (source == levelButton) {
			Object[] possibleValues = { 1, 2, 3, 4, 5 };

			Object selectedValue = JOptionPane.showInputDialog(null, "Choose a level:", "Level",
					JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
			if (selectedValue != null) {
				this.setVisible(false);
				constructBoard(((Integer) selectedValue));
			}
		} else if (source == newGameButton) {
			int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game?",
					"Confirm New Game", JOptionPane.YES_NO_OPTION);
			if (response == 0) {
				constructBoard(engine.getLevel());
			}
		} else {
			if (engine.getCurrentSeqSize() < 4) {
				if (source == redButton) {
					engine.addPegToSeq(Peg.RED);
				} else if (source == yellowButton) {
					engine.addPegToSeq(Peg.YELLOW);
				} else if (source == greenButton) {
					engine.addPegToSeq(Peg.GREEN);
				} else if (source == purpleButton) {
					engine.addPegToSeq(Peg.PURPLE);
				} else if (source == blueButton) {
					engine.addPegToSeq(Peg.BLUE);
				} else if (source == orangeButton) {
					engine.addPegToSeq(Peg.ORANGE);
				} else if (source == pinkButton) {
					engine.addPegToSeq(Peg.PINK);
				} else if (source == aquaButton) {
					engine.addPegToSeq(Peg.AQUA);
				}
				spaces[engine.getCurrentRow()][engine.getCurrentCol() - 1].setIcon(engine.getLastPeg().getImage());
			}
		}
	}

	private void constructBoard(int level) {
		engine = new GameEngine(level);
		mainPane = new JPanel(); // main panel
		mainBoard = new JPanel(); // initialize main board
		goalPane = new JPanel();
		buttonPane = new JPanel();
		JPanel colorButtonsPane = new JPanel();
		colorButtonsPane.setLayout(new GridLayout((level + 4), 1, 2, 2));
		JPanel gameButtonsPane = new JPanel();
		gameButtonsPane.setLayout(new GridLayout(7, 1, 2, 2));
		rocketPaneMain = new JPanel();
		rocketPanes = new JPanel[10];
		rocketSpaces = new JLabel[10][4];
		spaces = new JLabel[10][4];
		mainPane.setLayout(new BorderLayout(15, 15));
		mainBoard.setLayout(new GridLayout(10, 4, 4, 4));
		buttonPane.setLayout(new GridLayout(2, 1, 2, 2));
		rocketPaneMain.setLayout(new GridLayout(10, 1, 2, 5));
		mainBoard.setBackground(Color.blue); // set background color
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 4; j++) {
				spaces[i][j] = new JLabel(new Peg(Peg.EMPTY).getImage());
				mainBoard.add(spaces[i][j]);
			}
		}
		goalPane.setLayout(new GridLayout(1, 4, 5, 5));
		goalPane.setBackground(Color.white);
		g = new JLabel(new ImageIcon("res/g.gif"));
		o = new JLabel(new ImageIcon("res/o.gif"));
		a = new JLabel(new ImageIcon("res/a.gif"));
		l = new JLabel(new ImageIcon("res/l.gif"));
		goalPane.add(g);
		goalPane.add(o);
		goalPane.add(a);
		goalPane.add(l);

		buttons = new ArrayList<>();

		buttonPane.setBackground(Color.red);
		redButton = new JButton("Red");
		redButton.addActionListener(this);
		buttons.add(redButton);
		yellowButton = new JButton("Yellow");
		yellowButton.addActionListener(this);
		buttons.add(yellowButton);
		greenButton = new JButton("Green");
		greenButton.addActionListener(this);
		buttons.add(greenButton);
		purpleButton = new JButton("Purple");
		purpleButton.addActionListener(this);
		buttons.add(purpleButton);
		okButton = new JButton("Ok");
		okButton.addActionListener(this);
		buttons.add(okButton);
		clearButton = new JButton("Clear");
		clearButton.addActionListener(this);
		buttons.add(clearButton);
		instructionsButton = new JButton("Instructions");
		instructionsButton.addActionListener(this);
		levelButton = new JButton("Change Level");
		levelButton.addActionListener(this);
		colorButtonsPane.setBackground(Color.red);
		colorButtonsPane.add(redButton);
		colorButtonsPane.add(yellowButton);
		colorButtonsPane.add(greenButton);
		colorButtonsPane.add(purpleButton);
		if (engine.getLevel() > 1) {
			blueButton = new JButton("Blue");
			blueButton.addActionListener(this);
			buttons.add(blueButton);
			colorButtonsPane.add(blueButton);
			if (engine.getLevel() > 2) {
				orangeButton = new JButton("Orange");
				orangeButton.addActionListener(this);
				buttons.add(orangeButton);
				colorButtonsPane.add(orangeButton);
				if (engine.getLevel() > 3) {
					pinkButton = new JButton("Pink");
					pinkButton.addActionListener(this);
					buttons.add(pinkButton);
					colorButtonsPane.add(pinkButton);
					if (engine.getLevel() > 4) {
						aquaButton = new JButton("Aqua");
						aquaButton.addActionListener(this);
						buttons.add(aquaButton);
						colorButtonsPane.add(aquaButton);
						colorButtonsPane.add(new JLabel());
					}
				}
			}
		}
		gameButtonsPane.setBackground(Color.red);
		gameButtonsPane.add(okButton);
		gameButtonsPane.add(clearButton);
		gameButtonsPane.add(new JLabel());
		gameButtonsPane.add(instructionsButton);
		levelLabel = new JLabel("Current Level: " + engine.getLevel());
		levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameButtonsPane.add(levelButton);
		gameButtonsPane.add(levelLabel);
		buttons.add(levelButton);
		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(this);
		gameButtonsPane.add(newGameButton);
		buttonPane.add(colorButtonsPane);
		buttonPane.add(gameButtonsPane);
		rocketPaneMain.setBackground(Color.green);
		for (int i = 0; i < 10; i++) {
			rocketPanes[i] = new JPanel();
			rocketPanes[i].setBackground(Color.yellow);
			rocketPanes[i].setLayout(new GridLayout(2, 2, 1, 1));
			for (int j = 0; j < 4; j++) {
				rocketSpaces[i][j] = new JLabel(new Rocket(Rocket.EMPTY).getImage());
				rocketPanes[i].add(rocketSpaces[i][j]);
			}
			rocketPaneMain.add(rocketPanes[i]);
		}
		mainPane.setBackground(Color.black);
		mainPane.add(buttonPane, BorderLayout.EAST);
		mainPane.add(mainBoard, BorderLayout.CENTER);
		mainPane.add(goalPane, BorderLayout.NORTH);
		mainPane.add(rocketPaneMain, BorderLayout.WEST);
		setContentPane(mainPane); // set main pane as the content pane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void win() {
		showGoal();
		JOptionPane.showMessageDialog(null, "Congratulations -- You win!");
		disableButtons();
	}

	private void lose() {
		showGoal();
		JOptionPane.showMessageDialog(null, "Sorry -- You lose.");
		disableButtons();
	}

	private void showGoal() {
		g.setIcon(engine.getGoal().getSequence(0).getImage());
		o.setIcon(engine.getGoal().getSequence(1).getImage());
		a.setIcon(engine.getGoal().getSequence(2).getImage());
		l.setIcon(engine.getGoal().getSequence(3).getImage());
	}

	private void disableButtons() {
		for (JButton button : buttons) {
			button.setEnabled(false);
		}
	}
}