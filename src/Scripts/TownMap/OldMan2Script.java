package Scripts.TownMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

// This will be the second Old Man (the merchant) that the player encounters in the market
// He will direct the player to the Town Hall to talk to the last Old Man (Old Man 3)
public class OldMan2Script extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
         scriptActions.add(new LockPlayerScriptAction());
        
        scriptActions.add(new ConditionalScriptAction() {{
            
            // if the player has not talked to Old Man 1
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToOldMan1", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("Good day. I am selling many goods if you are interested.");
                }});
            }});
            
            // if the player has talked to Old Man 1
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToOldMan1", true));

                addScriptAction(new TextboxScriptAction () {{
                    addText("Hello. \nI've heard whisperings of this group called \"The Uncanny\", and rumors that they \nare the ones who stole The Forseeable Eye.");
                    addText("They live somewhere deep in the woods. Supposedly, \nthere is a man who lives behind the Town Hall who knows how to get there.");
                }});
                
                addScriptAction(new SetMainQuest("talkedToOldMan2"));
                addScriptAction(new ChangeFlagScriptAction("talkedToOldMan2", true));
            }});

        }});

         scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
