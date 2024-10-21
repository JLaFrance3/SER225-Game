package Maps;

import EnhancedMapTiles.Chest;
import EnhancedMapTiles.Gate;
import Level.*;
import NPCs.Bug;
import NPCs.Dinosaur;
import NPCs.Goblin;
import NPCs.Skeleton;
import NPCs.TestDummy;
import NPCs.Walrus;
import Scripts.LockedDoorScript;
import Scripts.SimpleTextScript;
import Scripts.TestMap.*;
import Tilesets.DungeonTileSet;
import Tilesets.FarmlandTileset;
import Utils.Point;
import Scripts.TestMap.DungeonToStartScript;
import java.util.ArrayList;

//this class represents the Dungeon map I am building

public class DungeonMap extends Map {
    
    public DungeonMap() {
        super("Dungeon.txt", new DungeonTileSet());
        this.playerStartPosition = getMapTile(7, 5).getLocation();
        
    }


    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
      
        triggers.add(new Trigger(getMapTile(7,3).getX(),getMapTile(7,3).getY(), width, height, new DungeonToStartScript(), "dungeonToStart"));
        triggers.add(new Trigger(getMapTile(8,3).getX(),getMapTile(8,3).getY(), width, height, new DungeonToStartScript(), "dungeonToStart"));


        return triggers;
    }

}
