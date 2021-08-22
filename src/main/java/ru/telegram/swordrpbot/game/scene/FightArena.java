package ru.telegram.swordrpbot.game.scene;

import lombok.Getter;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.game.nps.CasheMob;
import ru.telegram.swordrpbot.game.nps.Mob;

import java.util.*;

@Getter
public class FightArena{
    private final String ProbMobDefense = "Вероятность, что противник сможет защититься: ", ProbYouDefense = "Вероятность, что вы сможете защититься: ";
    private final String MobDefense = "Противник смог защититься", YouDefense = "Вы смогли защититься";
    private final String MobNotDefense = "Противник не смог защититься", YouNotDefense = "Вы не смогли защититься";

    private final String ProbMobContrAttack = "Вероятность, что противник сможет провести контратаку: ", ProbYouContrAttack= "Вероятность, что вы сможете провести контратаку: ";
    private final String MobContrAttack = "Противник смог контратаковать", YouContrAttack = "Вы смогли контратаковать";
    private final String MobNotContrAttack = "Противник не смог защититься", YouNotContrAttack= "Вы не смогли контратаковать";

    private final String DamageDealt = "Нанесённый урон: ", DamageReceived = "Полученный урон: ";

    //public static Map<User, FightArena> mapFightArena = new HashMap<>(); Collections.synchronizedMap(new HashMap(...));

    public static Map<User, FightArena> mapFightArena = Collections.synchronizedMap(new HashMap<>());
    private static CasheMob casheMob = new CasheMob();

    private int hpMob;
    private int hpPlayer;
    private Mob mob;
    private Player player;
    private Condition condition;
    SplittableRandom random = new SplittableRandom();
    //private boolean flag;

    public FightArena(Player player){
        this.mob = casheMob.getMob(player);
        this.hpMob = mob.getHp();
        this.hpPlayer = player.getHp();
        this.player = player;
    }

    //private Map<Player, Mob> mapFight = new HashMap<>();
    //private Map<Player, HPFight> mapHP = new HashMap<>();

    public void addFight(Player player){
        this.mob = casheMob.getMob(player);
        this.hpMob = mob.getHp();
        this.hpPlayer = player.getHp();
    }

    public void closeFight(Player player) {
        //mapFight.remove(player);
        //mapHP.remove(player);
    }

    public void generate(){
        if(random.nextInt(1, 101) <= 50)
            condition = Condition.Attack;
        else
            condition = Condition.Defence;
    }

    public String resultsBattle() {
        StringBuffer str = new StringBuffer();

        str.append("Итоги стычки:");
        str.append("\n\n");
        str.append("Осталось xp у Вас: " + hpPlayer);
        str.append("\n");
        str.append("Осталось xp у Моба: " + hpMob);
        return str.toString();
    }

    public void attack() {
        generate();
        if (condition.equals(Condition.Attack)) {
            System.out.println("Вы выбрали: Атаковать.");
            System.out.println("Противник выбрал: Атаковать.");
            attackAttack();
        }
        else {
            System.out.println("Вы выбрали: Атаковать.");
            System.out.println("Противник выбрал: Защититься.");
            attackDefense(player.getDamage(), mob.getDamage(), mob.getDefense(), true);
        }
    }

    public void defense() {
        generate();
        if (condition.equals(Condition.Attack)) {
            System.out.println("Вы выбрали: Защититься.");
            System.out.println("Противник выбрал: Атаковать.");
            attackDefense(player.getDamage(), mob.getDamage(), mob.getDefense(), false);
        }
        else {
            System.out.println("Вы выбрали: Защититься.");
            System.out.println("Противник выбрал: Защититься.");
        }
    }

    private void attackAttack() {
        System.out.println("Ваш урон:" + player.getDamage());
        System.out.println("Урон противника:" + mob.getDamage());
        hpPlayer = hpPlayer - mob.getDamage();
        hpMob = hpMob - player.getDamage();
        System.out.println("Получено урона: " + mob.getDamage() + ", осталось xp: " + hpPlayer);
        System.out.println("Нанесено урона: " + player.getDamage() + ", осталось xp: " + hpMob);
    }

    private void attackDefense(int damage, int contrAttack, int defense, boolean flag) {
        int attack = damage - defense;
        int probability = 0;

        if (attack >= 0) {
            probability = (int) ((double) (defense - attack) / defense * 100);
            probability = (probability < 0) ? 0 : probability;
            //System.out.println("Вероятность, что противник сможет защититься: " + probability + "%");
            System.out.println((flag ? ProbMobDefense : ProbYouDefense) + probability + "%");
            if (isProbability(probability)) {
                //System.out.println("Противник смог защититься");
                System.out.println((flag ? MobDefense : YouDefense));
            } else {

                System.out.println((flag ? MobNotDefense : YouNotDefense));
                //System.out.println("Нанесённый урон: " + damage);
                System.out.println((flag ? DamageDealt : DamageReceived) + damage);
                if(flag) hpMob = hpMob - damage;
                else hpPlayer = hpPlayer - damage;
            }
        } else //
        {
            attack = -attack;
            probability = (int) ((double) attack / defense * 100);
            //System.out.println("Вероятность, что противник сможет провести контратаку: " + probability + "%");
            System.out.println((flag ? ProbMobContrAttack : ProbYouContrAttack) + probability + "%");

            if (isProbability(probability)) {
                //System.out.println("Противник смог нанести контратаку");
                System.out.println((flag ? MobContrAttack: YouContrAttack));
                System.out.println((flag ? DamageReceived : DamageDealt) + contrAttack);
                if(flag) hpPlayer = hpPlayer - contrAttack;
                else hpMob = hpMob - contrAttack;
            } else {

                System.out.println((flag ? MobNotContrAttack: YouNotContrAttack));
                System.out.println((flag ? DamageDealt : DamageReceived) + damage);
                if(flag) hpMob = hpMob - damage;
                else hpPlayer = hpPlayer - damage;
            }
        }
    }

    public boolean isProbability(int probability) {
        SplittableRandom random = new SplittableRandom();
        return random.nextInt(1, 101) <= probability;
    }

}
