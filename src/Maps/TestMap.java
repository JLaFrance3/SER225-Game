package Maps;

import EnhancedMapTiles.Gate;
import Level.*;
import NPCs.Bug;
import NPCs.Dinosaur;
import NPCs.Goblin;
import NPCs.Skeleton;
import NPCs.TestDummy;
import NPCs.Walrus;
import Scripts.LockedDoorScript;
import Scripts.SimpleTextScript;
import Scripts.TestMap.*;
import Tilesets.FarmlandTileset;
import Utils.Point;
import java.util.ArrayList;

// Represents a test map to be used in a level
public class TestMap extends Map {

    public TestMap() {
        super("test_map.txt", new FarmlandTileset());
        this.playerStartPosition = getMapTile(17, 20).getLocation();
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        Gate gate = new Gate(getMapTile(5, 8).getLocation());
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

        Dinosaur dinosaur = new Dinosaur(2, getMapTile(10, 7).getLocation());
        dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        dinosaur.setInteractScript(new DinoScript());
        npcs.add(dinosaur);
        
        Bug bug = new Bug(3, getMapTile(7, 12).getLocation().subtractX(20));
        bug.setInteractScript(new BugScript());
        npcs.add(bug);

        TestDummy dummy = new TestDummy(4, getMapTile(5, 17).getLocation().subtractX(20));
        dummy.setInteractScript(new CombatScript("Uh oh! This bug is evil as hell?"));
        npcs.add(dummy);

        Goblin goblin1 = new Goblin(5, getMapTile(5, 15).getLocation().subtractX(20));
        goblin1.setInteractScript(new DefaultMonsterScript());
        npcs.add(goblin1);

        Goblin goblin2 = new Goblin(5, getMapTile(17, 9).getLocation().subtractX(20));
        goblin2.setInteractScript(new DefaultMonsterScript());
        npcs.add(goblin2);

        Skeleton skeleton1 = new Skeleton(6, getMapTile(9, 26).getLocation().subtractX(20));
        skeleton1.setInteractScript(new DefaultMonsterScript());
        npcs.add(skeleton1);

        Skeleton skeleton2 = new Skeleton(6, getMapTile(17, 2).getLocation().subtractX(20));
        skeleton1.setInteractScript(new DefaultMonsterScript());
        npcs.add(skeleton2);

        return npcs;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        Point tileLocation1 = getMapTile(6, 10).getLocation();//getting location of certain tile needed for gate
        Point townMapTrigger = getPositionByTileIndex(25, 6);
        triggers.add(new Trigger(500, 660, 100, 10, new backgroundScript(), "readBackground"));
        triggers.add(new Trigger(500, 580, 10, 80, new backgroundScript(), "readBackground"));
        triggers.add(new Trigger(600, 580, 10, 80, new backgroundScript(), "readBackground"));
        triggers.add(new Trigger(tileLocation1.x, tileLocation1.y, width, height, new GateScript(), "gateInteract")); //putting the gate script in associated location
        triggers.add(new Trigger(getMapTile(5, 17).getX(),getMapTile(5, 17).getY(),100,10,new CombatScript("Uh oh! This bug is evil as hell?"),"hasfought"));
        triggers.add(new Trigger(820, 1200, 150, 10, new TestScript(), "flowerBed"));
        triggers.add(new Trigger(townMapTrigger.x + 20, townMapTrigger.y,10, 128, new StartToTownPathScript(), "startToTownMapPath"));
        //triggers.add(new Trigger(getMapTile(2, 8).getX(),getMapTile(2, 8).getY(), 30, 30, new TestQuestScript(), "readTestQuest"));
        triggers.add(new Trigger(getMapTile(1, 6).getX(),getMapTile(1, 6).getY(), 30, 10, new QuestOneScript(), "readQuestOne"));
        triggers.add(new Trigger(getMapTile(17, 4).getX(),getMapTile(17, 4).getY(), 30, 30, new QuestOneChestScript(), "readQuestOneChest"));

        //Locked door triggers
        Point[] lockDoorTriggers = new Point[] {
            getPositionByTileIndex(5, 26),
            getPositionByTileIndex(17, 18),
            getPositionByTileIndex(13, 5)
        };
        Script lockedDoorScript = new LockedDoorScript();
        triggers.add(new Trigger(lockDoorTriggers[0].x, lockDoorTriggers[1].y + 24, 32, 10, lockedDoorScript,"lockedDoor"));
        triggers.add(new Trigger(lockDoorTriggers[1].x, lockDoorTriggers[1].y + 24, 32, 10, lockedDoorScript,"lockedDoor"));
        triggers.add(new Trigger(lockDoorTriggers[2].x, lockDoorTriggers[2].y + 24, 32, 10, lockedDoorScript,"lockedDoor"));

        //Sign triggers
        Point walrusHouseSign = getMapTile(7, 26).getLocation();
        Point leaveStartAreaSign = getMapTile(22, 8).getLocation();
        triggers.add(new Trigger(walrusHouseSign.x, walrusHouseSign.y, 32, 32, new WalrusHouseSign(),"walrusHouseSign"));
        triggers.add(new Trigger(leaveStartAreaSign.x, leaveStartAreaSign.y, 32, 32, new LeaveStartAreaSign(),"leaveStartAreaSign"));

        return triggers;
    }
}

