package Maps;

import java.util.ArrayList;

import Engine.ImageLoader;
import EnhancedMapTiles.Chest;
import EnhancedMapTiles.Gate;
import EnhancedMapTiles.Gold;
import EnhancedMapTiles.InventoryItem;
import EnhancedMapTiles.InventoryItem.EQUIP_TYPE;
import EnhancedMapTiles.KeyItem;
import EnhancedMapTiles.Armor.DemoLeatherarmour;
import EnhancedMapTiles.Armor.DemoMagicarmour;
import EnhancedMapTiles.Armor.DemoPlatearmor;
import EnhancedMapTiles.Spells.Fire;
import EnhancedMapTiles.Spells.Thunder;
import EnhancedMapTiles.Swords.GreatSword;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Trigger;
import NPCs.Bug;
import NPCs.Dog;
import NPCs.GenericNPC;
import NPCs.Goat;
import NPCs.Human;
import NPCs.OldMan1;
import NPCs.OldMan2;
import NPCs.OldMan3;
import NPCs.TestDummy;
import NPCs.Monsters.BadFlower;
import NPCs.Monsters.Bat;
import NPCs.Monsters.Goblin;
import NPCs.Monsters.GoldDragon;
import NPCs.Monsters.Pumpkin;
import NPCs.Monsters.Skeleton;
import Players.Avatar;
import Players.PlayerAction;
import Level.Script;
import Scripts.LockedDoorScript;
import Scripts.SimpleTextScript;
import Scripts.TestMap.CombatScript;
import Scripts.TestMap.FireSpellScript;
import Scripts.TestMap.GateScript;
import Scripts.TestMap.GoldScript;
import Scripts.TestMap.Chest2Script;
import Scripts.TestMap.GreatSwordScript;
import Scripts.TestMap.Key2Script;
import Scripts.TestMap.OldMan1Script;
import Scripts.TestMap.StartToDungeonScript;
import Scripts.TestMap.ThunderSpellScript;
import Scripts.TestMap.TownToForestScript;
import Scripts.TownMap.*;
import Tilesets.TownTileset;
import Utils.Direction;
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
        
        OldMan2 oldman2 = new OldMan2(41, getMapTile(50,82).getLocation().subtractX(10));
        oldman2.setInteractScript(new OldMan2Script());
        npcs.add(oldman2);

        OldMan3 oldman3 = new OldMan3(42, getMapTile(78,59).getLocation().subtractX(20));
        oldman3.setCurrentAnimationName("STAND_RIGHT");
        oldman3.setInteractScript(new OldMan3Script());
        npcs.add(oldman3);

        Human fisherGuy = new Human(43, getMapTile(31, 122).getLocation(), new SpriteSheet(ImageLoader.load("/NPCSprites/Fisherguy.png", true), 64, 64));
        fisherGuy.setInteractScript(new FisherGuyScript());
        npcs.add(fisherGuy);

        Human suspiciousGuy = new Human(44, getMapTile(34, 82).getLocation(), new SpriteSheet(ImageLoader.load("/NPCSprites/MysteriousGuy.png", true), 64, 64));
        suspiciousGuy.setInteractScript(new SimpleTextScript("Go away"));
        suspiciousGuy.setExistenceFlag("foughtSusCharacter");
        npcs.add(suspiciousGuy);

        Human dogOwner = new Human(45, getMapTile(98, 73).getLocation(), new SpriteSheet(ImageLoader.load("/NPCSprites/DogOwner.png", true), 64, 64), Direction.LEFT);
        dogOwner.setInteractScript(new DogOwnerScript());
        dogOwner.setQuestIndicator(true);
        npcs.add(dogOwner);

        Dog dog = new Dog(46, getMapTile(100, 74).getLocation());
        dog.setInteractScript(new SickDogScript(dogOwner));
        npcs.add(dog);

        Goat goat1 = new Goat(60, getMapTile(22, 70).getLocation(), Direction.LEFT);
        npcs.add(goat1);

        Goat goat2 = new Goat(60, getMapTile(24, 66).getLocation(), Direction.DOWN);
        npcs.add(goat2);

        Goblin goblin1 = new Goblin(100, getMapTile(79, 112).getLocation().subtractX(20));
        goblin1.setInteractScript(new CombatScript("Uh oh, this goblin is evil as hell",4,8,"the goblin slashes at you with its claws",15,"goblin1Flag","piercing"));
        goblin1.setExistenceFlag("goblin1Flag");
        npcs.add(goblin1);

        Goblin goblin2 = new Goblin(101, getMapTile(86, 109).getLocation().subtractX(20));
        goblin2.setInteractScript(new CombatScript("Uh oh, this goblin is evil as hell",4,8,"the goblin slashes at you with its claws",15,"goblin2Flag","piercing"));
        goblin2.setExistenceFlag("goblin2Flag");
        npcs.add(goblin2);

        Goblin goblin3 = new Goblin(102, getMapTile(71, 102).getLocation().subtractX(20));
        goblin3.setInteractScript(new CombatScript("Uh oh, this goblin is evil as hell",4,8,"the goblin slashes at you with its claws",15,"goblin3Flag","piercing"));
        goblin3.setExistenceFlag("goblin3Flag");
        npcs.add(goblin3);

        Goblin goblin4 = new Goblin(103, getMapTile(66, 112).getLocation().subtractX(20));
        goblin4.setInteractScript(new CombatScript("Uh oh, this goblin is evil as hell",4,8,"the goblin slashes at you with its claws",50,"goblin4Flag","piercing"){
            @Override
            public String getDrop(){
                Avatar.itemAction.addAction(new PlayerAction("M Sword", 8, "You hack at your enemy with a magic sword", "magic"){
                    @Override
                    public double attack() {
                        // TODO Auto-generated method stub
                        return 8;
                    }
                });
                player.getInventoryArrayList().add((new InventoryItem(ImageLoader.loadSubImage("items.png", 135, 100, 37, 38), 
                "Magic Shortsword", 10, "A shortsword made with fire magic",
                "Equipment/weapon/sword/longsword/", EQUIP_TYPE.SWORD)));
                return "The goblin drops a Magic Shortsword";
            }
        });
        goblin4.setExistenceFlag("goblin4Flag");
        npcs.add(goblin4);
        
        //GoldDragon goldDragon1 = new GoldDragon(5, getMapTile(5,12).getLocation().subtractX(20));
        //npcs.add(goldDragon1);

        Skeleton skeleton1 = new Skeleton(10, getMapTile(2, 110).getLocation().subtractX(20));
        skeleton1.setInteractScript(new CombatScript("Uh oh, this skeleton is evil as hell",6,13,"the skeleton hacks at you with his shortsword",50,"skeleton1Flag", "magic"));
        skeleton1.setExistenceFlag("skeleton1Flag");
        npcs.add(skeleton1);

        Skeleton skeleton2 = new Skeleton(11, getMapTile(11, 115).getLocation().subtractX(20));
        skeleton2.setInteractScript(new CombatScript("Uh oh, this skeleton is evil as hell",6,13,"the skeleton hacks at you with his shortsword",50,"skeleton2Flag", "magic"));
        skeleton2.setExistenceFlag("skeleton2Flag");
        npcs.add(skeleton2);

        Skeleton skeleton3 = new Skeleton(12, getMapTile(32, 107).getLocation().subtractX(20));
        skeleton3.setInteractScript(new CombatScript("Uh oh, this skeleton is evil as hell",6,13,"the skeleton hacks at you with his shortsword",50,"skeleton3Flag", "magic"){
            @Override
            public String getDrop(){
                Avatar.intelligence++;
                return "The skeleton drops a piece of paper?...\n hmm this appears to be a fragment of the Scroll of Thunder";
            }
        });
        skeleton3.setExistenceFlag("skeleton3Flag");
        npcs.add(skeleton3);

        Skeleton skeleton4 = new Skeleton(13, getMapTile(30, 111).getLocation().subtractX(20));
        skeleton4.setInteractScript(new CombatScript("Uh oh, this skeleton is evil as hell",6,13,"the skeleton hacks at you with his shortsword",50,"skeleton4Flag", "magic"));
        skeleton4.setExistenceFlag("skeleton4Flag");
        npcs.add(skeleton4);

       Bat bat1 = new Bat(14, getMapTile(17,94).getLocation().subtractX(20));
       bat1.setInteractScript(new CombatScript("Uh oh, this bat is evil as hell",8,22,"the bat chomps into you",50,"bat1Flag","bludgeoning"));
       bat1.setExistenceFlag("bat1Flag");
       npcs.add(bat1);

       Bat bat2 = new Bat(15, getMapTile(15,117).getLocation().subtractX(20));
       bat2.setInteractScript(new CombatScript("Uh oh, this bat is evil as hell",8,22,"the bat chomps into you",50,"bat2Flag","bludgeoning"));
       bat2.setExistenceFlag("bat2Flag");
       npcs.add(bat2);

       Bat bat3 = new Bat(16, getMapTile(25,114).getLocation().subtractX(20));
       bat3.setInteractScript(new CombatScript("Uh oh, this bat is evil as hell",8,22,"the bat chomps into you",50,"bat3Flag","bludgeoning"));
       bat3.setExistenceFlag("bat3Flag");
       npcs.add(bat3);

        Pumpkin pumpkin1 = new Pumpkin(5, getMapTile(21,108).getLocation().subtractX(20));
        pumpkin1.setInteractScript(new CombatScript("Uh oh, this Pumpkin is evil as hell",15,57,"the evil pumpkin rolls into you", 150,"pumkin1Flag","bludgeoning"));
        pumpkin1.setExistenceFlag("pumpkin1Flag");
        npcs.add(pumpkin1);

        Pumpkin pumpkin2 = new Pumpkin(5, getMapTile(2,117).getLocation().subtractX(20));
        pumpkin2.setInteractScript(new CombatScript("Uh oh, this Pumpkin is evil as hell",15,57,"the evil pumpkin rolls into you", 150,"pumpkin2Flag","bludgeoning"));
        pumpkin1.setExistenceFlag("pumpkin2Flag");
        npcs.add(pumpkin2);

        Pumpkin pumpkin3 = new Pumpkin(5, getMapTile(36,114).getLocation().subtractX(20));
        pumpkin3.setInteractScript(new CombatScript("Uh oh, this Pumpkin is evil as hell",15,57,"the evil pumpkin rolls into you", 150,"pumpkin3Flag","bludgeoning"));
        pumpkin3.setExistenceFlag("pumpkin3Flag");
        npcs.add(pumpkin3);

        BadFlower flower1 = new BadFlower(20, getMapTile(78,116).getLocation().subtractX(20));
        flower1.setInteractScript(new CombatScript("Uh oh, this flower is evil as hell",3,10,"tendrils lash at you and thorns cut your skin",10,"flower1Flag","slashing"));
        flower1.setExistenceFlag("flower1Flag");
        npcs.add(flower1);

        BadFlower flower2 = new BadFlower(21, getMapTile(74,106).getLocation().subtractX(20));
        flower2.setInteractScript(new CombatScript("Uh oh, this flower is evil as hell",3,10,"tendrils lash at you and thorns cut your skin",10,"flower2Flag","slashing"));
        flower2.setExistenceFlag("flower2Flag");
        npcs.add(flower2);

        BadFlower flower3 = new BadFlower(22, getMapTile(76,109).getLocation().subtractX(20));
        flower3.setInteractScript(new CombatScript("Uh oh, this flower is evil as hell",3,10,"tendrils lash at you and thorns cut your skin",10,"flower3Flag","slashing"));
        flower3.setExistenceFlag("flower3Flag");
        npcs.add(flower3);

        GenericNPC kid = new GenericNPC(10, getMapTile(98,56).getLocation(), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_2.png", true), 64, 64),
            Direction.RIGHT, 350, true);
        kid.setSpeed(4);
        kid.toggleDelay();
        npcs.add(kid);
        GenericNPC citizen1 = new GenericNPC(11, getMapTile(92,48).getLocation(), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_3.png", true), 64, 64),
            Direction.LEFT, 2000, true);
        npcs.add(citizen1);
        GenericNPC citizen2 = new GenericNPC(12, getMapTile(13,78).getLocation(), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_4.png", true), 64, 64),
            Direction.RIGHT, 2500, true);
        npcs.add(citizen2);
        GenericNPC citizen3 = new GenericNPC(13, getMapTile(16,60).getLocation(), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_6.png", true), 64, 64),
            Direction.LEFT, 300, true);
        npcs.add(citizen3);
        GenericNPC citizen4 = new GenericNPC(14, getMapTile(61,92).getLocation(), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_7.png", true), 64, 64),
            Direction.UP, 1400, false);
        npcs.add(citizen4);
        GenericNPC citizen5 = new GenericNPC(15, getMapTile(46,75).getLocation().subtractY(24).subtractX(20), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_8.png", true), 64, 64), Direction.DOWN);
        npcs.add(citizen5);
        GenericNPC citizen6 = new GenericNPC(16, getMapTile(70,44).getLocation().subtractY(20), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_9.png", true), 64, 64), Direction.DOWN);
        npcs.add(citizen6);
        GenericNPC citizen7 = new GenericNPC(17, getMapTile(8,78).getLocation().subtractX(10), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_10.png", true), 64, 64), Direction.DOWN);
        npcs.add(citizen7);
        GenericNPC citizen8 = new GenericNPC(18, getMapTile(45,69).getLocation().subtractY(20), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_11.png", true), 64, 64), Direction.DOWN);
        npcs.add(citizen8);
        GenericNPC citizen9 = new GenericNPC(19, getMapTile(42,80).getLocation().subtractY(10), 
            new SpriteSheet(ImageLoader.load("NPCSprites/NPC_12.png", true), 64, 64),
            Direction.RIGHT, 1600, true);
        npcs.add(citizen9);

         
        return npcs;
    }

    
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        Gold gold1 = new Gold(getMapTile(33,59).getLocation());
        gold1.setExistenceFlag("gotGold1");
        gold1.setInteractScript(new GoldScript());
       enhancedMapTiles.add(gold1);

        Gate gate = new Gate(getMapTile(107, 110).getLocation());
        // gate.setInteractScript(new TownToForestScript());
        enhancedMapTiles.add(gate);

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

       // Point tileLocation1 = getMapTile(119, 114).getLocation();//getting location of certain tile needed for gate


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
        triggers.add(new Trigger(getMapTile(106,114).getX(),getMapTile(106,114).getY(), width, height, new TownToForestScript(), "townToForest"));

        triggers.add(new Trigger(getMapTile(106,114).getX(),getMapTile(106,114).getY(), width, height, new TownToForestScript(), "gateInteract"));

        

        //Sign triggers
        Point townHallSign = getMapTile(76, 77).getLocation();
        Point directionSign = getMapTile(26, 107).getLocation();
        Point startAreaSign = getMapTile(30, 121).getLocation();
        Point house0112Sign = getMapTile(108,69).getLocation();
        triggers.add(new Trigger(townHallSign.x, townHallSign.y, 32, 32, new TownHallSign(),"townHallSign"));
        triggers.add(new Trigger(directionSign.x, directionSign.y, 32, 32, new DirectionSign(),"directionSign"));
        triggers.add(new Trigger(startAreaSign.x, startAreaSign.y, 32, 32, new StartAreaSign(),"startAreaSign"));
        triggers.add(new Trigger(house0112Sign.x, house0112Sign.y, 32, 32, new House0112Sign(),"house0112Sign"));

       

        //Other Triggers
       triggers.add(new Trigger(getMapTile(89,100).getX(),getMapTile(89,100).getY(), 20, 900, new EnterForestScript(), "foughtEnemiesToEnterForest"));

        return triggers;
    }
}
