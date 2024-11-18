package ScriptActions;

import Level.ScriptState;

public class SetMainQuest extends ScriptAction {
    
    String flag;

    public SetMainQuest(String flag){
        this.flag = flag;
    }

    @Override
    public ScriptState execute() {
        player.setMainQuest(flag);;

        return ScriptState.COMPLETED;
    } 
}