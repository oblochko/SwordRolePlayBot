package ru.telegram.swordrpbot.game.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Shield implements Armor, Serializable {
    @JsonProperty("name")
    String name;
    @JsonProperty("cost")
    int cost;
    @JsonProperty("defense")
    int defense;

    public Shield(String name, int cost, int defense) {
        this.name = name;
        this.cost = cost;
        this.defense = defense;
    }

    public Shield() {

    }

    @Override
    public int protection() {
        return defense;
    }

    @Override
    public void info() {
        System.out.println("Щит");
        System.out.println("Название: " + name);
        System.out.println("Стоимость: " + cost);
        System.out.println("Очки защиты: " + defense);
    }

    @Override
    public int cost() {
        return cost;
    }

    public String toString() {
        String getInfo = "Броня\n" +
                "Название: " + name + "\n" +
                "Стоимость: " + cost + "\n" +
                "Очков защиты: " + defense;
        return getInfo;
    }
}
