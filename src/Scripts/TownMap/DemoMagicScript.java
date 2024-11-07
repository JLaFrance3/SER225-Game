package Scripts.TownMap;

import java.util.ArrayList;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import EnhancedMapTiles.InventoryItem.EQUIP_TYPE;
import Level.*;
import ScriptActions.*;

public class DemoMagicScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasInteractedDemoMagicarmor", false));

                addScriptAction(new ChangeFlagScriptAction("hasInteractedDemoMagicarmor", true));
            }});
        }});

        InventoryItem staff = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 224, 928, 32, 32), 
            "", 1, "",
            "Equipment/weapon/magic/simple/", EQUIP_TYPE.STAFF);
        InventoryItem head = new InventoryItem(ImageLoader.loadSubImage("IndoorTileset.png", 192, 928, 32, 32), 
            "", 1, "",
            "Equipment/armor/magic/", EQUIP_TYPE.HEAD);

        //Unequip and equip items
        scriptActions.add(new Unequip());
        scriptActions.add(new Equip(staff));
        scriptActions.add(new Equip(head));
        return scriptActions;
    }
}
