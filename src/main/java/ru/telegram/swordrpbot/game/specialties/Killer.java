package ru.telegram.swordrpbot.game.specialties;

import ru.telegram.swordrpbot.game.equipment.*;
import ru.telegram.swordrpbot.game.scene.EntityException;

import java.io.Serializable;

public class Killer implements Entity, Serializable {
    Weapon sword_left;
    Weapon sword_right;
    Armor clothes;

    public Killer(Weapon sword_left, Weapon sword_right, Armor clothes) {
        this.sword_left = sword_left;
        this.sword_right = sword_right;
        this.clothes = clothes;
    }

    @Override
    public int attack() {
        return sword_left.attack() + sword_right.attack();
    }

    @Override
    public int defense() {
        return clothes.protection();
    }

    @Override
    public int range() {
        return (sword_right.range() > sword_left.range()) ? sword_right.range() : sword_left.range();
    }

    @Override
    public void info() {
        System.out.println("Класс персонажа: Убица");
        System.out.println("Вооружение: ");
        System.out.println();
        sword_left.info();
        System.out.println();
        sword_right.info();
        System.out.println();
        clothes.info();
    }

    @Override
    public boolean setWeapon(Weapon weapon) throws EntityException {
        if(weapon instanceof Sword) {
            if(sword_right.attack() < sword_left.attack())
                sword_right = weapon;
            else
                sword_left = weapon;
            return true;
        }
        throw new EntityException();
    }

    @Override
    public boolean setArmor(Armor armor) throws EntityException {
        if(armor instanceof Clothes) {
            clothes = armor;
            return true;
        }
        throw new EntityException();
    }
}
