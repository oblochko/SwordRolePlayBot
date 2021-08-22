package ru.telegram.swordrpbot.game.nps;

import ru.telegram.swordrpbot.game.cache.GoodsCache;
import ru.telegram.swordrpbot.game.specialties.Entity;
import ru.telegram.swordrpbot.game.specialties.Warrior;

import java.util.SplittableRandom;

public class Robber extends Mob {
    Entity spec;

    Robber(String _name, int _hp, int _damage, int _defense, int _speed, Entity entity) {
        this.name = name;

        this.hp = _hp;
        this.damage = _damage;
        this.defense = _defense;
        this.speed = _speed;
        this.spec = entity;
    }

    Robber(int level) {
        this.name = "Разбойник";

        this.hp = 20;
        this.damage = 5;
        this.defense = 5;
        this.speed = 10;
        this.gold = level + 1;
        this.exp = level + 1;

        upStat(level);
        setSpec(level);
    }

    @Override
    public void info() {
        System.out.println("Имя персонажа: " + name);
        System.out.println("Xарактеристки:");
        System.out.println("ХП:" + hp);
        System.out.println("Урон:" + damage);
        System.out.println("Защита:" + defense);
        System.out.println("Скорость:" + speed);
        spec.info();
    }

    @Override
    public String toString(){
        StringBuffer str = new StringBuffer("Имя персонажа: " + name);
        str.append("\n");
        str.append("Xарактеристки:");
        str.append("\n");
        str.append("ХП:" + hp);
        str.append("\n");
        str.append("Урон:" + getDamage());
        str.append("\n");
        str.append("Защита:" + getDefense());
        str.append("\n");
        str.append("Скорость:" + speed);
        str.append("\n");

        return str.toString();
    }

    @Override
    public int getDamage(){
        return damage + spec.attack();
    }

    @Override
    public int getDefense() {
        return defense + spec.defense();
    }

    private void upStat(int level) {
        for(int i = 0; i < level; i++){
            int key = getKey();
            switch (key) {
                case 1 :
                    this.hp += 5;
                    break;

                case 2:
                    this.damage += 5;
                    break;

                case 3:
                    this.defense += 5;
                    break;
            }
        }
    }

    private void setSpec(int level) {
        if(level < 5) {
            this.spec = new Warrior(GoodsCache.Instance.getListSword().get(getKey()-1),
                    GoodsCache.Instance.getListClothes().get(getKey()-1));
        }
    }

    public int getKey() {
        SplittableRandom random = new SplittableRandom();
        return random.nextInt(1, 4);
    }
}

