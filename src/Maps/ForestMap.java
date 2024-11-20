package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.Chest;
import EnhancedMapTiles.Gate;
import Level.*;
import Scripts.IndoorMaps.H3_1ToTownDoorScript;
import Tilesets.ForestTileset;



public class ForestMap extends Map {
    
    public ForestMap() {
        super("Forest.txt", new ForestTileset());
        this.playerStartPosition = getMapTile(3, 5).getLocation();
        
    }

     @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        return triggers;
    }
}
