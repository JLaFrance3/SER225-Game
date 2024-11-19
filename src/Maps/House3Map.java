package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.IndoorMaps.H3ToTownDoorScript;
import Scripts.TownMap.AncientScriptScript;
import Tilesets.IndoorTileset;
import Utils.Point;

public class House3Map extends Map {
    
    public House3Map() {
        super("House3.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(10, 13);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new H3ToTownDoorScript(), "H3ToTownDoor"));
        Point ancientScriptTrigger = getPositionByTileIndex(11, 9);
        triggers.add(new Trigger(ancientScriptTrigger.x, ancientScriptTrigger.y,32, 20, new AncientScriptScript(), "seenAncientScript"));

        return triggers;
    }
}
