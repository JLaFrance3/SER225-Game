package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class StartToDungeonScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        

        // Present the "Steal Corn?" choice
        scriptActions.add(new TextboxScriptAction() {{
            addText("Steal Corn?", new String[] { "Yes", "No" });
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
                
                // Set startToDungeon flag to true if the player chooses to steal corn
                addScriptAction(new TextboxScriptAction("You chose to steal the corn!"));
                addScriptAction(new ChangeFlagScriptAction("startToDungeon", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1; // No
                    }
                });

                addScriptAction(new TextboxScriptAction("You chose not to steal the corn."));
                addScriptAction(new ChangeFlagScriptAction("notStealCorn", true));
            }});
        }});

        

        return scriptActions;
    }
}