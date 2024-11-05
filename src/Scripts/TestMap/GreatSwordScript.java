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

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction("Cool! A Great Sword!"));
        scriptActions.add(new TextboxScriptAction("This will definitly be useful."));
        scriptActions.add(new TextboxScriptAction("I can press 'i' to open my inventory."));
        

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasInteractedGreatSword", false));
                

              //  addScriptAction(new WaitScriptAction(70));

                addScriptAction(new ChangeFlagScriptAction("hasInteractedGreatSword", true));
            }});
        }});

        InventoryItem sword = new InventoryItem(ImageLoader.loadSubImage("items.png", 135, 100, 37, 38), 
            "Great-Sword", 10, "A Weapon that deals more damage but it may impede your speed",
            "Equipment/weapon/sword/longsword/", EQUIP_TYPE.SWORD);

        scriptActions.add(new AddInventory(sword));
        scriptActions.add(new Equip(sword));

        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
