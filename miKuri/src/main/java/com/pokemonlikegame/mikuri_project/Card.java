package com.pokemonlikegame.mikuri_project;

public class Card {
    private String name;
    private int hp;
    private String type;
    private int attackPower;

    public Card(String name, int hp, String type, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.type = type;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public String getType(){
        return type;
    }

    public int getAttackPower(){
        return attackPower;
    }

    //new
    public void takeDamage(int damage) {
        this.hp -= damage;
    }

    //new
    public boolean isAlive() {
        return this.hp > 0;
    }

    @Override
    public String toString() {
        return "Card [name=" + name + ", hp=" + hp + ", type=" + type + ", Attack =" + attackPower +"]";
    }
}
