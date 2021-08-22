package ru.telegram.swordrpbot.game.statemachine;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Location extends State{
    @Override
    public BotApiMethod<?> enter(Message message) {
        String chatId = message.getChatId().toString();
        System.out.println("Добро пожаловать в раздел Локации");
        System.out.println("1. Обратно");
//        String getMsg = "Добро пожаловать в раздел Локации\n1. Обратно";
        String getMsg = "Добро пожаловать в раздел Локации";
        return mainMenuService.getMainMenuMessage(chatId, getMsg, new ArrayList<String>(Arrays.asList("Лес", "Обратно")));
    }

    @Override
    public void update(String message, long userId) {

        User user = UserDataCache.Instance.getUserProfileData(userId);
        switch (message) {
            case "Лес":
                user.setState(State.forest);
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

