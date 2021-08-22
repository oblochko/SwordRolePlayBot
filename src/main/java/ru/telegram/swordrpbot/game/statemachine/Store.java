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

public class Store  extends State{

    @Override
    public BotApiMethod<?> enter(Message message) {
        long userId = message.getFrom().getId();
        String chatId = message.getChatId().toString();
        System.out.println("Добро пожаловать в раздел Магазин");
        System.out.println("1. Обратно");
//        String getMsg = "Добро пожаловать в раздел Локации\n1. Обратно";
        String getMsg = "Добро пожаловать в раздел Магазин\n";
        return mainMenuService.getMainMenuMessage(chatId, getMsg,
                new ArrayList<String>(Arrays.asList("Оружие", "Броня", "Обратно")));
    }

    @Override
    public void update(String message, long userId) {
        User user = UserDataCache.Instance.getUserProfileData(userId);
        switch (message) {
            case "Оружие":
                user.setState(State.weapon);
                break;
            case "Броня":
                user.setState(State.armor);
                break;
            case "Обратно":
                user.setState(State.mainMenu);
                break;
        }
    }

    @Override
    public BotApiMethod<?> checkCallbackQuery(CallbackQuery callbackQuery) {
        return null;
    }
}
