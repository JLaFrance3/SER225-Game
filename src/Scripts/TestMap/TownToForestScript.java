package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class TownToForestScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

       // scriptActions.add(new LockPlayerScriptAction());

        // Present the "Steal Corn?" choice
        scriptActions.add(new TextboxScriptAction() {{
            addText("Enter Final Boss Area", new String[] { "Yes", "No" });
        }});

        // Check the player's response
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 0; // Yes
                    }
                });
                
                // Set townToForest flag to true if the player chooses yes
                addScriptAction(new ChangeFlagScriptAction("townToForest", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1; // No
                    }
                });

                addScriptAction(new TextboxScriptAction("You can come back later."));
                addScriptAction(new ChangeFlagScriptAction("gateInteract", true));
            }});
        }});

        
       // scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    } 

}