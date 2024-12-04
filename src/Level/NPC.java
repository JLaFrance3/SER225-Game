package Level;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import java.awt.image.BufferedImage;
import Utils.Direction;

import java.util.HashMap;

// This class is a base class for all npcs in the game -- all npcs should extend from it
public class NPC extends MapEntity {
    protected boolean drawQuestIndicator;
    protected static BufferedImage questIndicator = ImageLoader.load("quest_icon.png", true);
    protected int id = 0;
    protected boolean isLocked = false;

    public NPC(int id, float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
        this.id = id;
        this.drawQuestIndicator = false;
    }

    public NPC(int id, float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
        this.id = id;
        this.drawQuestIndicator = false;
    }

    public NPC(int id, float x, float y, Frame[] frames) {
        super(x, y, frames);
        this.id = id;
        this.drawQuestIndicator = false;
    }

    public NPC(int id, float x, float y, Frame frame) {
        super(x, y, frame);
        this.id = id;
        this.drawQuestIndicator = false;
    }

    public NPC(int id, float x, float y) {
        super(x, y);
        this.id = id;
        this.drawQuestIndicator = false;
    }

    public int getId() { return id; }

    public void facePlayer(Player player) {
        // if npc's center point is to the right of the player's center point, npc needs to face left
        // else if npc's center point is to the left of the player's center point, npc needs to face right
        float centerPointX = getBounds().getX() + (getBounds().getWidth() / 2);
        float playerCenterPointX = player.getBounds().getX() + (player.getBounds().getWidth() / 2);
        float centerPointY = getBounds().getY() + (getBounds().getHeight() / 2);
        float playerCenterPointY = player.getBounds().getY() + (player.getBounds().getHeight() / 2);
        float xDiff = centerPointX - playerCenterPointX;
        float yDiff = centerPointY - playerCenterPointY;
        
        if (Math.abs(xDiff) > Math.abs(yDiff)) {
            if (centerPointX < playerCenterPointX) {
                this.currentAnimationName = "STAND_RIGHT";
            }
            else if (centerPointX >= playerCenterPointX) {
                this.currentAnimationName = "STAND_LEFT";
            }
        }
        else {
            if (centerPointY < playerCenterPointY) {
                this.currentAnimationName = "STAND_UP";
            }
            else if (centerPointY >= playerCenterPointY) {
                this.currentAnimationName = "STAND_DOWN";
            }
        }
    }

    public void setQuestIndicator(boolean drawQuestIndicator) {
        this.drawQuestIndicator = drawQuestIndicator;
    }

    public void toggleQuestIndicator() {
        setQuestIndicator(!drawQuestIndicator);
    }

    public void stand(Direction direction) {
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "STAND_RIGHT";
        }
        else if (direction == Direction.LEFT) {
            this.currentAnimationName = "STAND_LEFT";
        }
        else if (direction == Direction.DOWN) {
            this.currentAnimationName = "STAND_DOWN";
        }
        else if (direction == Direction.UP) {
            this.currentAnimationName = "STAND_UP";
        }
    }

    public void walk(Direction direction, float speed) {
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "WALK_RIGHT";
        }
        else if (direction == Direction.LEFT) {
            this.currentAnimationName = "WALK_LEFT";
        }
        else if (direction == Direction.UP) {
            this.currentAnimationName = "WALK_UP";
        }
        else if (direction == Direction.DOWN) {
            this.currentAnimationName = "WALK_DOWN";
        }
        else {
            if (this.currentAnimationName.contains("RIGHT")) {
                this.currentAnimationName = "WALK_RIGHT";
            }
            else if (this.currentAnimationName.contains("LEFT")) {
                this.currentAnimationName = "WALK_LEFT";
            }
            else if (this.currentAnimationName.contains("UP")) {
                this.currentAnimationName = "WALK_UP";
            }
            else {
                this.currentAnimationName = "WALK_DOWN";
            }
        }
        if (direction == Direction.UP) {
            moveY(-speed);
        }
        else if (direction == Direction.DOWN) {
            moveY(speed);
        }
        else if (direction == Direction.LEFT) {
            moveX(-speed);
        }
        else if (direction == Direction.RIGHT) {
            moveX(speed);
        }
    }

    public void update(Player player) {
        if (!isLocked) {
            this.performAction(player);
        }
        super.update();
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock() {
        isLocked = false;
    }

    protected void performAction(Player player) {}

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        if(drawQuestIndicator) {
            graphicsHandler.drawImage(
				questIndicator, Math.round(getCalibratedXLocation()) + 12, Math.round(getCalibratedYLocation()) - 35,
				40, 40, ImageEffect.NONE);
        }
    }
}
