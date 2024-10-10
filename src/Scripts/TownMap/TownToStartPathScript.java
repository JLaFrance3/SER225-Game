package Scripts.TownMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class TownToStartPathScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ChangeFlagScriptAction("townToStartMapPath", true));

        return scriptActions;
    }   
}

  

   

