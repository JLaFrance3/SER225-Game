package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

// This class is for the walrus NPC
public class Goat extends NPC {
    int delay;
    Direction direction;

    public Goat(int id, Point location, Direction direction) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("/NPCSprites/goat.png", true), 128, 128), "EAT_" + direction.toString());

        this.delay = 300;
        this.direction = direction;
    }

    @Override
    public void performAction(Player player) {
        if (delay > 0) {
            delay--;
        } else {
            delay = 300;
        }

        if (delay <= 56) {
            currentAnimationName = "EAT_" + direction.toString();
        } else {
            currentAnimationName = "STAND_" + direction.toString();
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(4, 0, false))
                        .withBounds(46, 27, 30, 71)
                        .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(7, 0, false))
                        .withBounds(46, 27, 30, 71)
                        .build()
            });
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(5, 0, false))
                        .withBounds(46, 27, 30, 71)
                        .build()
            });
            put("STAND_DOWN", new Frame[] {
                new FrameBuilder(spriteSheet.getSubImage(6, 0, false))
                    .withBounds(46, 27, 30, 71)
                    .build()
            });
            put("EAT_UP", new Frame[] {
                new FrameBuilder(spriteSheet.getSubImage(4, 0, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(4, 1, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(4, 2, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(4, 3, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build()
            });
            put("EAT_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSubImage(5, 0, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(5, 1, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(5, 2, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(5, 3, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build()
            });
            put("EAT_DOWN", new Frame[] {
                new FrameBuilder(spriteSheet.getSubImage(6, 0, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(6, 1, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(6, 2, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(6, 3, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build()
            });
            put("EAT_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSubImage(7, 0, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(7, 1, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(7, 2, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(7, 3, false), 14)
                    .withBounds(46, 27, 30, 71)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
