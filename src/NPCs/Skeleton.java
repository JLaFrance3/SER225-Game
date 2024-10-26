package NPCs;

import java.util.HashMap;

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

public class Skeleton extends NPC {

    private int totalAmountMoved = 0;
    private Direction direction = Direction.RIGHT;
    private float speed = 1;
    private int moveDistance = 200;  // Distance to move before turning around
    private int health = 50; //monster health
    private int attackPower = 10; // moster attack power

    public Skeleton(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(
            ImageLoader.load("Skelington.png", true),
            64, 64), "WALK_RIGHT");        this.health = health;
        this.attackPower = attackPower;

    }

    public int getSkeletonHealth() {
        return health;
    }

    public int getSkeletonAttackpower(){
        return attackPower;
    }

    public void setSkeletonAttackPower(int attackPower){
        this.attackPower = attackPower;
    }

    public void setSkeletonHealth(int health){
        this.health = health;
    }

    @Override
    public void performAction(Player player) {
        // Move the skeleton in the current direction until it reaches the specified distance
        if (totalAmountMoved < moveDistance) {
            float amountMoved = moveXHandleCollision(speed * direction.getVelocity());
            totalAmountMoved += Math.abs(amountMoved);
        } else {
            // If the skeleton has moved 50 pixels, reset the counter and change direction
            totalAmountMoved = 0;
            
            // Simplified if-statement to toggle direction
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
                    new FrameBuilder(spriteSheet.getSprite(1, 0, false))
                            .withBounds(24, 26, 16, 32)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(3, 0, false))
                            .withBounds(24, 26, 16, 32)
                            .build()
           });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(9, 0, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 1, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 2, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 3, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 4, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 5, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 6, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 7, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(9, 8, false), 14)
                            .withBounds(24, 26, 16, 32)
                            .build(),
            });

            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(11, 0, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(11, 1, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(11, 2, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(11, 3, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(11, 4, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(11, 5, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(11, 6, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(11, 7, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(11, 8, false), 14)
                        .withBounds(24, 26, 16, 32)
                        .build(),
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
