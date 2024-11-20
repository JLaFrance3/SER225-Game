package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Level.Player;
import Scripts.GenericResponseScript;
import Utils.Direction;
import Utils.Point;

import java.util.HashMap;

// This class is for the walrus NPC
public class GenericNPC extends NPC {
    private int totalAmountMoved;
    private int maxMove;
    private Direction direction;
    private boolean horizontalPathing;
    private float speed = 0;
    private int delayTime = 0;
    private boolean delay = true;

    public GenericNPC(int id, Point location, SpriteSheet spriteSheet, Direction direction) {
        super(id, location.x, location.y, spriteSheet, "STAND_" + direction.toString());
        this.direction = direction;
        
        setInteractScript(new GenericResponseScript());
    }

    public GenericNPC(int id, Point location, SpriteSheet spriteSheet, Direction direction, int maxMove, boolean horizontalPathing) {
        super(id, location.x, location.y, spriteSheet, "STAND_" + direction.toString());
        this.direction = direction;
        this.speed = 1;
        this.totalAmountMoved = 0;
        this.maxMove = maxMove;
        this.horizontalPathing = horizontalPathing;

        setInteractScript(new GenericResponseScript());
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void toggleDelay() {
        delay = !delay;
    }

    @Override
    public void performAction(Player player) {
        if (speed > 0) {
            if (delayTime > 0) {
                currentAnimationName = "STAND_" + direction.toString();
                delayTime--;
            } else if (horizontalPathing) {
                if (totalAmountMoved < maxMove) {
                    float amountMoved = moveXHandleCollision(speed * direction.getVelocity());
                    totalAmountMoved += Math.abs(amountMoved);
                } else {
                    totalAmountMoved = 0;
                    if (direction == Direction.LEFT) {
                        direction = Direction.RIGHT;
                    }
                    else {
                        direction = Direction.LEFT;
                    }
                    if(delay) delayTime = 180;
                }
        
                if (direction == Direction.RIGHT) {
                    currentAnimationName = "WALK_RIGHT";
                }
                else {
                    currentAnimationName = "WALK_LEFT";
                }
            } else {
                if (totalAmountMoved < maxMove) {
                    float amountMoved = moveYHandleCollision(speed * direction.getVelocity());
                    totalAmountMoved += Math.abs(amountMoved);
                } else {
                    totalAmountMoved = 0;
                    if (direction == Direction.UP) {
                        direction = Direction.DOWN;
                    }
                    else {
                        direction = Direction.UP;
                    }
                    if(delay) delayTime = 180;
                }
        
                if (direction == Direction.UP) {
                    currentAnimationName = "WALK_UP";
                }
                else {
                    currentAnimationName = "WALK_DOWN";
                }
            }
        } else {
            currentAnimationName = "STAND_" + direction.toString();
        }
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
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
                    .build()
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
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
