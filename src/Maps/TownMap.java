package Maps;

import Level.Map;
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
}
