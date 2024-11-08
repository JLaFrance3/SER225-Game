package Scripts.TestMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

// This will be the first Old Man the player encounters in the test map where they spawn in
// He will direct the player to the market to talk to a merchant (Old Man 2)
public class FarmerGirlScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
         scriptActions.add(new LockPlayerScriptAction());
        
        
        scriptActions.add(new TextboxScriptAction() {{
            addText("Hi!!");
            addText("I Like farming Corn.");
            addText("I sure hope nobody steals my corn.");
        }});

        scriptActions.add(new ChangeFlagScriptAction("talkedToFarmerGirl", true));
         scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
