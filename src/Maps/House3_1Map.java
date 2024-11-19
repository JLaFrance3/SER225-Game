package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.IndoorMaps.H3_1ToTownDoorScript;
import Tilesets.IndoorTileset;
import Utils.Point;
import Scripts.TownMap.AncientScriptScript;

public class House3_1Map extends Map {
    
    public House3_1Map() {
        super("House3.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(10, 13);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new H3_1ToTownDoorScript(), "H3_1ToTownDoor"));
        Point ancientScriptTrigger = getPositionByTileIndex(11, 9);
        triggers.add(new Trigger(ancientScriptTrigger.x, ancientScriptTrigger.y,32, 20, new AncientScriptScript(), "seenAncientScript"));

        return triggers;
    }
}
