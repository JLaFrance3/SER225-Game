package Maps;

import Level.Map;
import Tilesets.IndoorTileset;
import Utils.Point;

public class GeneralStoreMap extends Map {
    
    public GeneralStoreMap() {
        super("GeneralStore.txt", new IndoorTileset());
    }
}