package ru.telegram.swordrpbot.game.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Clothes implements Armor, Serializable {
    @JsonProperty("name")
    String name;
    @JsonProperty("cost")
    int cost;
    @JsonProperty("defense")
    int defense;

    public Clothes(String name, int cost, int defense) {
        this.name = name;
        this.cost = cost;
        this.defense = defense;
    }

    public Clothes() {

    }

    @Override
    public int protection() {
        return defense;
    }

    @Override
    public void info() {
        System.out.println("Броня");
        System.out.println("Название: " + name);
        System.out.println("Стоимость: " + cost);
        System.out.println("Очков защиты: " + defense);
    }

    @Override
    public int cost() {
        return cost;
    }

    @Override
    public String toString(){
        String getInfo = "Броня\n" +
                "Название: " + name + "\n" +
                "Стоимость: " + cost + "\n" +
                "Очков защиты: " + defense;
        return getInfo;
    }
}
