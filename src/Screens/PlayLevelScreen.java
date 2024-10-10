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
    protected Map startMap, townMap;
    protected DungeonScreen dungeon;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
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
        flagManager.addFlag("startToTownMapPath", false);
        flagManager.addFlag("townToStartMapPath", false);

        // game maps
        startMap = new TestMap();
        startMap.setFlagManager(flagManager);

        townMap = new TownMap();
        townMap.setFlagManager(flagManager);

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

        // if flag is set at any point during gameplay, game is "won"
        if (map.getFlagManager().isFlagSet("hasFoundBall")) {
            playLevelScreenState = PlayLevelScreenState.LEVEL_COMPLETED;
        }

        if (map.getFlagManager().isFlagSet("gateInteract")) { // if the gate interact flag is set then change the screen
            screenCoordinator.setGameState(GameState.DUNGEON);
            
        }

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
