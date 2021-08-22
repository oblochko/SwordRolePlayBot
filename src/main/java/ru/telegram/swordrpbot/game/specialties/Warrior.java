package ru.telegram.swordrpbot.game.specialties;

import ru.telegram.swordrpbot.game.equipment.*;
import ru.telegram.swordrpbot.game.scene.EntityException;
import java.io.Serializable;

public class Warrior implements Entity, Serializable {
    Weapon sword;
    Armor clothes;

    public Warrior(Weapon sword, Armor clothes) {
        this.sword = sword;
        this.clothes = clothes;
    }

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
        return sword.range();
    }

    @Override
    public void info() {
        System.out.println("Класс персонажа: Воин");
        System.out.println("Вооружение: ");
        System.out.println();
        sword.info();
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
        if(armor instanceof Clothes) {
            clothes = armor;
            return true;
        }
        throw new EntityException();
    }
}
