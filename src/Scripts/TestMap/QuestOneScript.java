package Scripts.TestMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class QuestOneScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("While exploring, you've discovered a mysterious carving in this tree.");
            addText("It looks similar to the symbol that is rumored to represent the Uncanny!");
            addText("Underneath the symbol, there seems to be more carved into the \ntrunk, but it is hard to decipher.");
            addText("As you examine it further, it appears to be an image of a chest!");
            addText("Maybe this is a clue to where they are located?");
            addText("You also notice a key buried under some leaves on the ground next \nto the tree. You take it.");
            addText("Lets look around some more....");
        }});

        scriptActions.add(new ChangeFlagScriptAction("readQuestOne", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
