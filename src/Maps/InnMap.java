package Maps;

import Level.Map;
import Tilesets.IndoorTileset;
import Utils.Point;

public class InnMap extends Map {
    
    public InnMap() {
        super("Inn.txt", new IndoorTileset());
    }
}
