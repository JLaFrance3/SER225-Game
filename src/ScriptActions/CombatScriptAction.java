package ScriptActions;

import java.util.ArrayList;
import java.util.Arrays;

import Level.ScriptState;
import Level.TextboxItem;
import Players.Avatar;

public class CombatScriptAction extends ScriptAction {
    private ArrayList<TextboxItem> textboxItems;
    private boolean combat = true;
    int answer;

    public CombatScriptAction() {
        this.textboxItems = new ArrayList<TextboxItem>();
    }

    public CombatScriptAction(String combatAlertText) {
        this.textboxItems = new ArrayList<TextboxItem>();
        this.textboxItems.add(new TextboxItem(combatAlertText, new ArrayList<>(Arrays.asList( new String[] { "Fight", "Move", "Run"}))));
        this.textboxItems.add(new TextboxItem("How will you strike?", new ArrayList<>(Arrays.asList( new String[] { "Attack", "Spell", "Item"}))));
        this.textboxItems.add(new TextboxItem("this will be implemented soon :)"));
        this.textboxItems.add(new TextboxItem("this will be implemented soon ;)"));
    }

    public CombatScriptAction(String[] textItems) {
        this.textboxItems = new ArrayList<>();
        for (String text : textItems) {
            textboxItems.add(new TextboxItem(text));
        }
    }

    public CombatScriptAction(ArrayList<String> textItems) {
        this.textboxItems = new ArrayList<>();
        for (String text : textItems) {
            textboxItems.add(new TextboxItem(text));
        }
    }


    public void addText(String text) {
        this.textboxItems.add(new TextboxItem(text));
    }

    public void addText(TextboxItem text) {
        this.textboxItems.add(text);
    }

    public void addText(String text, String[] options) {
        this.textboxItems.add(new TextboxItem(text, new ArrayList<>(Arrays.asList(options))));
    }

    @Override
    public void setup() {
        TextboxItem[] textboxItemsArray = textboxItems.toArray(new TextboxItem[0]);
        this.map.getTextbox().addText(textboxItemsArray[0]);
        this.map.getTextbox().setIsActive(true);
    }

    @Override
    public ScriptState execute() {
        TextboxItem[] textboxItemsArray = textboxItems.toArray(new TextboxItem[0]);
        if (!this.map.getTextbox().isTextQueueEmpty()) {
            if (this.map.getTextbox().getSelectedOption() != -1){
                if (this.map.getTextbox().getSelectedOption() == 0){
                    this.map.getTextbox().addText(textboxItemsArray[1]);
                    this.map.getTextbox().addText(textboxItemsArray[0]); 
                }
                if (this.map.getTextbox().getSelectedOption() == 1){
                    this.map.getTextbox().addText(textboxItemsArray[2]); 
                }
                if (this.map.getTextbox().getSelectedOption() == 2){
                    this.map.getTextbox().addText(textboxItemsArray[3]); 
                }
                this.map.getTextbox().setSelectedOption(-1);
            }
            return ScriptState.RUNNING;
        }
        return ScriptState.COMPLETED;
    }

    @Override
    public void cleanup() {
        this.map.getTextbox().setIsActive(false);
    }
}
