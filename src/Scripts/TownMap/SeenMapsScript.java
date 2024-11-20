package Scripts.TownMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;
import Utils.*;
import ScriptActions.PlayerWalkScriptAction;
import Level.Player;

// this script sends the player to one of the houses (house 3) 
public class SeenMapsScript extends Script{
    protected Player player;
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
         scriptActions.add(new LockPlayerScriptAction());
        
        scriptActions.add(new ConditionalScriptAction() {{
            
            // if the player has not talked to Old Man 3
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToOldMan3", false));

                addScriptAction(new TextboxScriptAction () {{
                    addText("These maps look pretty old...");
                }});

                addScriptAction(new PlayerWalkScriptAction(Direction.DOWN, 20, 1));

            }});
            
            // if the player has talked to Old Man 3
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("talkedToOldMan3", true));

                addScriptAction(new TextboxScriptAction () {{
                    addText("These maps are very detailed. It looks like this \ntown has been here for many years.");
                    addText("It seems that there is a label on town house number \nnumber 0112, around the corner.");
                    addText("The label says \"UNC4NN7\", as if it is code of some kind. It could be an \nimportant clue!");
                }});
                
                addScriptAction(new SetMainQuest("seenMaps"));
                addScriptAction(new ChangeFlagScriptAction("seenMaps", true));
            }});

        }});

         scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
