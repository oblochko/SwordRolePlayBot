package ru.telegram.swordrpbot.game.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Sword implements Weapon, Serializable {
    @JsonProperty("name")
    String name;
    @JsonProperty("cost")
    int cost;
    @JsonProperty("damage")
    int damage;
    @JsonProperty("range")
    int range;

    public Sword(){

    }

    public Sword(String name, int cost, int damage) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.range = 10;
    }

    public Sword(String name, int cost, int damage, int range) {
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.range = 10;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int attack() {
        return this.damage;
    }

    @Override
    public int range() {
        return 10;
    }

    @Override
    public void info() {
        System.out.println("Меч");
        System.out.println("Название: " + name);
        System.out.println("Стоимость: " + cost);
        System.out.println("Урон: " + damage);
    }

    @Override
    public int cost() {
        return cost;
    }

    @Override
    public String toString() {
        String getInfo = "Броня\n" +
                "Название: " + name + "\n" +
                "Стоимость: " + cost + "\n" +
                "Урон: " + damage;
        return getInfo;
    }
}
