package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class ForestToTownScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ChangeFlagScriptAction("forestToTown", true));

        return scriptActions;
    }   
}

  