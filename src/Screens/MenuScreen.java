package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont playGame, newGame, control;
    protected SpriteFont credits;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        playGame = new SpriteFont("PLAY GAME", 200, 123, "Arial", 30, new Color(49, 207, 240));
        playGame.setOutlineColor(Color.black);
        playGame.setOutlineThickness(3);
        newGame = new SpriteFont("NEW GAME", 200, 223, "Arial", 30, new Color(49, 207, 240));
        newGame.setOutlineColor(Color.black);
        newGame.setOutlineThickness(3);
        credits = new SpriteFont("CREDITS", 200, 323, "Arial", 30, new Color(49, 207, 240));
        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(3);
        control = new SpriteFont("CONTROLS", 200, 432, "Arial", 30, new Color(49, 207, 240));
        control.setOutlineColor(Color.black);
        control.setOutlineThickness(3);
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        // if down or up is pressed, change menu item "hovered" over (blue square in
        // front of text will move along with currentMenuItemHovered changing)
        if ((Keyboard.isKeyDown(Key.DOWN) || Keyboard.isKeyDown(Key.S)) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered++;
        } else if ((Keyboard.isKeyDown(Key.UP) || Keyboard.isKeyDown(Key.W)) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered--;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        // if down is pressed on last menu item or up is pressed on first menu item,
        // "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 3) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 3;
        }

        // sets location for blue square in front of text (pointerLocation) and also
        // sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            playGame.setColor(new Color(255, 215, 0));
            newGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(49, 207, 240));
            control.setColor(new Color(49, 215, 240));
            pointerLocationX = 170;
            pointerLocationY = 130;
        } else if (currentMenuItemHovered == 1) {
            playGame.setColor(new Color(49, 207, 240));
            newGame.setColor(new Color(255, 215, 0));
            credits.setColor(new Color(49, 207, 240));
            control.setColor(new Color(49, 207, 240));
            pointerLocationX = 170;
            pointerLocationY = 230;
        } else if (currentMenuItemHovered == 2) {
            playGame.setColor(new Color(49, 207, 240));
            newGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(255, 215, 0));
            control.setColor(new Color(49, 207, 240));
            pointerLocationX = 170;
            pointerLocationY = 330;
        } else if (currentMenuItemHovered == 3) {
            playGame.setColor(new Color(49, 207, 240));
            newGame.setColor(new Color(49, 207, 240));
            credits.setColor(new Color(49, 207, 240));
            control.setColor(new Color(225, 215, 0));

            pointerLocationX = 170;
            pointerLocationY = 430;
        }
        // if space is pressed on menu item, change to appropriate screen based on which
        // menu item was chosen
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {// if space key is pressed, item is
                                                                                 // selected
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 0) {// if first menu item is selected "Play Game", sets ScreenCoordinator game state
                                        // to LEVEL
                screenCoordinator.setGameState(GameState.LOADING);// transition to loading screen first
            } else if (menuItemSelected == 1) { // sets ScreenCoordinator game state to Character
                screenCoordinator.setGameState(GameState.LOADING2);
            } else if (menuItemSelected == 2) {// sets ScreenCoordinator game state to CREDITS
                screenCoordinator.setGameState(GameState.CREDITS);
            } else if (menuItemSelected == 3) {
                screenCoordinator.setGameState(GameState.CONTROL);
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        playGame.draw(graphicsHandler);
        newGame.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        control.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20,
                new Color(49, 207, 240), Color.black, 2);
    }
}
