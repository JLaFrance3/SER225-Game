package Scripts.TownMap;

import java.util.ArrayList;
import Engine.ImageLoader;
import EnhancedMapTiles.InventoryItem;
import EnhancedMapTiles.InventoryItem.EQUIP_TYPE;
import Level.NPC;
import Level.Player;
import Level.Script;
import NPCs.Dog;
import NPCs.Human;
import ScriptActions.*;
import Utils.Direction;
import Utils.Visibility;

// Script used for fisher guy side quest
public class SickDogScript extends Script{
    private NPC npc;
    private Human dogOwner;

    public SickDogScript(Human dogOwner) {
        super();
        this.dogOwner = dogOwner;
    }

    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        npc = (NPC)getEntity();

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());
        scriptActions.add(new NPCLockScriptAction());
        
        scriptActions.add(new ConditionalScriptAction() {{
            // Give dog health potion dialogue
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("foundHealthPotion", true));
                addRequirement(new FlagRequirement("healDog", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("Woof woof!");
                }});

                addScriptAction(new ChangeFlagScriptAction("healDog", true));
                addScriptAction(new ToggleQuestIcon(npc));
                addScriptAction(new ToggleQuestIcon(dogOwner));
                addScriptAction(new RemoveSideQuestNote("Find a health potion for the cute dog"));
                addScriptAction(new AddSideQuestNote("Talk to the dog's owner"));
                addScriptAction(new NPCStandScriptAction(Direction.LEFT));
            }});
            
            // Response after quest complete
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("sickDogComplete", true));

                addScriptAction(new TextboxScriptAction() {{
                    addText("Woof!");
                }});
            }});
        }});
        
        scriptActions.add(new NPCUnlockScriptAction());
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
