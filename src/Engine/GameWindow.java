package Engine;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;

/*
 * The JFrame that holds the GamePanel
 * Just does some setup and exposes the gamePanel's screenManager to allow an external class to setup their own content and attach it to this engine.
 */
public class GameWindow {
	private JFrame gameWindow;
	private GamePanel gamePanel;

	public GameWindow() {
		gameWindow = new JFrame("Thief of Visions");
		gamePanel = new GamePanel();
		gamePanel.setFocusable(true);
		gamePanel.requestFocusInWindow();
		gameWindow.setContentPane(gamePanel);
		gameWindow.setResizable(false);
		gameWindow.setSize(Config.GAME_WINDOW_WIDTH, Config.GAME_WINDOW_HEIGHT);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // it'd be nice if this actually worked more than
																	// 1/3rd of the time
		gamePanel.setupGame();

		try {
			Image I = ImageIO.read(new File("Resources/Icon/shadow2.png"));
			gameWindow.setIconImage(I);
			// gameWindow.setIconImage(ImageIO.read(getClass().getResourceAsStream("/Resources/castle.png")));
		} catch (IOException e) {
			System.out.println("Icon Image Error");
			e.printStackTrace();
		}
	}

	// triggers the game loop to start as defined in the GamePanel class
	public void startGame() {
		gamePanel.startGame();
	}

	public ScreenManager getScreenManager() {
		return gamePanel.getScreenManager();
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}
}
