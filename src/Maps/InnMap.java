package Maps;

import java.util.ArrayList;

import Engine.ImageLoader;
import GameObject.SpriteSheet;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.GenericNPC;
import Scripts.IndoorMaps.InnToTownDoorScript;
import Tilesets.IndoorTileset;
import Utils.Direction;
import Utils.Point;

public class InnMap extends Map {
    
    public InnMap() {
        super("Inn.txt", new IndoorTileset());
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        GenericNPC innKeeper = new GenericNPC(1, getMapTile(10,8).getLocation().subtractY(20), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_1.png", true), 64, 64), Direction.DOWN);
        npcs.add(innKeeper);
        
        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        Point townMapTrigger = getPositionByTileIndex(9, 13);
        triggers.add(new Trigger(townMapTrigger.x, townMapTrigger.y + 26,64, 6, new InnToTownDoorScript(), "innToTownDoor"));
 
        return triggers;
    }
}
