package Level;

import Engine.GraphicsHandler;
import GameObject.Rectangle;
import Utils.Direction;
import Utils.Point;

import java.awt.*;

// This class represents a trigger script that can be placed on a map
// upon the player colliding with the trigger, it will play out the attached script
public class MovingTrigger extends MapEntity {
    protected Script triggerScript;

    public MovingTrigger(float x, float y, int width, int height, Script triggerScript) {
        super(x, y);
        this.triggerScript = triggerScript;
        this.setWidth(width);
        this.setHeight(height);
        this.setBounds(new Rectangle(0, 0, width, height));
    }

    public MovingTrigger(float x, float y, int width, int height, Script triggerScript, String existenceFlag) {
        super(x, y);
        this.triggerScript = triggerScript;
        this.setWidth(width);
        this.setHeight(height);
        this.setBounds(new Rectangle(0, 0, width, height));
        this.existenceFlag = existenceFlag;
    }

    public void performAction(Player player, NPC npc) {
        // else if bug has already moved 90 pixels in one direction, flip the bug's direction
        moveYHandleCollision(npc.getY());
        moveXHandleCollision(npc.getX());
    }

    public void setLocation(float x, float y){
        super.setX(x);
        super.setY(y);
    }


    protected Script loadTriggerScript() { return null; }

    public Script getTriggerScript() { return triggerScript; }
    public void setTriggerScript(Script triggerScript) {
        this.triggerScript = triggerScript;
    }

    // only used for debugging purposes if seeing the trigger is necessary
    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        drawBounds(graphicsHandler, Color.red);
    }

    // only used for debugging purposes if seeing the trigger is necessary
    public void draw(GraphicsHandler graphicsHandler, Color color) {
        Rectangle scaledCalibratedBounds = getCalibratedBounds();
        scaledCalibratedBounds.setColor(color);
        scaledCalibratedBounds.setBorderColor(Color.black);
        scaledCalibratedBounds.setBorderThickness(1);
        scaledCalibratedBounds.draw(graphicsHandler);
    }
}
