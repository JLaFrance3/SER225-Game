package Screens;

import Engine.*;
import EnhancedMapTiles.InventoryItem;
import Level.FlagManager;
import Level.Player;
import SpriteFont.SpriteFont;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class QuestLogScreen extends Screen {

    protected PlayLevelScreen playLevelScreen;
    protected FlagManager questFlagManager;
    protected SpriteFont currentQuestLabel;
    protected SpriteFont sideQuestLabel;
    protected String currentQuest = "";
    protected BufferedImage questLog;
    protected String[] questList;
    protected ArrayList<String> sideQuestList;

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
            "Return to the old man",                                   
            "Travel northeast to town and find the\nmerchant in the market.",
            "Investigate suspicious merchant in the market",
            "Return to the old merchant",
            "Talk to the old guard that lives behind\nthe Town Hall.",
            "Make your way to the southeast outskirts \nof town where the big forest is located."
        };

        currentQuest = questList[1];
        sideQuestList = new ArrayList<String>();

        initialize();
    }

    @Override
    public void initialize() {
        currentQuestLabel = new SpriteFont("Main Quest:", 75, 100, "Arial",30, Color.white);
        sideQuestLabel = new SpriteFont("Side Quests:", 75, 280, "Arial",30, Color.white);
    }

    @Override
    public void update() {

    }

    public void setMainQuest(Integer questNum){
        currentQuest = questList[questNum];
    }

    public void setSideQuestNote(String questNote) {
        sideQuestList.add(questNote);
    }

    public void removeSideQuestNote(String questNote) {
        sideQuestList.remove(questNote);
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawImage(questLog, 0, 0, ScreenManager.getScreenWidth(),ScreenManager.getScreenHeight() );
        currentQuestLabel.draw(graphicsHandler);
        if(currentQuest != ""){
            new SpriteFont(currentQuest, 75, 140, "Arial",30, Color.white).drawWithParsedNewLines(graphicsHandler, 20);
        }
        if (!sideQuestList.isEmpty()) {
            sideQuestLabel.draw(graphicsHandler);
        }
        int yPos = 320;
        for (String note : sideQuestList) {
            new SpriteFont("-" + note, 75, yPos, "Arial",30, Color.white).draw(graphicsHandler);
            yPos += 40;
        }
    }
        // winMessage.draw(graphicsHandler);
        // instructions.draw(graphicsHandler);
}
