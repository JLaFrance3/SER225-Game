package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LoadingScreen2 extends Screen {
    private static boolean checkIFloaded = false;
    protected SpriteFont LoadingTitle;
    protected SpriteFont LoadingMessage;
    protected SpriteFont LoadingMessage2;
    private BufferedImage backgroundImage;
    private Sprite sprite;
    protected KeyLocker kl = new KeyLocker(); // 'locks'keys , so that you can prevent the player form accidently
                                              // triggering action to early when screen first loads
    protected boolean isLoaded = false; // signal s when loading is comlete, letting the game move forward to
                                        // PlayerLevel Screen
    protected PlayLevelScreen PLS;
    private ScreenCoordinator SC;
    private static final int Loading_Duration = 50;
    private int timer = 10;

    public LoadingScreen2(ScreenCoordinator SC) {
        this.SC = SC;
        initialize();
    }

    @Override
    public void initialize() {
        LoadingTitle = new SpriteFont("Loading:", 315, 80, "Arial", 30, Color.black);
        LoadingMessage = new SpriteFont("Creating a New Character?..I see..", 200, 125, "Arial", 30,
                Color.black);
        LoadingMessage2 = new SpriteFont("Will they be able to see.. the truth lying ahead???", 100, 145,
                "Arial", 30,
                Color.black);
        kl.lockKey(Key.SPACE); // locks key when loading screen pops up

        try {
            backgroundImage = ImageLoader.load("LoadingAnimations/LoadingScreen3.png", false);
            sprite = new Sprite(backgroundImage);// taking in image as sprite
            sprite.setScale(0.6f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        timer++;
        if (LoadingScreen2.checkIFloaded) {
            isLoaded = true;
        }
        if (timer >= Loading_Duration) { // when timer reaches loading duration , transitons to PlayerLevelScreen
            SC.setGameState(GameState.CHARACTER);// Trigger to Transition to PlayerLevelScreen
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
        LoadingMessage2.draw(graphicsHandler);
        LoadingTitle.draw(graphicsHandler);
    }

}
