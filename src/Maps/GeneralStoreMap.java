package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.IndoorMaps.StoreToTownDoorScript;
import Tilesets.IndoorTileset;
import Utils.Point;

public class GeneralStoreMap extends Map {
    
    public GeneralStoreMap() {
        super("GeneralStore.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(9, 14);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new StoreToTownDoorScript(), "storeToTownDoor"));
 
        return triggers;
    }
}