package ru.telegram.swordrpbot.game.nps;

import lombok.Getter;

public class Animal extends Mob {
    Animal() {

    }

    Animal(String _name, int _hp, int _damage, int _defense, int _speed, int _gold, int _exp) {
        this.name = _name;

        this.hp = _hp;
        this.damage = _damage;
        this.defense = _defense;
        this.speed = _speed;
        this.gold = _gold;
        this.exp = _exp;
    }

    @Override
    public void info(){
        System.out.println(name);
        System.out.println("xp: " + hp);
        System.out.println("Урон: " + damage);
        System.out.println("Защита: " + defense);
        System.out.println("Скорость: " + speed);
    }

    @Override
    public String toString(){
        StringBuffer str = new StringBuffer("Имя персонажа: " + name);
        str.append("\n");
        str.append("Xарактеристки:");
        str.append("\n");
        str.append("ХП:" + hp);
        str.append("\n");
        str.append("Урон:" + damage);
        str.append("\n");
        str.append("Защита:" + defense);
        str.append("\n");
        str.append("Скорость:" + speed);

        return str.toString();
    }
}
