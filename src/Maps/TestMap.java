package Maps;

import EnhancedMapTiles.PushableRock;
import EnhancedMapTiles.Gate;
import Level.*;
import NPCs.Bug;
import NPCs.Dinosaur;
import NPCs.Goblin;
import NPCs.Skeleton;
import NPCs.TestDummy;
import NPCs.Walrus;
import Scripts.SimpleTextScript;
import Scripts.TestMap.*;
import Tilesets.CommonTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

import Engine.ImageLoader;

// Represents a test map to be used in a level
public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new CommonTileset());
        this.playerStartPosition = getMapTile(17, 20).getLocation();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        PushableRock pushableRock = new PushableRock(getMapTile(2, 7).getLocation());
        enhancedMapTiles.add(pushableRock);

        Gate gate = new Gate(getMapTile(16, 27).getLocation());
        enhancedMapTiles.add(gate);
        
        

        return enhancedMapTiles;
    }

    //array to load npc's
    @Override
    public ArrayList<NPC> loadNPCs() {
        //this holds the npc's (?)
        ArrayList<NPC> npcs = new ArrayList<>();

        Walrus walrus = new Walrus(1, getMapTile(4, 28).getLocation().subtractY(40));//npc constructor appears to set an id (?) then set location
        walrus.setInteractScript(new WalrusScript());//used for interaction
        npcs.add(walrus);//adds the npc to the array

        Dinosaur dinosaur = new Dinosaur(2, getMapTile(13, 4).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);
        
        Bug bug = new Bug(3, getMapTile(7, 12).getLocation().subtractX(20));
        bug.setInteractScript(new BugScript());
        npcs.add(bug);

        TestDummy dummy = new TestDummy(4, getMapTile(5, 17).getLocation().subtractX(20));
        dummy.setInteractScript(new BugScript());
        npcs.add(dummy);

        Goblin goblin1 = new Goblin(5, getMapTile(3, 21).getLocation().subtractX(20));
        goblin1.setInteractScript(new DefaultMonsterScript());
        npcs.add(goblin1);

        Goblin goblin2 = new Goblin(5, getMapTile(18, 14).getLocation().subtractX(20));
        goblin2.setInteractScript(new DefaultMonsterScript());
        npcs.add(goblin2);

        Skeleton skeleton1 = new Skeleton(6, getMapTile(16, 25).getLocation().subtractX(20));
        skeleton1.setInteractScript(new DefaultMonsterScript());
        npcs.add(skeleton1);

        Skeleton skeleton2 = new Skeleton(6, getMapTile(10, 20).getLocation().subtractX(20));
        skeleton1.setInteractScript(new DefaultMonsterScript());
        npcs.add(skeleton2);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        Point tileLocation1 = getMapTile(6, 10).getLocation();//getting location of certain tile needed for gate
        triggers.add(new Trigger(790, 1030, 100, 10, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(790, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(890, 960, 10, 80, new LostBallScript(), "hasLostBall"));
        triggers.add(new Trigger(tileLocation1.x, tileLocation1.y, width, height, new GateScript(), "gateInteract")); //putting the gate script in associated location
        triggers.add(new Trigger(820, 1200, 150, 10, new TestScript(), "flowerBed"));
        return triggers;
    }

   
    

    @Override
    public void loadScripts() {
        getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

        getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

        getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

        getMapTile(2, 6).setInteractScript(new TreeScript());
    }
}

