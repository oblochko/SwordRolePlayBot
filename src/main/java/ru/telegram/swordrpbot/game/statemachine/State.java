package ru.telegram.swordrpbot.game.statemachine;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.service.MainMenuService;

import java.io.Serializable;

public abstract class State implements Serializable {

    protected MainMenuService mainMenuService = new MainMenuService();
    public static State mainMenu, profile, store, location, weapon, armor, sword, ranged, clothes, shield, current,
            forest, registration, errorregistr, goodbought, littlefunds, noavailble, attack, defense, victory, defeat;

//        static {
//        System.out.println("Иницилизация прошла успешна");
//        State.mainMenu = new MainMenu();
//        State.profile = new Profile();
//        State.store = new Store();
//        State.location = new Location();
//        State.registration = new Registration();
//        State.errorregistr = new ErrorRegistr();
//    }


    abstract public BotApiMethod<?> enter(Message message) ;
    abstract public void update(String message, long userId);
    public InlineKeyboardMarkup getInlineMessageButtons() {
        return null;
    };

     public BotApiMethod<?> checkCallbackQuery(CallbackQuery callbackQuery) {
         return null;
     }
}
