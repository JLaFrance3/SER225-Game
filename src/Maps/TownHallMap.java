package Maps;

import java.util.ArrayList;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Bug;
import NPCs.GenericNPC;
import NPCs.OldMan3;
import NPCs.TestDummy;
import NPCs.Monsters.Goblin;
import NPCs.Monsters.GoldDragon;
import NPCs.Monsters.Skeleton;
import Scripts.IndoorMaps.HallToTownDoorScript;
import Scripts.TownMap.SeenMapsScript;
import Scripts.TestMap.CombatScript;
import Scripts.TownMap.OldMan3Script;
import Tilesets.IndoorTileset;
import Utils.Direction;
import Utils.Point;

public class TownHallMap extends Map {
    
    public TownHallMap() {
        super("TownHall.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        GenericNPC administrator = new GenericNPC(1, getMapTile(5,8).getLocation().subtractY(20), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_0.png", true), 64, 64), Direction.DOWN);
        npcs.add(administrator);
        
        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(10, 14);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new HallToTownDoorScript(), "hallToTownDoor"));
        Point townMapTrigger2 = getPositionByTileIndex(9, 8);
        triggers.add(new Trigger(townMapTrigger2.x, townMapTrigger2.y, 32, 16, new SeenMapsScript(), "seenMaps"));
 
        return triggers;
    }
}
