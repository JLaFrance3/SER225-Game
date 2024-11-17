package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.Chest;
import EnhancedMapTiles.Gate;
import EnhancedMapTiles.Gold;
import EnhancedMapTiles.KeyItem;
import EnhancedMapTiles.Armor.DemoLeatherarmour;
import EnhancedMapTiles.Armor.DemoMagicarmour;
import EnhancedMapTiles.Armor.DemoPlatearmor;
import EnhancedMapTiles.Spells.Fire;
import EnhancedMapTiles.Spells.Thunder;
import EnhancedMapTiles.Swords.GreatSword;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Bug;
import NPCs.OldMan1;
import NPCs.TestDummy;
import NPCs.Monsters.BadFlower;
import NPCs.Monsters.Bat;
import NPCs.Monsters.Goblin;
import NPCs.Monsters.GoldDragon;
import NPCs.Monsters.Pumpkin;
import NPCs.Monsters.Skeleton;
import Level.Script;
import Scripts.LockedDoorScript;
import Scripts.TestMap.CombatScript;
import Scripts.TestMap.FireSpellScript;
import Scripts.TestMap.GoldScript;
import Scripts.TestMap.Chest2Script;
import Scripts.TestMap.GreatSwordScript;
import Scripts.TestMap.Key2Script;
import Scripts.TestMap.OldMan1Script;
import Scripts.TestMap.ThunderSpellScript;
import Scripts.TownMap.*;
import Tilesets.TownTileset;
import Utils.Point;

//this class represents the Dungeon map I am building

public class TownMap extends Map {
    
    public TownMap() {
        super("Town.txt", new TownTileset());
        this.playerStartPosition = new Point(500, 3000);
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        //this holds the npc's (?)
        ArrayList<NPC> npcs = new ArrayList<>();
        
//         Bug bug = new Bug(3, getMapTile(7, 12).getLocation().subtractX(20));
//         bug.setInteractScript(new CombatScript("Uh oh, this bug is evil as hell"));
//       //  npcs.add(bug);

// //32,107), 25, 114), 36,114), 27, 118)

//         TestDummy dummy = new TestDummy(4, getMapTile(5, 17).getLocation().subtractX(20)); 
//         dummy.setInteractScript(new CombatScript("Uh oh, this bug is evil as hell"));
//        // npcs.add(dummy);

        Goblin goblin1 = new Goblin(5, getMapTile(90, 106).getLocation().subtractX(20));
        goblin1.setInteractScript(new CombatScript("Uh oh, this goblin is evil as hell",4,8,"the goblin slashes at you with its claws",15,"goblin1Flag"));
        goblin1.setExistenceFlag("goblin1Flag");
        npcs.add(goblin1);

        Goblin goblin2 = new Goblin(5, getMapTile(86, 109).getLocation().subtractX(20));
        goblin2.setInteractScript(new CombatScript("Uh oh, this goblin is evil as hell",4,8,"the goblin slashes at you with its claws",15,"goblin2Flag"));
        goblin2.setExistenceFlag("goblin2Flag");
        npcs.add(goblin2);

        Goblin goblin3 = new Goblin(5, getMapTile(71, 102).getLocation().subtractX(20));
        goblin3.setInteractScript(new CombatScript("Uh oh, this goblin is evil as hell",4,8,"the goblin slashes at you with its claws",15,"goblin3Flag"));
        goblin3.setExistenceFlag("goblin3Flag");
        npcs.add(goblin3);

        Goblin goblin4 = new Goblin(5, getMapTile(66, 112).getLocation().subtractX(20));
        goblin4.setInteractScript(new CombatScript("Uh oh, this goblin is evil as hell",4,8,"the goblin slashes at you with its claws",50,"goblin4Flag"));
        goblin4.setExistenceFlag("goblin4Flag");
        npcs.add(goblin4);

        //GoldDragon goldDragon1 = new GoldDragon(5, getMapTile(5,12).getLocation().subtractX(20));
        //npcs.add(goldDragon1);

        Skeleton skeleton1 = new Skeleton(6, getMapTile(2, 110).getLocation().subtractX(20));
        skeleton1.setInteractScript(new CombatScript("Uh oh, this skeleton is evil as hell",6,13,"the skeleton hacks at you with his shortsword",50,"skeleton1Flag"));
        skeleton1.setExistenceFlag("skeleton1Flag");
        npcs.add(skeleton1);

        Skeleton skeleton2 = new Skeleton(6, getMapTile(11, 115).getLocation().subtractX(20));
        skeleton2.setInteractScript(new CombatScript("Uh oh, this skeleton is evil as hell",6,13,"the skeleton hacks at you with his shortsword",50,"skeleton2Flag"));
        skeleton2.setExistenceFlag("skeleton2Flag");
        npcs.add(skeleton2);

        Skeleton skeleton3 = new Skeleton(6, getMapTile(32, 107).getLocation().subtractX(20));
        skeleton3.setInteractScript(new CombatScript("Uh oh, this skeleton is evil as hell",6,13,"the skeleton hacks at you with his shortsword",50,"skeleton3Flag"));
        skeleton3.setExistenceFlag("skeleton3Flag");
        npcs.add(skeleton3);

        Skeleton skeleton4 = new Skeleton(6, getMapTile(30, 111).getLocation().subtractX(20));
        skeleton4.setInteractScript(new CombatScript("Uh oh, this skeleton is evil as hell",6,13,"the skeleton hacks at you with his shortsword",50,"skeleton4Flag"));
        skeleton4.setExistenceFlag("skeleton4Flag");
        npcs.add(skeleton4);


       BadFlower flower1 = new BadFlower(5, getMapTile(78,116).getLocation().subtractX(20));
       flower1.setInteractScript(new CombatScript("Uh oh, this flower is evil as hell",3,10,"tendrils lash at you and thorns cut your skin",10,"flower1Flag"));
       flower1.setExistenceFlag("flower1Flag");
       npcs.add(flower1);

       BadFlower flower2 = new BadFlower(5, getMapTile(74,106).getLocation().subtractX(20));
       flower2.setInteractScript(new CombatScript("Uh oh, this flower is evil as hell",3,10,"tendrils lash at you and thorns cut your skin",10,"flower2Flag"));
       flower2.setExistenceFlag("flower2Flag");
       npcs.add(flower2);

       BadFlower flower3 = new BadFlower(5, getMapTile(76,109).getLocation().subtractX(20));
       flower3.setInteractScript(new CombatScript("Uh oh, this flower is evil as hell",3,10,"tendrils lash at you and thorns cut your skin",10,"flower3Flag"));
       flower3.setExistenceFlag("flower3Flag");
       npcs.add(flower3);

       Bat bat1 = new Bat(5, getMapTile(17,94).getLocation().subtractX(20));
       bat1.setInteractScript(new CombatScript("Uh oh, this bat is evil as hell",8,22,"the bat chomps into you",50,"bat1Flag"));
       bat1.setExistenceFlag("bat1Flag");
       npcs.add(bat1);

       Bat bat2 = new Bat(5, getMapTile(15,117).getLocation().subtractX(20));
       bat2.setInteractScript(new CombatScript("Uh oh, this bat is evil as hell",8,22,"the bat chomps into you",50,"bat2Flag"));
       bat2.setExistenceFlag("bat2Flag");
       npcs.add(bat2);

       Bat bat3 = new Bat(5, getMapTile(25,114).getLocation().subtractX(20));
       bat3.setInteractScript(new CombatScript("Uh oh, this bat is evil as hell",8,22,"the bat chomps into you",50,"bat3Flag"));
       bat3.setExistenceFlag("bat3Flag");
       npcs.add(bat3);

        Pumpkin pumpkin1 = new Pumpkin(5, getMapTile(21,108).getLocation().subtractX(20));
        pumpkin1.setInteractScript(new CombatScript("Uh oh, this Pumpkin is evil as hell",22,59,"the evil pumpkin rolls into you", 150,"pumkin1FLag"));
        pumpkin1.setExistenceFlag("pumpkin1FLag");
        npcs.add(pumpkin1);

        Pumpkin pumpkin2 = new Pumpkin(5, getMapTile(2,117).getLocation().subtractX(20));
        pumpkin2.setInteractScript(new CombatScript("Uh oh, this Pumpkin is evil as hell",22,59,"the evil pumpkin rolls into you", 150,"pumpkin2FLag"));
        pumpkin1.setExistenceFlag("pumpkin2FLag");
        npcs.add(pumpkin2);

        Pumpkin pumpkin3 = new Pumpkin(5, getMapTile(36,114).getLocation().subtractX(20));
        pumpkin3.setInteractScript(new CombatScript("Uh oh, this Pumpkin is evil as hell",22,59,"the evil pumpkin rolls into you", 150,"pumpkin3FLag"));
        pumpkin3.setExistenceFlag("pumpkin3FLag");
        npcs.add(pumpkin3);


         
        return npcs;
    }

    
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        Gold gold1 = new Gold(getMapTile(33,59).getLocation());
        gold1.setExistenceFlag("gotGold1");
        gold1.setInteractScript(new GoldScript());
       enhancedMapTiles.add(gold1);

        Fire fireSpell1 = new Fire(getMapTile(64,61).getLocation());
        fireSpell1.setExistenceFlag("gotFire");
        fireSpell1.setInteractScript(new FireSpellScript());
        enhancedMapTiles.add(fireSpell1);
        
        return enhancedMapTiles;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        Point startMapTrigger = getPositionByTileIndex(32, 127);
        Point storeDoorTrigger = getPositionByTileIndex(38, 56);
        Point house1DoorTrigger = getPositionByTileIndex(76, 45);
        Point house2DoorTrigger = getPositionByTileIndex(85, 45);
        Point house3DoorTrigger = getPositionByTileIndex(110, 67);
        Point house3_1DoorTrigger = getPositionByTileIndex(100, 51);
        Point innDoorTrigger = getPositionByTileIndex(33, 38);
        Point manorDoorTrigger = getPositionByTileIndex(60, 29);
        Point smithDoorTrigger = getPositionByTileIndex(13, 76);
        Point townHallDoorTrigger = getPositionByTileIndex(78, 75);
        Point[] lockDoorTriggers = new Point[] {
            getPositionByTileIndex(102, 67),
            getPositionByTileIndex(104, 51),
            getPositionByTileIndex(110, 51),
            getPositionByTileIndex(77, 55),
            getPositionByTileIndex(81, 55),
            getPositionByTileIndex(14, 49),
            getPositionByTileIndex(22, 92),
        };

        //Locked door triggers
        Script lockedDoorScript = new LockedDoorScript();
        triggers.add(new Trigger(lockDoorTriggers[0].x, lockDoorTriggers[0].y + 24, 32, 10, lockedDoorScript,"lockedDoor"));
        triggers.add(new Trigger(lockDoorTriggers[1].x, lockDoorTriggers[1].y + 24, 32, 10, lockedDoorScript,"lockedDoor"));
        triggers.add(new Trigger(lockDoorTriggers[2].x, lockDoorTriggers[2].y + 24, 32, 10, lockedDoorScript,"lockedDoor"));
        triggers.add(new Trigger(lockDoorTriggers[3].x + 24, lockDoorTriggers[3].y, 10, 32, lockedDoorScript,"lockedDoor"));
        triggers.add(new Trigger(lockDoorTriggers[4].x + 30, lockDoorTriggers[4].y, 10, 32, lockedDoorScript,"lockedDoor"));
        triggers.add(new Trigger(lockDoorTriggers[5].x + 24, lockDoorTriggers[5].y, 10, 32, lockedDoorScript,"lockedDoor"));
        triggers.add(new Trigger(lockDoorTriggers[6].x, lockDoorTriggers[6].y + 24, 32, 10, lockedDoorScript,"lockedDoor"));        

        // Map transfer triggers
        triggers.add(new Trigger(startMapTrigger.x, startMapTrigger.y + 20,128, 10, new TownToStartPathScript(), "townToStartMapPath"));
        triggers.add(new Trigger(storeDoorTrigger.x, storeDoorTrigger.y + 24, 32, 10, new TownToStoreScript(), "townToStoreDoor"));
        triggers.add(new Trigger(house1DoorTrigger.x, house1DoorTrigger.y + 24, 32, 10, new TownToH1Script(), "townToH1Door"));
        triggers.add(new Trigger(house2DoorTrigger.x, house2DoorTrigger.y + 24, 32, 10, new TownToH2Script(), "townToH2Door"));
        triggers.add(new Trigger(house3DoorTrigger.x, house3DoorTrigger.y + 24, 32, 10, new TownToH3Script(), "townToH3Door"));
        triggers.add(new Trigger(house3_1DoorTrigger.x, house3_1DoorTrigger.y + 24, 32, 10, new TownToH3_1Script(), "townToH3_1Door"));
        triggers.add(new Trigger(innDoorTrigger.x, innDoorTrigger.y + 24, 32, 10, new TownToInnScript(), "townToInnDoor"));
        triggers.add(new Trigger(manorDoorTrigger.x, manorDoorTrigger.y + 24, 32, 10, new TownToManorScript(), "townToManorDoor"));
        triggers.add(new Trigger(smithDoorTrigger.x, smithDoorTrigger.y + 24, 32, 10, new TownToSmithScript(), "townToSmithDoor"));
        triggers.add(new Trigger(townHallDoorTrigger.x, townHallDoorTrigger.y + 24, 64, 10, new TownToHallScript(), "townToHallDoor"));

        //Sign triggers
        Point townHallSign = getMapTile(76, 77).getLocation();
        Point directionSign = getMapTile(26, 107).getLocation();
        Point startAreaSign = getMapTile(30, 121).getLocation();
        triggers.add(new Trigger(townHallSign.x, townHallSign.y, 32, 32, new TownHallSign(),"townHallSign"));
        triggers.add(new Trigger(directionSign.x, directionSign.y, 32, 32, new DirectionSign(),"directionSign"));
        triggers.add(new Trigger(startAreaSign.x, startAreaSign.y, 32, 32, new StartAreaSign(),"startAreaSign"));

        //Old Men triggers 
        //triggers.add(new Trigger(getMapTile(40,102).getX(),getMapTile(40,102).getY(), 32, 32, new OldMan2Script(), "talkedToOldMan2"));
        //triggers.add(new Trigger(getMapTile(66,103).getX(),getMapTile(66,103).getY(), 32, 32, new OldMan3Script(), "talkedToOldMan3"));

        return triggers;
    }
}
