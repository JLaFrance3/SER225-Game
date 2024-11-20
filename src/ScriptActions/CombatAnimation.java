package ScriptActions;

import Level.Player;
import Level.ScriptState;
import Level.TextboxItem;

public class CombatAnimation extends ScriptAction {

    private String weaponName;

    
    public CombatAnimation(String text) {
        weaponName = text;
    }


    @Override
    public ScriptState execute() {
        switch (weaponName) {
            case "SWORD":
                player.handleSwordAttack();
                break;
            case "BOW":
                player.handleArrowAttack();
                break;
            case "MAGIC":
                player.handleMagicAttack();
                break;
            case "DEATH":
                player.handleDeathAttack();
                break;
        
            default:
                break;
        }
        if (player.getAnimationLooped() == false) {
            return ScriptState.RUNNING;
        } else {
            player.stand(player.getFacingDirection());
        }
        return ScriptState.COMPLETED;
    }

    @Override
    public void cleanup() {
        this.map.getTextbox().setIsActive(false);
    }
    
}