/* Name: 12
 Nguyễn Minh VIệt - ITDSIU21130
 Purpose: control the panel interactions (with pop-up messages and the main game screen).
*/

package view;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import control.World;

public class GamePanel extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	private PanelNotification p1;
	private PanelPlayer p2;

	private GameFrame gameFrame;

	private World world;

	private int w;
	private int h;
	private int boom;

	public GamePanel(int w, int h, int boom, GameFrame gameFrame) {

		this.gameFrame = gameFrame;

		this.boom = boom;
		this.w = w;
		this.h = h;

		world = new World(w, h, boom, this);

		setLayout(new BorderLayout(20, 20));

		add(p1 = new PanelNotification(this), BorderLayout.NORTH);
		add(p2 = new PanelPlayer(this), BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

    String[] loseMessages = {
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Better luck next time!\nTry again?",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "Don't give up yet!",
            "At least you tried...\nDo you want to give another try?",
            "At least you tried...\nDo you want to give another try?",
            "At least you tried...\nDo you want to give another try?",
            "At least you tried...\nDo you want to give another try?",
            "At least you tried...\nDo you want to give another try?",
            "At least you tried...\nDo you want to give another try?",
            "At least you tried...\nDo you want to give another try?",
            "At least you tried...\nDo you want to give another try?",
            "At least you tried...\nDo you want to give another try?",
            "At least you tried...\nDo you want to give another try?",
            "Oopsie, you tripped!\nGive it another go.",
            "Will thy kindly egress from this particularly irritating situation?",
            "One's actions to complete the exasperating task of obtaining such a fair mandain is preposterously ammusing...",
            "Thus, you as an individual irritate me greatly in even the smallest ways.",
            "One's intent for a malicious comeback is hilariously preposterous and extremely amusing\nRetry?",
            "Such actions compel me to be significantly repolsed!",
            "I am completely baffled by thy's preposterously strange actions.",
            "Thou is the one that seeks the attention of other individuals to search for the absolute desires.",
            "Thus, you as an individual are undeveloped?",
            "It's giving loser now :p\nMay be you should stop and go play chess?",
        };
    int loseIndex = (int) (Math.random() * loseMessages.length);
    
    String[] winMessages = {
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "Lowkey, you good\nStart fresh?",
            "BOOOOOOOOOOM!!! Mic drop!\nNew challenge?",
            "BOOOOOOOOOOM!!! Mic drop!\nNew challenge?",
            "BOOOOOOOOOOM!!! Mic drop!\nNew challenge?",
            "BOOOOOOOOOOM!!! Mic drop!\nNew challenge?",
            "BOOOOOOOOOOM!!! Mic drop!\nNew challenge?",
            "BOOOOOOOOOOM!!! Mic drop!\nNew challenge?",
            "BOOOOOOOOOOM!!! Mic drop!\nNew challenge?",
            "BOOOOOOOOOOM!!! Mic drop!\nNew challenge?",
            "BOOOOOOOOOOM!!! Mic drop!\nNew challenge?",
            "BOOOOOOOOOOM!!! Mic drop!\nNew challenge?",
            "W to thy who deserves!",
            "Winner chicken diner.\nWould you like to be served another meal?",
            "Thy who has passed championship vibe check ^^",
            "Thy will not be canceled by the top G",
            "You crushed, bet!",
            "Thy shall live rent free in hall of fame eternally.",
            "Main character vibe",
            "Ate and left no crumbs",
            "You capping hard, didn't you?",
            "Thou is completely impotent at such festitives that thy individual has vto preposterously escape the demise of a sufferable defeat\nKindly redeem thyself!"
        };
        int winIndex = (int) (Math.random() * winMessages.length);

	@Override
	public void mousePressed(MouseEvent e) {
		getP1().getBt().setStage(ButtonSmile.wow);
		getP1().getBt().repaint();
		ButtonPlay[][] arrayButton = p2.getArrayButton();
		for (int i = 0; i < arrayButton.length; i++) {
			for (int j = 0; j < arrayButton[i].length; j++) {
				if (e.getButton() == 1 && e.getSource() == arrayButton[i][j] && !world.getArrayFlag()[i][j]) {

					if (!getP1().getTime().isRunning()) {
						getP1().getTime().start();
					}

					if (!world.open(i, j)) {

						if (world.isComplete()) {

							getP1().getTime().stop();
							getP1().getBt().setStage(ButtonSmile.lose);
							getP1().getBt().repaint();

							int option = JOptionPane.showConfirmDialog(this, loseMessages[loseIndex], "Notification",
									JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								gameFrame.setVisible(false);
								new GameFrame(w, h, boom);
							} else {
								world.fullTrue();
							}
						} else if (world.isEnd()) {

							getP1().getTime().stop();
							getP1().getBt().setStage(ButtonSmile.win);
							getP1().getBt().repaint();

							int option = JOptionPane.showConfirmDialog(this, winMessages[winIndex], "Notification",
									JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								gameFrame.setVisible(false);
								new GameFrame(w, h, boom);
							}
						}
					}
				} else if (e.getButton() == 3 && e.getSource() == arrayButton[i][j]) {
					world.flag(i, j);
				}
				if (e.getClickCount() == 2 && e.getSource() == arrayButton[i][j] && world.getArrayBoolean()[i][j]) {
					if (!world.clickDouble(i, j)) {

						int option = JOptionPane.showConfirmDialog(this, loseMessages[loseIndex], "Notification",
								JOptionPane.YES_NO_OPTION);

						if (option == JOptionPane.YES_OPTION) {
							gameFrame.setVisible(false);
							new GameFrame(w, h, boom);
						} else {
							world.fullTrue();
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		getP1().getBt().setStage(ButtonSmile.now);
		getP1().getBt().repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	public int getBoom() {
		return boom;
	}

	public void setBoom(int boom) {
		this.boom = boom;
	}

	public PanelNotification getP1() {
		return p1;
	}

	public void setP1(PanelNotification p1) {
		this.p1 = p1;
	}

	public PanelPlayer getP2() {
		return p2;
	}

	public void setP2(PanelPlayer p2) {
		this.p2 = p2;
	}
}
