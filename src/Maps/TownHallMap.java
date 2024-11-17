package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Bug;
import NPCs.OldMan3;
import NPCs.TestDummy;
import NPCs.Monsters.Goblin;
import NPCs.Monsters.GoldDragon;
import NPCs.Monsters.Skeleton;
import Scripts.IndoorMaps.HallToTownDoorScript;
import Scripts.TestMap.CombatScript;
import Scripts.TownMap.OldMan3Script;
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
