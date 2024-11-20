package Screens;
import java.awt.image.BufferedImage;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.SpriteSheet;
import Level.*;
import Maps.*;
import Players.Avatar;
import Players.PlayerAction;
import ScriptActions.AddSideQuestNote;
import ScriptActions.AttackGenerator;
import ScriptActions.ChangeFlagScriptAction;
import Scripts.TestMap.CombatScript;
import Utils.Direction;
import Utils.Point;

// these are for intro sound 
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected BufferedImage inventory;
    protected BufferedImage itemDescript;
    protected BufferedImage questLog;
    protected Map startMap, townMap, generalStoreMap, H1Map, H2Map, H3Map, H3_1Map, dungeonMap, forestMap;
    protected Map innMap, manorMap, smithMap, townHallMap;
    protected String[] mapChangeFlags;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected InventoryScreen inventoryScreen;
    protected FlagManager flagManager;
    protected Point lockDoorInteractPoint;
    protected KeyLocker keyLocker = new KeyLocker();
    protected boolean invToggle = false;
    protected boolean questToggle = false;
    protected boolean winScreenToggle = false;
    protected int keyPressTimer = 0;
    protected Point chestInteractPoint;
    protected SpriteSheet[] playerSpriteComponents;
    protected String playerName;
    protected boolean player_isMale;
    protected String playerClass;

    protected QuestLogScreen questLogScreen;
    private HashMap<String, Integer> mainQuestFlags;
    private int currentMainQuest;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        lockDoorInteractPoint = null;
        inventory = ImageLoader.load("inventory.png");
        itemDescript = ImageLoader.load("itemDescribe.png");
        questLog = ImageLoader.load("QuestLog.png");
        keyPressTimer = 0;

        chestInteractPoint = null;
        this.lockDoorInteractPoint = null;
        this.chestInteractPoint = null;
        this.playerSpriteComponents = null;
        this.playerName = null;
        this.player_isMale = false;
        this.playerClass = null;
        this.currentMainQuest = 1;
    }

    public PlayLevelScreen(ScreenCoordinator screenCoordinator, SpriteSheet[] spriteComponents, String name, boolean isMale, String playerClass) {
        this.screenCoordinator = screenCoordinator;
        this.lockDoorInteractPoint = null;
        this.chestInteractPoint = null;
        this.playerSpriteComponents = spriteComponents;
        this.playerName = name;
        this.player_isMale = isMale;
        this.playerClass = playerClass;
        this.currentMainQuest = 1;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("goblin1Flag", false);
        flagManager.addFlag("goblin2Flag", false);
        flagManager.addFlag("goblin3Flag", false);
        flagManager.addFlag("goblin4Flag", false);
        flagManager.addFlag("skeleton1Flag", false);
        flagManager.addFlag("skeleton2Flag", false);
        flagManager.addFlag("skeleton3Flag", false);
        flagManager.addFlag("skeleton4Flag", false);
        flagManager.addFlag("flower1Flag", false);
        flagManager.addFlag("flower2Flag", false);
        flagManager.addFlag("flower3Flag", false);
        flagManager.addFlag("pumpkin1Flag", false);
        flagManager.addFlag("pumpkin2Flag", false);
        flagManager.addFlag("pumpkin3Flag", false);
        flagManager.addFlag("bat1Flag", false);
        flagManager.addFlag("bat2Flag", false);
        flagManager.addFlag("bat3Flag", false);
        flagManager.addFlag("BossAlive", false);
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasDied", false);
        flagManager.addFlag("hasFoundBall", false);
        flagManager.addFlag("gateInteract", false);
        flagManager.addFlag("flowerBed", false);
        flagManager.addFlag("readBackground", false);
        flagManager.addFlag("hasfought", false);
        flagManager.addFlag("lockedDoor", false);
        flagManager.addFlag("walrusHouseSign", false);
        flagManager.addFlag("leaveStartAreaSign", false);
        flagManager.addFlag("townHallSign", false);
        flagManager.addFlag("directionSign", false);
        flagManager.addFlag("startAreaSign", false);
        flagManager.addFlag("house0112Sign", false);
        flagManager.addFlag("hasInteractedKey1", false);
        flagManager.addFlag("hasInteractedThunder", false);
        flagManager.addFlag("hasInteractedChest2", false);
        flagManager.addFlag("talkedToFarmerGirl", false);
        flagManager.addFlag("notStealCorn", false);
        flagManager.addFlag("gotGold1", false);
        flagManager.addFlag("gotFire", false);
        flagManager.addFlag("bossDead", false);
        flagManager.addFlag("knightInteract", false);


        flagManager.addFlag("readTestQuest", false);
        flagManager.addFlag("readQuestOne", false);
        flagManager.addFlag("readQuestOneChest", false);
        flagManager.addFlag("fisherguyInitiate", false);
        flagManager.addFlag("clearedEnemies", false);
        flagManager.addFlag("fisherguyComplete", false);
        flagManager.addFlag("sickDogInitiate", false);
        flagManager.addFlag("foundHealthPotion", false);
        flagManager.addFlag("healDog", false);
        flagManager.addFlag("sickDogComplete", false);

        //Main quest flags array. Easier to update questlog
        mainQuestFlags = new HashMap<>();
        mainQuestFlags.put("canHaveWeapon", 2);
        mainQuestFlags.put("hasInteractedGreatSword", 3);
        mainQuestFlags.put("returnedSword", 4);
        mainQuestFlags.put("dummyAlive", 5);
        mainQuestFlags.put("talkedToOldMan1", 6);
        mainQuestFlags.put("investigateSusCharacter", 7);
        mainQuestFlags.put("foughtSusCharacter", 8);
        mainQuestFlags.put("talkedToOldMan2", 9);
        mainQuestFlags.put("talkedToOldMan3", 10);
        mainQuestFlags.put("seenMaps", 11);
        mainQuestFlags.put("seenAncientScript", 12);
        mainQuestFlags.put("foughtEnemiesToEnterForest", 13);
        for (String mainQuestFlag : mainQuestFlags.keySet()) {
            flagManager.addFlag(mainQuestFlag, false);
        }
        
        // Map change flags
        mapChangeFlags = new String[] {
                "startToTownMapPath",
                "townToStartMapPath",
                "townToStoreDoor",
                "storeToTownDoor",
                "townToH1Door",
                "H1ToTownDoor",
                "townToH2Door",
                "H2ToTownDoor",
                "townToH3Door",
                "townToH3_1Door",
                "H3ToTownDoor",
                "H3_1ToTownDoor",
                "townToInnDoor",
                "innToTownDoor",
                "townToManorDoor",
                "manorToTownDoor",
                "townToSmithDoor",
                "smithToTownDoor",
                "townToHallDoor",
                "hallToTownDoor",
                "startToDungeon",
                "dungeonToStart",
                "townToForest",
                "forestToTown"
        };

        // Add all map change flags
        for (String s : mapChangeFlags) {
            flagManager.addFlag(s, false);
        }

        // game maps
        startMap = new TestMap();
        startMap.setFlagManager(flagManager);
        townMap = new TownMap();
        townMap.setFlagManager(flagManager);
        generalStoreMap = new GeneralStoreMap();
        generalStoreMap.setFlagManager(flagManager);
        H1Map = new House1Map();
        H1Map.setFlagManager(flagManager);
        H2Map = new House2Map();
        H2Map.setFlagManager(flagManager);
        H3Map = new House3Map();
        H3Map.setFlagManager(flagManager);
        H3_1Map = new House3_1Map();
        H3_1Map.setFlagManager(flagManager);
        innMap = new InnMap();
        innMap.setFlagManager(flagManager);
        manorMap = new ManorMap();
        manorMap.setFlagManager(flagManager);
        smithMap = new SmithMap();
        smithMap.setFlagManager(flagManager);
        townHallMap = new TownHallMap();
        townHallMap.setFlagManager(flagManager);
        dungeonMap = new DungeonMap();
        dungeonMap.setFlagManager(flagManager);
        forestMap = new ForestMap();
        forestMap.setFlagManager(flagManager);

        // set current map to startMap
        map = startMap;

        // setup player
        if (playerSpriteComponents != null) {
            player = new Avatar(playerSpriteComponents, map.getPlayerStartPosition().x, map.getPlayerStartPosition().y, playerName, player_isMale, playerClass); 
        }
        else {
            SpriteSheet[] dougSheets = new SpriteSheet[] {
                new SpriteSheet(ImageLoader.load("PlayerSprite/body/male/body_0.png", true), 64, 64),
                new SpriteSheet(ImageLoader.load("PlayerSprite/head/male/head_0.png", true), 64, 64),
                new SpriteSheet(ImageLoader.load("PlayerSprite/eyes/eyes_0.png", true), 64, 64),
                new SpriteSheet(ImageLoader.load("PlayerSprite/shoes/male/shoes_1.png", true), 64, 64),
                new SpriteSheet(ImageLoader.load("PlayerSprite/pants/male/pants_2.png", true), 64, 64),
                new SpriteSheet(ImageLoader.load("PlayerSprite/shirt/male/shirt_3.png", true), 64, 64),
                new SpriteSheet(ImageLoader.load("PlayerSprite/facehair/facehair_2/color_6.png", true), 64, 64),
                new SpriteSheet(ImageLoader.load("PlayerSprite/hair/male/hair_16/color_6.png", true), 64, 64),
            };
            playerClass = "Warrior";
            player = new Avatar(dougSheets, map.getPlayerStartPosition().x, map.getPlayerStartPosition().y, "Doug", true, "Warrior");
        }
        switch (playerClass) {
            case "Warrior":
                Avatar.meleeAction.addAction(new PlayerAction("Cleave", 10, "you cleave your foe") {
                    @Override
                    public double attack(){
                        this.setLastAttack(this.getValue());
                        return this.getLastAttack();
                    }
                });
                Avatar.spellAction.addAction(new PlayerAction("Get Angy", 0, "you brace your self"){
                    @Override
                    public double attack(){
                        return 0.10;
                    }
                });
                Avatar.meleeAction.addAction(new PlayerAction("Kick", 4, "You kick at your foe"){
                    @Override
                    public double attack(){
                        this.setLastAttack(this.getValue());
                        return this.getLastAttack();
                    }
                });
                break;
            case "Wizard":
                Avatar.meleeAction.addAction(new PlayerAction("club", 4, "you bonk your foe"){
                    @Override
                    public double attack(){
                        this.setLastAttack(this.getValue());
                        return this.getLastAttack();
                    }
                });
                Avatar.spellAction.addAction(new PlayerAction("Harm", 8, "waving your hands you cast harm, battering your foe"){
                    @Override
                    public double attack(){
                        this.setLastAttack(this.getValue());
                        return this.getLastAttack();
                    }
                });
                Avatar.spellAction.addAction(new PlayerAction("Heal", -6, "you mend your wounds with blessed words"){
                    @Override
                    public double attack(){
                        this.setLastAttack(this.getValue());
                        return this.getLastAttack();
                    }
                });
                break;
            case "Ranger":
                
                break;
        
            default:
                break;
        }
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        player.setFacingDirection(Direction.DOWN);

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        winScreen = new WinScreen(this);
        inventoryScreen = new InventoryScreen(this, player);
        questLogScreen = new QuestLogScreen(this, flagManager);

        //Give player the quest log so scripts can update
        player.setQuestLog(questLogScreen, mainQuestFlags);

        try {
            AudioInputStream AIS = AudioSystem
                    .getAudioInputStream(new File("Resources/SoundEffects_AttackMotions/intro to rpg2.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(AIS);
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);// intro sound would be continously
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the
            // platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }

        questLogScreen.update();

        if (Keyboard.isKeyDown(Key.I) && keyPressTimer == 0) {
            keyPressTimer = 16;
            invToggle = ! invToggle;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }if(invToggle == true){
            inventoryScreen.update();
        }

        if (Keyboard.isKeyDown(Key.Q) && keyPressTimer == 0) {
            keyPressTimer = 14;
            questToggle = ! questToggle;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }
        

        // Gamestate changes
        if (map.getFlagManager().isFlagSet("hasFoundBall")) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
        }
        if (map.getFlagManager().isFlagSet("hasDied")) {
            playLevelScreenState = PlayLevelScreenState.GAME_OVER;
        }

        if(map.getFlagManager().isFlagSet("bossDead")){
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
        }

        if (map.getFlagManager().isFlagSet("lockedDoor")) {
            // Attempting to not spam player with lockedDoor textboxes
            if (lockDoorInteractPoint == null) {
                lockDoorInteractPoint = player.getLocation();
            }
            // Checks if player has moved from tile in which lockedDoorScript was triggered
            if (map.getTileByPosition(player.getX1(), player.getY1()).getIntersectRectangle()
                    .contains(lockDoorInteractPoint)) {
                // Do nothing
            } else if (map.getTextbox().isActive()) {
                lockDoorInteractPoint = player.getLocation();
            } else {
                flagManager.unsetFlag("lockedDoor");
                lockDoorInteractPoint = null;
            }
        }

        // Quests
        if (map.getFlagManager().isFlagSet("readQuestOneChest")) {
            // Attempting to not spam player with chest textboxes
            if (chestInteractPoint == null) {
                chestInteractPoint = player.getLocation();
            }
            // Checks if player has moved from tile in which QuestOneChestScript was
            // triggered
            if (map.getTileByPosition(player.getX1(), player.getY1()).getIntersectRectangle()
                    .contains(chestInteractPoint)) {
                // Do nothing
            } else if (map.getTextbox().isActive()) {
                chestInteractPoint = player.getLocation();
            } else {
                flagManager.unsetFlag("readQuestOneChest");
                chestInteractPoint = null;
            }
        }
        if (flagManager.isFlagSet("dummyAlive") && !flagManager.isFlagSet("talkedToOldMan1")) {
            startMap.getNPCById(40).setQuestIndicator(true);
            player.setMainQuest("dummyAlive");
        }
        if (flagManager.isFlagSet("talkedToOldMan1") && !flagManager.isFlagSet("investigateSusCharacter")) {
            startMap.getNPCById(40).setQuestIndicator(false);
            townMap.getNPCById(41).setQuestIndicator(true);
        } 
        if (!flagManager.isFlagSet("fisherguyInitiate") && flagManager.isFlagSet("talkedToOldMan1")) {
            townMap.getNPCById(43).setQuestIndicator(true);
        }
        if (!flagManager.isFlagSet("clearedEnemies") && flagManager.isFlagSet("fisherguyInitiate")) {
            // Check the status of enemies for fisherguy side quest
            int enemyAlive = 0;
            for (int i = 10; i <= 19; i++) {
                if (townMap.getNPCById(i).exists() == true) {
                    enemyAlive++;
                }
            }
            if (enemyAlive < 5) {
                flagManager.setFlag("clearedEnemies");
                townMap.getNPCById(43).toggleQuestIndicator();
            }
        }
        if (flagManager.isFlagSet("sickDogInitiate") && !flagManager.isFlagSet("foundHealthPotion")) {
            if (player.inventoryContains("Health Potion")) {
                townMap.getNPCById(46).setQuestIndicator(true);
                flagManager.setFlag("foundHealthPotion");
            }
        }
        if (flagManager.isFlagSet("investigateSusCharacter") && !flagManager.isFlagSet("foughtSusCharacter")) {
            townMap.getNPCById(44).setQuestIndicator(true);
            townMap.getNPCById(44).setInteractScript(new CombatScript("So you want to know about The Uncanny?",
                4,8,"The suspicious individual stabs at you",30,"foughtSusCharacter"));
        }
        if (flagManager.isFlagSet("foughtSusCharacter") && !flagManager.isFlagSet("talkedToOldMan2")) {
            player.setMainQuest("foughtSusCharacter");
            townMap.getNPCById(41).setQuestIndicator(true);
        }
        if (flagManager.isFlagSet("talkedToOldMan2") && !flagManager.isFlagSet("talkedToOldMan3")) {
            townMap.getNPCById(41).setQuestIndicator(false);
            townMap.getNPCById(42).setQuestIndicator(true);
        } 
        if (flagManager.isFlagSet("talkedToOldMan3")) {
            townMap.getNPCById(42).setQuestIndicator(false);
        }

        // Map change triggers
        if (map.getFlagManager().isFlagSet("startToTownMapPath")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(33, 125);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            map.setPlayer(player);
            flagManager.unsetFlag("startToTownMapPath");
        }
        if (map.getFlagManager().isFlagSet("townToStartMapPath")) {
            Point p;
            map = startMap;
            p = map.getPositionByTileIndex(23, 7);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.LEFT);
            map.setPlayer(player);
            flagManager.unsetFlag("townToStartMapPath");
        }
        if (map.getFlagManager().isFlagSet("townToStoreDoor")) {
            Point p;
            map = generalStoreMap;
            p = map.getPositionByTileIndex(9, 12);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            map.setPlayer(player);
            flagManager.unsetFlag("townToStoreDoor");
        }
        if (map.getFlagManager().isFlagSet("storeToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(38, 57);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("storeToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToH1Door")) {
            Point p;
            map = H1Map;
            p = map.getPositionByTileIndex(9, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            map.setPlayer(player);
            flagManager.unsetFlag("townToH1Door");
        }
        if (map.getFlagManager().isFlagSet("H1ToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(76, 46);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("H1ToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToH2Door")) {
            Point p;
            map = H2Map;
            p = map.getPositionByTileIndex(9, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            map.setPlayer(player);
            flagManager.unsetFlag("townToH2Door");
        }
        if (map.getFlagManager().isFlagSet("H2ToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(85, 46);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("H2ToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToH3Door")) {
            Point p;
            map = H3Map;
            p = map.getPositionByTileIndex(10, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            map.setPlayer(player);
            flagManager.unsetFlag("townToH3Door");
        }
        if (map.getFlagManager().isFlagSet("H3ToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(110, 68);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("H3ToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToH3_1Door")) {
            Point p;
            map = H3_1Map;
            p = map.getPositionByTileIndex(10, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            map.setPlayer(player);
            flagManager.unsetFlag("townToH3_1Door");
        }
        if (map.getFlagManager().isFlagSet("H3_1ToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(100, 52);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("H3_1ToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToInnDoor")) {
            Point p;
            map = innMap;
            p = map.getPositionByTileIndex(9, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            map.setPlayer(player);
            flagManager.unsetFlag("townToInnDoor");
        }
        if (map.getFlagManager().isFlagSet("innToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(33, 39);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("innToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToManorDoor")) {
            Point p;
            map = manorMap;
            p = map.getPositionByTileIndex(15, 23);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            map.setPlayer(player);
            flagManager.unsetFlag("townToManorDoor");
        }
        if (map.getFlagManager().isFlagSet("manorToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(60, 30);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("manorToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToSmithDoor")) {
            Point p;
            map = smithMap;
            p = map.getPositionByTileIndex(10, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            map.setPlayer(player);
            flagManager.unsetFlag("townToSmithDoor");
        }
        if (map.getFlagManager().isFlagSet("smithToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(13, 77);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("smithToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToHallDoor")) {
            Point p;
            map = townHallMap;
            p = map.getPositionByTileIndex(10, 12);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            map.setPlayer(player);
            flagManager.unsetFlag("townToHallDoor");
        }
        if (map.getFlagManager().isFlagSet("hallToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(78, 76);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("hallToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("startToDungeon")) {
            Point p;
            map = dungeonMap;
            p = map.getPositionByTileIndex(8, 5);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("startToDungeon");
        }
        if (map.getFlagManager().isFlagSet("dungeonToStart")) {
            Point p;
            map = startMap;
            p = map.getPositionByTileIndex(17, 20);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("dungeonToStart");
        }
        if (map.getFlagManager().isFlagSet("townToForest")) {
            Point p;
            map = forestMap;
            p = map.getPositionByTileIndex(5, 5);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("townToForest");
        }
        if (map.getFlagManager().isFlagSet("forestToTown")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(102, 113);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);
            flagManager.unsetFlag("forestToTown");
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        switch (playLevelScreenState) {
            case RUNNING:
                map.draw(player, graphicsHandler);
                break;
            case LEVEL_COMPLETED:
                winScreen.draw(graphicsHandler);
                break;
            case GAME_OVER:
                Avatar.resetPlayer();
                this.goBackToMenu();
                break;
        }
        if(invToggle){
            inventoryScreen.draw(graphicsHandler);
        }  
        if(questToggle){
            questLogScreen.draw(graphicsHandler);
        }
    }
            
    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    private void printMemoryUsage() {
		Runtime runtime = Runtime.getRuntime();
		long usedMemory = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Used memory: " + usedMemory / 1024 / 1024 + " MB");
	}

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }
        // }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED, GAME_OVER,
    }
}