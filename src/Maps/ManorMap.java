package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.IndoorMaps.ManorToTownDoorScript;
import Tilesets.IndoorTileset;
import Utils.Point;

public class ManorMap extends Map {
    
    public ManorMap() {
        super("Manor.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(15, 25);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new ManorToTownDoorScript(), "manorToTownDoor"));
 
        return triggers;
    }
}
