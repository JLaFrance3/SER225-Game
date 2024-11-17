package ScriptActions;

import EnhancedMapTiles.InventoryItem;
import Level.EnhancedMapTile;
import Level.ScriptState;

public class RemoveInventory extends ScriptAction {

    protected InventoryItem item; 
    protected String itemName;
    
    public RemoveInventory(InventoryItem item){
        this.item  = item;
    }

    public RemoveInventory(String itemName){
        this.itemName = itemName;
    }

    @Override
    public ScriptState execute() {
        if (item != null) {
            player.getInventoryArrayList().remove((item));
        } else if (itemName != null) {
            for (InventoryItem invItem : player.getInventoryArrayList()) {
                if (invItem.getItemName() == itemName) {
                    item = invItem;
                    break;
                }
            }
        }

        return ScriptState.COMPLETED;
    } 
}