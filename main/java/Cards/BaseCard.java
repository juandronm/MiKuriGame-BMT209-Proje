package Cards;


import Functionality.CardFunctionality;

public class BaseCard extends CardFunctionality {
    private final int id;
    private final String name;
    private int hp;
    private final String type;
    private final int attack;


    public BaseCard(int id, String name, int hp, String type, int attack) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.type = type;
        this.attack = attack;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public String getType(){
        return type;
    }

    @Override
    public int getAttacks(){ return attack; }

    @Override
    public String toString() {
        return "Card [name=" + name + ", hp=" + hp + ", type=" + type + ", Attacks =" + attack +"]";
    }
}
