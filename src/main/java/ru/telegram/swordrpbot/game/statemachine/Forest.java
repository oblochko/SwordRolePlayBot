package ru.telegram.swordrpbot.game.statemachine;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.nps.Mob;
import ru.telegram.swordrpbot.game.scene.FightArena;
import ru.telegram.swordrpbot.game.scene.Player;

import java.util.ArrayList;
import java.util.Arrays;

import static ru.telegram.swordrpbot.game.scene.FightArena.mapFightArena;

public class Forest extends State{
    @Override
    public BotApiMethod<?> enter(Message message) {
        String chatId = message.getChatId().toString();
        long userId = message.getFrom().getId();
        User user = UserDataCache.Instance.getUserProfileData(userId);
        Player player = user.getPlayer();
        //FightArena fightArena = ;
        mapFightArena.put(user, new FightArena(player));
        FightArena fightArena = mapFightArena.get(user);
        Mob mob = fightArena.getMob();
        StringBuffer getMsg = new StringBuffer("Добро пожаловать в раздел Лес\n");
        getMsg.append("Ваш противник:\n");
        getMsg.append(mob);

        return mainMenuService.getMainMenuMessage(chatId, getMsg.toString(),
                new ArrayList<String>(Arrays.asList("Атаковать", "Поставить блок", "Искать друго противника")));
    }

    @Override
    public void update(String message, long userId) {
        User user = UserDataCache.Instance.getUserProfileData(userId);
        switch (message) {

            case "Атаковать":
                user.setState(State.attack);
                break;
            case "Поставить блок":
                user.setState(State.defense);
                break;
            case "Искать друго противника":
                user.setState(State.forest);
                mapFightArena.remove(user);
                //FightArena.Instance.addFight(UserDataCache.Instance.getUserProfileData(userId).getPlayer());
                break;
        }
    }
}
