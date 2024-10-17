package Scripts.TownMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class StartAreaSign extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ChangeFlagScriptAction("startAreaSign", true));
        scriptActions.add(new TextboxScriptAction() {{
            addText("South: Hamlet");
        }});

        return scriptActions;
    }
}
