package Maps;
import java.util.ArrayList;

import Level.*;
import NPCs.TestDummy;
import Scripts.TestMap.BugScript;
import Tilesets.CommonTileset;
import Utils.Point;

public class TestDungeon extends Map {
    //constructor for creating a map
    public TestDungeon() {
        super("TestDungeon.txt", new CommonTileset());
        this.playerStartPosition = new Point(1, 11);
    }

    //array to load npc's
    @Override
    public ArrayList<NPC> loadNPCs() {
        //this holds the npc's (?)
        ArrayList<NPC> npcs = new ArrayList<>();

        TestDummy dummy = new TestDummy(1, getMapTile(5, 5).getLocation().subtractX(20));
        dummy.setInteractScript(new BugScript());
        npcs.add(dummy);

        return npcs;
    }
}

