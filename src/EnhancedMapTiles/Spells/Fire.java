package EnhancedMapTiles.Spells;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.TileType;
import Utils.Point;

public class Fire extends EnhancedMapTile{

    private int damage; 
    private String description;

    public Fire(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("items.png"), 33, 36), TileType.NOT_PASSABLE);
    }

    public int getDamage() {
        return damage;
    }



    public void setDamage(int damage) {
        this.damage = damage;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    

    

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(12, 7))
                .withScale(1)
                .build();
        return new GameObject(x, y, frame);
    }
}
