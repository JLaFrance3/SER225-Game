package Screens;

import Engine.*;
import GameObject.Sprite;
import SpriteFont.SpriteFont;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;

// This class is for the win level screen
public class WinScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont winMessage2;
    protected SpriteFont winMessage3;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;
    private BufferedImage backgroundImage;
    private Sprite sprite;

    public WinScreen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }

    @Override
    public void initialize() {
        winMessage = new SpriteFont("You win!", 350, 239, "Arial", 30, Color.white);        
        winMessage2 = new SpriteFont("The Forseeable Eye has been retrieved and the King is Pleased.", 150, 279, "Arial", 20, Color.white);
        winMessage3 = new SpriteFont("You have saved The Kingdom... or have you??", 210, 309, "Arial", 20, Color.white);
        instructions = new SpriteFont("Press Space to play again or Escape to go back to the main menu", 170, 400,"Arial", 15, Color.white);
        keyLocker.lockKey(Key.SPACE);
        keyLocker.lockKey(Key.ESC);

        try {
            backgroundImage = ImageLoader.load("WinScreenImage.png", false);
            sprite = new Sprite(backgroundImage);// taking in image as sprite
            sprite.setScale(1.3f);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            playLevelScreen.resetLevel();
        } else if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC)) {
            playLevelScreen.goBackToMenu();
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (backgroundImage != null) {
            sprite.draw(graphicsHandler);
        } else {
            graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(),
                    Color.black);
        }
        
        winMessage.draw(graphicsHandler);
        winMessage2.draw(graphicsHandler);
        winMessage3.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}
