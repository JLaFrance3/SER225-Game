package Scripts.IndoorMaps;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class SmithToTownDoorScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ChangeFlagScriptAction("smithToTownDoor", true));

        return scriptActions;
    }   
}