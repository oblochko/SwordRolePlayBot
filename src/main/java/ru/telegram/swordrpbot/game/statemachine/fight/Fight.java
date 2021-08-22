package ru.telegram.swordrpbot.game.statemachine.fight;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.statemachine.State;

import java.util.ArrayList;
import java.util.Arrays;

public class Fight extends State {
    @Override
    public BotApiMethod<?> enter(Message message) {
        String chatId = message.getChatId().toString();
        long userId = message.getFrom().getId();
        String getMsg = "Добро пожаловать в раздел Брони";
        return mainMenuService.getMainMenuMessage(chatId, getMsg,
                new ArrayList<String>(Arrays.asList("Одежда", "Щиты", "Обратно")));
    }

    @Override
    public void update(String message, long userId) {
        User user = UserDataCache.Instance.getUserProfileData(userId);
        switch (message) {

            case "Одежда":
                user.setState(State.clothes);
                break;
            case "Щиты":
                user.setState(State.shield);
                break;
            case "Обратно":
                user.setState(State.store);
                break;
        }
    }
}
