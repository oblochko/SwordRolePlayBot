package ru.telegram.swordrpbot.game.specialties;

import ru.telegram.swordrpbot.game.equipment.*;
import ru.telegram.swordrpbot.game.scene.EntityException;

import java.io.Serializable;

public class Knight implements Entity, Serializable {
    Weapon sword;
    Armor shield;
    Armor clothes;

    public Knight(Weapon sword, Armor shield, Armor clothes) {
        this.sword = sword;
        this.shield = shield;
        this.clothes = clothes;
    }

    @Override
    public int attack() {
        return sword.attack();
    }

    @Override
    public int defense() {
        return shield.protection() + clothes.protection();
    }

    @Override
    public int range() {
        return sword.range();
    }

    @Override
    public void info() {
        System.out.println("Класс персонажа: Рыцарь");
        System.out.println("Вооружение: ");
        System.out.println();
        sword.info();
        System.out.println();
        shield.info();
        System.out.println();
        clothes.info();
    }

    @Override
    public boolean setWeapon(Weapon weapon) throws EntityException{
        if(weapon instanceof Sword) {
            sword = weapon;
            return true;
        }
        throw new EntityException();
    }

    @Override
    public boolean setArmor(Armor armor) throws EntityException {
        if (armor instanceof Shield) {
            shield = armor;
            return true;
        }
        if (armor instanceof Clothes) {
            clothes = armor;
            return true;
        }
        throw new EntityException();
    }
}
