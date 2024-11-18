package ScriptActions;

import Level.NPC;
import Level.ScriptState;

public class ToggleQuestIcon extends ScriptAction {

    protected NPC npc;
    
    public ToggleQuestIcon(NPC npc){
        this.npc = npc;
    }

    @Override
    public ScriptState execute() {
        npc.toggleQuestIndicator();

        return ScriptState.COMPLETED;
    } 
}