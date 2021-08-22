package ru.telegram.swordrpbot.game.statemachine;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.statemachine.State;

public class ErrorRegistr extends State {
    @Override
    public BotApiMethod<?> enter(Message message) {
        long userId = message.getFrom().getId();
        User user = UserDataCache.Instance.getUserProfileData(userId);
        String getMes = null;

        getMes = "Ваше имя занято(\nПопобуйте другое";
        System.out.println("Ваше имя занято(\nПопобуйте другое");

        return new SendMessage(String.valueOf(userId), getMes);
        //return getMes;
    }

    @Override
    public void update(String message, long userId) {
        User user = UserDataCache.Instance.getUserProfileData(userId);
        if(message == "error") {
            //user.setFlag(false);
        }
        else {
            user.setName(message);
            user.setState(State.registration);
        }
    }

    @Override
    public BotApiMethod<?> checkCallbackQuery(CallbackQuery callbackQuery) {
        return null;
    }
}
