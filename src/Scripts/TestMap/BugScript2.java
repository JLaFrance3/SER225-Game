package Scripts.TestMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;

// script for talking to bug npc
// checkout the documentation website for a detailed guide on how this script works
public class BugScript2 extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new NPCLockScriptAction());

        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("Hello!");
            addText("Do you like bugs?", new String[] { "Yes", "No"});
        }});

        scriptActions.add(new LoopScriptAction() {{
            addLoopScriptActionGroup(new LoopScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 0;
                    }
                });
                
                addScriptAction(new TextboxScriptAction() {{
                    addText("I knew you were a cool cat!");
                    addText("I'm going to let you in on a little secret...\nYou can push some rocks out of the way.");
                }});

                addScriptAction(new TextboxScriptAction() {{
                    addText("yap alert time hehehehehehehehehehehehehehehehe");
                }});

                addScriptAction(new TextboxScriptAction() {{
                    addText("Hello!");
                    addText("Do you like bugs?", new String[] { "Yes", "No"});
                }});

                addScriptAction(new ChangeFlagScriptAction("TEXTBOX_OPTION_SELECTION", true));
            }});

            addLoopScriptActionGroup(new LoopScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });
                
                // addScriptAction(new ChangeFlagScriptAction("TEXTBOX_OPTION_SELECTION", false));

                addScriptAction(new TextboxScriptAction("Oh...uh...awkward..."));
            }});

            // addLoopScriptActionGroup(new LoopScriptActionGroup() {{
            //     addRequirement(new CustomRequirement() {
            //         @Override
            //         public boolean isRequirementMet() {
            //             int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
            //             return answer == 2;
            //         }
            //     });
                
            //     addScriptAction(new ChangeFlagScriptAction("TEXTBOX_OPTION_SELECTION", false));
            //     addScriptAction(new TextboxScriptAction("OOOOOOOOOOOOoooooooooOOoOoooooOOO"));
            // }});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });
                
                addScriptAction(new TextboxScriptAction("Oh...uh...awkward..."));
            }});
        }});

        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
