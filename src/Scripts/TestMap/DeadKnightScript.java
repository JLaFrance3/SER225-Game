package Scripts.TestMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class DeadKnightScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("The EYE!!!!");
            
        }});
        scriptActions.add(new TextboxScriptAction() {{
            addText("WE Never SHould Have used it's power...");
            
        }});
        scriptActions.add(new TextboxScriptAction() {{
            addText("The King is CRAZY!!");
            
        }});
        scriptActions.add(new TextboxScriptAction() {{
            addText("You Must destroy It!!!");
            
        }});
        

        scriptActions.add(new ChangeFlagScriptAction("knightInteract", true));
        scriptActions.add(new SetMainQuest("knightInteract"));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
