package Scripts.TownMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class TownToH3_1Script extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ChangeFlagScriptAction("townToH3_1Door", true));

        return scriptActions;
    }   
}