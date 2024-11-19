package Scripts.TownMap;

import java.util.ArrayList;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import EnhancedMapTiles.InventoryItem.EQUIP_TYPE;
import Level.NPC;
import Level.Player;
import Level.Script;
import ScriptActions.*;
import Utils.Direction;

// Script used for fisher guy side quest
public class FisherGuyScript extends Script{
    private String playerClass;
    private String playerName;
    private NPC npc;

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        playerClass = player.getPlayerClass();
        playerName = player.getPlayerName();
        npc = (NPC)getEntity();

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new NPCLockScriptAction());
        scriptActions.add(new NPCFacePlayerScriptAction());
        
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToOldMan1", false));
                
                addScriptAction(new TextboxScriptAction () {{
                    addText("Wait!");
                    addText("The path is pretty dangerous right now. Maybe you\nshould come back later.");
                }});
            }});

            // Fisher guy initial quest dialogue
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToOldMan1", true));
                addRequirement(new FlagRequirement("fisherguyInitiate", false));
                addRequirement(new FlagRequirement("clearedEnemies", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("I was out fishing but now there are all these enemies!");
                    addText("Can you clear them out for me?");
                }});

                addScriptAction(new ChangeFlagScriptAction("fisherguyInitiate", true));
                addScriptAction(new ToggleQuestIcon(npc));
                addScriptAction(new AddSideQuestNote("Clear out some enemies on the path to Town"));
            }});

            // Finish quest
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("clearedEnemies", true));
                addRequirement(new FlagRequirement("fisherguyComplete", false));

                InventoryItem armor;
                if(playerClass == "Wizard") {
                    armor = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 288, 768, 32, 32, true), 
                    "Wizard Hat", 3, "A hat that looks magical",
                    "Equipment/armor/magic/", EQUIP_TYPE.HEAD);
                }
                else if (playerClass == "Ranger") {
                    armor = new InventoryItem(ImageLoader.loadSubImage("items.png", 32, 374, 37, 37), 
                    "Kettle Helm", 6, "A disk hat to protect your noggin",
                    "Equipment/armor/leatherarmor/", EQUIP_TYPE.HEAD);
                } else {
                    armor = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 32, 832, 32, 32, true), 
                    "Plate Helm", 10, "A full plate helm",
                    "Equipment/armor/platearmor/", EQUIP_TYPE.HEAD);
                }
                addScriptAction(new AddInventory(armor));
                addScriptAction(new Equip(armor));

                addScriptAction(new TextboxScriptAction() {{
                    addText("Great job! I want to give you this hat I found in the lake.");
                    addText("You might look silly but it could save your life!");
                }});

                addScriptAction(new RemoveSideQuestNote("Clear out some enemies on the path to Town"));
                addScriptAction(new ToggleQuestIcon(npc));
                addScriptAction(new ChangeFlagScriptAction("fisherguyComplete", true));

                addScriptAction(new NPCWalkScriptAction(Direction.UP, 448, 3));
                addScriptAction(new NPCWalkScriptAction(Direction.RIGHT, 288, 10));
                addScriptAction(new NPCWalkScriptAction(Direction.UP, 192, 10));
            }});
            
            // Response after quest complete
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("fisherguyComplete", true));

                addScriptAction(new TextboxScriptAction() {{
                    addText("Thank you " + playerName + "! Those enemies were a nuisance.");
                }});
            }});

        }});
        
        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new NPCStandScriptAction(Direction.DOWN));
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
