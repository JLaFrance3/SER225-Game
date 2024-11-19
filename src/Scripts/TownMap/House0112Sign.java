package Scripts.TownMap;

import java.util.ArrayList;

import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import Level.Script;
import ScriptActions.*;

public class House0112Sign extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ChangeFlagScriptAction("house0112Sign", true));
        scriptActions.add(new TextboxScriptAction() {{
            addText("House Number 0112");
        }});

        return scriptActions;
    }
}
