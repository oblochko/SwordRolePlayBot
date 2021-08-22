package ru.telegram.swordrpbot.game.equipment;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Ranged implements Weapon, Serializable {
    @JsonProperty("name")
     String name;
    @JsonProperty("cost")
     int cost;
    @JsonProperty("damage")
     int damage;
    @JsonProperty("range")
     int range;

     public Ranged(String name, int cost, int damage, int range) {
          this.name = name;
          this.cost = cost;
          this.damage = damage;
          this.range = range;
     }

    public Ranged() {

    }

    @Override
     public int attack() {
          return this.damage;
     }

     @Override
     public int range() {
          return this.range;
     }

     @Override
     public void info() {
          System.out.println("Оружие дальнего боя");
          System.out.println("Название: " + name);
          System.out.println("Стоимость: " + cost);
          System.out.println("Урон: " + damage);
          System.out.println("Расстояние: " + range);
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
                "Урон: " + damage + "\n" +
                "Расстояние: " + range;
        return getInfo;
    }
}
