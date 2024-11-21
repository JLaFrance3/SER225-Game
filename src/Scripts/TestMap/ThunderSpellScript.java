package Scripts.TestMap;

import java.util.ArrayList;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import Level.*;
import Players.Avatar;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class ThunderSpellScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction("Wow! A Thunder Scroll!, this will make my magic more potent"));
        
        

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasInteractedThunder", false));
                

              //  addScriptAction(new WaitScriptAction(70));
                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        Avatar.intelligence++;
                        return ScriptState.COMPLETED;
                    }
                });

                addScriptAction(new ChangeFlagScriptAction("hasInteractedThunder", true));
            }});
        }});

        scriptActions.add(new AddInventory(new InventoryItem(ImageLoader.loadSubImage("items.png", 68, 439, 35, 35), "Thunder Scroll", 12, "A powerful scroll that uses the aspect of lightning to enhance all of a users spells")));

        


        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
