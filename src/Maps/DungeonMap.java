package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Tilesets.DungeonTileSet;
import Utils.Point;

//this class represents the Dungeon map I am building

public class DungeonMap extends Map {
    
    public DungeonMap() {
        super("Dungeon.txt", new DungeonTileSet());
        this.playerStartPosition = getMapTile(7, 5).getLocation();
    }
}
