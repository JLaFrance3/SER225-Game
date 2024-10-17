package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.IndoorMaps.H1ToTownDoorScript;
import Tilesets.IndoorTileset;
import Utils.Point;

public class House1Map extends Map {
    
    public House1Map() {
        super("House1.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(9, 13);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new H1ToTownDoorScript(), "H1ToTownDoor"));
 
        return triggers;
    }
}
