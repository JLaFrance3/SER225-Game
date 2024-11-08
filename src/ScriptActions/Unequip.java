package ScriptActions;

import EnhancedMapTiles.InventoryItem;
import Level.ScriptState;

public class Unequip extends ScriptAction {

    protected InventoryItem item; 
    protected InventoryItem.EQUIP_TYPE equipType;

    public Unequip() {
        this.equipType = InventoryItem.EQUIP_TYPE.NOT_EQUIPPABLE;
    }

    public Unequip(InventoryItem item){
        this.item  = item;
        this.equipType = item.getType();
    }

    public Unequip(InventoryItem.EQUIP_TYPE equipType) {
        this.equipType = equipType;
    }

    @Override
    public ScriptState execute() {
        player.unequip(equipType);

        return ScriptState.COMPLETED;
    } 
}
