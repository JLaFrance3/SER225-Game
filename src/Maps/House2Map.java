package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.IndoorMaps.H2ToTownDoorScript;
import Tilesets.IndoorTileset;
import Utils.Point;

public class House2Map extends Map {
    
    public House2Map() {
        super("House2.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(9, 13);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new H2ToTownDoorScript(), "H2ToTownDoor"));
 
        return triggers;
    }
}
