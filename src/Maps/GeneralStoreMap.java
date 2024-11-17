package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Bug;
import NPCs.OldMan2;
import NPCs.TestDummy;
import NPCs.Monsters.Goblin;
import NPCs.Monsters.GoldDragon;
import NPCs.Monsters.Skeleton;
import Scripts.IndoorMaps.StoreToTownDoorScript;
import Scripts.TestMap.CombatScript;
import Scripts.TownMap.OldMan2Script;
import Tilesets.IndoorTileset;
import Utils.Point;

public class GeneralStoreMap extends Map {
    
    public GeneralStoreMap() {
        super("GeneralStore.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(9, 14);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new StoreToTownDoorScript(), "storeToTownDoor"));
 
        return triggers;
    }
}