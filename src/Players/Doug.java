package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;

import java.util.HashMap;

// This is the class for Doug, a demo character
public class Doug extends Player {

    public Doug(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("Doug.png"), 63, 63), x, y, "STAND_DOWN");
        walkSpeed = 2.3f;
    }

    public void update() {
        super.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(8, 0))
                            .withBounds(17, 14, 30, 48)
                            .build()
            });

            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(9, 0))
                        .withBounds(17, 14, 30, 48)
                        .build()
            });

            put("STAND_DOWN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(10, 0))
                        .withBounds(17, 14, 30, 48)
                        .build()
            });

            put("STAND_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(11, 0))
                        .withBounds(17, 14, 30, 48)
                        .build()
            });

            put("WALK_UP", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(8, 0), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(8, 1), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(8, 2), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(8, 3), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(8, 4), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(8, 5), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(8, 6), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(8, 7), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(8, 8), 14)
                            .withBounds(17, 14, 30, 48)
                            .build()
            });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(9, 0), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 1), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 2), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 3), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 4), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 5), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 6), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 7), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 8), 14)
                            .withBounds(17, 14, 30, 48)
                            .build()
                });

        put("WALK_DOWN", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(10, 0), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(10, 1), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(10, 2), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(10, 3), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(10, 4), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(10, 5), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(10, 6), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(10, 7), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(10, 8), 14)
                            .withBounds(17, 14, 30, 48)
                            .build()
                });

        put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(11, 0), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 1), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 2), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 3), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 4), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 5), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 6), 14)
                            .withBounds(17, 14, 30, 48)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 7), 14)
                            .withBounds(17, 14, 30, 488)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 8), 14)
                            .withBounds(17, 14, 30, 48)
                            .build()
                });
        }};
    }
}
