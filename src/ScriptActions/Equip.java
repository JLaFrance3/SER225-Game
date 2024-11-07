package ScriptActions;

import EnhancedMapTiles.InventoryItem;
import Level.ScriptState;

public class Equip extends ScriptAction {

    protected InventoryItem item; 

    public Equip(InventoryItem item){
        this.item  = item;
    }

    @Override
    public ScriptState execute() {
        player.equip(item);

        return ScriptState.COMPLETED;
    } 
}
