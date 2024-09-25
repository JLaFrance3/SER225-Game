package MapEditor;

import Level.Map;
import Maps.TestDungeon;
import Maps.TestMap;
import Maps.TitleScreenMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("TestDungeon");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "TestDungeon":
                return new TestDungeon();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
