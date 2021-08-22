package ru.telegram.swordrpbot.game.statemachine.store.state;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.statemachine.State;

import java.util.ArrayList;
import java.util.Arrays;

public class LittleFunds extends State {
    @Override
    public BotApiMethod<?> enter(Message message) {
        String chatId = message.getChatId().toString();
        long userId = message.getFrom().getId();
        String getMsg = "У вас недостаточно средств!!!\nВыберите куда хотите вернуться";
        return mainMenuService.getMainMenuMessage(chatId, getMsg,
                new ArrayList<String>(Arrays.asList("Броня", "Оружие", "Главное меню")));
    }

    @Override
    public void update(String message, long userId) {
        User user = UserDataCache.Instance.getUserProfileData(userId);
        switch (message) {

            case "Броня":
                user.setState(State.armor);
                break;
            case "Оружие":
                user.setState(State.weapon);
                break;
            case "Главное меню":
                user.setState(State.mainMenu);
                break;
        }
    }
}
