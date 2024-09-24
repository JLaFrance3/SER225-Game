// package Scripts.TestMap;

// import java.util.ArrayList;

// import Level.Script;
// import ScriptActions.*;

// public class NewMap extends Script {
//     private String[] textItems;

//     public NewMap(String text) {
//         this.textItems = new String[] { text };
//     }

//     public NewMap(String[] text) {
//         this.textItems = text;
//     }
    
//     @Override
//     public ArrayList<ScriptAction> loadScriptActions() {
//         ArrayList<ScriptAction> scriptActions = new ArrayList<>();
//         scriptActions.add(new LockPlayerScriptAction());
//         scriptActions.add(new TextboxScriptAction(textItems));
//         scriptActions.add(new UnlockPlayerScriptAction());
//         return scriptActions;
//     }
//     }
// }
// ```