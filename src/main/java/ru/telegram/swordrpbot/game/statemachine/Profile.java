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

public class Profile extends State{
    @Override
    public BotApiMethod<?> enter(Message message) {
        String chatId = message.getChatId().toString();
        long userId = message.getFrom().getId();
        //System.out.println("Добро пожаловать в раздел Персонаж");
        System.out.println("1. Обратно");
//        String getMsg = "Добро пожаловать в раздел Профиль\n1. Обратно";
        String getMsg = "Добро пожаловать в раздел Профиль\n" + UserDataCache.Instance.getUserProfileData(userId).getPlayer();
        return mainMenuService.getMainMenuMessage(chatId, getMsg,
                new ArrayList<String>(Arrays.asList("Обратно")));
    }

    @Override
    public void update(String message, long userId) {
        User user = UserDataCache.Instance.getUserProfileData(userId);
        switch (message) {
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

