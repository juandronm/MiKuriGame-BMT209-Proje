package InGameScreen;


import Functionality.CardFunctionality;

public class Card extends CardFunctionality {
    private static int uniqueIdCounter = 1;
    private final int id;
    private final String name;
    private int hp;
    private final String type;
    private final int attack;


    public Card(String[] characterDatum) {
        this.id = uniqueIdCounter++;
        this.name = characterDatum[0];
        this.hp = Integer.parseInt(characterDatum[1]) ;
        this.type = characterDatum[2] ;
        this.attack = Integer.parseInt(characterDatum[3]);
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
