package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.TestMap.GateScript;
import Scripts.TestMap.LostBallScript;
import Scripts.TestMap.TestScript;
import Scripts.TestMap.backgroundScript;
import Scripts.TownMap.TownToStartPathScript;
import Tilesets.CommonTileset;
import Tilesets.DungeonTileSet;
import Tilesets.TownTileset;
import Utils.Point;

//this class represents the Dungeon map I am building

public class TownMap extends Map {
    
    public TownMap() {
        super("Town.txt", new TownTileset());
        this.playerStartPosition = new Point(1000, 2500);
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        Point startMapTrigger = getPositionByTileIndex(32, 139);

        triggers.add(new Trigger(startMapTrigger.x, startMapTrigger.y + 20,128, 10, new TownToStartPathScript(), "townToStartMapPath"));
        return triggers;
    }
}
