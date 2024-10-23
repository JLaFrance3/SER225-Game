package Screens;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.SpriteSheet;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CharacterScreen extends Screen {
    private ScreenCoordinator screenCoordinator;
    private Map background;
    private BufferedImage menuGUI;
    private BufferedImage menuImage;
    private JPanel gamePanel;
    private JTextField nameField;
    private JLabel nameLabel, skinLabel, hairLabel, hairColorLabel, eyeLabel, faceHairLabel,
            shirtLabel, pantsLabel, shoesLabel, classLabel;
    private JButton[] arrowSelectors;
    private JButton confirmButton, cancelButton, exitButton;
    private JToggleButton maleRB, femaleRB;
    private ButtonGroup genderBG;
    private ButtonListener buttonListener;
    private KeyLocker keyLocker = new KeyLocker();

    private SpriteSheet[][] headAndBodySpritesheets;
    private SpriteSheet[][] hairSpritesheets;
    private String[] hairColors;
    private SpriteSheet[] eyeSpritesheets;
    private SpriteSheet[][] faceHairSpritesheets;
    private SpriteSheet[] shirtSpritesheet;
    private SpriteSheet[] pantSpritesheet;
    private SpriteSheet[] shoeSpritesheets;
    private String name;
    private Boolean isMale;
    private int[] spriteSelections;
    private int[] previousSelections;
    private BufferedImage displaySprite, scaleDisplaySprite;

    public CharacterScreen(ScreenCoordinator screenCoordinator, JPanel gamePanel) {
        this.screenCoordinator = screenCoordinator;
        this.gamePanel = gamePanel;
        this.buttonListener = new ButtonListener();
        this.name = "Doug";
        this.isMale = true;
        this.spriteSelections = new int[8];
        this.spriteSelections[2] = 1;
        this.spriteSelections[4] = -1;
        this.previousSelections = new int[8];
        this.hairColors = new String[] {
            "/ash", "/black", "/blonde", "/chestnut", "/dark_brown",
            "/dark_gray", "/ginger", "/gray", "/green", "/light_brown",
            "/pink", "/platinum", "/raven", "/redhead", "/sandy",
            "/strawberry", "/white",
        };
        this.displaySprite = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB); 
        this.scaleDisplaySprite = new BufferedImage(128, 128, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void initialize() {
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyLocker.lockKey(Key.SPACE);

        // Character customization menu components
        menuGUI = ImageLoader.load("CharacterMenuGUI.png", true);
        menuImage = menuGUI.getSubimage(0, 0, 400, 330);
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
        hairColorLabel = new JLabel();
        hairColorLabel.setText("Hair Color: ");
        hairColorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        hairColorLabel.setLocation(216, 227);
        hairColorLabel.setSize(148, 14);
        hairColorLabel.setBorder(null);
        hairColorLabel.setBackground(null);
        eyeLabel = new JLabel();
        eyeLabel.setText("Eyes: ");
        eyeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        eyeLabel.setLocation(216, 249);
        eyeLabel.setSize(148, 14);
        eyeLabel.setBorder(null);
        eyeLabel.setBackground(null);
        faceHairLabel = new JLabel();
        faceHairLabel.setText("Face Hair: ");
        faceHairLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        faceHairLabel.setLocation(216, 271);
        faceHairLabel.setSize(148, 14);
        faceHairLabel.setBorder(null);
        faceHairLabel.setBackground(null);
        shirtLabel = new JLabel();
        shirtLabel.setText("Shirt: ");
        shirtLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        shirtLabel.setLocation(216, 293);
        shirtLabel.setSize(148, 14);
        shirtLabel.setBorder(null);
        shirtLabel.setBackground(null);
        pantsLabel = new JLabel();
        pantsLabel.setText("Pants: ");
        pantsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pantsLabel.setLocation(216, 315);
        pantsLabel.setSize(148, 14);
        pantsLabel.setBorder(null);
        pantsLabel.setBackground(null);
        shoesLabel = new JLabel();
        shoesLabel.setText("Shoes: ");
        shoesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        shoesLabel.setLocation(216, 337);
        shoesLabel.setSize(148, 14);
        shoesLabel.setBorder(null);
        shoesLabel.setBackground(null);
        classLabel = new JLabel();
        classLabel.setText("Class Selection: ");
        classLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        classLabel.setLocation(216, 359);
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
        maleRB.addActionListener(buttonListener);
        femaleRB = new JToggleButton();
        femaleRB.setLocation(237, 158);
        femaleRB.setSize(19, 19);
        femaleRB.setBorder(null);
        femaleRB.setIcon(new ImageIcon(menuGUI.getSubimage(443, 81, 19, 19)));
        femaleRB.setSelectedIcon(new ImageIcon(menuGUI.getSubimage(464, 81, 19, 19)));
        femaleRB.addActionListener(buttonListener);
        genderBG = new ButtonGroup();
        genderBG.add(maleRB);
        genderBG.add(femaleRB);
        confirmButton = new JButton();
        confirmButton.setFont(new Font("Serif", Font.PLAIN, 10));
        confirmButton.setHorizontalTextPosition(SwingConstants.CENTER);
        confirmButton.setText("Confirm");
        confirmButton.setLocation(324, 396);
        confirmButton.setSize(66, 21);
        confirmButton.setBorder(null);
        confirmButton.setIcon(new ImageIcon(menuGUI.getSubimage(400, 18, 66, 21)));
        confirmButton.setPressedIcon(new ImageIcon(menuGUI.getSubimage(400, 39, 66, 21)));
        confirmButton.addActionListener(buttonListener);
        cancelButton = new JButton();
        cancelButton.setFont(new Font("Serif", Font.PLAIN, 10));
        cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
        cancelButton.setText("Cancel");
        cancelButton.setLocation(410, 396);
        cancelButton.setSize(66, 21);
        cancelButton.setBorder(null);
        cancelButton.setIcon(new ImageIcon(menuGUI.getSubimage(466, 18, 66, 21)));
        cancelButton.setPressedIcon(new ImageIcon(menuGUI.getSubimage(466, 39, 66, 21)));
        cancelButton.addActionListener(buttonListener);
        exitButton = new JButton();
        exitButton.setLocation(570, 116);
        exitButton.setSize(14, 14);
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(buttonListener);

        // Add customization menu components to gamepanel
        gamePanel.add(nameField);
        gamePanel.add(nameLabel);
        gamePanel.add(skinLabel);
        gamePanel.add(hairLabel);
        gamePanel.add(hairColorLabel);
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
        gamePanel.add(exitButton);

        // Arrow selector buttons
        arrowSelectors = new JButton[18];
        for (int i = 0; i < arrowSelectors.length; i++) {
            ImageIcon leftArrow, rightArrow;
            leftArrow = new ImageIcon(menuGUI.getSubimage(401, 61, 18, 18));
            rightArrow = new ImageIcon(menuGUI.getSubimage(421, 61, 18, 18));
            arrowSelectors[i] = new JButton();
            arrowSelectors[i].setSize(18, 18);
            arrowSelectors[i].setBorder(null);
            arrowSelectors[i].addActionListener(buttonListener);
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

        // Load sprite components
        headAndBodySpritesheets = new SpriteSheet[14][2];
        hairSpritesheets = new SpriteSheet[34][17];
        eyeSpritesheets = new SpriteSheet[8];
        faceHairSpritesheets = new SpriteSheet[6][17];
        shirtSpritesheet = new SpriteSheet[16];
        pantSpritesheet = new SpriteSheet[8];
        shoeSpritesheets = new SpriteSheet[14];

        for (int i = 0; i < headAndBodySpritesheets.length; i++) {
            if (i < headAndBodySpritesheets.length / 2) {
                headAndBodySpritesheets[i][0] = new SpriteSheet(ImageLoader.load("PlayerSprite/head/male/head_" +
                        i  + ".png", true), 64, 64);
                headAndBodySpritesheets[i][1] = new SpriteSheet(ImageLoader.load("PlayerSprite/body/male/body_" +
                        i + ".png", true), 64, 64);
            }
            else {
                headAndBodySpritesheets[i][0] = new SpriteSheet(ImageLoader.load("PlayerSprite/head/female/head_" + 
                        (i - headAndBodySpritesheets.length / 2)  + ".png", true), 64, 64);
                headAndBodySpritesheets[i][1] = new SpriteSheet(ImageLoader.load("PlayerSprite/body/female/body_" +
                        (i - headAndBodySpritesheets.length / 2) + ".png", true), 64, 64);
            }
            printMemoryUsage();
        }
        for (int i = 0; i < hairSpritesheets.length; i++) {
            if (i < hairSpritesheets.length / 2) {
                for (int j = 0; j < hairSpritesheets[i].length; j++) {
                    hairSpritesheets[i][j] = new SpriteSheet(ImageLoader.load("PlayerSprite/hair/male/hair_" +
                        i  + hairColors[j] + ".png", true), 64, 64);
                }
            }
            else {
                for (int k = 0; k < hairSpritesheets[i].length; k++) {
                    hairSpritesheets[i][0] = new SpriteSheet(ImageLoader.load("PlayerSprite/hair/female/hair_" + 
                            (i - hairSpritesheets.length / 2) + hairColors[k] + ".png", true), 64, 64);
                }
            }
            printMemoryUsage();
        }
        for (int i = 0; i < eyeSpritesheets.length; i++) {
            eyeSpritesheets[i] = new SpriteSheet(ImageLoader.load("PlayerSprite/eyes/eyes_" + i  + ".png", true), 
                    64, 64);
            printMemoryUsage();
        }
        for (int i = 0; i < faceHairSpritesheets.length; i++) {
            for (int j = 0; j < faceHairSpritesheets[i].length; j++) {
                faceHairSpritesheets[i][j] = new SpriteSheet(ImageLoader.load("PlayerSprite/facehair/facehair_" +
                    i  + hairColors[j] + ".png", true), 64, 64);
            }
            printMemoryUsage();
        }
        for (int i = 0; i < shirtSpritesheet.length; i++) {
            if (i < shirtSpritesheet.length / 2) {
                shirtSpritesheet[i] = new SpriteSheet(ImageLoader.load("PlayerSprite/shirt/male/shirt_" +
                        i  + ".png", true), 64, 64);
            }
            else {
                shirtSpritesheet[i] = new SpriteSheet(ImageLoader.load("PlayerSprite/shirt/female/shirt_" + 
                        (i - shirtSpritesheet.length / 2)  + ".png", true), 64, 64);
            }
            printMemoryUsage();
        }
        for (int i = 0; i < pantSpritesheet.length; i++) {
            if (i < pantSpritesheet.length / 2) {
                pantSpritesheet[i] = new SpriteSheet(ImageLoader.load("PlayerSprite/pants/male/pants_" +
                        i  + ".png", true), 64, 64);
            }
            else {
                pantSpritesheet[i] = new SpriteSheet(ImageLoader.load("PlayerSprite/pants/female/pants_" + 
                        (i - pantSpritesheet.length / 2)  + ".png", true), 64, 64);
            }
            printMemoryUsage();
        }
        for (int i = 0; i < shoeSpritesheets.length; i++) {
            if (i < shoeSpritesheets.length / 2) {
                shoeSpritesheets[i] = new SpriteSheet(ImageLoader.load("PlayerSprite/shoes/male/shoes_" +
                        i  + ".png", true), 64, 64);
            }
            else {
                shoeSpritesheets[i] = new SpriteSheet(ImageLoader.load("PlayerSprite/shoes/female/shoes_" + 
                        (i - shoeSpritesheets.length / 2)  + ".png", true), 64, 64);
            }
            printMemoryUsage();
        }

        updateDisplaySprite();
    }

    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }

        Boolean newDisplaySprite = false;
        for (int i = 0; i < spriteSelections.length; i++) {
            if (previousSelections[i] != spriteSelections[i]) {
                newDisplaySprite = true;
                previousSelections[i] = spriteSelections[i];
            }
        }

        if (newDisplaySprite) {
            newDisplaySprite = false;
            updateDisplaySprite();
        }
    }

    private void updateDisplaySprite() {
        // Create default display sprite
        Graphics g = displaySprite.getGraphics();
        g.setColor(Color.decode("#EFE4B0"));
        g.fillRect(0, 0, displaySprite.getWidth(), displaySprite.getHeight());
        g.drawImage(headAndBodySpritesheets[spriteSelections[0]][1].getSubImage(2, 0, false), 0, 0, null);
        g.drawImage(headAndBodySpritesheets[spriteSelections[0]][0].getSubImage(2, 0, false), 0, 0, null);
        g.drawImage(eyeSpritesheets[spriteSelections[3]].getSubImage(2, 0, false), 0, 0, null);
        g.drawImage(hairSpritesheets[spriteSelections[1]][spriteSelections[2]].getSubImage(2, 0, false), 0, 0, null);
        if (isMale && spriteSelections[4] > -1) {
            g.drawImage(faceHairSpritesheets[spriteSelections[4]][spriteSelections[2]].getSubImage(2, 0, false), 0, 0, null);
        }
        g.drawImage(shirtSpritesheet[spriteSelections[5]].getSubImage(2, 0, false), 0, 0, null);
        g.drawImage(pantSpritesheet[spriteSelections[6]].getSubImage(2, 0, false), 0, 0, null);
        g.drawImage(shoeSpritesheets[spriteSelections[7]].getSubImage(2, 0, false), 0, 0, null);
        g.dispose();

        // Scale up display sprite
        Graphics2D g2 = scaleDisplaySprite.createGraphics();
        g2.drawImage(displaySprite, 0, 0, scaleDisplaySprite.getWidth(), scaleDisplaySprite.getHeight(), null);
        g2.dispose();
    }

    private void printMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Used memory: " + usedMemory / 1024 / 1024 + " MB");
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        graphicsHandler.drawImage(menuImage, 200, 100);
        graphicsHandler.drawImage(scaleDisplaySprite, 434, 199);
    }

    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == exitButton) {
                screenCoordinator.setGameState(GameState.MENU);
            }
            else if (e.getSource() == confirmButton) {
                //TODO: Add sprite to player
                screenCoordinator.setGameState(GameState.LEVEL);
            }
            else if (e.getSource() == cancelButton) {
                screenCoordinator.setGameState(GameState.MENU);
            }
            else if (e.getSource() == maleRB) {
                isMale = true;
            }
            else if (e.getSource() == femaleRB) {
                isMale = false;
                spriteSelections[3] = -1;
            }
            else if (e.getSource() == arrowSelectors[0]) {
                if (isMale) {
                    spriteSelections[0] = Math.floorMod(spriteSelections[0] - 1, headAndBodySpritesheets.length / 2);
                }
                else {
                    spriteSelections[0] = Math.floorMod(spriteSelections[0] - 1, headAndBodySpritesheets.length / 2)
                            + headAndBodySpritesheets.length / 2;
                }
            }
            else if (e.getSource() == arrowSelectors[1]) {
                if (isMale) {
                    spriteSelections[0] = (spriteSelections[0] + 1) % (headAndBodySpritesheets.length / 2);
                }
                else {
                    spriteSelections[0] = (spriteSelections[0] + 1) % (headAndBodySpritesheets.length / 2)
                            + headAndBodySpritesheets.length / 2;
                }
            }
            else if (e.getSource() == arrowSelectors[2]) {
                if (isMale) {
                    spriteSelections[1] = Math.floorMod(spriteSelections[1] - 1, hairSpritesheets.length / 2);
                }
                else {
                    spriteSelections[1] = Math.floorMod(spriteSelections[1] - 1, hairSpritesheets.length / 2)
                            + hairSpritesheets.length / 2;
                }
            }
            else if (e.getSource() == arrowSelectors[3]) {
                if (isMale) {
                    spriteSelections[1] = (spriteSelections[1] + 1) % (hairSpritesheets.length / 2);
                }
                else {
                    spriteSelections[1] = (spriteSelections[1] + 1) % (hairSpritesheets.length / 2)
                            + hairSpritesheets.length / 2;
                }
            }
            else if (e.getSource() == arrowSelectors[4]) {
                spriteSelections[2] = Math.floorMod(spriteSelections[2] - 1, hairColors.length);
            }
            else if (e.getSource() == arrowSelectors[5]) {
                spriteSelections[2] = (spriteSelections[2] + 1) % (hairColors.length);
            }
            else if (e.getSource() == arrowSelectors[6]) {
                spriteSelections[3] = Math.floorMod(spriteSelections[3] - 1, eyeSpritesheets.length);
            }
            else if (e.getSource() == arrowSelectors[7]) {
                spriteSelections[3] = (spriteSelections[3] + 1) % (eyeSpritesheets.length);
            }
            else if (e.getSource() == arrowSelectors[8]) {
                if (isMale) {
                    spriteSelections[4] = Math.floorMod(spriteSelections[4] - 1, faceHairSpritesheets.length + 1) - 1;
                }
            }
            else if (e.getSource() == arrowSelectors[9]) {
                if (isMale) {
                    spriteSelections[4] = (spriteSelections[4] + 1) % (faceHairSpritesheets.length + 1) - 1;
                }
            }
            else if (e.getSource() == arrowSelectors[10]) {
                if (isMale) {
                    spriteSelections[5] = Math.floorMod(spriteSelections[5] - 1, shirtSpritesheet.length / 2);
                }
                else {
                    spriteSelections[5] = Math.floorMod(spriteSelections[5] - 1, shirtSpritesheet.length / 2)
                            + shirtSpritesheet.length / 2;
                }
            }
            else if (e.getSource() == arrowSelectors[11]) {
                if (isMale) {
                    spriteSelections[5] = (spriteSelections[5] + 1) % (shirtSpritesheet.length / 2);
                }
                else {
                    spriteSelections[5] = (spriteSelections[5] + 1) % (shirtSpritesheet.length / 2)
                            + shirtSpritesheet.length / 2;
                }
            }
            else if (e.getSource() == arrowSelectors[12]) {
                if (isMale) {
                    spriteSelections[6] = Math.floorMod(spriteSelections[6] - 1, pantSpritesheet.length / 2);
                }
                else {
                    spriteSelections[6] = Math.floorMod(spriteSelections[6] - 1, pantSpritesheet.length / 2)
                            + pantSpritesheet.length / 2;
                }
            }
            else if (e.getSource() == arrowSelectors[13]) {
                if (isMale) {
                    spriteSelections[6] = (spriteSelections[6] + 1) % (pantSpritesheet.length / 2);
                }
                else {
                    spriteSelections[6] = (spriteSelections[6] + 1) % (pantSpritesheet.length / 2)
                            + pantSpritesheet.length / 2;
                }
            }
            else if (e.getSource() == arrowSelectors[14]) {
                if (isMale) {
                    spriteSelections[7] = Math.floorMod(spriteSelections[7] - 1, shoeSpritesheets.length / 2);
                }
                else {
                    spriteSelections[7] = Math.floorMod(spriteSelections[7] - 1, shoeSpritesheets.length / 2)
                            + shoeSpritesheets.length / 2;
                }
            }
            else if (e.getSource() == arrowSelectors[15]) {
                if (isMale) {
                    spriteSelections[7] = (spriteSelections[7] + 1) % (shoeSpritesheets.length / 2);
                }
                else {
                    spriteSelections[7] = (spriteSelections[7] + 1) % (shoeSpritesheets.length / 2)
                            + shoeSpritesheets.length / 2;
                }
            }
            else if (e.getSource() == arrowSelectors[16]) {

            }
            else if (e.getSource() == arrowSelectors[17]) {
                
            }
        }
    }
}
