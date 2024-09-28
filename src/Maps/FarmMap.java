package Maps;

import EnhancedMapTiles.PushableRock;
import Level.*;
import NPCs.Bug;
import NPCs.Dinosaur;
import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.TestMap.*;
import Tilesets.CommonTileset;
import Tilesets.FarmlandTileset;

import java.util.ArrayList;

// Represents a test map to be used in a level
public class FarmMap extends Map {

    public FarmMap() {
        super("farm_map.txt", new FarmlandTileset());
    }
}

