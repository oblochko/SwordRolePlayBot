package ru.telegram.swordrpbot.game.nps;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Mob {
    protected String name;

    protected int hp;
    protected int damage;
    protected int defense;
    protected int speed;

    protected int gold;
    protected int exp;

    public abstract void info();

    public int getAction() {
        int a = 0;
        int b = 2;
        int numb = a + (int) (Math.random() * b);
        return numb;
    }

}
