package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

// script for talking to bug npc
// checkout the documentation website for a detailed guide on how this script works
public class CombatScript extends Script {
    private String combatAlertText;
    private boolean combat = true;
    public CombatScript(String combatAlertText){
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
        scriptActions.add(new TextboxScriptAction() {{
            addText(combatAlertText,new String[] { "Fight"});
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
                    addText("How will you strike?", new String[] {"Attack", "Spell", "Item"});
                }});

                addScriptAction(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{    
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                return answer == 0;
                            }
                        });

                        addScriptAction(new TextboxScriptAction() {{
                            addText("you slash your opponent dealing " + (int)(Math.random()*6) + " damage");
                        }});

                        switch ((int)(Math.random()*6)%3) {
                            case 0:
                                addScriptAction(new TextboxScriptAction() {{
                                    addText("it appears bloodied");
                                }});
                                break;
                            case 1:
                                addScriptAction(new TextboxScriptAction() {{
                                    addText("it appears healthy");
                                }});
                                break;
                            case 2:
                                addScriptAction(new TextboxScriptAction() {{
                                    addText("it is near death");
                                }});
                                break;
                            default:
                                break;
                        }
                        addScriptAction(new AttackScriptAction(){{
                            updateDamage();
                            applyDamage();
                            addText("you get bit for " + getDamage() + " damage");
                        }});
        
                        addScriptAction(new TextboxScriptAction() {{
                            addText("you have... ");
                        }});

                        addScriptAction(new TextboxScriptAction() {{
                            addText(player.health + " HP left");
                        }});
                    }});
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{    
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                return answer == 1;
                            }
                        });

                        addScriptAction(new TextboxScriptAction() {{
                            addText("you heal for " + (int)(Math.random()*6) + " HP");
                        }});
                        addScriptAction(new AttackScriptAction(){{
                            updateDamage();
                            applyDamage();
                            addText("you get bit for " + getDamage() + " damage");
                        }});
        
                        addScriptAction(new TextboxScriptAction() {{
                            addText("you have... ");
                        }});

                        addScriptAction(new TextboxScriptAction() {{
                            addText(player.health + " HP left");
                        }});
                    }});

                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{    
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                return answer == 2;
                            }
                        });
        
                        addScriptAction(new TextboxScriptAction() {{
                            addText("inventoru",new String[] { "item", "itemer"});
                        }});
;
                    }});
                }});

                if (player.health > 0){
                    addScriptAction(new TextboxScriptAction() {{
                        addText(combatAlertText,new String[] { "Fight", "Run"});
                    }});
                }
            }});

            addLoopScriptActionGroup(new LoopScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });
                
                addScriptAction(new ChangeFlagScriptAction("TEXTBOX_OPTION_SELECTION", true));
                addScriptAction(new TextboxScriptAction("you retreat from the battle"));
            }});
            
            
        }});

        
        scriptActions.add(new ChangeFlagScriptAction("hasfought", true));
        scriptActions.add(new UnlockPlayerScriptAction());
        if (this.map.getNPCs() != null){
            scriptActions.add(new NPCUnlockScriptAction());
        }

        return scriptActions;
    }
}

