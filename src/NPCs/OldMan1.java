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
public class OldMan1 extends NPC {

    public OldMan1(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("OldMan1.png", true), 64, 64), "STAND_UP");
        drawQuestIndicator = true;
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
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
