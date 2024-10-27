package Scripts.TestMap;

import java.util.ArrayList;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import Level.*;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class Key2Script extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction("A Key?... Cool! I'll take it. I can press 'I' to open my inventory."));
        

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasInteractedKey1", false));
                

              //  addScriptAction(new WaitScriptAction(70));

                addScriptAction(new ChangeFlagScriptAction("hasInteractedKey1", true));
            }});
        }});

        scriptActions.add(new AddInventory(new InventoryItem(ImageLoader.loadSubImage("items.png", 241, 338, 26, 35), "Chest Key", 0, "A key that opens a certain chest")));

        


        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

