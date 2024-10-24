package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.IndoorMaps.SmithToTownDoorScript;
import Tilesets.IndoorTileset;
import Utils.Point;

public class SmithMap extends Map {
    
    public SmithMap() {
        super("Smith.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(10, 13);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new SmithToTownDoorScript(), "smithToTownDoor"));
 
        return triggers;
    }
}
