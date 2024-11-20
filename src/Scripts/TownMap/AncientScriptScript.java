package Scripts.TownMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;
import Utils.Direction;

public class AncientScriptScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
         scriptActions.add(new LockPlayerScriptAction());
        
        scriptActions.add(new ConditionalScriptAction() {{
            
            // if the player has not looked at the maps in town hall
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("seenMaps", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("This script looks old, wonder what it's for?");
                }});

                addScriptAction(new PlayerWalkScriptAction(Direction.DOWN, 10, 1));
            }});
            
            // if the player has looked at the maps in Town Hall
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("seenMaps", true));

                addScriptAction(new TextboxScriptAction () {{
                    addText("Wow, this ancient script must be significant. \nIt has the same code, \"UNC4NN7\"!");
                    addText("This must tell me how to find the Unanny, \nso maybe I can get the Forseeable Eye back!");
                    addText("There is mention of the forest on the southeast \noutskirts of town. I have to check it out.");
                }});
                
                addScriptAction(new SetMainQuest("seenAncientScript"));
                addScriptAction(new ChangeFlagScriptAction("seenAncientScript", true));
            }});

        }});

         scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
