package Maps;

import java.util.ArrayList;

import EnhancedMapTiles.Chest;
import EnhancedMapTiles.Gate;
import EnhancedMapTiles.KeyItem;
import EnhancedMapTiles.Spells.Thunder;
import EnhancedMapTiles.Swords.GreatSword;
import Level.*;
import NPCs.FarmerGirl;
import NPCs.Knight;
import NPCs.OldMan1;
import NPCs.TestDummy;
import NPCs.Monsters.Eye;
import Scripts.IndoorMaps.H3_1ToTownDoorScript;
import Scripts.TestMap.Chest2Script;
import Scripts.TestMap.CombatScript;
import Scripts.TestMap.DeadKnightScript;
import Scripts.TestMap.FarmerGirlScript;
import Scripts.TestMap.ForestToTownScript;
import Scripts.TestMap.GreatSwordScript;
import Scripts.TestMap.Key2Script;
import Scripts.TestMap.OldMan1Script;
import Scripts.TestMap.ThunderSpellScript;
import Scripts.TestMap.TownToForestScript;
import Tilesets.ForestTileset;



public class ForestMap extends Map {
    
    public ForestMap() {
        super("Forest.txt", new ForestTileset());
        this.playerStartPosition = getMapTile(3, 5).getLocation();
        
    }

     //array to load npc's
    @Override
    public ArrayList<NPC> loadNPCs() {
        //this holds the npc's (?)
        ArrayList<NPC> npcs = new ArrayList<>();
 

      Eye eye1 = new Eye(5, getMapTile(23,5).getLocation().subtractX(20));
      eye1.setInteractScript(new CombatScript("The Final Boss... I HAVE TO KILL You!!", 1, 1, "LASER!!. The EYEBALL shoots a laser at you", 200, "bossDead"));
      eye1.setExistenceFlag("bossDead");
      npcs.add(eye1);


        Knight knight1 = new Knight(69, getMapTile(7,3).getLocation().subtractX(20));
        knight1.setInteractScript(new DeadKnightScript());
        npcs.add(knight1);



        return npcs;



    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
    

        return enhancedMapTiles;
    }

     @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();


        triggers.add(new Trigger(getMapTile(0,3).getX(),getMapTile(0,3).getY(), width, height, new ForestToTownScript(), "forestToTown"));


        return triggers;
    }
}
