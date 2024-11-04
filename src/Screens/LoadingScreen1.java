package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import SpriteFont.SpriteFont;

import java.awt.*;

public class LoadingScreen1 extends Screen {
    private static boolean checkIFloaded = false;
    protected SpriteFont LoadingTitle;
    protected SpriteFont LoadingMessage;
    protected KeyLocker kl = new KeyLocker(); // 'locks'keys , so that you can prevent the player form accidently
                                              // triggering action to early when screen first loads
    protected boolean isLoaded = false; // signal s when loading is comlete, letting the game move forward to
                                        // PlayerLevel Screen
    protected PlayLevelScreen PLS;
    private ScreenCoordinator SC;
    private static final int Loading_Duration = 300;
    private int timer = 0;

    public LoadingScreen1(ScreenCoordinator SC) {
        this.SC = SC;
        initialize();
    }

    @Override
    public void initialize() {
        LoadingTitle = new SpriteFont("Loading:", 300, 230, "Arial", 30, Color.white);
        LoadingMessage = new SpriteFont("Soon to Uncover the Mysteries... ??", 200, 275, "Arial", 30, Color.white);
        kl.lockKey(Key.SPACE); // locks key when loading screen pops up
    }

    @Override
    public void update() {
        timer++;
        if (LoadingScreen1.checkIFloaded) {
            isLoaded = true;
            SC.setGameState(GameState.LEVEL);// transition to PlayerLevelScreen
        }
        if (timer >= Loading_Duration) { // when timer reaches loading duration , transitons to PlayerLevelScreen
            SC.setGameState(GameState.LEVEL);// Trigger to Transition to PlayerLevelScreen
        }

    }

    public boolean isLoaded() {
        return isLoaded;
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(),
                Color.black);
        LoadingMessage.draw(graphicsHandler); // draws the loaded message
        LoadingTitle.draw(graphicsHandler);
    }

}
