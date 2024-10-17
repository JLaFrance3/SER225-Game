package Scripts.TownMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class DirectionSign extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ChangeFlagScriptAction("directionSign", true));
        scriptActions.add(new TextboxScriptAction() {{
            addText("Northeast: Lumina City\nWest: WIP\nSouth: Hamlet");
        }});

        return scriptActions;
    }   
}
