package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.IndoorMaps.H3_1ToTownDoorScript;
import Tilesets.IndoorTileset;
import Utils.Point;

public class House3_1Map extends Map {
    
    public House3_1Map() {
        super("House3.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(10, 13);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new H3_1ToTownDoorScript(), "H3_1ToTownDoor"));
 
        return triggers;
    }
}
