package ru.telegram.swordrpbot.game.statemachine.store;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.statemachine.State;

import java.util.ArrayList;
import java.util.Arrays;

public class Weapon extends State {
    @Override
    public BotApiMethod<?> enter(Message message) {
        String chatId = message.getChatId().toString();
        long userId = message.getFrom().getId();
        String getMsg = "Добро пожаловать в раздел Оружия";
        return mainMenuService.getMainMenuMessage(chatId, getMsg,
                new ArrayList<String>(Arrays.asList("Оружие ближнего боя", "Оружие дальнего боя", "Обратно")));
    }

    @Override
    public void update(String message, long userId) {
        User user = UserDataCache.Instance.getUserProfileData(userId);
        switch (message) {

            case "Оружие ближнего боя":
                user.setState(State.sword);
                break;
            case "Оружие дальнего боя":
                user.setState(State.ranged);
                break;
            case "Обратно":
                user.setState(State.weapon);
                break;
        }
    }
}
