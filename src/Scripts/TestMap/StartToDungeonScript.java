package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class StartToDungeonScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("A large Gate?");
            addText("Wonder where it leads?");
            
        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        scriptActions.add(new ChangeFlagScriptAction("startToDungeon", true));

        

        return scriptActions;
    }   
}

  