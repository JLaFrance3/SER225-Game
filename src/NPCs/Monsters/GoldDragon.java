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
    private int moveDistance = 150;  // Distance to move before turning around
    private int health = 40; //monster health
    private int attackPower = 30; // moster attack power

    public GoldDragon(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(
                ImageLoader.load("goldDragon.png"),
                144, 123), "WALK_RIGHT");
        this.health = health;
        this.attackPower = attackPower;
    }

    

    


    @Override
    public void performAction(Player player) {
        // Move the GoldDragon in the current direction until it reaches the specified distance
        if (totalAmountMoved < moveDistance) {
            float amountMovedX = 0;
            float amountMovedY = 0;

            // Handle movement and collisions based on the current direction
            switch (direction) {
                case RIGHT:
                    amountMovedX = moveXHandleCollision(speed);
                    break;
                case DOWN:
                    amountMovedY = moveYHandleCollision(speed);
                    break;
                case LEFT:
                    amountMovedX = moveXHandleCollision(-speed);
                    break;
                case UP:
                    amountMovedY = moveYHandleCollision(-speed);
                    break;
            }

            totalAmountMoved += Math.abs(amountMovedX) + Math.abs(amountMovedY);
        } else {
            // Reset the counter and change direction in the square pattern
            totalAmountMoved = 0;
            switch (direction) {
                case RIGHT:
                    direction = Direction.DOWN;
                    break;
                case DOWN:
                    direction = Direction.LEFT;
                    break;
                case LEFT:
                    direction = Direction.UP;
                    break;
                case UP:
                    direction = Direction.RIGHT;
                    break;
            }
        }

        // Set animation based on the current direction
        switch (direction) {
            case RIGHT:
                currentAnimationName = "WALK_RIGHT";
                break;
            case DOWN:
                currentAnimationName = "WALK_DOWN";
                break;
            case LEFT:
                currentAnimationName = "WALK_LEFT";
                break;
            case UP:
                currentAnimationName = "WALK_UP";
                break;
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 1, false))
                            .withBounds(24, 26, 16, 32)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 2, false))
                            .withBounds(24, 26, 16, 32)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 0, false))
                            .withBounds(24, 26, 16, 32)
                            .withScale(3)
                            .build(),
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(1, 1, false))
                            .withBounds(24, 26, 16, 32)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2, false))
                            .withBounds(24, 26, 16, 32)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 0, false))
                            .withBounds(24, 26, 16, 32)
                            .withScale(3)
                            .build(),
           });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(3, 2, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 1, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .withScale(3)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(3, 0, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .withScale(3)
                            .build(),
                    
            });

            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 0, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .withScale(3)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 1, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .withScale(3)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(1, 2, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .withScale(3)
                        .build(),
            });
            put("WALK_UP", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 1, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .withScale(3)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .withScale(3)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .withScale(3)
                        .build(),
            });
            put("WALK_DOWN", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 0, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .withScale(3)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 1, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .withScale(3)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 2, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .withScale(3)
                        .build(),
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
