package EnhancedMapTiles;

import java.awt.image.BufferedImage;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Player;
import Level.PlayerState;
import Level.TileType;
import Utils.Direction;
import Utils.Point;




public class InventoryItem{
    protected BufferedImage inventoryItem;
    private String itemName;
    private float itemDamage;
    private String itemDescription;

    public InventoryItem(BufferedImage inventoryItem, String itemName, float itemDamage, String itemDescription ){
        this.inventoryItem = inventoryItem;
        this.itemName = itemName;
        this.itemDamage = itemDamage;
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public float getItemDamage() {
        return itemDamage;
    }

    public String getItemDescription() {
        return itemDescription;
    }



    public void draw(GraphicsHandler graphicsHandlerr, int x, int y){
        graphicsHandlerr.drawImage(inventoryItem, x, y);
    }
}
