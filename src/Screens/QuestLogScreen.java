package Screens;

import Engine.*;
import EnhancedMapTiles.InventoryItem;
import Level.FlagManager;
import Level.Player;
import SpriteFont.SpriteFont;
import java.awt.*;
import java.awt.image.BufferedImage;

public class QuestLogScreen extends Screen {

    protected PlayLevelScreen playLevelScreen;
    protected FlagManager questFlagManager;
    protected SpriteFont currentQuestLabel;
    protected String currentQuest = "";
    protected BufferedImage questLog;
    protected String[] questList;

    public QuestLogScreen(PlayLevelScreen playLevelScreen, FlagManager flagManager) {
        this.playLevelScreen = playLevelScreen;
        this.questFlagManager = flagManager;
        questLog = ImageLoader.load("QuestLog.png");

        // List of strings that will be displayed based on which quest the player is on
        this.questList = new String[]{
            "There is no current quest.",
            "Talk to the old man in front of one of the \nhouses where you started.",
            "Find the old man's sword",
            "Return the sword to the old man",
            "Kill the evil bug",  
            "Talk to the old man again",                                                           
            "Travel northeast to town and find the merchant \nin the market.",
            "Find the mysterious man by the Town Hall \nfor more information.",
            "Make your way into the Town Hall to \nlook at the maps.",
            "Find house 0112 in the town for \n another clue.",
            "Go down to the southeast outskirts \nof town to find the forest of the Uncanny.",
            "Kill the Goblins to enter the forest."
        };

        currentQuest = questList[1];

        initialize();
    }

    @Override
    public void initialize() {
        currentQuestLabel = new SpriteFont("Main Quest:", 75, 100, "Arial",30, Color.white);
    }

    @Override
    public void update() {

    }

    public void setMainQuest(Integer questNum){
        currentQuest = questList[questNum];
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(questLog, 0, 0, ScreenManager.getScreenWidth(),ScreenManager.getScreenHeight() );
        currentQuestLabel.draw(graphicsHandler);
        if(currentQuest != ""){
            new SpriteFont(currentQuest, 75, 200, "Arial",30, Color.white).drawWithParsedNewLines(graphicsHandler, 30);
        }
    }
        // winMessage.draw(graphicsHandler);
        // instructions.draw(graphicsHandler);
}
