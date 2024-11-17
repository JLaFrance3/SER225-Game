package Maps;

import EnhancedMapTiles.Chest;
import EnhancedMapTiles.Gate;
import EnhancedMapTiles.Gold;
import EnhancedMapTiles.Swords.GreatSword;
import EnhancedMapTiles.KeyItem;
import EnhancedMapTiles.Spells.Fire;
import EnhancedMapTiles.Spells.Thunder;
import Level.*;
import NPCs.Bug;
import NPCs.FarmerGirl;
import NPCs.OldMan1;
import NPCs.OldMan2;
import NPCs.OldMan3;
import NPCs.TestDummy;
import NPCs.Monsters.BadFlower;
import NPCs.Monsters.Bat;
import NPCs.Monsters.Eye;
import NPCs.Monsters.Goblin;
import NPCs.Monsters.GoldDragon;
import NPCs.Monsters.Pumpkin;
import NPCs.Monsters.Skeleton;
import Scripts.TestMap.*;
import Scripts.TownMap.OldMan2Script;
import Scripts.TownMap.OldMan3Script;
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
        //enhancedMapTiles.add(gate);

        Chest chest2 = new Chest(getMapTile(12, 24).getLocation());
        chest2.setExistenceFlag("hasInteractedChest2");
        chest2.setInteractScript(new Chest2Script());
        enhancedMapTiles.add(chest2);
        
        
        Chest chest1 = new Chest(getMapTile(17, 3).getLocation());
        enhancedMapTiles.add(chest1);

        KeyItem key2 = new KeyItem(getMapTile(3, 28).getLocation());
        key2.setExistenceFlag("hasInteractedKey1");
        key2.setInteractScript(new Key2Script());
        enhancedMapTiles.add(key2);

        Thunder thunderSpell = new Thunder(getMapTile(10,10).getLocation());
        thunderSpell.setExistenceFlag("hasInteractedThunder");
        thunderSpell.setInteractScript(new ThunderSpellScript());
        enhancedMapTiles.add(thunderSpell);


        GreatSword greatsword = new GreatSword(getMapTile(11,20).getLocation());
        greatsword.setExistenceFlag("hasInteractedGreatSword");
        greatsword.setInteractScript(new GreatSwordScript());
        enhancedMapTiles.add(greatsword);



        return enhancedMapTiles;
    }

    //array to load npc's
    @Override
    public ArrayList<NPC> loadNPCs() {
        //this holds the npc's (?)
        ArrayList<NPC> npcs = new ArrayList<>();
        
        // Bug bug = new Bug(3, getMapTile(7, 12).getLocation().subtractX(20));
        // bug.setInteractScript(new CombatScript("Uh oh, this bug is evil as hell", 4));
      //  npcs.add(bug);



        TestDummy dummy = new TestDummy(4, getMapTile(5, 17).getLocation().subtractX(20)); 
        dummy.setInteractScript(new CombatScript("Uh oh, this bug is evil as hell", 4, 5, "CHOMP!!! you are bit by the bug", 100, "dummyAlive"));
       // npcs.add(dummy);
        dummy.setExistenceFlag("dummyAlive");
        npcs.add(dummy);

    //     Goblin goblin1 = new Goblin(5, getMapTile(5, 15).getLocation().subtractX(20));
    //     goblin1.setInteractScript(new CombatScript("Uh oh, this bug is evil as hell", 4));
    //     //npcs.add(goblin1);

    //     Goblin goblin2 = new Goblin(5, getMapTile(17, 9).getLocation().subtractX(20));
    //     goblin2.setInteractScript(new CombatScript("Uh oh, this bug is evil as hell", 4));
    //    // npcs.add(goblin2);

    //     GoldDragon goldDragon1 = new GoldDragon(5, getMapTile(5,12).getLocation().subtractX(20));
    //     //npcs.add(goldDragon1);

    //     Skeleton skeleton1 = new Skeleton(6, getMapTile(9, 26).getLocation().subtractX(20));
    //     skeleton1.setInteractScript(new CombatScript("Uh oh, this bug is evil as hell", 4));
    //   //  npcs.add(skeleton1);

    //     Skeleton skeleton2 = new Skeleton(6, getMapTile(17, 2).getLocation().subtractX(20));
    //     skeleton1.setInteractScript(new CombatScript("Uh oh, this bug is evil as hell", 4));
    //    // npcs.add(skeleton2);


    //    BadFlower flower1 = new BadFlower(5, getMapTile(6,13).getLocation().subtractX(20));
    //   // npcs.add(flower1);

    //   Bat bat1 = new Bat(5, getMapTile(6,13).getLocation().subtractX(20));
    // //   npcs.add(bat1);

    //     Pumpkin pumpkin1 = new Pumpkin(5, getMapTile(6,13).getLocation().subtractX(20));
    //    // npcs.add(pumpkin1);

       OldMan1 oldman1 = new OldMan1(5, getMapTile(5,27).getLocation().subtractX(20));
       oldman1.setInteractScript(new OldMan1Script());
       npcs.add(oldman1);

      FarmerGirl farmerGirl1 = new FarmerGirl(5,getMapTile(7,8).getLocation().subtractX(20));
      farmerGirl1.setInteractScript(new FarmerGirlScript());
      npcs.add(farmerGirl1);  

      Eye eye1 = new Eye(5, getMapTile(5,12).getLocation().subtractX(20));
      npcs.add(eye1);
        return npcs;


    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
       // Point tileLocation1 = getMapTile(6, 10).getLocation();//getting location of certain tile needed for gate
        Point townMapTrigger = getPositionByTileIndex(25, 6);
        triggers.add(new Trigger(500, 660, 100, 10, new backgroundScript(), "readBackground"));
        triggers.add(new Trigger(500, 580, 10, 80, new backgroundScript(), "readBackground"));
        triggers.add(new Trigger(600, 580, 10, 80, new backgroundScript(), "readBackground"));
        // triggers.add(new Trigger(getMapTile(5, 17).getX(),getMapTile(5, 17).getY(),100,10,new CombatScript("Uh oh! This bug is evil as hell?"),"hasfought"));
       // triggers.add(new Trigger(tileLocation1.x, tileLocation1.y, width, height, new GateScript(), "gateInteract")); //putting the gate script in associated location
        //triggers.add(new Trigger(820, 1200, 150, 10, new TestScript(), "flowerBed"));
        triggers.add(new Trigger(townMapTrigger.x + 20, townMapTrigger.y,10, 128, new StartToTownPathScript(), "startToTownMapPath"));
      //  triggers.add(new Trigger(getMapTile(6,10).getX(),getMapTile(3,6).getY(), width, height, new StartToDungeonScript(), "startToDungeon"));
       
      //triggers.add(new Trigger(getMapTile(2, 8).getX(),getMapTile(2, 8).getY(), 30, 30, new TestQuestScript(), "readTestQuest"));
        //triggers.add(new Trigger(getMapTile(1, 6).getX(),getMapTile(1, 6).getY(), 30, 10, new QuestOneScript(), "readQuestOne"));
        //triggers.add(new Trigger(getMapTile(17, 4).getX(),getMapTile(17, 4).getY(), 30, 10, new QuestOneChestScript(), "readQuestOneChest"));
        triggers.add(new Trigger(getMapTile(6,10).getX(),getMapTile(3,6).getY(), width, height, new StartToDungeonScript(), "notStealCorn"));
        triggers.add(new Trigger(getMapTile(25,28).getX(),getMapTile(25,28).getY(), width, height, new OldMan1Script(), "talkedToOldMan1"));
        triggers.add(new Trigger(getMapTile(6,10).getX(),getMapTile(3,6).getY(), width, height, new StartToDungeonScript(), "startToDungeon"));
        //triggers.add(new Trigger(getMapTile(25,28).getX(),getMapTile(25,28).getY(), width, height, new OldMan1Script(), "talkedToOldMan1"));

        //Locked door triggers
        // Point[] lockDoorTriggers = new Point[] {
        //     getPositionByTileIndex(5, 26),
        //     getPositionByTileIndex(17, 18),
        //     getPositionByTileIndex(13, 5)
        // };
        // Script lockedDoorScript = new LockedDoorScript();
        // triggers.add(new Trigger(lockDoorTriggers[0].x, lockDoorTriggers[0].y + 24, 32, 10, lockedDoorScript,"lockedDoor"));
        // triggers.add(new Trigger(lockDoorTriggers[1].x, lockDoorTriggers[1].y + 24, 32, 10, lockedDoorScript,"lockedDoor"));
        // triggers.add(new Trigger(lockDoorTriggers[2].x, lockDoorTriggers[2].y + 24, 32, 10, lockedDoorScript,"lockedDoor"));

        //Sign triggers
        Point walrusHouseSign = getMapTile(7, 26).getLocation();
        Point leaveStartAreaSign = getMapTile(22, 8).getLocation();
        triggers.add(new Trigger(walrusHouseSign.x, walrusHouseSign.y, 32, 32, new WalrusHouseSign(),"walrusHouseSign"));
        triggers.add(new Trigger(leaveStartAreaSign.x, leaveStartAreaSign.y, 32, 32, new LeaveStartAreaSign(),"leaveStartAreaSign"));

        return triggers;
    }
}

