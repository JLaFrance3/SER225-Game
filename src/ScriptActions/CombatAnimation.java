package ScriptActions;

import Level.Player;
import Level.ScriptState;
import Level.TextboxItem;

public class CombatAnimation extends ScriptAction {

    private String weaponName;

    
    public CombatAnimation(String text) {
        
    }


    @Override
    public ScriptState execute() {
        player.handleSwordAttack();
        if (player.getAnimationLooped() == false) {
            return ScriptState.RUNNING;
        } 
        return ScriptState.COMPLETED;
    }

    @Override
    public void cleanup() {
        this.map.getTextbox().setIsActive(false);
    }
    
}