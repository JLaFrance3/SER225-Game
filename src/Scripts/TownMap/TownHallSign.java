package Scripts.TownMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class TownHallSign extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ChangeFlagScriptAction("townHallSign", true));
        scriptActions.add(new TextboxScriptAction() {{
            addText("Welcome to Town Hall!");
        }});

        return scriptActions;
    }   
}
