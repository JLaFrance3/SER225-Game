package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.IndoorMaps.HallToTownDoorScript;
import Tilesets.IndoorTileset;
import Utils.Point;

public class TownHallMap extends Map {
    
    public TownHallMap() {
        super("TownHall.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(10, 14);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new HallToTownDoorScript(), "hallToTownDoor"));
 
        return triggers;
    }
}
