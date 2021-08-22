package ru.telegram.swordrpbot.game.statemachine.fight;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.scene.FightArena;
import ru.telegram.swordrpbot.game.statemachine.State;

import java.util.ArrayList;
import java.util.Arrays;

import static ru.telegram.swordrpbot.game.scene.FightArena.mapFightArena;

public class Attack extends State {
    @Override
    public BotApiMethod<?> enter(Message message) {
        String chatId = message.getChatId().toString();
        long userId = message.getFrom().getId();
        User user = UserDataCache.Instance.getUserProfileData(userId);
        /*ArrayList<User> key = new ArrayList<>(mapFightArena.keySet());
        ArrayList<FightArena> values = new ArrayList<>(mapFightArena.values());
        FightArena fightArena = null;
        int i=0;
        for (User e: key) {
            if(e.equals(user))
                fightArena = values.get(0);
            i++;
        }*/
        FightArena fightArena = mapFightArena.get(user);
        fightArena.attack();
        String getMsg = fightArena.resultsBattle();
        return mainMenuService.getMainMenuMessage(chatId, getMsg,
                new ArrayList<String>(Arrays.asList("Атаковать", "Защититься", "Убежать")));
    }

    @Override
    public void update(String message, long userId) {
        User user = UserDataCache.Instance.getUserProfileData(userId);
        FightArena fightArena = mapFightArena.get(user);
        if (fightArena.getHpMob() <= 0) {
            user.setState(State.victory);
            return;
        }
        if (fightArena.getHpPlayer() <= 0) {
            user.setState(State.defeat);
            return;
        }

        switch (message) {

            case "Атаковать":
                user.setState(State.attack);
                break;
            case "Защититься":
                user.setState(State.defense);
                break;
            case "Убежать":
                user.setState(State.store);
                break;
        }
    }
}
