package EnhancedMapTiles;

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

// This class is for the special rock in the map that can be moved around by the player
// when the player walks into it, it will be "pushed" forward in the same direction the player was moving in
public class Chest extends EnhancedMapTile {
    public Chest(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("treasureChest.png"), 34, 32), TileType.NOT_PASSABLE);
    }

    

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(1, 0))
                .withScale(1)
                .build();
        return new GameObject(x, y, frame);
    }
}
