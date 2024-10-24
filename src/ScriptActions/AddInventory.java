package ScriptActions;

import EnhancedMapTiles.InventoryItem;
import Level.EnhancedMapTile;
import Level.ScriptState;



public class AddInventory extends ScriptAction {

    protected InventoryItem item; 
    

    public AddInventory(InventoryItem item){
        this.item  = item;
    }

    @Override
    public ScriptState execute() {
        player.getInventoryArrayList().add((item));

        return ScriptState.COMPLETED;
    } 
}
