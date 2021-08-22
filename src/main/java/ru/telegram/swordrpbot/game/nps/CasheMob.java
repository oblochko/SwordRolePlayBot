package ru.telegram.swordrpbot.game.nps;

import ru.telegram.swordrpbot.game.scene.Player;

import java.util.SplittableRandom;

public class CasheMob {
    Mob wolf;
    Mob bear;
    Mob robber;

    public CasheMob() {

        wolf = new Animal("Волк", 30, 20, 20, 10, 2, 2 );
        bear = new Animal("Медведь", 60, 31, 40, 10, 10, 10);
    }

    private Mob initRobber(Player player) {
        if(robber == null) {
            robber = new Robber(player.getLaval());
        }
        return robber;
    }

    public Mob getMob(Player player) {
        robber = initRobber(player);
        if(player.getLaval() < 5) {
            if (getKeyMob(0)) {
                return wolf;
            }
            else {
                return robber;
            }
        }
        return null;
    }

    public boolean getKeyMob(int probability) {
        SplittableRandom random = new SplittableRandom();
        return random.nextInt(1, 101) <= probability;
    }
}
