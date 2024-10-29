package Scripts.TestMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class backgroundScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("In the Kingdom of Solarnelle, the ruler, King König \n has announced a terrible theft!");
            addText("The magical artifact, \"The Forseeable Eye\", was stolen!");
            addText("Mr. Walrus has just returned from the castle to relay some \n important information from King König himself.");
        }});

        scriptActions.add(new ChangeFlagScriptAction("readBackground", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
