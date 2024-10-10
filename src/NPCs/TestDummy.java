package NPCs;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.MapTile;
import Level.NPC;
import Level.Player;
import Utils.Direction;
import Utils.Point;

public class TestDummy extends NPC {
    private int totalAmountMoved = 0;
    private Direction direction = Direction.UP;
    private float speed = 1;
    private int random = 45 + (int)(Math.random()*45);
    private int totalMoveCap = 90;
    private Point max, min;
    private Point inital;
    //private int lowerX, lowerY, upperX, upperY;

    private int health = 50; //monster health
    private int attackPower = 10; // monster attack power

    
    public TestDummy(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Bug.png"), 24, 15), "WALK_RIGHT");
    }

    public TestDummy(int id, Point location, Direction direction, Point max) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Bug.png"), 24, 15), "WALK_RIGHT");
        this.direction = direction;
        this.inital = location;
        this.max = max;
        //this.min = min;
    }

        // this code makes the bug npc walk back and forth (left to right)
    @Override
    public void performAction(Player player) {
        // if bug has not yet moved 135 pixels in one direction, move bug forward
        if (totalAmountMoved < 90 && totalAmountMoved < random) {

            float amountMoved;
            //moves either up or down
            if (direction == Direction.UP || direction == Direction.DOWN){
                amountMoved = moveYHandleCollision(speed * direction.getVelocity());
            }
            //moves either left or right
            else{
                amountMoved = moveXHandleCollision(speed * direction.getVelocity());
            }

            totalAmountMoved += Math.abs(amountMoved);
        }

        // else if bug has already moved 90 pixels in one direction, flip the bug's direction
        else {
            totalAmountMoved = 135 - totalAmountMoved;
            random = 45 + (int)(Math.random()*45);
            if (direction == Direction.LEFT) {
                direction = Direction.RIGHT;
            }
            else if (direction == Direction.RIGHT){
                direction = Direction.LEFT;
            }
            else if (direction == Direction.UP) {
                direction = Direction.DOWN;
            }
            else {
                direction = Direction.UP;
            }
        }

        // based off of the bugs current walking direction, set its animation to match
        if (direction == Direction.UP) {
            currentAnimationName = "WALK_RIGHT";
        }
        else {
            currentAnimationName = "WALK_LEFT";
        }
    }
    // // this code makes the bug npc walk back and forth (left to right)
    // @Override
    // public void performAction(Player player) {
    //     // if bug has not yet moved 135 pixels in one direction, move bug forward
    //     if (super.x < upperX && super.y < upperY && super.x > lowerX && super.y > lowerY && totalAmountMoved < random) {

    //         float amountMoved;
    //         //moves either up or down
    //         if (direction == Direction.UP || direction == Direction.DOWN){
    //             amountMoved = moveYHandleCollision(speed * direction.getVelocity());
    //         }
    //         //moves either left or right
    //         else{
    //             amountMoved = moveXHandleCollision(speed * direction.getVelocity());
    //         }

    //         totalAmountMoved += Math.abs(amountMoved);
    //         System.out.println(random);
    //     }

    //     // else if bug has already moved 90 pixels in one direction, flip the bug's direction
    //     else {
    //         totalAmountMoved = 135-totalAmountMoved;

    //         random = 90 + (int)(Math.random()*45);

    //         /*if (direction == Direction.DOWN) {
    //             direction = Direction.UP;
    //         }
    //         else {
    //             direction = Direction.DOWN;
    //         }*/
    //         direction = direction.random();
    //     }

    //     // based off of the bugs current walking direction, set its animation to match
    //     if (direction == Direction.UP) {
    //         currentAnimationName = "WALK_RIGHT";
    //     }
    //     else {
    //         currentAnimationName = "WALK_LEFT";
    //     }
    // }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(3, 5, 18, 7)
                    .build()
            });
            put("STAND_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(3, 5, 18, 7)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build()
           });
           put("WALK_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                        .withScale(2)
                        .withBounds(3, 5, 18, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                        .withScale(2)
                        .withBounds(3, 5, 18, 7)
                        .build()
            });
            put("WALK_RIGHT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                        .withScale(2)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .withBounds(3, 5, 18, 7)
                        .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                        .withScale(2)
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .withBounds(3, 5, 18, 7)
                        .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    public void createBounding(MapTile lower, MapTile upper){
        // lowerX = (int)lower.getLocation().x;
        // lowerY = (int)lower.getLocation().y;
        // upperX = (int)upper.getLocation().x;
        // upperY = (int)upper.getLocation().y;
    }
}
