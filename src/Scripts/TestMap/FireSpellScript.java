package Scripts.TestMap;

import java.util.ArrayList;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import Level.*;
import Players.Avatar;
import ScriptActions.*;

// script for talking to dino npc
// checkout the documentation website for a detailed guide on how this script works
public class FireSpellScript extends Script {

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new TextboxScriptAction("Wow! A Fire Spell!"));
        
        

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("gotFire", false));
                

              //  addScriptAction(new WaitScriptAction(70));
                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {
                        if (Avatar.class.equals("Wizard") || Avatar.class.equals("Ranger")){
                            Avatar.spellAction.getAction(0).setValue(Avatar.spellAction.getAction(0).getValue() + 1);   
                        }
                        return ScriptState.COMPLETED;
                    }
                });
                addScriptAction(new ChangeFlagScriptAction("gotFire", true));
            }});
        }});

        scriptActions.add(new AddInventory(new InventoryItem(ImageLoader.loadSubImage("items.png", 239, 443, 33, 33), "Fire Scroll", 12, "A powerful that increases Fire damage")));

        


        scriptActions.add(new UnlockPlayerScriptAction());
        return scriptActions;
    }
}
