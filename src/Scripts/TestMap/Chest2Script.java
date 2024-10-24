package Scripts.TestMap;

import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import GameObject.Frame;
import Level.*;
import ScriptActions.*;

import Utils.Direction;
import Utils.Point;
import Utils.Visibility;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class Chest2Script extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        
        

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasInteractedKey1", false));
                addScriptAction(new TextboxScriptAction("It looks locked... Maybe I should look for Key?  "));
                

            }});
        }});

        scriptActions.add(new ConditionalScriptAction(){{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasInteractedKey1", true));
                addScriptAction(new TextboxScriptAction("It's Open! I found a suspicious looking bottle of red liquid... "));
                addScriptAction(new TextboxScriptAction(" ...which I am just gonna assume is a health potion because this is an rpg."));
                addScriptAction(new TextboxScriptAction("Press 'I' to view inventory."));
                addScriptAction(new ChangeFlagScriptAction("hasInteractedChest2", true));
                scriptActions.add(new AddInventory(new InventoryItem(ImageLoader.loadSubImage("items.png", 0, 67, 35, 35))));
            }});
        }});
        
        


        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}

