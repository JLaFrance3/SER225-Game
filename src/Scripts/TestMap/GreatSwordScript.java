package Scripts.TestMap;

import java.util.ArrayList;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import EnhancedMapTiles.InventoryItem.EQUIP_TYPE;
import Level.*;
import Players.Avatar;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class GreatSwordScript extends Script {
    private InventoryItem weapon;
    private NPC oldMan;

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        weapon = new InventoryItem(ImageLoader.loadSubImage("items.png", 135, 100, 37, 38), 
                "Great-Sword", 10, "A Weapon that deals more damage but it may impede your speed",
                "Equipment/weapon/sword/longsword/", EQUIP_TYPE.SWORD);
        oldMan = getMap().getNPCById(40);

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("canHaveWeapon", true));

                addScriptAction(new TextboxScriptAction() {{
                    addText("This will definitly be useful.");
                    addText("I can press 'i' to open my inventory.");
                }});
                addScriptAction(new AddInventory(weapon));
                addScriptAction(new Equip(weapon));
                addScriptAction(new ToggleQuestIcon(oldMan));

                //update quest log
                addScriptAction(new SetMainQuest("hasInteractedGreatSword"));
                addScriptAction(new ChangeFlagScriptAction("hasInteractedGreatSword", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("canHaveWeapon", false));

                addScriptAction(new TextboxScriptAction() {{
                    addText("I wonder who this belongs to...");
                }});
            }});
        }});

        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
