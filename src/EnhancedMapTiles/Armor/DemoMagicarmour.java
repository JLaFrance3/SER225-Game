package EnhancedMapTiles.Armor;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.TileType;
import Utils.Point;

public class DemoMagicarmour extends EnhancedMapTile{
    private int damage; // need to instantiate and make getters/setters
    private String description;

    public DemoMagicarmour(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("IndoorTileset.png", true)
                .getSubimage(0, 736, 768, 224), 32, 32), TileType.NOT_PASSABLE);
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(6, 8, false))
                .build();
        return new GameObject(x, y, frame);
    }
      
}
