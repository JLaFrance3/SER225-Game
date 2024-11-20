package ScriptActions;

import Level.Player;
import Level.ScriptState;
import Utils.Direction;

public class PlayerWalkScriptAction extends ScriptAction{
    protected Direction direction;
    protected float distance;
    protected float speed;
    protected float amountMoved;

    public PlayerWalkScriptAction( Direction direction, float distance, float speed) {
        this.direction = direction;
        this.distance = distance;
        this.speed = speed;
    }

    @Override
    public void setup() {
        amountMoved = 0;
    }

    @Override
    public ScriptState execute() {
        player.walk(direction, speed);
        amountMoved += speed;
        if (amountMoved < distance) {
            return ScriptState.RUNNING;
        }
        return ScriptState.COMPLETED;
    }
}
