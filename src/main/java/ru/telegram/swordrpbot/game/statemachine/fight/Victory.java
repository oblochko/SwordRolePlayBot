package ru.telegram.swordrpbot.game.statemachine.fight;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.nps.Mob;
import ru.telegram.swordrpbot.game.scene.FightArena;
import ru.telegram.swordrpbot.game.scene.Player;
import ru.telegram.swordrpbot.game.statemachine.State;

import java.util.ArrayList;
import java.util.Arrays;

import static ru.telegram.swordrpbot.game.scene.FightArena.mapFightArena;

public class Victory extends State {
    @Override
    public BotApiMethod<?> enter(Message message) {
        String chatId = message.getChatId().toString();
        long userId = message.getFrom().getId();
        User user = UserDataCache.Instance.getUserProfileData(userId);
        Player player = user.getPlayer();
        FightArena fightArena = mapFightArena.get(user);
        Mob mob = fightArena.getMob();
        StringBuffer getMsg = new StringBuffer("Ура!!! Вы победили!");
        getMsg.append("Получено опыта: " + mob.getExp());
        getMsg.append("Получено золота: " + mob.getGold());
        player.setExp(mob.getExp());
        player.setSize_gold(mob.getGold());
        getMsg.append(player.checkLevel());
        mapFightArena.remove(user);
        return mainMenuService.getMainMenuMessage(chatId, getMsg.toString(),
                new ArrayList<String>(Arrays.asList("Обратно")));
    }

    @Override
    public void update(String message, long userId) {
        User user = UserDataCache.Instance.getUserProfileData(userId);
        switch (message) {
            case "Обратно":
                user.setState(State.location);
                break;
        }
    }
}
