package Scripts.TestMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

// This will be the first Old Man the player encounters in the test map where they spawn in
// He will direct the player to the market to talk to a merchant (Old Man 2)
public class OldMan1Script extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
         scriptActions.add(new LockPlayerScriptAction());
        
        
        scriptActions.add(new TextboxScriptAction() {{
            addText("The King has requested YOU for the great task of retrieving The Forseeable Eye.");
            addText("There have been some rumors around the kingdom about the group who stole it. \nOne of the merchants in the market has observed some suspicious conversations.");
            addText("Make your way to the market to learn more about them.");
        }});

        scriptActions.add(new ChangeFlagScriptAction("talkedToOldMan1", true));
         scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
