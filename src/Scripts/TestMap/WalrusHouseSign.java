package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class WalrusHouseSign extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ChangeFlagScriptAction("walrusHouseSign", true));
        scriptActions.add(new TextboxScriptAction() {{
            addText("Mr. Walrus' House");
        }});

        return scriptActions;
    }   
}