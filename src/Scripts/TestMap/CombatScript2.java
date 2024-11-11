package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import Players.Avatar;
import Players.PlayerAction;
import ScriptActions.*;

public class CombatScript2 extends Script {
    private String combatAlertText;
    private boolean combat = true;
    public CombatScript2(String combatAlertText){
        this.combatAlertText = combatAlertText;
    }

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        ArrayList<ScriptAction> combatActions = new ArrayList<>();

        scriptActions.add(new LockPlayerScriptAction());
        if (this.map.getNPCs() != null){
            
            scriptActions.add(new NPCLockScriptAction());

            scriptActions.add(new NPCFacePlayerScriptAction());
        }

        scriptActions.add(new ChangeFlagScriptAction("dummyAlive", true));

        scriptActions.add(new LoopScriptAction(){{
            addLoopScriptActionGroup(new LoopScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("dummyAlive");
                        return answer == 0;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("Oh no, this one is looking for a fight!", new String[] {"Fight", "Run"});
                }});

                addScriptAction(new ConditionalScriptAction() {{
                    //attack
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{    
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                return answer == 0;
                            }
                        });
                    }});

                    addScriptAction(new TextboxScriptAction() {{
                        addText("How will you strike? ", Avatar.meleeAction.getActions());
                    }});
                }});

                addScriptAction(new ConditionalScriptAction() {{
                    //run
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{    
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                return answer == 1;
                            }
                        });
                    }});

                    addScriptAction(new TextboxScriptAction() {{
                        addText("you get away safely");
                    }});

                    addScriptAction(new ChangeFlagScriptAction("dummyAlive", true));
                }});
            }});
        }});
        
        return scriptActions;
    }
}
