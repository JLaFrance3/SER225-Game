package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Tilesets.DungeonTileSet;
import Utils.Point;

public class DungeonMap extends Map {
    
    public DungeonMap() {
        super("Dungeon.txt", new DungeonTileSet());
        this.playerStartPosition = new Point(7, 4);
    }
}
