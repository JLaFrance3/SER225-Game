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
public class Dog extends NPC {
    public Dog(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("/NPCSprites/dog.png", true), 32, 32), "LAY");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("LAY", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 3, false))
                        .withScale(2)
                        .withBounds(5, 9, 20, 18)
                        .build()
            });
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSubImage(4, 0, false), 14)
                    .withScale(2)
                    .withBounds(5, 9, 20, 18)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(4, 1, false), 14)
                    .withScale(2)
                    .withBounds(5, 9, 20, 18)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(4, 2, false), 14)
                    .withScale(2)
                    .withBounds(5, 9, 20, 18)
                    .build(),
                new FrameBuilder(spriteSheet.getSubImage(4, 3, false), 14)
                    .withScale(2)
                    .withBounds(5, 9, 20, 18)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
