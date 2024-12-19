package InGameScreen;

import java.util.List;

public class Card {
    private static int uniqueIdCounter = 1;
    private final int id;
    private String name;
    private int hp;
    private String type;
    private int attack;

    public Card(String name, int hp, String type, int attackPower) {
        this.id = uniqueIdCounter++;
        this.name = name;
        this.hp =  hp;
        this.type = type;
        this.attack = attackPower;
    }

    public Card(String[] characterDatum) {
        this.id = uniqueIdCounter++;
        this.name = characterDatum[0];
        this.hp = Integer.parseInt(characterDatum[1]) ;
        this.type = characterDatum[2] ;
        this.attack = Integer.parseInt(characterDatum[3]);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getType(){
        return type;
    }

    public int getAttacks(){ return attack; }

    @Override
    public String toString() {
        return "Card [name=" + name + ", hp=" + hp + ", type=" + type + ", Attacks =" + attack +"]";
    }
}
