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
            "Find the merchant in the market and talk to him.",
            "Find the mysterious man by the Town Hall \nfor more information.",
            "Make your way to the southeast outskirts \nof town where the big forest is located."
        };

        initialize();
    }

    @Override
    public void initialize() {
        currentQuestLabel = new SpriteFont("Your current quest:", 75, 100, "Arial",30, Color.white);
    }

    @Override
    public void update() {
        if (questFlagManager != null){
            if(!questFlagManager.isFlagSet("talkedToOldMan1")){
                // if not talked to old man 1 yet
                currentQuest = questList[1];
            } else{
                if(!questFlagManager.isFlagSet("talkedToOldMan2")){
                    // if talked to old man 1 but not old man 2
                    currentQuest = questList[2];
                } else{
                    if(!questFlagManager.isFlagSet("talkedToOldMan3")){
                        // if talked to old man 2 but not old man 3
                        currentQuest = questList[3];
                    } else{
                        currentQuest = questList[4];
                    }
                }
            }
        }
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
