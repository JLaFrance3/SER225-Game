package Screens;

import Engine.*;
import EnhancedMapTiles.InventoryItem;
import Level.EnhancedMapTile;
import Level.FlagManager;
import Level.Player;
import SpriteFont.SpriteFont;
import Players.Doug;
import java.awt.*;
import java.awt.image.BufferedImage;

// This class is for the win level screen
public class InventoryScreen extends Screen {
    protected SpriteFont winMessage;
    protected SpriteFont playerAp;
    protected SpriteFont playerHealth;
    protected SpriteFont instructions;
    protected SpriteFont playerName;
    protected KeyLocker keyLocker = new KeyLocker();
    protected PlayLevelScreen playLevelScreen;
    protected BufferedImage inventory;
    protected BufferedImage playerImage;
    protected Player player; 
    protected FlagManager flagManager;


    public InventoryScreen(PlayLevelScreen playLevelScreen, Player player) {
        this.playLevelScreen = playLevelScreen;
        this.player = player;
        inventory = ImageLoader.load("inventory.png");
        playerImage = ImageLoader.loadSubImage("Doug.png", 0, 393, 51, 53);
        initialize();
    }

    @Override
    public void initialize() {
        playerAp = new SpriteFont("Attack Power", 50, 435, "Arial",20, Color.black);
        playerHealth = new SpriteFont("Health", 50, 409, "Arial",20, Color.black);
        playerName = new SpriteFont("Doug", 175, 90, "Arial",20, Color.black);

    }

    @Override
    public void update() {
        
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        graphicsHandler.drawImage(inventory, 0, 0, ScreenManager.getScreenWidth(),ScreenManager.getScreenHeight() );
        graphicsHandler.drawImage(playerImage, 170, 200);
        playerAp.draw(graphicsHandler);
        System.out.println(player.getInventoryArrayList());
        int startX = 400;
        int startY = 95;
        for(int i = 0; i < player.getInventoryArrayList().size(); i++){
            InventoryItem item1 = player.getInventoryArrayList().get(i);
            if(startX < 720){
                item1.draw(graphicsHandler, startX, startY); //where each slot goes
                startX +=70;
            } else{
                startX = 400;
                startY +=85;
                item1.draw(graphicsHandler, startX, startY);
            }
            
        }
        playerAp.draw(graphicsHandler);
        playerHealth.draw(graphicsHandler);
        playerName.draw(graphicsHandler);
        


       
        
        // winMessage.draw(graphicsHandler);
        // instructions.draw(graphicsHandler);
    }
}


