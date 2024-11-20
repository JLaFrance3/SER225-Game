package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

import java.util.HashMap;

// This class is for the walrus NPC
public class Knight extends NPC {

    public Knight(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("knight.png"), 64, 63), "STAND_UP");
        drawQuestIndicator = true;
        setQuestIndicator(true);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(20, 4))
                            .withScale(1)
                            .withBounds(17, 14, 30, 48)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(3, 0))
                           .withScale(1)
                           .withBounds(17, 14, 30, 48)
                           .build()
           });
           put("STAND_LEFT", new Frame[] {
            new FrameBuilder(spriteSheet.getSprite(5, 0))
                    .withScale(1)
                    .withBounds(17, 14, 30, 48)
                    .build()
            });
            put("STAND_DOWN", new Frame[] {
                new FrameBuilder(spriteSheet.getSubImage(10, 0, false))
                                .withBounds(17, 14, 30, 48)
                                .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}

