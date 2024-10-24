package Scripts.TestMap;
import java.util.ArrayList;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import GameObject.Frame;
import Level.MapTile;
import Level.Script;
import Level.ScriptState;
import Level.TileType;
import ScriptActions.*;
import Utils.Direction;
import Utils.Point;
import Utils.Visibility;

public class QuestOneChestScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        // scriptActions.add(new LockPlayerScriptAction());
        
        scriptActions.add(new ConditionalScriptAction() {{
            // if the player has visited the tree with the carving, the text will inform the player of what they found in the chest
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("readQuestOne", true));
                addRequirement(new FlagRequirement("readQuestOneChest", false));

                addScriptAction(new ChangeFlagScriptAction("readQuestOneChest", true));
                addScriptAction(new TextboxScriptAction () {{
                    addText("You try to use the key you obtained from the tree to unlock the chest.");
                    addText("It opens! Inside, you find a map with the same \"Uncanny\" symbol at the top.");
                    addText("You have completed your first quest! Now let's examine this map further...");
                }});
            }});

            // if the player has not visited any part of the quest yet, the text will explain that the chest is locked but could be helpful
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("readQuestOne", false));
                addRequirement(new FlagRequirement("readQuestOneChest", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("This chest appears to be locked. Maybe it will be useful later...");
                }});
            }});
        }});

        // scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}