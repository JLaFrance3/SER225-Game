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

    public InventoryItem(BufferedImage inventoryItem){
        this.inventoryItem = inventoryItem;
    }

    public void draw(GraphicsHandler graphicsHandlerr, int x, int y){
        graphicsHandlerr.drawImage(inventoryItem, x, y);
    }
}
