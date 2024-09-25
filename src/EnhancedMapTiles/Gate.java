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
public class Gate extends EnhancedMapTile {
    
    public Gate(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Gate.png"), 250, 254), TileType.NOT_PASSABLE);
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(1)
                .build();
        return new GameObject(x, y, frame);
    }
    
}
