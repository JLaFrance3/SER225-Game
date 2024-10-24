package ScriptActions;

import java.util.ArrayList;

import Level.Map;
import Level.MapEntity;
import Level.Player;
import Level.ScriptState;

public class LoopScriptAction extends ScriptAction {
    
    protected ArrayList<LoopScriptActionGroup> LoopScriptActionGroups;
    protected int currentScriptActionGroupIndex;
    protected int currentScriptActionIndex;

    public LoopScriptAction() {
        this.LoopScriptActionGroups = new ArrayList<>();
    }

    public LoopScriptAction(ArrayList<LoopScriptActionGroup> LoopScriptActionGroups) {
        this.LoopScriptActionGroups = LoopScriptActionGroups;
    }

    public void addLoopScriptActionGroup(LoopScriptActionGroup LoopScriptActionGroup) {
        LoopScriptActionGroups.add(LoopScriptActionGroup);
    }

    public ArrayList<LoopScriptActionGroup> getLoopScriptActionGroups() {
        return LoopScriptActionGroups;
    }

    @Override
    public void setup() {
        boolean groupRequirementMet = false;
        for (int i = 0; i < LoopScriptActionGroups.size(); i++) {
            LoopScriptActionGroup LoopScriptActionGroup = LoopScriptActionGroups.get(i);
            
            if (areRequirementsMet(LoopScriptActionGroup)) {
                currentScriptActionGroupIndex = i;
                currentScriptActionIndex = 0;
                LoopScriptActionGroups.get(currentScriptActionGroupIndex).getScriptActions().get(currentScriptActionIndex).setup();
                groupRequirementMet = true;
                break;
            }
        }
        if (!groupRequirementMet) {
            // this prevents a crash from occurring if no group requirements have been met
            // it just adds a fake group with a fake script action that does nothing
            // while there are other ways of fixing this, the other ways result in the script execution code being less efficient, which is not ideal for a game that needs to run as fast as possible
            LoopScriptActionGroups.add(doNothingActionGroup);
            currentScriptActionGroupIndex = LoopScriptActionGroups.size() - 1;
            currentScriptActionIndex = 0;
        }
    }

    private static LoopScriptActionGroup doNothingActionGroup = new LoopScriptActionGroup() {{
        addScriptAction(new DoNothingScriptAction());
    }};

    protected boolean areRequirementsMet(LoopScriptActionGroup LoopScriptActionGroup) {
        ArrayList<Boolean> metRequirementStatuses = new ArrayList<>();
        for (Requirement requirement : LoopScriptActionGroup.getRequirements()) {
            boolean requirementStatus = false;
            if (requirement instanceof FlagRequirement) {
                requirementStatus = isFlagRequirementMet((FlagRequirement)requirement);
            }
            else if (requirement instanceof CustomRequirement) {
                requirementStatus = ((CustomRequirement)requirement).isRequirementMet();
            }
            if (!requirementStatus && LoopScriptActionGroup.flagStrategy == FlagStrategy.AND) {
                return false;
            }
            else if (requirementStatus && LoopScriptActionGroup.flagStrategy == FlagStrategy.OR) {
                return true;
            }
            else {
                metRequirementStatuses.add(requirementStatus);
            }
        }
        // if strategy is AND, all requirements had to have been met up to this point to avoid the short circuit, so we know its true
        if (LoopScriptActionGroup.getFlagStrategy() == FlagStrategy.AND) {
            return true;
        }
        // if strategy is OR, no requirements had to have been met up to this point to avoid the short circuit, so we know its false
        else {
            return false;
        }

    }

    protected boolean isFlagRequirementMet(FlagRequirement flagRequirement) {
        String flagName = flagRequirement.getFlagName();
        boolean currentFlagStatus = this.map.getFlagManager().isFlagSet(flagName);
        return flagRequirement.flagValue == currentFlagStatus;
    }

    @Override
    public ScriptState execute() {
        // Runs an execute cycle of the Script
        ArrayList<ScriptAction> scriptActions = LoopScriptActionGroups.get(currentScriptActionGroupIndex).getScriptActions();
        ScriptAction currentScriptAction = scriptActions.get(currentScriptActionIndex);
        ScriptState scriptState = currentScriptAction.execute();
        if (scriptState == ScriptState.COMPLETED) {
            currentScriptAction.cleanup();
            currentScriptActionIndex++;

            if (currentScriptActionIndex < scriptActions.size()) {
                scriptActions.get(currentScriptActionIndex).setup();
                return ScriptState.RUNNING;
            }
            else {
                if (areRequirementsMet(LoopScriptActionGroups.get(currentScriptActionGroupIndex))){
                    currentScriptActionIndex = 0;
                    currentScriptAction = scriptActions.get(currentScriptActionIndex);
                    return ScriptState.RUNNING;
                }else {
                    return ScriptState.COMPLETED;
                }
            }
        }
        return ScriptState.RUNNING;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setEntity(MapEntity entity) {
        this.entity = entity;
    }
}
