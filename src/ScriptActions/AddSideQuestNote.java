package ScriptActions;

import Level.ScriptState;

public class AddSideQuestNote extends ScriptAction {

    protected String note;
    
    public AddSideQuestNote(String note){
        this.note = note;
    }

    @Override
    public ScriptState execute() {
        player.setSideQuestNote(note);
        
        return ScriptState.COMPLETED;
    } 
}
