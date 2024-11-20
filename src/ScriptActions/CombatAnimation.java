package ScriptActions;

public class CombatAnimation extends ScriptAction {

    private String weaponName;

    
    public CombatAnimation(String text) {
        
    }


    @Override
    public ScriptState execute() {
        if (Player.) {
            return ScriptState.RUNNING;
        } 
        return ScriptState.COMPLETED;
    }

    @Override
    public void cleanup() {
        this.map.getTextbox().setIsActive(false);
    }
    
}