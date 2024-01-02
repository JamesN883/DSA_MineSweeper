/* Name: 12
 Nguyễn Minh VIệt - ITDSIU21130
 Purpose: contain main method and implement the GUI.
*/

package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import control.World;
import model.LoadData;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private LoadData loadData;

	private GamePanel gamePanel;
	
	private World world;

	private JMenuBar mnb;
	private JMenu menu;
	private JMenuItem basic, nomal, hard, newGame, exit, save, undo;

	public GameFrame(int w, int h, int boom) {
		loadData = new LoadData();
		
		setJMenuBar(mnb = new JMenuBar());
		mnb.add(menu = new JMenu("MENU"));

		menu.add(newGame = new JMenuItem("New game"));
		menu.add(save = new JMenuItem("Save"));
		menu.add(undo = new JMenuItem("Undo"));
		menu.addSeparator();
		menu.add(basic = new JMenuItem("Basic"));
		menu.add(nomal = new JMenuItem("Nomal"));
		menu.add(hard = new JMenuItem("Hard"));
		menu.addSeparator();
		menu.add(exit = new JMenuItem("Exit"));

        // JButton undoButton = new JButton("Undo");
        // mnb.add(undoButton);

        /* undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.undo(); // Call the undo method
            }
        }); */
		
        //JTextField nameField = new JTextField(10); // Adjust width as needed
        //mnb.add(nameField); // Add it to the menu bar

		if (w == 8 && h == 8) {
			basic.setIcon(new ImageIcon(loadData.getListImage().get("check")));
		}
		if (w == 16 && h == 16) {
			nomal.setIcon(new ImageIcon(loadData.getListImage().get("check")));
		}
		if (w == 16 && h == 30) {
			hard.setIcon(new ImageIcon(loadData.getListImage().get("check")));
		}

		basic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
		        world.setFirstClick(true); // Reset isFirstClick
				new GameFrame(8, 8, 10);
			}
		});

		nomal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
		        world.setFirstClick(true); // Reset isFirstClick
				new GameFrame(16, 16, 40);
			}
		});

		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
		        world.setFirstClick(true); // Reset isFirstClick
				new GameFrame(16, 30, 99);
			}
		});

		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
		        world.setFirstClick(true); // Reset isFirstClick
				new GameFrame(w, h, boom);
			}
		});
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});


		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		add(gamePanel = new GamePanel(w, h, boom, this));
	    world = gamePanel.getWorld(); // Access World object
	    
		setIconImage(loadData.getListImage().get("title"));
        setTitle("Minesweeper");
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GameFrame(8, 8, 10);
	}

	public LoadData getLoadData() {
		return loadData;
	}

	public void setLoadData(LoadData loadData) {
		this.loadData = loadData;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
}
