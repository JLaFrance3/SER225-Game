package Scripts.TestMap;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import GameObject.Frame;
import Level.MapTile;
import Level.Script;
import Level.ScriptState;
import Level.TileType;
import ScriptActions.*;
import Utils.Point;

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
        // if (this.map.getNPCs() != null){
            
        //     scriptActions.add(new NPCLockScriptAction());

        //     scriptActions.add(new NPCFacePlayerScriptAction());
        // }
        scriptActions.add(new TextboxScriptAction() {{
            addText(combatAlertText,new String[] { "Fight", "Move", "Run"});
        }});

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
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
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 1;
                    }
                });
                
                addScriptAction(new TextboxScriptAction("um... I promise this will be implemented soon"));
            }});
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        int answer = outputManager.getFlagData("TEXTBOX_OPTION_SELECTION");
                        return answer == 2;
                    }
                });
                
                addScriptAction(new TextboxScriptAction("hey where are you going?"));
            }});  
        }});

        
        scriptActions.add(new ChangeFlagScriptAction("hasfought", true));
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}

