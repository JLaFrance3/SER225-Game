package MapEditor;

import Level.Map;
import Maps.*;


import java.util.ArrayList;

import javax.management.RuntimeErrorException;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("DungeonMap");
            add("TownMap");
            add("Inn");
            add("Smith");
            add("GeneralStore");
            add("TownHall");
            add("Manor");
            add("House1");
            add("House2");
            add("House3");
            add("Forest");
        }};
    }

    public static Map getMapByName(String mapName) {
        Map map;

        switch (mapName) {
            case "TestMap" -> map = new TestMap();
            case "TitleScreen" -> map = new TitleScreenMap();
            case "DungeonMap" -> map = new DungeonMap();
            case "TownMap" -> map = new TownMap();
            case "Inn" -> map = new InnMap();
            case "Smith" -> map = new SmithMap();
            case "GeneralStore" -> map = new GeneralStoreMap();
            case "TownHall" -> map = new TownHallMap();
            case "Manor" -> map = new ManorMap();
            case "House1" -> map = new House1Map();
            case "House2" -> map = new House2Map();
            case "House3" -> map = new House3Map();
            case "Forest" -> map = new ForestMap();
            default -> throw new RuntimeException("Unrecognized map name");
        }

        return map;
    }

    
}
