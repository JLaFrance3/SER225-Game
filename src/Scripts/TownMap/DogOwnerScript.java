package Scripts.TownMap;

import java.util.ArrayList;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import EnhancedMapTiles.InventoryItem.EQUIP_TYPE;
import Level.NPC;
import Level.Player;
import Level.Script;
import NPCs.Dog;
import ScriptActions.*;
import Utils.Direction;
import Utils.Visibility;

// Script used for fisher guy side quest
public class DogOwnerScript extends Script{
    private String playerClass;
    private String playerGender;
    private NPC npc;

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        playerClass = player.getPlayerClass();
        playerGender = player.getPlayerGender();
        npc = (NPC)getEntity();

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new NPCLockScriptAction());
        scriptActions.add(new NPCFacePlayerScriptAction());
        
        scriptActions.add(new ConditionalScriptAction() {{
            // Dog owner initial quest dialogue
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("sickDogInitiate", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("I think my dog is sick, can you please help him?");
                    addText("I bet a health potion would do it.");
                }});

                addScriptAction(new ChangeFlagScriptAction("sickDogInitiate", true));
                addScriptAction(new ToggleQuestIcon(npc));
                addScriptAction(new AddSideQuestNote("Find a health potion for the cute dog"));
            }});

            // Finish quest
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("healDog", true));
                addRequirement(new FlagRequirement("sickDogComplete", false));

                addScriptAction(new TextboxScriptAction() {{
                    addText("Thank you! He loves health potions!");
                }});

                addScriptAction(new ToggleQuestIcon(npc));

                addScriptAction(new NPCWalkScriptAction(Direction.UP, 160, 3));
                addScriptAction(new NPCWalkScriptAction(Direction.RIGHT, 116, 3));
                addScriptAction(new NPCChangeVisibilityScriptAction(Visibility.HIDDEN));
                addScriptAction(new WaitScriptAction(70));
                addScriptAction(new NPCFacePlayerScriptAction());
                addScriptAction(new NPCChangeVisibilityScriptAction(Visibility.VISIBLE));
                addScriptAction(new NPCWalkScriptAction(Direction.LEFT, 116, 3));
                addScriptAction(new NPCWalkScriptAction(Direction.DOWN, 160, 3));
                addScriptAction(new NPCFacePlayerScriptAction());

                String reward;
                InventoryItem armor;
                if(playerClass == "Wizard") {
                    reward = "pair of boots";
                    armor = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 416, 832, 32, 32, true), 
                    "Wizard Boots", 4, "These look a lot like regular boots",
                    "Equipment/armor/magic/", EQUIP_TYPE.FEET);
                }
                else if (playerClass == "Ranger") {
                    reward = "pair of boots";
                    armor = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 416, 832, 32, 32, true), 
                    "Leather Boots", 4, "Some nice leather boots",
                    "Equipment/armor/leatherarmor/", EQUIP_TYPE.FEET);
                } else {
                    reward = "pair of armor pants";
                    armor = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 288, 928, 32, 32, true), 
                    "Plate Legs", 6, "Big metal pants",
                    "Equipment/armor/platearmor/", EQUIP_TYPE.LEGS);

                    addScriptAction(new Equip(new InventoryItem(ImageLoader.loadSubImage("items.png", 0, 0, 32, 32), 
                    "", 0, "", "Equipment/armor/platearmor/", EQUIP_TYPE.FEET)));
                }
                addScriptAction(new AddInventory(armor));
                addScriptAction(new Equip(armor));

                String dialogueBranch;
                if (playerGender == "male") {
                    dialogueBranch = "Here, take this " + reward + ". They are\nmy husband's but he won't notice they're gone.";
                } else {
                    dialogueBranch = "Here, take this " + reward + ". They are\nmy sister's but she won't notice they're gone.";
                }
                addScriptAction(new TextboxScriptAction() {{
                    addText(dialogueBranch);
                }});

                addScriptAction(new RemoveSideQuestNote("Talk to the dog's owner"));
                addScriptAction(new ChangeFlagScriptAction("sickDogComplete", true));
            }});
            
            // Response after quest complete
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("sickDogComplete", true));

                addScriptAction(new TextboxScriptAction() {{
                    addText("My dog loves health potions!");
                }});
            }});

        }});
        
        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new NPCStandScriptAction(Direction.LEFT));
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
