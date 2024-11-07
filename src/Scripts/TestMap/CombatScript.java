package Scripts.TestMap;

import java.util.ArrayList;
import Level.Script;
import Players.Avatar;
import Players.PlayerAction;
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
                    //attack
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{    
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                return answer == 0;
                            }
                        });

                        addScriptAction(new TextboxScriptAction() {{
                            addText("How will you strike? ", Avatar.meleeAction.getActions());
                        }});

                        addScriptAction(new TextboxScriptAction() {{
                            int omInt = 0;
                            try {
                                omInt = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            PlayerAction temp = Avatar.meleeAction.getAction(omInt);
                            System.out.println(temp.attack());
                            if (temp.getValue() >= 0)
                                addText("you deal " + temp.attack() + "Damage");
                            else {
                                addText("you heal " + temp.attack() + "health");
                            }
                        }});
                        
                        addScriptAction(new AttackScriptAction(){{
                            updateDamage((int)(Math.random() * 4));
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
                            addText("How will you strike? ", Avatar.spellAction.getActions());
                        }});

                        addScriptAction(new TextboxScriptAction() {{
                            int omInt = 0;
                            try {
                                omInt = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                            PlayerAction temp = Avatar.meleeAction.getAction(omInt);
                            System.out.println(temp.attack());
                            if (temp.getValue() >= 0)
                                addText("you deal " + temp.attack() + "Damage");
                            else {
                                addText("you heal " + temp.attack() + "health");
                            }
                        }});


                        addScriptAction(new AttackScriptAction(){{
                            updateDamage((int)(Math.random() * 4));
                            Avatar.health -= 2;
                            System.out.println("health= " + Avatar.health);
                            applyDamage();
                            addText("you get bit for " + getDamage() + " damage");
                        }});
        
                        addScriptAction(new TextboxScriptAction() {{
                            addText("you have... ");
                        }});

                        addScriptAction(new TextboxScriptAction() {{
                            addText(Avatar.health + " HP left");
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
                            addText("inventory",new String[] { "bug repelent"});
                            
                        }});

                        addScriptAction(new ChangeFlagScriptAction("dummyAlive", true));
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

