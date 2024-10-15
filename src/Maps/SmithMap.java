package Maps;

import Level.Map;
import Tilesets.IndoorTileset;
import Utils.Point;

public class SmithMap extends Map {
    
    public SmithMap() {
        super("Smith.txt", new IndoorTileset());
    }
}
