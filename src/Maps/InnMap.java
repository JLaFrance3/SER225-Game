package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.IndoorMaps.InnToTownDoorScript;
import Tilesets.IndoorTileset;
import Utils.Point;

public class InnMap extends Map {
    
    public InnMap() {
        super("Inn.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(9, 13);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new InnToTownDoorScript(), "innToTownDoor"));
 
        return triggers;
    }
}
