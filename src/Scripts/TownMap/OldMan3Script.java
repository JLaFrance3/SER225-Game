package Scripts.TownMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

// This will be the third Old Man that the player encounters by the Town Hall
// He will direct the player to the forest at the bottom right edge of town
public class OldMan3Script extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        
        scriptActions.add(new ConditionalScriptAction() {{
            
            // if the player has not talked to Old Man 2
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToOldMan2", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("Be careful who you trust.... It is a dangerous world out there.");
                }});
            }});
            
            // if the player has talked to Old Man 2
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToOldMan2", true));

                addScriptAction(new TextboxScriptAction () {{
                    addText("So you want to know about The Uncanny? \nHeed my warning when I say to explore at your own risk and be careful.");
                    addText("This group has existed for many, many years. \nThey keep to themselves, but it is not hard to find where they reside.");
                    addText("Follow the path outside of town to the lower right outskirts. \nThis is the forest they have claimed for decades. \nHere, you may explore and find your answers.");
                }});
                
                addScriptAction(new ChangeFlagScriptAction("talkedToOldMan3", true));
            }});

        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
