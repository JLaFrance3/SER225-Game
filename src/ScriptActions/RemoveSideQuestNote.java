package ScriptActions;

import Level.ScriptState;

public class RemoveSideQuestNote extends ScriptAction {

    protected String note;
    
    public RemoveSideQuestNote(String note){
        this.note = note;
    }

    @Override
    public ScriptState execute() {
        player.removeSideQuestNote(note);
        
        return ScriptState.COMPLETED;
    } 
}