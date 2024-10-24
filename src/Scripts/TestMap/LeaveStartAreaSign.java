package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class LeaveStartAreaSign extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ChangeFlagScriptAction("leaveStartAreaSign", true));
        scriptActions.add(new TextboxScriptAction() {{
            addText("Northeast: Lumina Town");
        }});

        return scriptActions;
    }   
}