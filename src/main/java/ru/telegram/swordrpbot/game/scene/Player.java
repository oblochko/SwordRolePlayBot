package ru.telegram.swordrpbot.game.scene;


import lombok.*;
import org.springframework.stereotype.Component;
import ru.telegram.swordrpbot.game.equipment.Armor;
import ru.telegram.swordrpbot.game.equipment.Weapon;
import ru.telegram.swordrpbot.game.specialties.Entity;

import java.io.*;

@Data
public class Player implements Serializable {
    private String name;
    private Entity spec;

    private int size_gold;
    private int laval;
    private int exp;

    private int hp;
    private int damage;
    private int defense;
    private int speed;

    public Player(Entity spec){
        System.out.println("create Player");
        this.spec = spec;

        size_gold = 100;
        laval = 1;

        hp = 30;
        damage = 10;
        defense = 10;
        speed = 10;

        exp = 0;
    }

    public void info() {
        System.out.println("Имя персонажа: " + name);
        System.out.println("Ваши характеристки:");
        System.out.println("ХП:" + hp);
        System.out.println("Урон:" + getDamage());
        System.out.println("Защита:" + getDefense());
        System.out.println("Скорость:" + speed);
        System.out.println();
        System.out.println("Уровень:" + laval);
        System.out.println("Колиечство опыта: " + exp + " количество опыта для следующего уровня: " + getExpLevel());
        System.out.println("Количество денег:" + size_gold);
        spec.info();
    }

    public boolean buy(int cost) {
        if (size_gold > cost) {
            size_gold = size_gold - cost;
            return true;
        }
        return false;
    }

    public void setWeapon(Weapon weapon) throws BuyException, EntityException {
        if (buy(weapon.cost())) {
            spec.setWeapon(weapon);
            System.out.println(getDamage());
            save();
        }
        else throw new BuyException();
    }

    public void setArmor(Armor armor) throws BuyException, EntityException {
        if (buy(armor.cost())) {
            spec.setArmor(armor);
            System.out.println(getDefense());
            save();
        }
        else throw new BuyException();
    }

    public int getExpLevel() {
        switch (laval) {
            case 1:
                return 5;
            case 2:
                return 10;
            case 3:
                return 15;
            case 4:
                return 20;
            case 5:
                return 25;
            case 6:
                return 30;
            case 7:
                return 35;
            case 8:
                return 40;
            case 9:
                return 45;
            case 10:
                return 50;
        }
        return 0;
    }

    public void setSize_gold(int size_gold) {
        this.size_gold += size_gold;
    }

    public void setExp(int exp) {
        this.exp += exp;
    }

    public String checkLevel() {
        switch (laval) {
            case 1:
                if (exp >= 5)
                    return upLevel();
            case 2:
                if (exp >= 10)
                    return upLevel();
            case 3:
                if (exp >= 15)
                    return upLevel();
            case 4:
                if (exp >= 20)
                    return upLevel();
            case 5:
                if (exp >= 25)
                    return upLevel();
            case 6:
                if (exp >= 30)
                    return upLevel();
            case 7:
                if (exp >= 35)
                    upLevel();
            case 8:
                if (exp >= 40)
                    return upLevel();
            case 9:
                if (exp >= 45)
                    return upLevel();
            case 10:
                if (exp >= 50)
                    return upLevel();
        }
        return "";
    }

    private String upLevel() {
        laval += 1;
        System.out.println("Вы достигли " + String.valueOf(laval) + " уровня!!!");
        String str = "\nВы достигли " + String.valueOf(laval) + " уровня!!!\n";
        exp = 0;
        save();
        return str;
    }


    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream("player.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Player;
    }

    @Override
    public String toString(){
        String getInfo =  "Имя персонажа: " + name + "\n" +
        "Ваши характеристки:" + "\n" +
        "ХП:" + String.valueOf(hp) + "\n" +
        "Урон:" + String.valueOf(getDamage()) + "\n" +
        "Защита:" + String.valueOf(getDefense()) + "\n" +
        "Скорость:" + String.valueOf(speed) + "\n" + "\n" +

        "Уровень:" + String.valueOf(laval) + "\n" +
        "Колиечство опыта: " + exp + " количество опыта для следующего уровня: " + getExpLevel() + "\n" +
        "Количество денег:" + size_gold;
        return getInfo;
    }

    public int getDamage() {
        return damage + spec.attack();
    }

    public int getDefense() {
        return defense + spec.defense();
    }
}
