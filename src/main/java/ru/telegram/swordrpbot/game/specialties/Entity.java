package ru.telegram.swordrpbot.game.specialties;

import ru.telegram.swordrpbot.game.equipment.Armor;
import ru.telegram.swordrpbot.game.equipment.Weapon;
import ru.telegram.swordrpbot.game.scene.EntityException;

public interface Entity {
    public int attack();
    public int defense();
    public int range();
    public void info();
    public boolean setWeapon(Weapon weapon) throws EntityException;
    public boolean setArmor(Armor armor) throws EntityException;
}
