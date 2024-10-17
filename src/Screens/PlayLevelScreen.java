package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.*;
import Maps.*;
import Players.Doug;
import Utils.Direction;
import Utils.Point;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Map startMap, townMap, generalStoreMap, H1Map, H2Map, H3Map, H3_1Map;
    protected Map innMap, manorMap, smithMap, townHallMap;
    protected String[] mapChangeFlags;
    protected DungeonScreen dungeon;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;
    protected Point lockDoorInteractPoint;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        lockDoorInteractPoint = null;
    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("hasLostBall", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
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

        // Map change flags
        mapChangeFlags = new String[]{
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
            "hallToTownDoor"
        };

        //Add all map change flags
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

        // set current map to startMap
        map = startMap;

        // setup player
        player = new Doug(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        player.setFacingDirection(Direction.DOWN);

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();

        winScreen = new WinScreen(this);
    }

    public void update() {
        // based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }

        // Gamestate changes
        if (map.getFlagManager().isFlagSet("hasFoundBall")) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
        }
        if (map.getFlagManager().isFlagSet("gateInteract")) { // if the gate interact flag is set then change the screen
            screenCoordinator.setGameState(GameState.DUNGEON);
        }

        if (map.getFlagManager().isFlagSet("lockedDoor")) {
            //Attempting to not spam player with lockedDoor textboxes
            if (lockDoorInteractPoint == null) {
                lockDoorInteractPoint = player.getLocation();
            }
            //Checks if player has moved from tile in which lockedDoorScript was triggered
            if (map.getTileByPosition(player.getX1(), player.getY1()).getIntersectRectangle().contains(lockDoorInteractPoint)) {
                //Do nothing
            }
            else if (map.getTextbox().isActive()) {
                lockDoorInteractPoint = player.getLocation();
            }
            else {
                flagManager.unsetFlag("lockedDoor");
                lockDoorInteractPoint = null;
            }
        }

        // Map change triggers
        if (map.getFlagManager().isFlagSet("startToTownMapPath")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(33, 125);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            flagManager.unsetFlag("startToTownMapPath");
        }
        if (map.getFlagManager().isFlagSet("townToStartMapPath")) {
            Point p;
            map = startMap;
            p = map.getPositionByTileIndex(23, 7);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.LEFT);
            flagManager.unsetFlag("townToStartMapPath");
        }
        if (map.getFlagManager().isFlagSet("townToStoreDoor")) {
            Point p;
            map = generalStoreMap;
            p = map.getPositionByTileIndex(9, 12);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            flagManager.unsetFlag("townToStoreDoor");
        }
        if (map.getFlagManager().isFlagSet("storeToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(38, 57);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            flagManager.unsetFlag("storeToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToH1Door")) {
            Point p;
            map = H1Map;
            p = map.getPositionByTileIndex(9, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            flagManager.unsetFlag("townToH1Door");
        }
        if (map.getFlagManager().isFlagSet("H1ToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(76, 46);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            flagManager.unsetFlag("H1ToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToH2Door")) {
            Point p;
            map = H2Map;
            p = map.getPositionByTileIndex(9, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            flagManager.unsetFlag("townToH2Door");
        }
        if (map.getFlagManager().isFlagSet("H2ToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(85, 46);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            flagManager.unsetFlag("H2ToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToH3Door")) {
            Point p;
            map = H3Map;
            p = map.getPositionByTileIndex(10, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            flagManager.unsetFlag("townToH3Door");
        }
        if (map.getFlagManager().isFlagSet("H3ToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(110, 68);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            flagManager.unsetFlag("H3ToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToH3_1Door")) {
            Point p;
            map = H3_1Map;
            p = map.getPositionByTileIndex(10, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            flagManager.unsetFlag("townToH3_1Door");
        }
        if (map.getFlagManager().isFlagSet("H3_1ToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(100, 52);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            flagManager.unsetFlag("H3_1ToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToInnDoor")) {
            Point p;
            map = innMap;
            p = map.getPositionByTileIndex(9, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            flagManager.unsetFlag("townToInnDoor");
        }
        if (map.getFlagManager().isFlagSet("innToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(33, 39);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            flagManager.unsetFlag("innToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToManorDoor")) {
            Point p;
            map = manorMap;
            p = map.getPositionByTileIndex(15, 23);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            flagManager.unsetFlag("townToManorDoor");
        }
        if (map.getFlagManager().isFlagSet("manorToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(60, 30);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            flagManager.unsetFlag("manorToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToSmithDoor")) {
            Point p;
            map = smithMap;
            p = map.getPositionByTileIndex(10, 11);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            flagManager.unsetFlag("townToSmithDoor");
        }
        if (map.getFlagManager().isFlagSet("smithToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(13, 77);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            flagManager.unsetFlag("smithToTownDoor");
        }
        if (map.getFlagManager().isFlagSet("townToHallDoor")) {
            Point p;
            map = townHallMap;
            p = map.getPositionByTileIndex(10, 12);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.UP);
            flagManager.unsetFlag("townToHallDoor");
        }
        if (map.getFlagManager().isFlagSet("hallToTownDoor")) {
            Point p;
            map = townMap;
            p = map.getPositionByTileIndex(78, 76);
            player.setMap(map);
            player.setLocation(p.x, p.y);
            player.setFacingDirection(Direction.DOWN);
            flagManager.unsetFlag("hallToTownDoor");
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
        }
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }


    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED,
    }
}
