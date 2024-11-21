package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import java.awt.Color;

import java.awt.*;

// This class is for the credits screen
public class ControlScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected KeyLocker keyLocker = new KeyLocker();
    protected SpriteFont cl;
    protected SpriteFont HowToPLay;
    protected SpriteFont el;
    protected SpriteFont kp;
    protected SpriteFont kp2;

    protected SpriteFont SP;
    protected SpriteFont n;
    protected SpriteFont I;
    protected SpriteFont C;
    protected SpriteFont shiftKey;
    protected SpriteFont iKey;
    protected SpriteFont inventoryDetails;
    protected SpriteFont FightorRun;
    protected SpriteFont cc;
    protected SpriteFont returnInstructionsLabel;
    protected SpriteFont key;

    public ControlScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        // setup graphics on screen (background map, spritefont text)
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        cl = new SpriteFont("Controls", 20, 10, "ALGERIAN", 30, Color.white);
        HowToPLay = new SpriteFont("How to Play ", 130, 121, "ALGERIAN", 20, Color.white);
        el = new SpriteFont("Movement: WASD Keyboard Keys or Arrow Keys ", 130, 150, "Times New Roman", 20,
                Color.white);

        kp = new SpriteFont("QUEST LOG: ", 130, 175, "ALGERIAN", 20, Color.white); // Quest Log header
        kp2 = new SpriteFont("Press Q to check your current quest", 130, 210, "Times New Roman", 20, Color.white); // Quest

        SP = new SpriteFont("Interact Key: ", 130, 260, "ALGERIAN", 20, Color.white); // Interact key instruction
        n = new SpriteFont("Press Space Key ", 130, 280, "Times New Roman", 20, Color.white); // Interact key

        shiftKey = new SpriteFont("Shift Key - Sprint", 130, 310, "ALGERIAN", 20, Color.white); // Sprint action

        iKey = new SpriteFont("Inventory:", 130, 340, "ALGERIAN", 20, Color.white); // Inventory action
        key = new SpriteFont("Press I key to see Invetory ", 130, 370, "Times New Roman", 20, Color.white);

        inventoryDetails = new SpriteFont("Press O for item descriptions: A and D to switch items", 130, 400,
                "Times New Roman", 20, Color.white); // Inventory description keys

        C = new SpriteFont("In Combat:", 130, 440, "ALGERIAN", 20, Color.white); // Combat
        cc = new SpriteFont("WASD to switch between options: items, spell or attack", 130, 460, "Times New Roman", 20,
                Color.white); // navigation
        FightorRun = new SpriteFont("Use Space to select an option (Fight or Run)", 130, 500, "Times New Roman", 20,
                Color.white); // Fight/Run select option

        returnInstructionsLabel = new SpriteFont("Press Space to return to the menu", 20, 532, "Times New Roman", 30,
                Color.white);
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // background.update(null);

        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        // if space is pressed, go back to main menu
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        cl.draw(graphicsHandler);
        HowToPLay.draw(graphicsHandler);
        el.draw(graphicsHandler);
        kp.draw(graphicsHandler);
        kp2.draw(graphicsHandler);
        SP.draw(graphicsHandler);
        n.draw(graphicsHandler);
        iKey.draw(graphicsHandler);
        key.draw(graphicsHandler);
        FightorRun.draw(graphicsHandler);
        C.draw(graphicsHandler);
        cc.draw(graphicsHandler);
        inventoryDetails.draw(graphicsHandler);
        returnInstructionsLabel.draw(graphicsHandler);
    }
}
