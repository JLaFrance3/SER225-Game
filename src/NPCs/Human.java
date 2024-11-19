package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

// This class is for the walrus NPC
public class Human extends NPC {

    public Human(int id, Point location, SpriteSheet ss) {
        super(id, location.x, location.y, ss, "STAND_DOWN");
    }

    public Human(int id, Point location, SpriteSheet ss, Direction dir) {
        super(id, location.x, location.y, ss, "STAND_" + dir.toString());
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(2, 0))
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
            put("WALK_UP", new Frame[] {
                new FrameBuilder(spriteSheet.getSubImage(8, 0, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(8, 1, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(8, 2, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(8, 3, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(8, 4, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(8, 5, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(8, 6, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(8, 7, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(8, 8, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build()
            });
            put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSubImage(9, 0, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(9, 1, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(9, 2, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(9, 3, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(9, 4, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(9, 5, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(9, 6, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(9, 7, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(9, 8, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build()
            });
            put("WALK_DOWN", new Frame[] {
                new FrameBuilder(spriteSheet.getSubImage(10, 0, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(10, 1, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(10, 2, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(10, 3, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(10, 4, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(10, 5, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(10, 6, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(10, 7, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(10, 8, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build()
            });
            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSubImage(11, 0, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(11, 1, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(11, 2, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(11, 3, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(11, 4, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(11, 5, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(11, 6, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(11, 7, false), 14)
                    .withBounds(17, 14, 30, 48)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(11, 8, false), 14)
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
