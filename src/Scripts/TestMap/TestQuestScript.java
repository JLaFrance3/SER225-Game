package Scripts.TestMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class TestQuestScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("This is a test quest.");
        }});

        scriptActions.add(new ChangeFlagScriptAction("readTestQuest", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
