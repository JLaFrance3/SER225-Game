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

public class TitleScreen extends Screen {
    private static boolean checkIFloaded = false;
    protected SpriteFont LoadingTitle;
    protected SpriteFont LoadingMessage;
    protected KeyLocker kl = new KeyLocker(); // 'locks'keys , so that you can prevent the player form accident
                                              // triggering action to early when screen first loads
    protected boolean isLoaded = false; // signal s when loading is comlete, letting the game move forward to
                                        // PlayerLevel Screen
    protected PlayLevelScreen PLS;
    private ScreenCoordinator SC;
    private static final int Loading_Duration = 300;
    private int timer = 2;
    private BufferedImage backgroundImage;
    private Sprite sprite;

    public TitleScreen(ScreenCoordinator SC) {
        this.SC = SC;
        initialize();
    }

    @Override
    public void initialize() {
        LoadingTitle = new SpriteFont("THE THEIF OF VISIONS ", 170, 100, "Algerian", 50, Color.white);
        kl.lockKey(Key.SPACE); // locks key when loading screen pops up

        try {
            backgroundImage = ImageLoader.load("LoadingAnimations/GameIntro Backround.png", false);
            sprite = new Sprite(backgroundImage);// taking in image as sprite
            sprite.setScale(0.9f);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        timer++;
        if (TitleScreen.checkIFloaded) {
            isLoaded = true;
            SC.setGameState(GameState.MENU);// transition to PlayerLevelScreen
        }
        if (timer >= Loading_Duration) { // w
            SC.setGameState(GameState.MENU);
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
        // LoadingMessage.draw(graphicsHandler); // draws the loaded message
        LoadingTitle.draw(graphicsHandler);
    }
}
