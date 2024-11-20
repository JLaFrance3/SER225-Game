package Scripts.TownMap;

import java.util.ArrayList;

import Level.Script;
import ScriptActions.*;
import Utils.Direction;

public class House0112Sign extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new TextboxScriptAction() {{
            addText("House Number 0112");
        }});

        scriptActions.add(new PlayerWalkScriptAction(Direction.DOWN, 10, 1));

        return scriptActions;
    }
}
