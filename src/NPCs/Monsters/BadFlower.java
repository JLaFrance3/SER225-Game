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

public class BadFlower extends NPC {

    private int totalAmountMoved = 0;
    private Direction direction = Direction.RIGHT;
    private float speed = 1;
    private int moveDistance = 200;  // Distance to move before turning around
    private int health = 40; //monster health
    private int attackPower = 30; // monster attack power

    public BadFlower(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(
                ImageLoader.load("BadFlower.png", true),
                128, 128), "WALK_LEFT");
        this.health = health;
        this.attackPower = attackPower;
    }

    public int getGoblinHealth(){
        return health;
    }

    public void setGoblinHealth(int health){
        this.health = health;
    }

    public int getGoblinAttackPower(){
        return attackPower;
    }

    public void setGoblinAttackPower(int attackPower){
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
                    new FrameBuilder(spriteSheet.getSprite(1, 0,false))
                            .withBounds(40,30, 52, 78)
                            .withScale(1)
                            .build(),
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(3, 0, false))
                            .withBounds(40,30, 52, 78)
                            .withScale(1)
                            .build(),
           });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0, false), 14)
                            .withBounds(40,30, 52, 78)
                            .withScale(1)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1, false), 14)
                            .withBounds(40,30, 52, 78)
                            .withScale(1)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 2, false), 14)
                            .withBounds(40,30, 52, 78)
                            .withScale(1)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 3, false), 14)
                            .withBounds(40,30, 52, 78)
                            .withScale(1)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 4, false), 14)
                            .withBounds(40,30, 52, 78)
                            .withScale(1)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 5, false), 14)
                            .withBounds(40,30, 52, 78)
                            .withScale(1)
                            .build(),
            });

            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(3, 0, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(1)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 1, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(1)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 2, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(1)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 3, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(1)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 4, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(1)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(3, 5, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(1)
                        .build(),
            });
            put("WALK_UP", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 1, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(1)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(1)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 2, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(3)
                        .build(),
            });
            put("WALK_DOWN", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(2, 0, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(1)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 1, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(1)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(2, 2, false), 14)
                        .withBounds(40,30, 52, 78)
                        .withScale(1)
                        .build(),
            });
        }};
    }
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
