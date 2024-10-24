package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Player;

import java.util.HashMap;

// This is the class for Doug, a demo character
public class Doug extends Player {
        public Doug(float x, float y) {
                super(new SpriteSheet(ImageLoader.load("Doug.png", true), 64, 64), x, y, "STAND_DOWN");
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
                return new HashMap<String, Frame[]>() {
                        {
                                put("STAND_UP", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(8, 0, false))
                                                        .withBounds(17, 14, 30, 48)
                                                        .build()
                                });

                                put("STAND_LEFT", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(9, 0, false))
                                                        .withBounds(17, 14, 30, 48)
                                                        .build()
                                });

                                put("STAND_DOWN", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(10, 0, false))
                                                        .withBounds(17, 14, 30, 48)
                                                        .build()
                                });

                                put("STAND_RIGHT", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(11, 0, false))
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
                                                        .build(),
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

                                put("SWORD_UP", new Frame[] {
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
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(8, 9, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),

                                });
                                put("SWORD_DOWN", new Frame[] {
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
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(10, 9, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                });
                                put("SWORD_RIGHT", new Frame[] {
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
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(11, 9, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build()
                                });
                                put("SWORD_LEFT", new Frame[] {
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
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(9, 9, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                });
                                // Bow and Arrow Motion for Doug
                                put("ARROW_UP", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(16, 0, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 1, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 2, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 3, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 4, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 5, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 6, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 7, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 8, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 9, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 10, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 11, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(16, 12, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                });
                                put("ARROW_DOWN", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(18, 0, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 1, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 2, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 3, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 4, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 5, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 6, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 7, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 8, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 9, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 10, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 11, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(18, 12, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                });
                                put("ARROW_RIGHT", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(19, 0, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 1, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 2, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 3, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 4, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 5, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 6, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 7, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 8, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 9, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 10, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 11, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(19, 12, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                });
                                put("ARROW_LEFT", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(17, 0, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 1, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 2, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 3, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 4, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 5, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 6, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 7, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 8, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 9, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 10, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 11, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(17, 12, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                });
                                put("MAGIC_UP", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(0, 0, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(0, 1, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(0, 2, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(0, 3, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(0, 4, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(0, 5, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(0, 6, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(0, 7, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                });
                                put("MAGIC_DOWN", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(2, 0, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(2, 1, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(2, 2, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(2, 3, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(2, 4, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(2, 5, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(2, 6, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(2, 7, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                });
                                put("MAGIC_RIGHT", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(3, 0, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(3, 1, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(3, 2, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(3, 3, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(3, 4, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(3, 5, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(3, 6, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(3, 7, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                });
                                put("MAGIC_LEFT", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(1, 0, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(1, 1, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(1, 2, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(1, 3, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(1, 4, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(1, 5, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(1, 6, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(1, 7, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                });
                                put("FALL_DOWN", new Frame[] {
                                        new FrameBuilder(spriteSheet.getSubImage(20, 0, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(20, 1, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(20, 2, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(20, 3, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(20, 4, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(20, 5, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                        new FrameBuilder(spriteSheet.getSubImage(20, 6, false), 14)
                                                        .withBounds(17, 14, 30, 48)
                                                        .build(),
                                });
                        }
                };

        }
}
