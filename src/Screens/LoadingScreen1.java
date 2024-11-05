package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
    private static final int Loading_Duration = 50;
    private int timer = 0;
    private BufferedImage backgroundImage;
    private Sprite sprite;

    public LoadingScreen1(ScreenCoordinator SC) {
        this.SC = SC;
        initialize();
    }

    @Override
    public void initialize() {
        LoadingTitle = new SpriteFont("Loading:", 300, 230, "Arial", 30, Color.white);
        LoadingMessage = new SpriteFont("Soon to Uncover the Mysteries... ??", 200, 275, "Arial", 30, Color.white);
        kl.lockKey(Key.SPACE); // locks key when loading screen pops up

        try {
            backgroundImage = ImageLoader.load("LoadingAnimations/LoadingScreen1.png", false);
            sprite = new Sprite(backgroundImage);// taking in image as sprite
            sprite.setScale(0.7f);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
        if (backgroundImage != null) {
            sprite.draw(graphicsHandler);
        } else {
            graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(),
                    Color.black);
        }
        LoadingMessage.draw(graphicsHandler); // draws the loaded message
        LoadingTitle.draw(graphicsHandler);
    }
}
