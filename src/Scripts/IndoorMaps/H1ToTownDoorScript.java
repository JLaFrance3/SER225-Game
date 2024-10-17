package Scripts.IndoorMaps;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class H1ToTownDoorScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ChangeFlagScriptAction("H1ToTownDoor", true));

        return scriptActions;
    }   
}