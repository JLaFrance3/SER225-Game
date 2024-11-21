package Maps;

import EnhancedMapTiles.Chest;
import EnhancedMapTiles.Gate;
import Level.*;
import NPCs.Bug;
import NPCs.OldMan3;
import NPCs.TestDummy;
import NPCs.Walrus;
import NPCs.Monsters.Goblin;
import NPCs.Monsters.GoldDragon;
import NPCs.Monsters.Skeleton;
import Scripts.LockedDoorScript;
import Scripts.SimpleTextScript;
import Scripts.TestMap.*;
import Tilesets.DungeonTileSet;
import Tilesets.FarmlandTileset;
import Utils.Point;
import Scripts.TownMap.OldMan3Script;

import java.util.ArrayList;

//this class represents the Dungeon map I am building

public class DungeonMap extends Map {
    
    public DungeonMap() {
        super("Dungeon.txt", new DungeonTileSet());
        this.playerStartPosition = getMapTile(3, 5).getLocation();
        
    }

     @Override
    public ArrayList<NPC> loadNPCs() {
        //this holds the npc's (?)
        ArrayList<NPC> npcs = new ArrayList<>();
        
       GoldDragon goldDragon1 = new GoldDragon(5, getMapTile(10,3).getLocation().subtractX(20));
       goldDragon1.setInteractScript(new CombatScript("This looks like a Boss Battle!", 25, 110, "The dragon breaths fire on you", 500, "BossAlive","Nothing"));
       goldDragon1.setExistenceFlag("BossAlive");
       npcs.add(goldDragon1);

        return npcs;
    }


    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
      
        triggers.add(new Trigger(getMapTile(7,3).getX(),getMapTile(7,3).getY(), width, height, new DungeonToStartScript(), "dungeonToStart"));
        triggers.add(new Trigger(getMapTile(8,3).getX(),getMapTile(8,3).getY(), width, height, new DungeonToStartScript(), "dungeonToStart"));


        return triggers;
    }

}
