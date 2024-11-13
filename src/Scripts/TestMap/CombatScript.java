package Scripts.TestMap;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.Arrays;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ScreenManager;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Frame;
import Level.MapTile;
import Level.Script;
import Level.ScriptState;
import Level.TextboxItem;
import Level.TileType;
import Players.Avatar;
import Players.PlayerAction;
import ScriptActions.*;
import Utils.Point;
import Screens.PlayLevelScreen;

// script for talking to bug npc
// checkout the documentation website for a detailed guide on how this script works
public class CombatScript extends Script {
    private String combatAlertText;
    private int attack = 0;
    private int npcHealth = 0;
    private int lastHit = 0;
    private int xp = 0;
    private String playerAttackSCString = "";
    private String enemyAttackString = "";
    private boolean combat = true;
    private double playerArmor = 1;
    private boolean hasLeveled = false;
    private String existanceFlag;

    public CombatScript(String combatAlertText, int d, int h, String ea, int xp, String ef){
        this.combatAlertText = combatAlertText;
        npcHealth = h;
        attack = d;
        enemyAttackString = ea;
        this.xp = xp;
        existanceFlag = ef;
    }

    public void setAttack(int x){
        attack = x;
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
                        return answer == 0 && combat;
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

                        addScriptAction(new CombatScriptAction() {{
                            addText("How will you strike? ", Avatar.meleeAction.getActions());                         
                        }});

                        addScriptAction(new ScriptAction(){
                            @Override
                            public ScriptState execute() {
                                lastHit = (int) (Math.random() * attack);
                                Avatar.health = Avatar.health - lastHit;
                                System.out.println(Avatar.health);
                                return ScriptState.COMPLETED;
                            }
                        });

                        addScriptAction(new ScriptAction(){
                            private ArrayList<TextboxItem> textboxItems = new ArrayList<>(Arrays.asList(new TextboxItem(""), new TextboxItem(""),new TextboxItem("")));

                            @Override
                            public void setup() {
                                textboxItems.set(0, new TextboxItem(enemyAttackString));
                                if (Avatar.health <= 12 && npcHealth > 6){
                                    textboxItems.set(1, new TextboxItem("blood trickles out a wound as life force starts to leave you"));
                                } else if (Avatar.health <= 6 && npcHealth > 2){
                                    textboxItems.set(1, new TextboxItem("you fall to a knee as your vision start to blur"));
                                }else if (Avatar.health <= 2 && npcHealth > 0){
                                    textboxItems.set(1, new TextboxItem("you are on the verge of collapse, but push through"));
                                }else if (Avatar.health <= 0){
                                    textboxItems.set(1, new TextboxItem("you collapse and die"));
                                }else {
                                    textboxItems.set(1, new TextboxItem("you stand strong through the pain"));
                                }
                                textboxItems.set(2, new TextboxItem("you have " + Avatar.health + " Health left"));
                                TextboxItem[] textboxItemsArray = textboxItems.toArray(new TextboxItem[0]);
                                this.map.getTextbox().addText(textboxItemsArray);
                                this.map.getTextbox().setIsActive(true);
                            }

                            @Override
                            public ScriptState execute() {
                                if (!this.map.getTextbox().isTextQueueEmpty()) {
                                    return ScriptState.RUNNING;
                                }
                                return ScriptState.COMPLETED;
                            }
                        });

                        addScriptAction(new ScriptAction(){
                            @Override
                            public ScriptState execute() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                lastHit = (int) (Math.random() * Avatar.meleeAction.getAction(answer).getValue()) + Avatar.strength;
                                npcHealth = npcHealth - lastHit;
                                playerAttackSCString = Avatar.meleeAction.getAction(answer).getDescription();
                                System.out.println(npcHealth);
                                return ScriptState.COMPLETED;
                            }
                        });

                        //THE PLACE WHERE MAGIC HAPPENS
                        addScriptAction(new ScriptAction(){
                            private ArrayList<TextboxItem> textboxItems = new ArrayList<>(Arrays.asList(new TextboxItem(""), new TextboxItem("")));

                            @Override
                            public void setup() {
                                textboxItems.set(0, new TextboxItem(playerAttackSCString + " for " + lastHit));
                                if (npcHealth <= 12 && npcHealth > 6){
                                    textboxItems.set(1, new TextboxItem("your enemy appears bloody"));
                                } else if (npcHealth <= 6 && npcHealth > 2){
                                    textboxItems.set(1, new TextboxItem("your enemy appears weak"));
                                }else if (npcHealth <= 2 && npcHealth > 0){
                                    textboxItems.set(1, new TextboxItem("your enemy is on the verge of collapse"));
                                }else if (npcHealth <= 0){
                                    textboxItems.set(1, new TextboxItem("your enemy collapses and dies"));
                                }else {
                                    textboxItems.set(1, new TextboxItem("your enemy stands strong"));
                                }
                                TextboxItem[] textboxItemsArray = textboxItems.toArray(new TextboxItem[0]);
                                this.map.getTextbox().addText(textboxItemsArray);
                                this.map.getTextbox().setIsActive(true);
                            }

                            @Override
                            public ScriptState execute() {
                                if (!this.map.getTextbox().isTextQueueEmpty()) {
                                    return ScriptState.RUNNING;
                                }
                                return ScriptState.COMPLETED;
                            }
                        });

                        

                        
                        
                    }});

                    //casting menu
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{    
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                return answer == 1;
                            }
                        });

                        addScriptAction(new CombatScriptAction() {{
                            addText("How will you strike? ", Avatar.spellAction.getActions());                         
                        }});

                        addScriptAction(new ScriptAction(){
                            @Override
                            public ScriptState execute() {
                                lastHit = (int) (Math.random() * attack);
                                Avatar.health = Avatar.health - lastHit;
                                System.out.println(Avatar.health);
                                return ScriptState.COMPLETED;
                            }
                        });

                        addScriptAction(new ScriptAction(){
                            private ArrayList<TextboxItem> textboxItems = new ArrayList<>(Arrays.asList(new TextboxItem(""), new TextboxItem(""),new TextboxItem("")));

                            @Override
                            public void setup() {
                                textboxItems.set(0, new TextboxItem(enemyAttackString));
                                if (Avatar.health <= 12 && npcHealth > 6){
                                    textboxItems.set(1, new TextboxItem("blood trickles out a wound as life force starts to leave you"));
                                } else if (Avatar.health <= 6 && npcHealth > 2){
                                    textboxItems.set(1, new TextboxItem("you fall to a knee as your vision start to blur"));
                                }else if (Avatar.health <= 2 && npcHealth > 0){
                                    textboxItems.set(1, new TextboxItem("you are on the verge of collapse, but push through"));
                                }else if (Avatar.health <= 0){
                                    textboxItems.set(1, new TextboxItem("you collapse and die"));
                                }else {
                                    textboxItems.set(1, new TextboxItem("you stand strong through the pain"));
                                }
                                textboxItems.set(2, new TextboxItem("you have " + Avatar.health + " Health left"));
                                TextboxItem[] textboxItemsArray = textboxItems.toArray(new TextboxItem[0]);
                                this.map.getTextbox().addText(textboxItemsArray);
                                this.map.getTextbox().setIsActive(true);
                            }

                            @Override
                            public ScriptState execute() {
                                if (!this.map.getTextbox().isTextQueueEmpty()) {
                                    return ScriptState.RUNNING;
                                }
                                return ScriptState.COMPLETED;
                            }
                        });

                        addScriptAction(new ScriptAction(){
                            @Override
                            public ScriptState execute() {
                                int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                                if (Avatar.spellAction.getAction(answer).getValue() > 0){
                                    lastHit = (int) (Math.random() * Avatar.spellAction.getAction(answer).getValue()) + Avatar.intelligence;
                                    npcHealth = npcHealth - lastHit;
                                } else if (Avatar.spellAction.getAction(answer).getValue() == 0){
                                    System.out.println("you have " + xp);
                                    System.out.println("you have " + xp);
                                } else {
                                    lastHit = (int) (Math.random() * Avatar.spellAction.getAction(answer).getValue()) + Avatar.intelligence;
                                    Avatar.health = Avatar.health - lastHit;
                                }
                                playerAttackSCString = Avatar.spellAction.getAction(answer).getDescription();
                                System.out.println(npcHealth);
                                return ScriptState.COMPLETED;
                            }
                        });

                        //THE PLACE WHERE MAGIC HAPPENS
                        addScriptAction(new ScriptAction(){
                            private ArrayList<TextboxItem> textboxItems = new ArrayList<>(Arrays.asList(new TextboxItem(""), new TextboxItem("")));

                            @Override
                            public void setup() {
                                textboxItems.set(0, new TextboxItem("you " + playerAttackSCString + " for " + Math.abs(lastHit)));
                                if (npcHealth <= 12 && npcHealth > 6){
                                    textboxItems.set(1, new TextboxItem("your enemy appears bloody"));
                                } else if (npcHealth <= 6 && npcHealth > 2){
                                    textboxItems.set(1, new TextboxItem("your enemy appears weak"));
                                }else if (npcHealth <= 2 && npcHealth > 0){
                                    textboxItems.set(1, new TextboxItem("your enemy is on the verge of collapse"));
                                }else if (npcHealth <= 0){
                                    textboxItems.set(1, new TextboxItem("your enemy collapses and dies"));
                                }else {
                                    textboxItems.set(1, new TextboxItem("your enemy stands strong"));
                                }
                                TextboxItem[] textboxItemsArray = textboxItems.toArray(new TextboxItem[0]);
                                this.map.getTextbox().addText(textboxItemsArray);
                                this.map.getTextbox().setIsActive(true);
                            }

                            @Override
                            public ScriptState execute() {
                                if (!this.map.getTextbox().isTextQueueEmpty()) {
                                    return ScriptState.RUNNING;
                                }
                                return ScriptState.COMPLETED;
                            }
                        });

                        
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
;
                    }});
                }});

                addScriptAction(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return npcHealth <= 0 && Avatar.health > 0;
                            }
                        });
                        addScriptAction(new ScriptAction(){
                            @Override
                            public ScriptState execute() {
                                Avatar.xp += xp;
                                if (xp >= 100){
                                    hasLeveled = true;
                                    Avatar.xp -= 100;
                                    Avatar.levelUp();
                                }
                                combat = false;
                                return ScriptState.COMPLETED;
                            }
                        });
                        addScriptAction(new ChangeFlagScriptAction(existanceFlag, true));
                        addScriptAction(new TextboxScriptAction("You slay your foe!!!!"));
                    }});
                }});

                addScriptAction(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return Avatar.health <= 0;
                            }
                        });

                        addScriptAction(new ScriptAction(){
                            @Override
                            public ScriptState execute() {
                                combat = false;
                                return ScriptState.COMPLETED;
                            }
                        });

                        addScriptAction(new TextboxScriptAction("you are dead, try again?"));

                        scriptActions.add(new ChangeFlagScriptAction("hasDied", true));
                    }});
                }});

                addScriptAction(new ConditionalScriptAction() {{
                    addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                        addRequirement(new CustomRequirement() {
                            @Override
                            public boolean isRequirementMet() {
                                return Avatar.health > 0 && npcHealth > 0;
                            }
                        });
                        addScriptAction(new TextboxScriptAction(){{
                            addText(combatAlertText,new String[] { "Fight", "Flee"});
                        }});
                    }});
                }});
            }});

            addLoopScriptActionGroup(new LoopScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });

                addScriptAction(new ScriptAction(){
                    @Override
                    public ScriptState execute() {
                        combat = false;
                        return ScriptState.COMPLETED;
                    }
                });
                addScriptAction(new ChangeFlagScriptAction("TEXTBOX_OPTION_SELECTION", true));
                addScriptAction(new TextboxScriptAction("you retreat from the battle"));
            }});
            
            
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        return hasLeveled;
                    }
                });

                addScriptAction(new TextboxScriptAction() {{
                    addText("You Leveled up!!! Your stats have increased and your health is restored.");
                }});

                addScriptAction(new ScriptAction(){
                    @Override
                    public ScriptState execute() {
                        hasLeveled = false;
                        return ScriptState.COMPLETED;
                    }
                });
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

