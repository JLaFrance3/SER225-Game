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

public class Goblin extends NPC {

    private int totalAmountMoved = 0;
    private Direction direction = Direction.RIGHT;
    private float speed = 1;
    private int moveDistance = 150;  // Distance to move before turning around
    private int health = 40; //monster health
    private int attackPower = 30; // moster attack power

    public Goblin(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("fixedGoblin.png"), 33, 35), "WALK_RIGHT");
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
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(4, 5, 5, 10)
                            .build()
            });
            put("STAND_RIGHT", new Frame[] {
                   new FrameBuilder(spriteSheet.getSprite(0, 0))
                           .withScale(3)
                           .withBounds(4, 5, 5, 10)
                           .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                           .build()
           });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(10, 1), 14)
                            .withScale(3)
                            .withBounds(4, 5, 5, 10)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                            .withScale(3)
                            .withBounds(4, 5, 5, 10)
                            .build()
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(1, 0), 14)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 5, 5, 10)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(1, 1), 14)
                            .withScale(3)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(4, 5, 5, 10)
                            .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
