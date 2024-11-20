package EnhancedMapTiles.Swords;

import Builders.FrameBuilder;
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


public class GreatSword extends EnhancedMapTile {

    private int damage; // need to instantiate and make getters/setters
    private String description;

    public GreatSword(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.loadSubImage("items.png", 103, 103, 32, 34), 32, 34), TileType.NOT_PASSABLE);
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(1)
                .build();
        return new GameObject(x, y, frame);
    }
}
