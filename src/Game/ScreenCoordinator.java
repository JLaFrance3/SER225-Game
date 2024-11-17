package Game;

import Engine.DefaultScreen;
import Engine.GraphicsHandler;
import Engine.Screen;
import Screens.CharacterScreen;
import Screens.CreditsScreen;
import Screens.LoadingScreen1;
import Screens.LoadingScreen2;
import Screens.MenuScreen;
import Screens.PlayLevelScreen;
import javax.swing.JPanel;
import GameObject.SpriteSheet;

/*
 * Based on the current game state, this class determines which Screen should be shown
 * There can only be one "currentScreen", although screens can have "nested" screens
 */
public class ScreenCoordinator extends Screen {
	// currently shown Screen
	protected Screen currentScreen = new DefaultScreen();

	// keep track of gameState so ScreenCoordinator knows which Screen to show
	protected GameState gameState;
	protected GameState previousGameState;

	// GamePanel used by some screens to access swing components
	protected JPanel gamePanel;

	public ScreenCoordinator(JPanel gp) {
		this.gamePanel = gp;
	}

	public GameState getGameState() {
		return gameState;
	}

	// Other Screens can set the gameState of this class to force it to change the
	// currentScreen
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void initialize() {
		// start game off with Menu Screen
		gameState = GameState.MENU;
	}

	@Override
	public void update() {
		do {
			// if previousGameState does not equal gameState, it means there was a change in
			// gameState
			// this triggers ScreenCoordinator to bring up a new Screen based on what the
			// gameState is
			if (previousGameState != gameState) {
				switch (gameState) {
					case MENU:
						currentScreen = new MenuScreen(this);
						break;
					case LOADING:
						currentScreen = new LoadingScreen1(this);
						break;
					case LEVEL:
						if (previousGameState == GameState.CHARACTER) {
							SpriteSheet[] playerSpriteComponents = ((CharacterScreen) currentScreen)
									.getPlayerSpriteComponents();
							String playerName = ((CharacterScreen) currentScreen).getPlayerName();
							boolean player_isMale = ((CharacterScreen) currentScreen).getPlayerGender();
							String playerClass = ((CharacterScreen) currentScreen).getPlayerClass();
							currentScreen = new PlayLevelScreen(this, playerSpriteComponents, playerName, player_isMale,
									playerClass);
						} else {
							currentScreen = new PlayLevelScreen(this);
						}
						break;
					case LOADING2:
						currentScreen = new LoadingScreen2(this);
						break;
					case CHARACTER:
						currentScreen = new CharacterScreen(this, gamePanel);
						break;
					case CREDITS:
						currentScreen = new CreditsScreen(this);
						break;
				}
				currentScreen.initialize();
			}
			previousGameState = gameState;

			// call the update method for the currentScreen
			currentScreen.update();
		} while (previousGameState != gameState);
	}

	private void printMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		long usedMemory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory: " + usedMemory / 1024 / 1024 + " MB");
	}

	@Override
	public void draw(GraphicsHandler graphicsHandler) {
		// call the draw method for the currentScreen
		currentScreen.draw(graphicsHandler);
	}
}
