package ru.telegram.swordrpbot.game.specialties;

import ru.telegram.swordrpbot.game.equipment.*;
import ru.telegram.swordrpbot.game.scene.EntityException;

import java.io.Serializable;

public class Shooter implements Entity, Serializable {
    Weapon sword;
    Weapon range;
    Armor clothes;

    @Override
    public int attack() {
        return sword.attack();
    }

    @Override
    public int defense() {
        return clothes.protection();
    }

    @Override
    public int range() {
        return range.range();
    }

    @Override
    public void info() {
        System.out.println("Класс персонажа: Лучник");
        System.out.println("Вооружение: ");
        System.out.println();
        sword.info();
        System.out.println();
        range.info();
        System.out.println();
        clothes.info();
    }

    @Override
    public boolean setWeapon(Weapon weapon) throws EntityException {
        if(weapon instanceof Sword) {
            sword = weapon;
            return true;
        }
        if(weapon instanceof Ranged) {
            sword = weapon;
            return true;
        }
        throw new EntityException();
    }

    @Override
    public boolean setArmor(Armor armor) throws EntityException{
        if(armor instanceof Clothes) {
            clothes = armor;
            return true;
        }
        throw new EntityException();
    }
}
