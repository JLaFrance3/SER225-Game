package Scripts.TownMap;
import java.util.ArrayList;

import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import EnhancedMapTiles.InventoryItem.EQUIP_TYPE;
import Level.NPC;
import Level.Script;
import ScriptActions.*;
import Utils.Direction;

// This will be the second Old Man (the merchant) that the player encounters in the market
// He will direct the player to the Town Hall to talk to the last Old Man (Old Man 3)
public class OldMan2Script extends Script{
    private String playerClass;
    private NPC npc;

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        playerClass = player.getPlayerClass();
        npc = (NPC)getEntity();

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new NPCLockScriptAction());
        scriptActions.add(new NPCFacePlayerScriptAction());
        scriptActions.add(new ConditionalScriptAction() {{
            // if the player has not talked to Old Man 1
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToOldMan1", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("Good day. I am selling many goods if you are interested.");
                }});
            }});

            // old man send player to investigate suspicious guy
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToOldMan1", true));
                addRequirement(new FlagRequirement("investigateSusCharacter", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("Hello. I've heard whisperings of a group called \"The Uncanny\" who\nmay be responsible for the stolen artifact.");
                    addText("There is a merchant at the other end of the market who I suspect deals with\nstolen goods. You should see what he knows.");
                }});

                addScriptAction(new ChangeFlagScriptAction("investigateSusCharacter", true));
                addScriptAction(new SetMainQuest("investigateSusCharacter"));
                addScriptAction(new ToggleQuestIcon(npc));
            }});

            // dialogue after fighting suspicious guy
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToOldMan1", true));
                addRequirement(new FlagRequirement("foughtSusCharacter", true));
                addRequirement(new FlagRequirement("talkedToOldMan2", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("I see the investigation didn't go well. You could ask the old guard behind Town Hall\nif he has seen anything suspicious.");
                    addText("Take this armor, it seems like you will need it with your luck.");
                }});

                InventoryItem armor;
                if(playerClass == "Wizard") {
                    armor = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 384, 768, 32, 32, true), 
                    "Wizard Cape", 4, "A cape that looks magical",
                    "Equipment/armor/magic/", EQUIP_TYPE.TORSO);
                    addScriptAction(new AddInventory(armor));
                    addScriptAction(new Equip(armor));
                }
                else if (playerClass == "Ranger") {
                    armor = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 800, 32, 32, true), 
                    "Leather Chest", 6, "Leather armor that offers some protection",
                    "Equipment/armor/leatherarmor/", EQUIP_TYPE.TORSO);
                    addScriptAction(new AddInventory(armor));
                    addScriptAction(new Equip(armor));

                    addScriptAction(new Equip(new InventoryItem(ImageLoader.loadSubImage("items.png", 0, 0, 32, 32, true), 
                    "", 0, "", "Equipment/armor/leatherarmor/", EQUIP_TYPE.SHOULDER)));
                    addScriptAction(new Equip(new InventoryItem(ImageLoader.loadSubImage("items.png", 0, 0, 32, 32, true), 
                    "", 0, "", "Equipment/armor/leatherarmor/", EQUIP_TYPE.HANDS)));
                } else {
                    armor = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 0, 832, 32, 32, true), 
                    "Plate Chest", 10, "Plate armor that offers a lot of protection",
                    "Equipment/armor/platearmor/", EQUIP_TYPE.TORSO);
                    addScriptAction(new AddInventory(armor));
                    addScriptAction(new Equip(armor));

                    addScriptAction(new Equip(new InventoryItem(ImageLoader.loadSubImage("items.png", 0, 0, 32, 32, true), 
                    "", 0, "", "Equipment/armor/platearmor/", EQUIP_TYPE.SHOULDER)));
                    addScriptAction(new Equip(new InventoryItem(ImageLoader.loadSubImage("items.png", 0, 0, 32, 32, true), 
                    "", 0, "", "Equipment/armor/platearmor/", EQUIP_TYPE.HANDS)));
                    addScriptAction(new Equip(new InventoryItem(ImageLoader.loadSubImage("items.png", 0, 0, 32, 32, true), 
                    "", 0, "", "Equipment/armor/platearmor/", EQUIP_TYPE.ARMS)));
                }
                
                addScriptAction(new ChangeFlagScriptAction("talkedToOldMan2", true));
                addScriptAction(new SetMainQuest("talkedToOldMan2"));
                addScriptAction(new ToggleQuestIcon(npc));
            }});
        }});

        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new NPCStandScriptAction(Direction.DOWN));
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
