package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

import java.util.HashMap;

// This class is for the walrus NPC
public class FarmerGirl extends NPC {

    public FarmerGirl(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("FarmerGirl.png"), 64, 64), "STAND_UP");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(10, 0))
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
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}