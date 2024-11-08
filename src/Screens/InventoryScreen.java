package Screens;

import Engine.*;
import EnhancedMapTiles.InventoryItem;
import Level.FlagManager;
import Level.Player;
import SpriteFont.SpriteFont;
import java.awt.*;
import java.awt.event.MouseEvent;
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
    protected int keyPressTimer;
    protected boolean itemDescriptToggle = false;
    protected BufferedImage itemDescript;
    protected SpriteFont itemDescription;
    protected SpriteFont itemName;
    protected int pointerLocationX = 400;
    protected int pointerLocationY = 139;
    protected int counter = 0;

    public InventoryScreen(PlayLevelScreen playLevelScreen, Player player) {
        this.playLevelScreen = playLevelScreen;
        this.player = player;
        inventory = ImageLoader.load("inventory.png");
        itemDescript = ImageLoader.load("itemDescribe.png");

        //Get player image from player sprite animation
        BufferedImage playerSprite = player.getAnimations().get("STAND_DOWN")[0].getImage();

        //Create a new image to scale up size of sprite 3x
        playerImage = new BufferedImage(192, 192, BufferedImage.TYPE_INT_ARGB);
        Graphics2D playerImageGraphics = playerImage.createGraphics();
        playerImageGraphics.drawImage(playerSprite, 0, 0, 192, 192, null);

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
        System.out.println("reached"); //make it so that if d is pressed, counter is incremented
        if(player.getInventoryArrayList().isEmpty() == false){
            
        if (Keyboard.isKeyDown(Key.O) && keyPressTimer == 0) {
            itemName = new SpriteFont(player.getInventoryArrayList().get(counter).getItemName(), 340, 409,"Arial", 20, Color.black);
            itemDescription = new SpriteFont(player.getInventoryArrayList().get(counter).getItemDescription(), 340, 450,"Arial", 15, Color.black);
            keyPressTimer = 14;
            itemDescriptToggle = ! itemDescriptToggle;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }
        if (Keyboard.isKeyDown(Key.D) && keyPressTimer == 0 && counter < player.getInventoryArrayList().size() - 1 && pointerLocationX > 0){
            pointerLocationX += 75;
            keyPressTimer = 14;
            counter++;
            itemName = new SpriteFont(player.getInventoryArrayList().get(counter).getItemName(), 340, 409,"Arial", 20, Color.black);
            itemDescription = new SpriteFont(player.getInventoryArrayList().get(counter).getItemDescription(), 340, 450,"Arial", 15, Color.black);            
        }
        if (Keyboard.isKeyDown(Key.A) && keyPressTimer == 0 && counter > 0 && pointerLocationX > 0){
            pointerLocationX -= 75;
            keyPressTimer = 14;
            counter--;
            itemName = new SpriteFont(player.getInventoryArrayList().get(counter).getItemName(), 340, 409,"Arial", 20, Color.black);
            itemDescription = new SpriteFont(player.getInventoryArrayList().get(counter).getItemDescription(), 340, 450,"Arial", 15, Color.black);            
        }
     }
 }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        graphicsHandler.drawImage(inventory, 0, 0, ScreenManager.getScreenWidth(),ScreenManager.getScreenHeight() );
        graphicsHandler.drawImage(playerImage, 110, 160);
        playerAp.draw(graphicsHandler);

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

        if(itemDescriptToggle){
            graphicsHandler.drawImage(itemDescript, 330, 398, 500, 140);
            itemDescription.draw(graphicsHandler);
            itemName.draw(graphicsHandler);
            graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, new Color(49, 207, 240), Color.black, 2);
            //based on value of counter change color
        }

        
       
        playerAp.draw(graphicsHandler);
        playerHealth.draw(graphicsHandler);
        playerName.draw(graphicsHandler);
        


       
        // System.out.println(player.getInventoryArrayList().get(0).getItemDescription())
        // winMessage.draw(graphicsHandler);
        // instructions.draw(graphicsHandler);
    }
}


