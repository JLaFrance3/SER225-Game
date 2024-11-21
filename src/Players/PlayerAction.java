package Players;

public abstract class PlayerAction {
    private String name;
    private int value;
    private int lastAttack;
    private String description;
    private String type = "";

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

    public PlayerAction(String n, int v, String d, String t){
        name = n;
        value = v;
        description = d;
        type = t;
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

    public abstract double attack();

    public int getLastAttack(){
        return lastAttack;
    }

    public void setLastAttack(int la){
        lastAttack = la;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type.toLowerCase();
    }

    public void setValue(int v){
        value = v;
    }
}
