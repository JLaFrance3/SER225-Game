package Scripts.TownMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class EnterForestScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        

        scriptActions.add(new ConditionalScriptAction() {{
            
            // if the player has not looked at the ancient script in the house yet
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("seenAncientScript", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("You cannot pass here yet.");
                }});
            }});

            // if the player has looked at the ancient script in the house
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("seenAncientScript", true));

                addScriptAction(new TextboxScriptAction () {{
                    addText("There is a barrier around the forest, preventing entry.");
                    addText("The Uncanny placed it to protect themselves from the Goblins!");
                    addText("In order to enter, you must kill all four goblins.");
                }});
                
            }});

            // if the player has killed the goblins and looked at the ancient script in the house
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("goblin1Flag", true));
                addRequirement(new FlagRequirement("goblin2Flag", true));
                addRequirement(new FlagRequirement("goblin3Flag", true));
                addRequirement(new FlagRequirement("goblin4Flag", true));
                addRequirement(new FlagRequirement("seenAncientScript", true));

                addScriptAction(new TextboxScriptAction () {{
                    addText("Entry granted.");
                }});
                
                addScriptAction(new SetMainQuest("foughtEnemiesToEnterForest"));
                addScriptAction(new ChangeFlagScriptAction("foughtEnemiesToEnterForest", true));
            }});

        }});

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
