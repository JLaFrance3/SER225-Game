package Scripts.TownMap;

import java.util.ArrayList;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import EnhancedMapTiles.InventoryItem.EQUIP_TYPE;
import Level.*;
import ScriptActions.*;

public class DemoPlateScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasInteractedDemoPlatearmor", false));

                addScriptAction(new ChangeFlagScriptAction("hasInteractedDemoPlatearmor", true));
            }});
        }});

        InventoryItem axe = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/weapon/blunt/waraxe/", EQUIP_TYPE.SWORD);
        InventoryItem head = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/platearmor/", EQUIP_TYPE.HEAD);
        InventoryItem arm = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/platearmor/", EQUIP_TYPE.ARMS);
        InventoryItem feet = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/platearmor/", EQUIP_TYPE.FEET);
        InventoryItem legs = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/platearmor/", EQUIP_TYPE.LEGS);
        InventoryItem torso = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/platearmor/", EQUIP_TYPE.TORSO);
        InventoryItem shoulder = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/platearmor/", EQUIP_TYPE.SHOULDER);
        InventoryItem hand = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/platearmor/", EQUIP_TYPE.HANDS);
        InventoryItem shield = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/shield/kite/", EQUIP_TYPE.SHIELD);

        //Unequip and equip items
        scriptActions.add(new Unequip());
        scriptActions.add(new Equip(axe));
        scriptActions.add(new Equip(head));
        scriptActions.add(new Equip(arm));
        scriptActions.add(new Equip(feet));
        scriptActions.add(new Equip(legs));
        scriptActions.add(new Equip(torso));
        scriptActions.add(new Equip(shoulder));
        scriptActions.add(new Equip(hand));
        scriptActions.add(new Equip(shield));

        return scriptActions;
    }
}
