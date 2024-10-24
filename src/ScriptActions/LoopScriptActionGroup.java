package ScriptActions;

import java.util.ArrayList;

public class LoopScriptActionGroup {
    protected ArrayList<ScriptAction> scriptActions;
    protected ArrayList<Requirement> requirements;
    protected FlagStrategy flagStrategy;

    public LoopScriptActionGroup() {
        scriptActions = new ArrayList<ScriptAction>();
        requirements = new ArrayList<Requirement>();
        this.flagStrategy = FlagStrategy.AND;
        addScriptAction(new DoNothingScriptAction());
        addScriptAction(new DoNothingScriptAction());
    }

    public LoopScriptActionGroup(FlagStrategy flagStrategy) {
        scriptActions = new ArrayList<ScriptAction>();
        requirements = new ArrayList<Requirement>();
        this.flagStrategy = flagStrategy;
        addScriptAction(new DoNothingScriptAction());
        addScriptAction(new DoNothingScriptAction());
    }
    
    public ArrayList<ScriptAction> getScriptActions() {
        return scriptActions;
    }

    public void addScriptAction(ScriptAction scriptAction) {
        scriptActions.add(scriptAction);
    }

    public void addRequirement(Requirement requirement) {
        requirements.add(requirement);
    }

    public ArrayList<Requirement> getRequirements() {
        return requirements;
    }

    public FlagStrategy getFlagStrategy() {
        return flagStrategy;
    }

    public void setFlagStrategy(FlagStrategy flagStrategy) {
        this.flagStrategy = flagStrategy;
    }
}
