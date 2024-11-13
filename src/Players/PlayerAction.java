package Players;

public abstract class PlayerAction {
    private String name;
    private int value;
    private int lastAttack;
    private String description;

    public PlayerAction(){
        name = null;
        value = 0;
        description = null;
    }

    public PlayerAction(String n, int v, String d){
        name = n;
        value = v;
        description = d;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getValue(){
        return value;
    }

    public abstract int attack();

    public int getLastAttack(){
        return lastAttack;
    }

    public void setLastAttack(int la){
        lastAttack = la;
    }
}
