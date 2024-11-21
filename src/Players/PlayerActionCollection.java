package Players;
import java.util.ArrayList;


public class PlayerActionCollection {
    private ArrayList<PlayerAction> actions;

    public PlayerActionCollection(ArrayList<PlayerAction> actions){
        this.actions = actions;
    }

    public PlayerActionCollection(){
        this.actions = new ArrayList<>();
    }

    public void addAction(PlayerAction action){
        actions.add(action);
    }

    public String[] getActions(){
        String[] temp = new String[actions.size()];
        for (int i = 0; i < actions.size(); i++) {
            temp[i] = actions.get(i).getName();
        }
        return temp;
    }

    public PlayerAction getAction(int x){
        return actions.get(x);
    }

    public PlayerAction setAction(int x, PlayerAction action){
        return actions.set(x,action);
    }
}
