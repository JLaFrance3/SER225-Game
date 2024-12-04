package Scripts.TestMap;
import java.util.ArrayList;

import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import EnhancedMapTiles.InventoryItem.EQUIP_TYPE;
import Level.NPC;
import Level.Player;
import Level.Script;
import Level.ScriptState;
import Players.Avatar;
import ScriptActions.*;
import Utils.Direction;

// This will be the first Old Man the player encounters in the test map where they spawn in
// He will direct the player to the market to talk to a merchant (Old Man 2)
public class OldMan1Script extends Script{
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
            // Old man fetch quest dialogue
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("canHaveWeapon", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("Hey kid, I seem to have misplaced my sword.");
                    addText("Can you see if you can find it for me? I'm pretty busy");
                }});

                //update quest log
                addScriptAction(new SetMainQuest("canHaveWeapon"));

                addScriptAction(new ChangeFlagScriptAction("canHaveWeapon", true));
                addScriptAction(new ToggleQuestIcon(npc));
            }});

            // Sword fetched dialogue for classes other than warrior
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("canHaveWeapon", true));
                addRequirement(new FlagRequirement("hasInteractedGreatSword", true));
                addRequirement(new FlagRequirement("returnedSword", false));
                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        if (playerClass != "Warrior") return true;
                        return false;
                    }
                });

                String weaponName;
                InventoryItem weapon;
                if(playerClass == "Wizard") {
                    weaponName = "staff";
                    addScriptAction(new Unequip(InventoryItem.EQUIP_TYPE.SWORD));
                    addScriptAction(new RemoveInventory("Great-Sword"));
                    weapon = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 640, 800, 32, 32, true), 
                    "Simple Staff", 10, "A staff that does magic",
                    "Equipment/weapon/magic/simple/", EQUIP_TYPE.STAFF);
                    addScriptAction(new AddInventory(weapon));
                    addScriptAction(new Equip(weapon));
                    addScriptAction(new ScriptAction() {
                        @Override
                        public ScriptState execute() {
                            Avatar.spellMod++;
                            return ScriptState.COMPLETED;
                        }
                    });
                }
                else {
                    weaponName = "bow";
                    addScriptAction(new Unequip(InventoryItem.EQUIP_TYPE.SWORD));
                    addScriptAction(new RemoveInventory("Great-Sword"));
                    weapon = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 128, 768, 32, 32, true), 
                    "Simple Bow", 10, "A simple stick launcher",
                    "Equipment/weapon/bow/normal/", EQUIP_TYPE.BOW);
                    addScriptAction(new AddInventory(weapon));
                    addScriptAction(new Equip(weapon));
                    addScriptAction(new ScriptAction() {
                        @Override
                        public ScriptState execute() {
                            Avatar.rangeMod++;
                            return ScriptState.COMPLETED;
                        }
                    });
                }

                addScriptAction(new TextboxScriptAction() {{
                    addText("Thank you " + playerName + ", there is a dangerous bug in the area.");
                    addText("Actually...");
                    addText("Maybe you should kill the evil bug. Take this " + weaponName);
                }});

                addScriptAction(new ToggleQuestIcon(npc));
                addScriptAction(new SetMainQuest("returnedSword"));
                addScriptAction(new ChangeFlagScriptAction("returnedSword", true));
            }});

            // Sword fetched dialogue for warrior class
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("canHaveWeapon", true));
                addRequirement(new FlagRequirement("hasInteractedGreatSword", true));
                addRequirement(new FlagRequirement("returnedSword", false));

                addScriptAction(new TextboxScriptAction() {{
                    addText("Thank you " + playerName + ", there is a dangerous bug in the area.");
                    addText("Actually...");
                    addText("Maybe you should kill the evil bug. Keep the sword");
                }});
        
                addScriptAction(new ChangeFlagScriptAction("returnedSword", true));

                addRequirement(new CustomRequirement() {
                    @Override
                    public boolean isRequirementMet() {
                        if (playerClass == "Warrior") return true;
                        return false;
                    }
                });

                addScriptAction(new ToggleQuestIcon(npc));
                addScriptAction(new SetMainQuest("returnedSword"));
                addScriptAction(new ChangeFlagScriptAction("returnedSword", true));
                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        Avatar.meleeMod++;
                        return ScriptState.COMPLETED;
                    }
                });
            }});
            
            // Bug slayer quest
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("canHaveWeapon", true));
                addRequirement(new FlagRequirement("hasInteractedGreatSword", true));
                addRequirement(new FlagRequirement("returnedSword", true));
                addRequirement(new FlagRequirement("dummyAlive", true));
                addRequirement(new FlagRequirement("talkedToOldMan1", false));

                addScriptAction(new TextboxScriptAction() {{
                    addText("You seem pretty strong " + playerName + ", you might be interested in a bounty\nthe King put out for a stolen artifact.");
                    addText("There have been some rumors around the kingdom about the group who stole it.\nTry talking to one of the merchants in the market.");;
                }});
                
                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        return ScriptState.COMPLETED;
                    }
                });

                addScriptAction(new ChangeFlagScriptAction("talkedToOldMan1", true));
                addScriptAction(new SetMainQuest("talkedToOldMan1"));
            }});
        }});
        
        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new NPCStandScriptAction(Direction.DOWN));
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
