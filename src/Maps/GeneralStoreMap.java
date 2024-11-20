package Maps;

import java.util.ArrayList;
import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.GenericNPC;
import Scripts.GenericResponseScript;
import Scripts.IndoorMaps.StoreToTownDoorScript;
import Tilesets.IndoorTileset;
import Utils.Direction;
import Utils.Point;

public class GeneralStoreMap extends Map {
    
    public GeneralStoreMap() {
        super("GeneralStore.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        GenericNPC merchant = new GenericNPC(1, getMapTile(8,9).getLocation().subtractY(20), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_5.png", true), 64, 64), Direction.DOWN);
        npcs.add(merchant);
        
        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(9, 14);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new StoreToTownDoorScript(), "storeToTownDoor"));
 
        return triggers;
    }
}