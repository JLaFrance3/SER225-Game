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

import java.awt.Color;
import java.awt.Font;
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
    private JLabel nameLabel, skinLabel, hairLabel, hairColorLabel, eyeLabel, faceHairLabel, faceHairColorLabel, shirtLabel, pantsLabel, shoesLabel, classLabel;
    private JButton confirmButton, cancelButton, exitButton;
    private JToggleButton maleRB, femaleRB;
    private ButtonGroup genderBG;
    private KeyLocker keyLocker = new KeyLocker();

    private String[] classSelections;
    private Gender gender;
    private JLabel nameDisplayLabel, classDisplayLabel;

    private CharacterPart[] characterParts;
    private BufferedImage[] partImages;
    private int currentClassIndex;

    public CharacterScreen(ScreenCoordinator screenCoordinator, JPanel gamePanel) {
        this.screenCoordinator = screenCoordinator;
        this.gamePanel = gamePanel;
        this.gender = Gender.MALE;
        classSelections = new String[] {
            "Warrior",
            "Wizard",
            "Ranger"
        };
    }

    @Override
    public void initialize() {
        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyLocker.lockKey(Key.SPACE);

        // Character customization menu components
        gamePanel.setLayout(null);
        menuGUI = ImageLoader.load("CharacterMenuGUI.png", true);
        menuImage = menuGUI.getSubimage(0, 0, 400, 352);
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
        faceHairColorLabel = new JLabel();
        faceHairColorLabel.setText("Face Hair Color: ");
        faceHairColorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        faceHairColorLabel.setLocation(216, 293);
        faceHairColorLabel.setSize(148, 14);
        faceHairColorLabel.setBorder(null);
        faceHairColorLabel.setBackground(null);
        shirtLabel = new JLabel();
        shirtLabel.setText("Shirt: ");
        shirtLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        shirtLabel.setLocation(216, 315);
        shirtLabel.setSize(148, 14);
        shirtLabel.setBorder(null);
        shirtLabel.setBackground(null);
        pantsLabel = new JLabel();
        pantsLabel.setText("Pants: ");
        pantsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        pantsLabel.setLocation(216, 337);
        pantsLabel.setSize(148, 14);
        pantsLabel.setBorder(null);
        pantsLabel.setBackground(null);
        shoesLabel = new JLabel();
        shoesLabel.setText("Shoes: ");
        shoesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        shoesLabel.setLocation(216, 359);
        shoesLabel.setSize(148, 14);
        shoesLabel.setBorder(null);
        shoesLabel.setBackground(null);
        classLabel = new JLabel();
        classLabel.setText("Class Selection: ");
        classLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        classLabel.setLocation(216, 381);
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
        maleRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gender = Gender.MALE;
                for (int i = 0; i < partImages.length; i++) {
                    partImages[i] = characterParts[i].loadImage(gender).getSubimage(0, 128, 64, 64);
                }
            }
        });
        femaleRB = new JToggleButton();
        femaleRB.setLocation(237, 158);
        femaleRB.setSize(19, 19);
        femaleRB.setBorder(null);
        femaleRB.setIcon(new ImageIcon(menuGUI.getSubimage(443, 81, 19, 19)));
        femaleRB.setSelectedIcon(new ImageIcon(menuGUI.getSubimage(464, 81, 19, 19)));
        femaleRB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gender = Gender.FEMALE;
                for (int i = 0; i < partImages.length; i++) {
                    partImages[i] = characterParts[i].loadImage(gender).getSubimage(0, 128, 64, 64);
                }
            }
        });        
        genderBG = new ButtonGroup();
        genderBG.add(maleRB);
        genderBG.add(femaleRB);
        confirmButton = new JButton();
        confirmButton.setFont(new Font("Serif", Font.PLAIN, 10));
        confirmButton.setHorizontalTextPosition(SwingConstants.CENTER);
        confirmButton.setText("Confirm");
        confirmButton.setLocation(324, 418);
        confirmButton.setSize(66, 21);
        confirmButton.setBorder(null);
        confirmButton.setIcon(new ImageIcon(menuGUI.getSubimage(400, 18, 66, 21)));
        confirmButton.setPressedIcon(new ImageIcon(menuGUI.getSubimage(400, 39, 66, 21)));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMenu();
                screenCoordinator.setGameState(GameState.LEVEL);
            }
        });
        cancelButton = new JButton();
        cancelButton.setFont(new Font("Serif", Font.PLAIN, 10));
        cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
        cancelButton.setText("Cancel");
        cancelButton.setLocation(410, 418);
        cancelButton.setSize(66, 21);
        cancelButton.setBorder(null);
        cancelButton.setIcon(new ImageIcon(menuGUI.getSubimage(466, 18, 66, 21)));
        cancelButton.setPressedIcon(new ImageIcon(menuGUI.getSubimage(466, 39, 66, 21)));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMenu();
                screenCoordinator.setGameState(GameState.MENU);
            }
        });
        exitButton = new JButton();
        exitButton.setLocation(570, 116);
        exitButton.setSize(14, 14);
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearMenu();
                screenCoordinator.setGameState(GameState.MENU);
            }
        });
        nameDisplayLabel = new JLabel();
        nameDisplayLabel.setLocation(416, 184);
        nameDisplayLabel.setSize(168, 20);
        nameDisplayLabel.setBackground(null);
        nameDisplayLabel.setBorder(null);
        nameDisplayLabel.setFont(new Font("Serif", Font.BOLD, 20));
        nameDisplayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameDisplayLabel.setText("");
        classDisplayLabel = new JLabel();
        classDisplayLabel.setLocation(416, 350);
        classDisplayLabel.setSize(168, 20);
        classDisplayLabel.setBackground(null);
        classDisplayLabel.setBorder(null);
        classDisplayLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        classDisplayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        classDisplayLabel.setText("");

        // Add customization menu components to gamepanel
        gamePanel.add(nameField);
        gamePanel.add(nameLabel);
        gamePanel.add(skinLabel);
        gamePanel.add(hairLabel);
        gamePanel.add(hairColorLabel);
        gamePanel.add(eyeLabel);
        gamePanel.add(faceHairLabel);
        gamePanel.add(faceHairColorLabel);
        gamePanel.add(shirtLabel);
        gamePanel.add(pantsLabel);
        gamePanel.add(shoesLabel);
        gamePanel.add(classLabel);
        gamePanel.add(maleRB);
        gamePanel.add(femaleRB);
        gamePanel.add(confirmButton);
        gamePanel.add(cancelButton);
        gamePanel.add(exitButton);
        gamePanel.add(nameDisplayLabel);
        gamePanel.add(classDisplayLabel);

        // this order needs to match the order the values are defined in the Part enum for this entire thing to work
        // this is also the order the parts are drawn in, which matters due to which part should overlap what
        characterParts = new CharacterPart[] {
            new CharacterPart(Part.BODY, 7),
            new CharacterPart(Part.HEAD, 7),
            new CharacterPart(Part.EYES, 8),
            new CharacterPart(Part.SHOES, 5),
            new CharacterPart(Part.PANTS, 4),
            new CharacterPart(Part.SHIRT, 8),
            new CharacterPart(Part.FACEHAIR, 7, 26),
            new CharacterPart(Part.HAIR, 20, 26)
        };

        partImages = new BufferedImage[8];
        for (int i = 0; i < partImages.length; i++) {
            partImages[i] = characterParts[i].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
        }
        classDisplayLabel.setText(classSelections[currentClassIndex]);

        ImageIcon leftArrow = new ImageIcon(menuGUI.getSubimage(401, 61, 18, 18));
        ImageIcon rightArrow = new ImageIcon(menuGUI.getSubimage(421, 61, 18, 18));
        JButton skinLeftArrowButton = createArrowButton(370, 181, leftArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.HEAD.ordinal()].decrementCurrentIndex();
                characterParts[Part.BODY.ordinal()].decrementCurrentIndex();
                partImages[Part.HEAD.ordinal()] = characterParts[Part.HEAD.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
                partImages[Part.BODY.ordinal()] = characterParts[Part.BODY.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(skinLeftArrowButton);
        JButton skinRightArrowButton = createArrowButton(392, 181, rightArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.HEAD.ordinal()].incrementCurrentIndex();
                characterParts[Part.BODY.ordinal()].incrementCurrentIndex();
                partImages[Part.HEAD.ordinal()] = characterParts[Part.HEAD.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
                partImages[Part.BODY.ordinal()] = characterParts[Part.BODY.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(skinRightArrowButton);
        JButton hairLeftArrowButton = createArrowButton(370, 203, leftArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.HAIR.ordinal()].decrementCurrentIndex();
                partImages[Part.HAIR.ordinal()] = characterParts[Part.HAIR.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(hairLeftArrowButton);
        JButton hairRightArrowButton = createArrowButton(392, 203, rightArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.HAIR.ordinal()].incrementCurrentIndex();
                partImages[Part.HAIR.ordinal()] = characterParts[Part.HAIR.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(hairRightArrowButton);
        JButton hairColorLeftArrowButton = createArrowButton(370, 225, leftArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.HAIR.ordinal()].decrementCurrentColorIndex();
                partImages[Part.HAIR.ordinal()] = characterParts[Part.HAIR.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);;
            }
        });
        gamePanel.add(hairColorLeftArrowButton);
        JButton hairColorRightArrowButton = createArrowButton(392, 225, rightArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.HAIR.ordinal()].incrementCurrentColorIndex();
                partImages[Part.HAIR.ordinal()] = characterParts[Part.HAIR.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(hairColorRightArrowButton);
        JButton eyesLeftArrowButton = createArrowButton(370, 247, leftArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.EYES.ordinal()].decrementCurrentIndex();
                partImages[Part.EYES.ordinal()] = characterParts[Part.EYES.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(eyesLeftArrowButton);
        JButton eyesRightArrowButton = createArrowButton(392, 247, rightArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.EYES.ordinal()].incrementCurrentIndex();
                partImages[Part.EYES.ordinal()] = characterParts[Part.EYES.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(eyesRightArrowButton);
        JButton faceHairLeftArrowButton = createArrowButton(370, 269, leftArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.FACEHAIR.ordinal()].decrementCurrentIndex();
                partImages[Part.FACEHAIR.ordinal()] = characterParts[Part.FACEHAIR.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(faceHairLeftArrowButton);
        JButton faceHairRightArrowButton = createArrowButton(392, 269, rightArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.FACEHAIR.ordinal()].incrementCurrentIndex();
                partImages[Part.FACEHAIR.ordinal()] = characterParts[Part.FACEHAIR.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(faceHairRightArrowButton);
        JButton faceHairColorLeftArrowButton = createArrowButton(370, 291, leftArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.FACEHAIR.ordinal()].decrementCurrentColorIndex();
                partImages[Part.FACEHAIR.ordinal()] = characterParts[Part.FACEHAIR.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(faceHairColorLeftArrowButton);
        JButton faceHairColorRightArrowButton = createArrowButton(392, 291, rightArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.FACEHAIR.ordinal()].incrementCurrentColorIndex();
                partImages[Part.FACEHAIR.ordinal()] = characterParts[Part.FACEHAIR.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(faceHairColorRightArrowButton);
        JButton shirtLeftArrowButton = createArrowButton(370, 313, leftArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.SHIRT.ordinal()].decrementCurrentIndex();
                partImages[Part.SHIRT.ordinal()] = characterParts[Part.SHIRT.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(shirtLeftArrowButton);
        JButton shirtRightArrowButton = createArrowButton(392, 313, rightArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.SHIRT.ordinal()].incrementCurrentIndex();
                partImages[Part.SHIRT.ordinal()] = characterParts[Part.SHIRT.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(shirtRightArrowButton);
        JButton pantsLeftArrowButton = createArrowButton(370, 335, leftArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.PANTS.ordinal()].decrementCurrentIndex();
                partImages[Part.PANTS.ordinal()] = characterParts[Part.PANTS.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(pantsLeftArrowButton);
        JButton pantsRightArrowButton = createArrowButton(392, 335, rightArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.PANTS.ordinal()].incrementCurrentIndex();
                partImages[Part.PANTS.ordinal()] = characterParts[Part.PANTS.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(pantsRightArrowButton);
        JButton shoesLeftArrowButton = createArrowButton(370, 357, leftArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.SHOES.ordinal()].decrementCurrentIndex();
                partImages[Part.SHOES.ordinal()] = characterParts[Part.SHOES.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(shoesLeftArrowButton);
        JButton shoesRightArrowButton = createArrowButton(392, 357, rightArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                characterParts[Part.SHOES.ordinal()].incrementCurrentIndex();
                partImages[Part.SHOES.ordinal()] = characterParts[Part.SHOES.ordinal()].loadImage(Gender.MALE).getSubimage(0, 128, 64, 64);
            }
        });
        gamePanel.add(shoesRightArrowButton);
        JButton classLeftArrowButton = createArrowButton(370, 379, leftArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentClassIndex--;
                if (currentClassIndex < 0) {
                    currentClassIndex = classSelections.length - 1;
                }
                classDisplayLabel.setText(classSelections[currentClassIndex]);
            }
        });
        gamePanel.add(classLeftArrowButton);
        JButton classRightArrowButton = createArrowButton(392, 379, rightArrow, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentClassIndex++;
                if (currentClassIndex >= classSelections.length) {
                    currentClassIndex = 0;
                }
                classDisplayLabel.setText(classSelections[currentClassIndex]);
            }
        });
        gamePanel.add(classRightArrowButton);
    }

    private JButton createArrowButton(int x, int y, ImageIcon imageIcon, ActionListener actionListener) {
        JButton arrowButton = new JButton();
        arrowButton.setSize(18, 18);
        arrowButton.setBorder(null);
        arrowButton.addActionListener(actionListener);
        arrowButton.setLocation(x, y);
        arrowButton.setIcon(imageIcon);
        return arrowButton;
    }

    public void update() {
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        nameDisplayLabel.setText(nameField.getText());
    }

    // Return player sprite components
    public SpriteSheet[] getPlayerSpriteComponents() {
        SpriteSheet[] spriteSheetParts = new SpriteSheet[8];
        for (int i = 0; i < characterParts.length; i++) {
            spriteSheetParts[i] = new SpriteSheet(characterParts[i].loadImage(gender), 64, 64);
        }
        return spriteSheetParts;
    }

    public String getPlayerName() {
        return nameField.getText();
    }

    public boolean getPlayerGender() {
        return gender == Gender.MALE;
    }

    public String getPlayerClass() {
        return classSelections[currentClassIndex];
    }

    private void clearMenu() {
        gamePanel.removeAll();
        gamePanel.requestFocus();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        graphicsHandler.drawImage(menuImage, 200, 100);
        for (BufferedImage partImage : partImages) {
            graphicsHandler.drawImage(partImage, 434, 199, partImage.getWidth() * 2, partImage.getHeight() * 2);
        }
    }

    private enum Part {
        BODY, HEAD, EYES, SHOES, PANTS, SHIRT, FACEHAIR, HAIR
    }

    private enum Gender {
        MALE("male"), FEMALE("female");

        private String resource;

        private Gender(String resource) {
            this.resource = resource;
        }

        public String getResource() {
            return resource;
        }
    }

    private class CharacterPart {
        private Part part;
        private int count;
        private int currentIndex;
        private int colorCount;
        private int currentColorIndex;
        
        public CharacterPart(Part part, int count) {
            this.part = part;
            this.count = count;
        }

        public CharacterPart(Part part, int count, int colorCount) {
            this.part = part;
            this.count = count;
            this.colorCount = colorCount;
        }

        public void setCurrentIndex(int currentIndex) {
            if (currentIndex > count - 1) {
                this.currentIndex = 0;
            }
            else if (currentIndex < 0) {
                this.currentIndex = count - 1;
            }
            else {
                this.currentIndex = currentIndex;
            }
        }

        public void incrementCurrentIndex() {
            setCurrentIndex(currentIndex + 1);
        }

        public void decrementCurrentIndex() {
            setCurrentIndex(currentIndex - 1);
        }
        
        public void setCurrentColorIndex(int currentColorIndex) {
            if (currentColorIndex > colorCount - 1) {
                this.currentColorIndex = 0;
            }
            else if (currentColorIndex < 0) {
                this.currentColorIndex = colorCount - 1;
            }
            else {
                this.currentColorIndex = currentColorIndex;
            }
        }

        public void incrementCurrentColorIndex() {
            setCurrentColorIndex(currentColorIndex + 1);
        }

        public void decrementCurrentColorIndex() {
            setCurrentColorIndex(currentColorIndex - 1);
        }

        public BufferedImage loadImage(Gender gender) {
            switch (part) {
                case HEAD: return ImageLoader.load(String.format("PlayerSprite/head/%s/head_%s.png", gender.getResource(), currentIndex), true);
                case BODY: return ImageLoader.load(String.format("PlayerSprite/body/%s/body_%s.png", gender.getResource(), currentIndex), true);
                case HAIR: return ImageLoader.load(String.format("PlayerSprite/hair/%s/hair_%s/color_%s.png", gender.getResource(), currentIndex, currentColorIndex), true);
                case EYES: return ImageLoader.load(String.format("PlayerSprite/eyes/eyes_%s.png", currentIndex), true);
                case FACEHAIR: return ImageLoader.load(String.format("PlayerSprite/facehair/facehair_%s/color_%s.png", currentIndex, currentColorIndex), true);
                case SHIRT: return ImageLoader.load(String.format("PlayerSprite/shirt/%s/shirt_%s.png", gender.getResource(), currentIndex), true);
                case PANTS: return ImageLoader.load(String.format("PlayerSprite/pants/%s/pants_%s.png", gender.getResource(), currentIndex), true);
                case SHOES: return ImageLoader.load(String.format("PlayerSprite/shoes/%s/shoes_%s.png", gender.getResource(), currentIndex), true);
                default: throw new RuntimeException("Invalid Part");
            }
        }
    }
}
