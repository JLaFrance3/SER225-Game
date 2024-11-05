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
    public static enum EQUIP_TYPE {
        SWORD, DAGGER, BOW, STAFF, SHIELD, TORSO, ARMS, 
        LEGS, SHOULDER, HEAD, FEET, HANDS, NOT_EQUIPPABLE
    }
    protected BufferedImage inventoryItem;
    private String itemName;
    private float itemDamage;
    private String itemDescription;
    private String spriteSheetPath;
    private EQUIP_TYPE equipType;

    public InventoryItem(BufferedImage inventoryItem, String itemName, float itemDamage, String itemDescription ){
        this.inventoryItem = inventoryItem;
        this.itemName = itemName;
        this.itemDamage = itemDamage;
        this.itemDescription = itemDescription;
        this.spriteSheetPath = null;
        this.equipType = EQUIP_TYPE.NOT_EQUIPPABLE;
    }

    //This constructor should be used for equippable weapons/armor
    public InventoryItem(BufferedImage inventoryItem, String itemName, float itemDamage, String itemDescription, String filepath, EQUIP_TYPE type){
        this.inventoryItem = inventoryItem;
        this.itemName = itemName;
        this.itemDamage = itemDamage;
        this.itemDescription = itemDescription;
        this.spriteSheetPath = filepath;
        this.equipType = type;
    }

    public EQUIP_TYPE getType() {
        return equipType;
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

    public void setFilePath(String filepath) {
        this.spriteSheetPath = filepath;
    }

    public String getFilePath() {
        return spriteSheetPath;
    }

    public void draw(GraphicsHandler graphicsHandlerr, int x, int y){
        graphicsHandlerr.drawImage(inventoryItem, x, y);
    }
}
