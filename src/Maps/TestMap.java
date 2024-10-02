package Maps;

import EnhancedMapTiles.PushableRock;
import EnhancedMapTiles.Gate;
import Level.*;
import NPCs.Bug;
import NPCs.Dinosaur;
import NPCs.TestDummy;
import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.TestMap.*;
import Tilesets.CommonTileset;
import Tilesets.FarmlandTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

import Engine.ImageLoader;

// Represents a test map to be used in a level
public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new FarmlandTileset());
        this.playerStartPosition = getMapTile(17, 20).getLocation();
    }

    // @Override
    // public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
    //     ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

    //     Gate gate = new Gate(getMapTile(5, 8).getLocation());
    //     enhancedMapTiles.add(gate);
        
    //     return enhancedMapTiles;
    // }

    //array to load npc's
    @Override
    public ArrayList<NPC> loadNPCs() {
        //this holds the npc's (?)
        ArrayList<NPC> npcs = new ArrayList<>();

        Walrus walrus = new Walrus(1, getMapTile(4, 28).getLocation().subtractY(40));//npc constructor appears to set an id (?) then set location
        walrus.setInteractScript(new WalrusScript());//used for interaction
        npcs.add(walrus);//adds the npc to the array

        Dinosaur dinosaur = new Dinosaur(2, getMapTile(10, 7).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);
        
        Bug bug = new Bug(3, getMapTile(7, 12).getLocation().subtractX(20));
        bug.setInteractScript(new BugScript());
        npcs.add(bug);

        TestDummy dummy = new TestDummy(4, getMapTile(5, 17).getLocation().subtractX(20));
        dummy.setInteractScript(new BugScript());
        npcs.add(dummy);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        Point tileLocation1 = getMapTile(6, 10).getLocation();//getting location of certain tile needed for gate
        triggers.add(new Trigger(500, 660, 100, 10, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(500, 580, 10, 80, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(600, 580, 10, 80, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(tileLocation1.x, tileLocation1.y, width, height, new GateScript(), "gateInteract")); //putting the gate script in associated location
        triggers.add(new Trigger(820, 1200, 150, 10, new TestScript(), "flowerBed"));
        return triggers;
    }

   
    

    @Override
    public void loadScripts() {
        getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));
    }
}

