package ru.telegram.swordrpbot.game.statemachine;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;

public class Registration extends State{
    @Override
    public BotApiMethod<?> enter(Message message) {
        long userId = message.getFrom().getId();
        User user = UserDataCache.Instance.getUserProfileData(userId);
        String getMsg = "";

        getMsg = "Вы успешно зарегестрировались!!!";
        System.out.println("Вы успешно зарегестрировались");

        user.setState(State.mainMenu);

        return new SendMessage(String.valueOf(userId), getMsg);
    }

    @Override
    public void update(String message, long userId) {

        User user = UserDataCache.Instance.getUserProfileData(userId);
        if(message.equals("error")) {
            //user.setFlag(false);

            user.setState(State.errorregistr);
        }
        else {
            user.setName(message);
            user.setState(State.mainMenu);
        }

    }

    @Override
    public BotApiMethod<?> checkCallbackQuery(CallbackQuery callbackQuery) {
        return null;
    }
}
