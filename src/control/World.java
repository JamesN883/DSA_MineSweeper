/* Name: 12
 Nguyễn Minh VIệt - ITDSIU21130
 Purpose: handle most of the algorithms of the game.
*/

package control;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

import MineSweeper.CellType;
import view.Move;
import view.ButtonPlay;
import view.ButtonSmile;
import view.GamePanel;
import view.LableNumber;

public class World {
	private Random rd;

	private ButtonPlay[][] arrayButton;
	private int[][] arrayMin;

	private boolean[][] arrayBoolean;

	private boolean[][] arrayFlag;
	private int flag;

	private boolean isComplete;
	private boolean isEnd;

	private ButtonSmile buttonSmile;
	private LableNumber lbTime, lbBoom;

	private int boom;

	private GamePanel game;

	private boolean isFirstClick = true;

	public World(int w, int h, int boom, GamePanel game) {
		showRules();

		this.game = game;
		this.boom = boom;

		arrayButton = new ButtonPlay[w][h];
		arrayMin = new int[w][h];
		arrayBoolean = new boolean[w][h];
		arrayFlag = new boolean[w][h];

		rd = new Random();

		createArrayMin(boom, w, h);
		getNoMine();
	}

	public boolean clickDouble(int i, int j) {

		boolean isMine = false;

		for (int l = i - 1; l <= i + 1; l++) {
			for (int k = j - 1; k <= j + 1; k++) {
				if (l >= 0 && l <= arrayMin.length - 1 && k >= 0 && k <= arrayMin[i].length - 1) {
					if (!arrayFlag[l][k]) {
						if (arrayMin[l][k] == -1) {
							isMine = true;
							arrayButton[l][k].setNumber(12);
							arrayButton[l][k].repaint();
							arrayBoolean[l][k] = true;
						} else if (!arrayBoolean[l][k]) {
							if (arrayMin[l][k] == 0) {
								open(l, k);
							} else {
								arrayButton[l][k].setNumber(arrayMin[l][k]);
								arrayButton[l][k].repaint();
								arrayBoolean[l][k] = true;
							}
						}
					}
				}
			}
		}

		if (isMine) {
			for (int j2 = 0; j2 < arrayBoolean.length; j2++) {
				for (int k = 0; k < arrayBoolean[i].length; k++) {
					if (arrayMin[j2][k] == -1 && !arrayBoolean[j2][k]) {
						arrayButton[j2][k].setNumber(10);
						arrayButton[j2][k].repaint();
					}
				}
			}
			return false;
		}

		return true;
	}

	public void flag(int i, int j) {
		if (!arrayBoolean[i][j]) {
			if (arrayFlag[i][j]) {
				flag--;
				arrayFlag[i][j] = false;
				arrayButton[i][j].setNumber(-1);
				arrayButton[i][j].repaint();
				game.getP1().updateLbBoom();
			} else if (flag < boom) {
				flag++;
				arrayFlag[i][j] = true;
				arrayButton[i][j].setNumber(9);
				arrayButton[i][j].repaint();
				game.getP1().updateLbBoom();
			}
		}
	}

	public boolean open(int i, int j) {
		if (!isComplete && !isEnd) {
			if (!arrayBoolean[i][j]) {
				// First click is not a mine
	            if (isFirstClick) {
	                if (arrayMin[i][j] == -1) {
	                    // Regenerate mine placement until first click is not a mine
	                    do {
	                        createArrayMin(boom, arrayMin.length, arrayMin[0].length);
	                    } while (arrayMin[i][j] == -1);
	                }
	                isFirstClick = false;
	            }
				if (arrayMin[i][j] == 0) {
					arrayBoolean[i][j] = true;
					arrayButton[i][j].setNumber(0);
					arrayButton[i][j].repaint();
					if (checkWin()) {
						isEnd = true;
						return false;
					}
					for (int l = i - 1; l <= i + 1; l++) {
						for (int k = j - 1; k <= j + 1; k++) {
							if (l >= 0 && l <= arrayMin.length - 1 && k >= 0 && k <= arrayMin[i].length - 1) {
								if (!arrayBoolean[l][k]) {
									open(l, k);
								}
							}
						}
					}
					if (checkWin()) {
						isEnd = true;
						return false;
					}
				} else {
					int number = arrayMin[i][j];
					if (number != -1) {
						arrayBoolean[i][j] = true;
						arrayButton[i][j].setNumber(number);
						arrayButton[i][j].repaint();
						if (checkWin()) {
							isEnd = true;
							return false;
						}
						return true;
					}
				}
			}
			if (arrayMin[i][j] == -1) {
				arrayButton[i][j].setNumber(11);
				arrayButton[i][j].repaint();
				isComplete = true;
				for (int j2 = 0; j2 < arrayBoolean.length; j2++) {
					for (int k = 0; k < arrayBoolean[i].length; k++) {
						if (arrayMin[j2][k] == -1 && !arrayBoolean[j2][k]) {
							if (j2 != i || k != j) {
								arrayButton[j2][k].setNumber(10);
								arrayButton[j2][k].repaint();
							}
						}
					}
				}
				return false;
			} else {
				if (checkWin()) {
					isEnd = true;
					return false;
				}
				return true;
			}
		} else
			return false;
	}

	public boolean checkWin() {
		int count = 0;
		for (int i = 0; i < arrayBoolean.length; i++) {
			for (int j = 0; j < arrayBoolean[i].length; j++) {
				if (!arrayBoolean[i][j]) {
					count++;
				}
			}
		}
		if (count == boom)
			return true;
		else
			return false;
	}

	public void getNoMine() {
		for (int i = 0; i < arrayMin.length; i++) {
			for (int j = 0; j < arrayMin[i].length; j++) {
				if (arrayMin[i][j] == 0) {
					int count = 0;
					for (int l = i - 1; l <= i + 1; l++) {
						for (int k = j - 1; k <= j + 1; k++) {
							if (l >= 0 && l <= arrayMin.length - 1 && k >= 0 && k <= arrayMin[i].length - 1)
								if (arrayMin[l][k] == -1) {
									count++;
								}
						}
					}
					arrayMin[i][j] = count;
				}
			}
		}
	}

	public void createArrayMin(int boom, int w, int h) {
		int locationX = rd.nextInt(w);
		int locationY = rd.nextInt(h);

		arrayMin[locationX][locationY] = -1;
		int count = 1;
		while (count != boom) {
			locationX = rd.nextInt(w);
			locationY = rd.nextInt(h);
			if (arrayMin[locationX][locationY] != -1) {

				arrayMin[locationX][locationY] = -1;

				count = 0;
				for (int i = 0; i < arrayMin.length; i++) {
					for (int j = 0; j < arrayMin[i].length; j++) {
						if (arrayMin[i][j] == -1)
							count++;
					}
				}
			}
		}

	}

	public void fullTrue() {
		for (int i = 0; i < arrayBoolean.length; i++) {
			for (int j = 0; j < arrayBoolean[i].length; j++) {
				if (!arrayBoolean[i][j]) {
					arrayBoolean[i][j] = true;
				}
			}
		}
	}
	
	// Saving game status
	/* public void saveProgress() {
	    try (PrintWriter writer = new PrintWriter(new FileWriter("save.txt"))) {
	        // Write player name
	        writer.println(playerName);

	        // Write game state information
	        writer.println(boom);
	        writer.println(arrayMin.length);
	        writer.println(arrayMin[0].length);
	        for (int i = 0; i < arrayMin.length; i++) {
	            for (int j = 0; j < arrayMin[i].length; j++) {
	                writer.println(arrayMin[i][j]);
	                writer.println(arrayBoolean[i][j]);
	                writer.println(arrayFlag[i][j]);
	            }
	        }
	        writer.println(flag);
	        writer.println(isComplete);
	        writer.println(isEnd);

	        // Announce success
	        JOptionPane.showMessageDialog(null, "Status is saved!");
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error saving status: " + e.getMessage());
	    }
	} */

	// Allows user to undo moves 
	/* public void undo() {
	    if (!gameSteps.isEmpty()) {
	        Move lastMove = gameSteps.pop();
	        // Restore button state based on move type
	        if (lastMove.isFlag) {
	            buttons[lastMove.i][lastMove.j].toggleFlag();
	        } else {
	            buttons[lastMove.i][lastMove.j].setNumber(lastMove.number);
	            arrayBoolean[lastMove.i][lastMove.j] = false;
	        }
	        // Update flag count and repaint
	        flag--;
	        repaint();
	        // Optionally, check for win condition
	    }
	} */

	private void showRules() {
	    JOptionPane.showMessageDialog(null,
	    "GAME RULES: \n"
	    + "\n"
	    + "Minesweeper is a game where mines are hidden in a grid of squares.\n"
	    + "There are three difficulties: Basic, Normal, and Hard.\n"
	    + "When first open, the game is set as default (Basic).\n"
	    + "There is \"MENU\" button to change the level of difficulty, initialize a new game, or exit.\n"
	    + "The game ends when all safe squares have been opened.\n"
	    + "Safe squares have numbers telling you how many mines touch the square.\n"
	    + "You can use the number clues to solve the game by opening all of the safe squares.\n"
	    + "If you click on a mine, you lose the game!\n"
	    + "You open squares with the left mouse button and put flags on mines with the right mouse button.\n"
	    //+ "Pressing the right mouse button again changes your flag into a questionmark.\n"
	    + "When you open a square that does not touch any mines, it will be empty.\n"
	    + "The adjacent squares will automatically open in all directions until reaching squares that contain numbers.\n"
	    + "A counter shows the number of mines without flags in the top left corner.\n"
	    + "The top right corner will be the timer, allowing you to know how long it took to solve the level.\n"
	    + "To start a new game, go to \"MENU\" or click smile face.\n"
	    + "You may stop the game by exiting the game.\n"
	    //+ "The game is automatically save and you can either choose to continue or start fresh when reloading the game."
	    );
	}

	public ButtonPlay[][] getArrayButton() {
		return arrayButton;
	}

	public void setArrayButton(ButtonPlay[][] arrayButton) {
		this.arrayButton = arrayButton;
	}

	public ButtonSmile getButtonSmile() {
		return buttonSmile;
	}

	public void setButtonSmile(ButtonSmile buttonSmile) {
		this.buttonSmile = buttonSmile;
	}

	public LableNumber getLbTime() {
		return lbTime;
	}

	public void setLbTime(LableNumber lbTime) {
		this.lbTime = lbTime;
	}

	public LableNumber getLbBoom() {
		return lbBoom;
	}

	public void setLbBoom(LableNumber lbBoom) {
		this.lbBoom = lbBoom;
	}

	public boolean[][] getArrayBoolean() {
		return arrayBoolean;
	}

	public void setArrayBoolean(boolean[][] arrayBoolean) {
		this.arrayBoolean = arrayBoolean;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public boolean[][] getArrayFlag() {
		return arrayFlag;
	}

	public void setArrayFlag(boolean[][] arrayFlag) {
		this.arrayFlag = arrayFlag;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Random getRd() {
		return rd;
	}

	public int[][] getArrayMin() {
		return arrayMin;
	}

	public int getBoom() {
		return boom;
	}

	public GamePanel getGame() {
		return game;
	}

	public boolean isFirstClick() {
		return isFirstClick;
	}

	public void setFirstClick(boolean isFirstClick) {
		this.isFirstClick = isFirstClick;
	}	
}
