package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Player;
import Screens.QuestLogScreen;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

// This is the class for the player avatar
public class Avatar extends Player {
        private String name;
        private boolean isMale;
        //private String playerClass;
        private SpriteSheet[] spriteComponents; //Holds character customization options
        private BufferedImage[][] weapon;       //Holds both weapon primary and shield
        private BufferedImage[] armor;          //torso, arms, legs, shoulder, head, feet, hands
        private boolean longWeapon;             //Long weapons need a larger sprite image
        private String longWeaponFilePath;      //Larger sprite image loaded separately
        private SpriteSheet slashAnimations;    //Slash animation spritesheet to account for longweapons
        public static int hitDice;
        public static String playerClass;       //"Warrior", "Wizard", "Ranger"
        public static PlayerActionCollection meleeAction = new PlayerActionCollection();
        public static PlayerActionCollection spellAction = new PlayerActionCollection();
        public static double health = 10;
        public static int healthInitial = 10;
        public static int meleeMod = 0;
        public static int spellMod = 0;
        public static int rangeMod = 0;
        public static int xp = 0;
        private boolean amuletOfLifeSteal = false;

        // Player stats
        public static int strength, dexterity, constitution, intelligence;

        public Avatar(float x, float y) {
                super(new SpriteSheet(ImageLoader.load("Doug.png", true), 64, 64), x, y, "STAND_DOWN");
                this.walkSpeed = 1.9f;
                this.runSpeed = 3.3f;
                this.name = "Doug";
                this.isMale = true;
                this.playerClass = "Warrior";
                this.spriteComponents = null;
                this.strength = 0;
                this.dexterity = 0;
                this.constitution = 0;
                this.intelligence = 0;
                this.hitDice = 10;
                this.weapon = new BufferedImage[2][2];
                this.armor = new BufferedImage[7];
                this.longWeapon = false;
                this.longWeaponFilePath = null;
                this.slashAnimations = new SpriteSheet(ImageLoader.load("Doug.png", true)
                        .getSubimage(0, 768, 384, 256), 64, 64);
        }

        public Avatar(SpriteSheet[] spriteComponents, float x, float y, String name, boolean isMale, String playerClass) {
                super(spriteComponents[0], x, y, "STAND_DOWN");
                this.walkSpeed = 1.9f;
                this.runSpeed = 3.3f;
                this.name = name;
                this.isMale = isMale;
                this.playerClass = playerClass;
                this.spriteComponents = spriteComponents;
                this.strength = 0;
                this.dexterity = 0;
                this.constitution = 0;
                this.intelligence = 0;
                this.health = 10;
                this.weapon = new BufferedImage[2][2];
                this.armor = new BufferedImage[7];
                this.longWeapon = false;
                this.longWeaponFilePath = null;
                this.slashAnimations = new SpriteSheet(spriteComponents[0].getImage()
                        .getSubimage(0, 768, 384, 256), 64, 64);

                updateSprite();
        }

        public static void resetPlayer(){
                playerClass = null;
                meleeAction = new PlayerActionCollection();
                spellAction = new PlayerActionCollection();
                health = 10;
                healthInitial = 10;
                strength = 0;
                dexterity = 0;
                constitution = 0;
                intelligence = 0;
        }

        public static void levelUp(){
                if (playerClass.equals("Warrior")){
                        strength += 2;
                        dexterity ++;
                        constitution += 2;
                        intelligence ++;
                        healthInitial += 7;
                        health = healthInitial;
                } else if (playerClass.equals("Wizard")){
                        strength += 1;
                        dexterity ++;
                        constitution += 1;
                        intelligence += 2;
                        healthInitial += 4;
                        health = healthInitial;
                }
                System.out.println("stenght: " + strength);
                System.out.println("int: " + intelligence);
                System.out.println("health: " + health);
        }

        // Create new spritesheet by combing component layers onto one buffered image
        public void updateSprite() {
                BufferedImage customSprite = new BufferedImage(832, 1344, BufferedImage.TYPE_INT_ARGB);
                Graphics g = customSprite.getGraphics();

                //Add weapon background
                if (weapon[0] != null) {g.drawImage(weapon[0][1], 0, 0, null);}
                if (weapon[1] != null) {g.drawImage(weapon[1][1], 0, 0, null);}

                //Add character customization sprite selections
                g.drawImage(spriteComponents[0].getImage(), 0, 0, null);
                g.drawImage(spriteComponents[1].getImage(), 0, 0, null);
                g.drawImage(spriteComponents[2].getImage(), 0, 0, null);
                g.drawImage(spriteComponents[3].getImage(), 0, 0, null);
                g.drawImage(spriteComponents[4].getImage(), 0, 0, null);
                if (armor[0] == null || playerClass == "Wizard") {
                        g.drawImage(spriteComponents[5].getImage(), 0, 0, null);
                }
                g.drawImage(spriteComponents[6].getImage(), 0, 0, null);
                if (armor[4] == null) {
                        g.drawImage(spriteComponents[7].getImage(), 0, 0, null);
                }

                //Add any weapons/armor
                for(BufferedImage armorPiece : armor) {
                        if (armorPiece != null) {g.drawImage(armorPiece, 0, 0, null);}
                }
                if (weapon[1] != null) {g.drawImage(weapon[1][0], 0, 0, null);}
                if (weapon[0] != null) {g.drawImage(weapon[0][0], 0, 0, null);}
                
                g.dispose();

                setSpriteSheet(new SpriteSheet(customSprite, 64, 64));
                updateSlashAnimation(customSprite);
        }

        //Adds equipment to character, the bg parameter is optional
        public void equip(InventoryItem item) {
                String filePath = item.getFilePath();
                InventoryItem.EQUIP_TYPE equipType = item.getType();
                BufferedImage fg;
                BufferedImage bg;

                switch (equipType) {
                        case SWORD, STAFF, DAGGER:
                                fg = ImageLoader.load(filePath + "fg.png", true);
                                bg = ImageLoader.load(filePath + "bg.png", true);
                                weapon[0][0] = fg;
                                weapon[0][1] = bg;
                                if (equipType == InventoryItem.EQUIP_TYPE.SWORD) {
                                        longWeapon = true;
                                        longWeaponFilePath = filePath + "slash/";
                                }
                                else longWeapon = false;
                                break;
                        case BOW:
                                fg = ImageLoader.load(filePath + "fg.png", true);
                                bg = ImageLoader.load(filePath + "bg.png", true);

                                //Add arrow sprites and bow to fg
                                Graphics fgGraphics = fg.getGraphics();
                                String arrowPath = "Equipment/weapon/bow/accessory/arrow.png";
                                fgGraphics.drawImage(ImageLoader.load(arrowPath, true), 0, 0, null);
                                fgGraphics.dispose();

                                //Add quiver to bg
                                Graphics bgGraphics = bg.getGraphics();
                                String quiverPath = "Equipment/weapon/bow/accessory/quiver.png";
                                bgGraphics.drawImage(ImageLoader.load(quiverPath, true), 0, 0, null);
                                bgGraphics.dispose();

                                weapon[0][0] = fg;
                                weapon[0][1] = bg;
                                longWeapon = false;
                                break;
                        case SHIELD: 
                                if (equipType != InventoryItem.EQUIP_TYPE.STAFF || equipType != InventoryItem.EQUIP_TYPE.BOW) {
                                        fg = ImageLoader.load(filePath + ((isMale) ? "male/" : "female/") + "fg.png", true);
                                        weapon[1][0] = fg;
                                }
                                break;
                        case TORSO:
                                fg = ImageLoader.load(filePath + ((isMale) ? "male/" : "female/") + "torso.png", true);
                                if (fg != null) {
                                        armor[0] = fg;
                                }
                                break;
                        case ARMS:
                                fg = ImageLoader.load(filePath + ((isMale) ? "male/" : "female/") + "arms.png", true);
                                armor[1] = fg;
                                break;
                        case LEGS:
                                fg = ImageLoader.load(filePath + ((isMale) ? "male/" : "female/") + "legs.png", true);
                                armor[2] = fg;
                                break;
                        case SHOULDER:
                                fg = ImageLoader.load(filePath + ((isMale) ? "male/" : "female/") + "shoulder.png", true);
                                armor[3] = fg;
                                break;
                        case HEAD:
                                fg = ImageLoader.load(filePath + ((isMale) ? "male/" : "female/") + "head.png", true);
                                armor[4] = fg;
                                break;
                        case FEET:
                                fg = ImageLoader.load(filePath + ((isMale) ? "male/" : "female/") + "feet.png", true);
                                armor[5] = fg;
                                break;
                        case HANDS:
                                fg = ImageLoader.load(filePath + ((isMale) ? "male/" : "female/") + "hand.png", true);
                                armor[6] = fg;
                                break;
                }

                updateSprite();
        }

        public void unequip(InventoryItem.EQUIP_TYPE equipType) {
                switch (equipType) {
                        case SWORD, STAFF, DAGGER, BOW:
                                weapon[0][0] = null;
                                weapon[0][1] = null;
                                longWeapon = false;
                                break;
                        case SHIELD:
                                weapon[1][0] = null;
                                weapon[1][1] = null;
                                break;
                        case TORSO:
                                armor[0] = null;
                                break;
                        case ARMS:
                                armor[1] = null;
                                break;
                        case LEGS:
                                armor[2] = null;
                                break;
                        case SHOULDER:
                                armor[3] = null;
                                break;
                        case HEAD:
                                armor[4] = null;
                                break;
                        case FEET:
                                armor[5] = null;
                                break;
                        case HANDS:
                                armor[6] = null;
                                break;
                        default:
                                //Unequip all
                                weapon[0][0] = null;
                                weapon[0][1] = null;
                                weapon[1][0] = null;
                                weapon[1][1] = null;
                                for (int i = 0; i < 7; i++) {armor[i] = null;}
                                break;
                }

        }

        public void update() {
                super.update();
        }

        // Player stats
        protected void setStats(int strength, int dexterity, int constitution, int intelligence) {
            setStrength(strength);
            setDexterity(dexterity);
            setConstitution(constitution);
            setIntelligence(intelligence);
        }
        public void setStrength(int strength) {this.strength = strength;}
        public void setDexterity(int dexterity) {this.dexterity = dexterity;}
        public void setConstitution(int constitution) {this.constitution = constitution;}
        public void setIntelligence(int intelligence) {this.intelligence = intelligence;}
        public int getStrength() {return strength;}
        public int getDexterity() {return dexterity;}
        public int getConstitution() {return constitution;}
        public int getIntelligence() {return intelligence;}
        public String getPlayerClass() {return playerClass;}
        public String getPlayerName() {return name;}
        public String getPlayerGender() {
                if (isMale) return "male";
                else return "female";
        }

        public void draw(GraphicsHandler graphicsHandler) {
                if (currentFrame.getWidth() < 192) {
                        super.draw(graphicsHandler);  
                } else {
                        graphicsHandler.drawImage(
				currentFrame.getImage(),				
				Math.round(getCalibratedXLocation()) - 64,
				Math.round(getCalibratedYLocation()) - 64,
				currentFrame.getWidth(),
				currentFrame.getHeight(),
				currentFrame.getImageEffect()
			);
                }
        }

        //Builds a new slashAnimation SpriteSheet for regular or long weapons
        private void updateSlashAnimation(BufferedImage customSprite) {
                SpriteSheet customSpriteSheet = new SpriteSheet(customSprite.getSubimage(0, 768, 384, 256), 64, 64);
                if (!longWeapon) {
                        //When not a longweapon, just re-use normal slash animations
                        slashAnimations = customSpriteSheet;
                } else {
                        //Otherwise, create large sprite animations to fit the sword swings
                        BufferedImage largeSpriteSheet = ImageLoader.load(longWeaponFilePath + "bg.png", true);
                        Graphics largeSpriteGraphics = largeSpriteSheet.getGraphics();

                        //Add player sprite components on top of slash animation background
                        // for (int i = 0; i < spriteComponents.length; i++) {
                        //         if (i == 5 && armor[0] != null) {}
                        //         else if (i == 7 && armor[4] != null) {}
                        //         else {
                        //                 for(int j = 0; j < 4; j++) {
                        //                         for(int k = 0; k < 6; k++) {
                        //                                 largeSpriteGraphics.drawImage(spriteComponents[i]
                        //                                         .getSubImage(j+12, k, false), 64+192*k, 64+192*j, null);
                        //                         }
                        //                 }
                        //         }
                        // }
                        for(int i = 0; i < 6; i++) {
                                largeSpriteGraphics.drawImage(customSpriteSheet.getSubImage(0, i, false), 64+192*i, 64, null);
                        }
                        for(int i = 0; i < 6; i++) {
                                largeSpriteGraphics.drawImage(customSpriteSheet.getSubImage(1, i, false), 64+192*i, 256, null);
                        }
                        for(int i = 0; i < 6; i++) {
                                largeSpriteGraphics.drawImage(customSpriteSheet.getSubImage(2, i, false), 64+192*i, 448, null);
                        }
                        for(int i = 0; i < 6; i++) {
                                largeSpriteGraphics.drawImage(customSpriteSheet.getSubImage(3, i, false), 64+192*i, 640, null);
                        }

                        //Add armor components on top of slash animation background
                        for(BufferedImage armorPiece : armor) {
                                if (armorPiece != null) {
                                        for(int j = 0; j < 4; j++) {
                                                for(int k = 0; k < 6; k++) {
                                                        largeSpriteGraphics.drawImage(armorPiece
                                                                .getSubimage(k*64, (j+12)*64, 64, 64), 64+192*k, 64+192*j, null);
                                                }
                                        }
                                }
                        }

                        //Add slash animation foreground over image
                        largeSpriteGraphics.drawImage(ImageLoader.load(longWeaponFilePath + "fg.png", true), 
                                0, 0, null);
                        largeSpriteGraphics.dispose();
                        slashAnimations = new SpriteSheet(largeSpriteSheet, 192, 192);
                }

                int animationX, animationY;
                animationX = (slashAnimations.getSpriteWidth() > 64) ? 81 : 17;
                animationY = (slashAnimations.getSpriteWidth() > 64) ? 78 : 14;

                //Add animations
                animations.put("SWORD_UP", new Frame[] {
                        new FrameBuilder(slashAnimations.getSubImage(0, 0, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(0, 1, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(0, 2, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(0, 3, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(0, 4, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(0, 5, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build()
                });
                animations.put("SWORD_LEFT", new Frame[] {
                        new FrameBuilder(slashAnimations.getSubImage(1, 0, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(1, 1, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(1, 2, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(1, 3, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(1, 4, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(1, 5, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build()
                });
                animations.put("SWORD_DOWN", new Frame[] {
                        new FrameBuilder(slashAnimations.getSubImage(2, 0, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(2, 1, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(2, 2, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(2, 3, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(2, 4, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(2, 5, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build()
                });
                animations.put("SWORD_RIGHT", new Frame[] {
                        new FrameBuilder(slashAnimations.getSubImage(3, 0, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(3, 1, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(3, 2, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(3, 3, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(3, 4, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build(),
                        new FrameBuilder(slashAnimations.getSubImage(3, 5, false), 14)
                                        .withBounds(animationX, animationY, 30, 48)
                                        .build()
                });
        }

        @Override
        public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
                return new HashMap<String, Frame[]>() {
                        {
                                put("STAND_UP", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(8, 0, false))
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });

                                put("STAND_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(9, 0, false))
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });

                                put("STAND_DOWN", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(10, 0, false))
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });

                                put("STAND_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(11, 0, false))
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });

                                put("WALK_UP", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(8, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 7, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 8, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });
                                put("WALK_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(9, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 7, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 8, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });

                                put("WALK_DOWN", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(10, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 7, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 8, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });

                                put("WALK_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(11, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 7, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 8, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });
                                // Bow and Arrow Motion for Doug
                                put("ARROW_UP", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(16, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 7, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 8, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 9, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 10, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 11, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(16, 12, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });
                                put("ARROW_DOWN", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(18, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 7, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 8, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 9, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 10, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 11, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(18, 12, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });
                                put("ARROW_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(19, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 7, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 8, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 9, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 10, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 11, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(19, 12, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });
                                put("ARROW_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(17, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 7, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 8, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 9, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 10, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 11, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(17, 12, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });
                                put("MAGIC_UP", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(0, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(0, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(0, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(0, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(0, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(0, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(0, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });
                                put("MAGIC_DOWN", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(2, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(2, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(2, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(2, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(2, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(2, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(2, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });
                                put("MAGIC_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(3, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(3, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(3, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(3, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(3, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(3, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(3, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });
                                put("MAGIC_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(1, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(1, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(1, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(1, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(1, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(1, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(1, 6, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });
                                put("FALL_DOWN", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(20, 0, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(20, 1, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(20, 2, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(20, 3, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(20, 4, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(20, 5, false), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });
                                put("RUN_UP", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(8, 0, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 1, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 2, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 3, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 4, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 5, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 6, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 7, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(8, 8, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });
                                put("RUN_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(9, 0, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 1, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 2, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 3, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 4, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 5, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 6, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 7, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(9, 8, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });

                                put("RUN_DOWN", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(10, 0, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 1, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 2, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 3, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 4, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 5, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 6, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 7, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(10, 8, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });
                                put("RUN_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSubImage(11, 0, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 1, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 2, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 3, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 4, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 5, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 6, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 7, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSubImage(11, 8, false), 1)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });
                        }
                };

        }

        public void addMeleeAction(PlayerAction pa){
                meleeAction.addAction(pa);
        }

        public void addSpellAction(PlayerAction pa){
                spellAction.addAction(pa);
        }

        public boolean hasAmulet(){
                return amuletOfLifeSteal;
        }

        public void setAmulet(){
                amuletOfLifeSteal = true;
        }

        @Override
        public void setQuestLog(QuestLogScreen questLog, HashMap<String, Integer> mainQuestFlags) {
                this.questLog = questLog; 
                this.mainQuestFlags = mainQuestFlags;
        }

        @Override
        public void setMainQuest(String flag) {
                questLog.setMainQuest(mainQuestFlags.get(flag));
        }

        @Override
        public void setSideQuestNote(String note) {
                questLog.setSideQuestNote(note);
        }

        @Override
        public void removeSideQuestNote(String note) {
                questLog.removeSideQuestNote(note);
        }
}
