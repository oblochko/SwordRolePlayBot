package ru.telegram.swordrpbot.game.statemachine;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainMenu  extends State{
    @Override
    public BotApiMethod<?> enter(Message message) {
        String chatId = message.getChatId().toString();
        System.out.println("Добро пожаловать в раздел Главное меню");
        System.out.println("1. Персонаж");
        System.out.println("2. Магазин");
        System.out.println("3. Локации");
//        String getMsg = "Добро пожаловать в раздел Главное меню\n1. Персонаж\n2. Магазин\n3. Локации";
        String getMsg = "Добро пожаловать в раздел Главное меню.";
        return mainMenuService.getMainMenuMessage(chatId, getMsg,
                new ArrayList<String>(Arrays.asList("Персонаж", "Магазин", "Локации")));
        //return new SendMessage(String.valueOf(userId), getMsg);
    }

    @Override
    public void update(String message, long userId) {
        User user = UserDataCache.Instance.getUserProfileData(userId);
        switch (message) {

            case "Персонаж":
                user.setState(State.profile);
                break;
            case "Магазин":
                user.setState(State.store);
                break;
            case "Локации":
                user.setState(State.location);
                break;
        }

    }

    @Override
    public BotApiMethod<?> checkCallbackQuery(CallbackQuery callbackQuery) {
        return null;
    }
}
