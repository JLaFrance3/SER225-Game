package NPCs.Monsters;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Utils.Direction;
import Utils.Point;

public class GoldDragon extends NPC {

    private int totalAmountMoved = 0;               // still need to fix the bounds
    private Direction direction = Direction.RIGHT;
    private float speed = 1;
    private int moveDistance = 180;  // Distance to move before turning around
    private int health = 40; //monster health
    private int attackPower = 30; // moster attack power

    public GoldDragon(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(
                ImageLoader.load("twinDragon.png"),
                144, 123), "WALK_RIGHT");
        this.health = 40;
        this.attackPower = 30;
    }

    
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
    @Override
    public void performAction(Player player) {
        // Move the goblin in the current direction until it reaches the specified distance
        if (totalAmountMoved < moveDistance) {
            float amountMoved = moveXHandleCollision(speed * direction.getVelocity());
            totalAmountMoved += Math.abs(amountMoved);
        } else {
            // If the goblin has moved 50 pixels, reset the counter and change direction
            totalAmountMoved = 0;
            if (direction == Direction.RIGHT) {
                direction = Direction.LEFT;
            } else {
                direction = Direction.RIGHT;
            }
        }

        // Set animation based on the current direction
        if (direction == Direction.RIGHT) {
            currentAnimationName = "WALK_RIGHT";
        } else {
            currentAnimationName = "WALK_LEFT";
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 1, false))
                            .withBounds(24, 26, 32, 32)
                            .withScale(2)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2, false))
                            .withBounds(24, 26, 32, 32)
                            .withScale(2)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 0, false))
                            .withBounds(24, 26, 32, 32)
                            .withScale(2)
                            .build(),
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(1, 1, false))
                            .withBounds(24, 26, 32, 32)
                            .withScale(2)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2, false))
                            .withBounds(24, 26, 32, 16)
                            .withScale(2)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 0, false))
                            .withBounds(24, 26, 32, 32)
                            .withScale(2)
                            .build(),
           });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 2, false), 14)
                            .withBounds(24, 26, 32, 32)
                            .withScale(2)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1, false), 14)
                            .withBounds(24, 26, 32, 32)
                            .withScale(2)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 0, false), 14)
                            .withBounds(24, 26, 32, 32)
                            .withScale(2)
                            .build(),
                    
            });

            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0, false), 14)
                        .withBounds(24, 26, 32, 32)
                        .withScale(2)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1, false), 14)
                        .withBounds(24, 26, 32, 32)
                        .withScale(2)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2, false), 14)
                        .withBounds(24, 26, 32, 32)
                        .withScale(2)
                        .build(),
            });
            put("WALK_UP", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 1, false), 14)
                        .withBounds(24, 26, 32, 32)
                        .withScale(2)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1, false), 14)
                        .withBounds(24, 26, 32, 32)
                        .withScale(2)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2, false), 14)
                        .withBounds(24, 26, 32, 32)
                        .withScale(2)
                        .build(),
            });
            put("WALK_DOWN", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 0, false), 14)
                        .withBounds(24, 26, 32, 32)
                        .withScale(2)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 1, false), 14)
                        .withBounds(24, 26, 32, 32)
                        .withScale(2)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 2, false), 14)
                        .withBounds(24, 26, 32, 32)
                        .withScale(2)
                        .build(),
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
