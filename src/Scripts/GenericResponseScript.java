package Scripts;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

// Simple random responses for generic NPCs
public class GenericResponseScript extends Script{
    private int random;

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        random = (int)(Math.random() * (5));     //Generate random int from 0 to 4

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new NPCLockScriptAction());
        scriptActions.add(new NPCFacePlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        if (random == 0) return true;
                        return false;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{addText("Can I help you?");}});
            }});
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        if (random == 1) return true;
                        return false;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{addText("Oh hello there.");}});
            }});
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        if (random == 2) return true;
                        return false;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{addText("Beautiful weather today!");}});
            }});
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        if (random == 3) return true;
                        return false;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{addText("Have you heard what happened to King König?");}});
            }});
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        if (random == 4) return true;
                        return false;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{addText("I'm super busy, sorry.");}});
            }});
        }});

        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
