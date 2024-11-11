package Players;

public abstract class PlayerAction {
    private String name;
    private int value;
    private int lastAttack;

    public PlayerAction(){
        name = null;
        value = 0;
    }

    public PlayerAction(String n, int v){
        name = n;
        value = v;
    }

    public String getName(){
        return name;
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
