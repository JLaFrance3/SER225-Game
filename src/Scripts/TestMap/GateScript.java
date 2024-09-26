package Scripts.TestMap;

import java.util.ArrayList;

import Game.GameState;
import Level.Script;
import ScriptActions.*;


public class GateScript extends Script {
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("A large Gate?");
            addText("Wonder where it leads?");
            
        }});
        scriptActions.add(new ChangeFlagScriptAction("gateInteract", true));
       
       // screenCoordinator.setGameState(GameState.DUNGEON);
       
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }   
}

  

   

