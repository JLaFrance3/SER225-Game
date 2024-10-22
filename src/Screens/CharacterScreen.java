package Screens;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class CharacterScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map background;
    protected BufferedImage menuGUI;
    protected BufferedImage menuImage;
    protected JPanel gamePanel;
    protected JTextField nameField;
    protected JLabel nameLabel, skinLabel, hairLabel, eyeLabel, faceHairLabel,
            shirtLabel, pantsLabel, shoesLabel, classLabel;
    protected JButton[] arrowSelectors;
    protected JButton confirmButton, cancelButton;
    protected JToggleButton maleRB, femaleRB;
    protected ButtonGroup genderBG;
    protected KeyLocker keyLocker = new KeyLocker();

    public CharacterScreen(ScreenCoordinator screenCoordinator, JPanel gamePanel) {
        this.screenCoordinator = screenCoordinator;
        this.gamePanel = gamePanel;
    }

    @Override
    public void initialize() {
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyLocker.lockKey(Key.SPACE);

        // Character customization menu components
        menuGUI = ImageLoader.load("CharacterMenuGUI.png", true);
        menuImage = menuGUI.getSubimage(0, 0, 400, 310);
        nameField = new JTextField();
        nameField.setLocation(372, 139);
        nameField.setSize(102, 12);
        nameField.setBorder(null);
        nameField.setBackground(Color.decode("#EFE4B0"));
        nameLabel = new JLabel();
        nameLabel.setText("Name: ");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nameLabel.setLocation(216, 138);
        nameLabel.setSize(148, 14);
        nameLabel.setBorder(null);
        nameLabel.setBackground(null);
        skinLabel = new JLabel();
        skinLabel.setText("Skin Tone: ");
        skinLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        skinLabel.setLocation(216, 183);
        skinLabel.setSize(148, 14);
        skinLabel.setBorder(null);
        skinLabel.setBackground(null);
        hairLabel = new JLabel();
        hairLabel.setText("Hair: ");
        hairLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        hairLabel.setLocation(216, 205);
        hairLabel.setSize(148, 14);
        hairLabel.setBorder(null);
        hairLabel.setBackground(null);
        eyeLabel = new JLabel();
        eyeLabel.setText("Eyes: ");
        eyeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        eyeLabel.setLocation(216, 227);
        eyeLabel.setSize(148, 14);
        eyeLabel.setBorder(null);
        eyeLabel.setBackground(null);
        faceHairLabel = new JLabel();
        faceHairLabel.setText("Face Hair: ");
        faceHairLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        faceHairLabel.setLocation(216, 249);
        faceHairLabel.setSize(148, 14);
        faceHairLabel.setBorder(null);
        faceHairLabel.setBackground(null);
        shirtLabel = new JLabel();
        shirtLabel.setText("Shirt: ");
        shirtLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        shirtLabel.setLocation(216, 271);
        shirtLabel.setSize(148, 14);
        shirtLabel.setBorder(null);
        shirtLabel.setBackground(null);
        pantsLabel = new JLabel();
        pantsLabel.setText("Pants: ");
        pantsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pantsLabel.setLocation(216, 293);
        pantsLabel.setSize(148, 14);
        pantsLabel.setBorder(null);
        pantsLabel.setBackground(null);
        shoesLabel = new JLabel();
        shoesLabel.setText("Shoes: ");
        shoesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        shoesLabel.setLocation(216, 315);
        shoesLabel.setSize(148, 14);
        shoesLabel.setBorder(null);
        shoesLabel.setBackground(null);
        classLabel = new JLabel();
        classLabel.setText("Class Selection: ");
        classLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        classLabel.setLocation(216, 337);
        classLabel.setSize(148, 14);
        classLabel.setBorder(null);
        classLabel.setBackground(null);
        maleRB = new JToggleButton();
        maleRB.setLocation(214, 158);
        maleRB.setSize(19, 19);
        maleRB.setBorder(null);
        maleRB.setIcon(new ImageIcon(menuGUI.getSubimage(401, 81, 19, 19)));
        maleRB.setSelectedIcon(new ImageIcon(menuGUI.getSubimage(422, 81, 19, 19)));
        maleRB.setSelected(true);
        femaleRB = new JToggleButton();
        femaleRB.setLocation(237, 158);
        femaleRB.setSize(19, 19);
        femaleRB.setBorder(null);
        femaleRB.setIcon(new ImageIcon(menuGUI.getSubimage(443, 81, 19, 19)));
        femaleRB.setSelectedIcon(new ImageIcon(menuGUI.getSubimage(464, 81, 19, 19)));
        genderBG = new ButtonGroup();
        genderBG.add(maleRB);
        genderBG.add(femaleRB);
        confirmButton = new JButton();
        confirmButton.setFont(new Font("Serif", Font.PLAIN, 10));
        confirmButton.setHorizontalTextPosition(SwingConstants.CENTER);
        confirmButton.setText("Confirm");
        confirmButton.setLocation(324, 376);
        confirmButton.setSize(66, 21);
        confirmButton.setBorder(null);
        confirmButton.setIcon(new ImageIcon(menuGUI.getSubimage(400, 18, 66, 21)));
        confirmButton.setPressedIcon(new ImageIcon(menuGUI.getSubimage(400, 39, 66, 21)));
        cancelButton = new JButton();
        cancelButton.setFont(new Font("Serif", Font.PLAIN, 10));
        cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
        cancelButton.setText("Cancel");
        cancelButton.setLocation(410, 376);
        cancelButton.setSize(66, 21);
        cancelButton.setBorder(null);
        cancelButton.setIcon(new ImageIcon(menuGUI.getSubimage(466, 18, 66, 21)));
        cancelButton.setPressedIcon(new ImageIcon(menuGUI.getSubimage(466, 39, 66, 21)));

        // Add customization menu components to gamepanel
        gamePanel.add(nameField);
        gamePanel.add(nameLabel);
        gamePanel.add(skinLabel);
        gamePanel.add(hairLabel);
        gamePanel.add(eyeLabel);
        gamePanel.add(faceHairLabel);
        gamePanel.add(shirtLabel);
        gamePanel.add(pantsLabel);
        gamePanel.add(shoesLabel);
        gamePanel.add(classLabel);
        gamePanel.add(maleRB);
        gamePanel.add(femaleRB);
        gamePanel.add(confirmButton);
        gamePanel.add(cancelButton);

        // Arrow selector buttons
        arrowSelectors = new JButton[16];
        for (int i = 0; i < arrowSelectors.length; i++) {
            ImageIcon leftArrow, rightArrow;
            leftArrow = new ImageIcon(menuGUI.getSubimage(401, 61, 18, 18));
            rightArrow = new ImageIcon(menuGUI.getSubimage(421, 61, 18, 18));
            arrowSelectors[i] = new JButton();
            arrowSelectors[i].setSize(18, 18);
            arrowSelectors[i].setBorder(null);
            if (i % 2 == 0) {
                arrowSelectors[i].setLocation(370, 181 + (i/2*22));
                arrowSelectors[i].setIcon(leftArrow);
            }
            else {
                arrowSelectors[i].setLocation(392, 181 + (i/2*22));
                arrowSelectors[i].setIcon(rightArrow);
            }
            gamePanel.add(arrowSelectors[i]);
        }
    }

    public void update() {
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
        graphicsHandler.drawImage(menuImage, 200, 100);
    }
}
