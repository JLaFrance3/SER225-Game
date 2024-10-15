package Maps;

import Level.Map;
import Tilesets.IndoorTileset;
import Utils.Point;

public class TownHallMap extends Map {
    
    public TownHallMap() {
        super("TownHall.txt", new IndoorTileset());
    }
}
