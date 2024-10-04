package Scripts.TestMap;
import java.util.ArrayList;
import Level.Script;
import ScriptActions.*;

public class backgroundScript extends Script{
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new TextboxScriptAction() {{
            addText("In the prosperous and powerful Kingdom of Solarnelle the ruler, King König \npossesses the most powerful artifact known to every villager, amongst the \nSeas of the Unknown, and every kingdom, \"The Foreseeable Eye\". ");
            addText("This ancient enchanted eye grants users the ability to see the fates of those who \ngaze upon its glorious and rich surface!");
            addText("But on one terrible evening,\"The Foreseeable Eye\" was stolen by the rebellious \ngroup, \"The Uncanny\", from the chamber of sacred artifacts, guarded by the \nKingsGuard and the strongest Knights in the Kingdom.");
            addText("Leaving the Kingdom of Solarnelle in misery and fear!! The Uncanny rebellion \nthreatens the lives of the peaceful and powerful in your very kingdom. ");
            addText("King Encoutus has asked you and your fellow adventurers to recover \"The \nForeseeable Eye\" and return the glory to your great Kingdom of Kingdoms! \nAs you begin your journey, be prepared for the unbearable truth!!");
            addText("Your Decisions are Ultimate to the Kingdom's livelihood! \nAre you ready to face what lies ahead?");
        }});

        scriptActions.add(new ChangeFlagScriptAction("readBackground", true));

        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;
    }
}
