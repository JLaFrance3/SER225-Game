package Scripts.TownMap;

import java.util.ArrayList;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import EnhancedMapTiles.InventoryItem.EQUIP_TYPE;
import Level.*;
import ScriptActions.*;

public class DemoLeatherScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasInteractedDemoLeatherarmor", false));

                addScriptAction(new ChangeFlagScriptAction("hasInteractedDemoLeatherarmor", true));
            }});
        }});

        InventoryItem bow = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 224, 928, 32, 32), 
            "", 1, "",
            "Equipment/weapon/bow/normal/", EQUIP_TYPE.BOW);
        InventoryItem head = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/leatherarmor/", EQUIP_TYPE.HEAD);
        InventoryItem feet = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/leatherarmor/", EQUIP_TYPE.FEET);
        InventoryItem torso = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/leatherarmor/", EQUIP_TYPE.TORSO);
        InventoryItem shoulder = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/leatherarmor/", EQUIP_TYPE.SHOULDER);
        InventoryItem hand = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/leatherarmor/", EQUIP_TYPE.HANDS);

        //Unequip and equip items
        scriptActions.add(new Unequip());
        scriptActions.add(new Equip(bow));
        scriptActions.add(new Equip(head));
        scriptActions.add(new Equip(feet));
        scriptActions.add(new Equip(torso));
        scriptActions.add(new Equip(shoulder));
        scriptActions.add(new Equip(hand));

        return scriptActions;
    }
}
