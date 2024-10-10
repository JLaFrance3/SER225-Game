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
        // private SpriteSheet attackSpriteSheet;
        // private String currentAction;
        // private boolean isWieldingSword = false;
        // private SpriteSheet swordSprite;
        // private HashMap<String,Frame[]> regularAnimations;
        // private HashMap<String, Frame[]> swordAnimations;

        public Doug(float x, float y) {
                super(new SpriteSheet(ImageLoader.load("Doug.png"), 63, 63), x, y, "STAND_DOWN");
                walkSpeed = 2.3f;
        }

        public void update() {
                super.update();
                // handlePlayerState();

        }

        public void draw(GraphicsHandler graphicsHandler) {
                super.draw(graphicsHandler);
        }

        @Override
        public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
                return new HashMap<String, Frame[]>() {
                        {
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
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(11, 8), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build()
                                });
                                // Swoed Motion For DOUG
                                put("SWORD_UP", new Frame[] {
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
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(8, 9), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                });

                                put("SWORD_DOWN", new Frame[] {
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
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(10, 9), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                });
                                put("SWORD_RIGHT", new Frame[] {
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
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(11, 8), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(11, 9), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });

                                put("SWORD_LEFT", new Frame[] {
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
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(9, 9), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });

                                // Bow and Arrow Motion for Doug

                                put("ARROW_UP", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(16, 0), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 1), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 2), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 3), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 4), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 5), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 6), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 7), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 8), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 9), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 10), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 11), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(16, 12), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                });

                                put("ARROW_DOWN", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(18, 0), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 1), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 2), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 3), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 4), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 5), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 6), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 7), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 8), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 9), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 10), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 11), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(18, 12), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                });
                                put("ARROW_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(19, 0), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 1), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 2), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 3), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 4), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 5), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 6), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 7), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 8), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 9), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 10), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 11), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(19, 12), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                });
                                put("ARROW_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(17, 0), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 1), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 2), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 3), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 4), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 5), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 6), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 7), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 8), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 9), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 10), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 11), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(17, 12), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                });
                                put("MAGIC_UP", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(0, 0), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(0, 1), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(0, 2), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(0, 3), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(0, 4), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(0, 5), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(0, 6), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(0, 7), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                });
                                put("MAGIC_DOWN", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(2, 0), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(2, 1), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(2, 2), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(2, 3), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(2, 4), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(2, 5), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(2, 6), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(2, 7), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                });
                                put("MAGIC_RIGHT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(3, 0), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(3, 1), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(3, 2), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(3, 3), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(3, 4), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(3, 5), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(3, 6), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                                new FrameBuilder(spriteSheet.getSprite(3, 7), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });
                                put("MAGIC_LEFT", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 2), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 3), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 4), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 5), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 6), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(1, 7), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                });
                                put("FALL_DOWN", new Frame[] {
                                                new FrameBuilder(spriteSheet.getSprite(20, 0), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(20, 1), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(20, 2), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(20, 3), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(20, 4), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(20, 5), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),
                                                new FrameBuilder(spriteSheet.getSprite(20, 6), 14)
                                                                .withBounds(17, 14, 30, 48)
                                                                .build(),

                                });

                        }
                };

        };
};
