package Maps;

import Level.Map;
import Tilesets.IndoorTileset;
import Utils.Point;

public class ManorMap extends Map {
    
    public ManorMap() {
        super("Manor.txt", new IndoorTileset());
    }
}
