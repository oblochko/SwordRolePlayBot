package ru.telegram.swordrpbot.botapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.cache.GoodsCache;
import ru.telegram.swordrpbot.game.parser.JsonParser;
import ru.telegram.swordrpbot.game.statemachine.FSM;
import ru.telegram.swordrpbot.game.statemachine.State;
import ru.telegram.swordrpbot.service.MainMenuService;

@Component
@Slf4j
public class TelegramFacade {
    private FSM fsm;
    private UserDataCache userDataCache;
    private MainMenuService mainMenuService;
    //private JsonParser jsonParser;

    public TelegramFacade(MainMenuService mainMenuService, JsonParser jsonParser) {
        this.fsm = new FSM();
        this.userDataCache = UserDataCache.Instance;
        this.mainMenuService = mainMenuService;
        //this.jsonParser = jsonParser;
        GoodsCache.Instance.setupJsonParser(jsonParser);
        //UserDataCache.Instance.loadUser();
    }

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;
        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            adminCommand(message.getText());
        }

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());
            return processCallbackQuery(callbackQuery);
        }


        if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            return handleInputMessage(message);
        }

        return replyMessage;
    }

    private void adminCommand(String command) {
        if(command.equals("/save_users"))
            UserDataCache.Instance.saveUser();
    }

    private BotApiMethod<?> handleInputMessage(Message message) {
        String inputMsg = message.getText();
        long userId = message.getFrom().getId();
        String chatId = message.getChatId().toString();
        //State botState;
        SendMessage replyMessage;
        String getMsg;


        // закинуть в save, чтобы сохранить юзера, если он не сущесвтует, или запустить с последнего места сохранения
        if(userDataCache.saveUserProfileData(userId)){
            getMsg = "Добро пожаловать в Sword Play\nПридумайте имя вашему персонажу:";
            //return mainMenuService.getMainMenuMessage(chatId, "Добро пожаловать в Sword Play\nПридумайте имя вашему персонажу:");
        } else {
            setState(inputMsg, userId);
            // запустить логику состояния, обработать логику юзера
            fsm.update(inputMsg, userId);
            // ввернуть текст для сообщения
            return fsm.enter(message);
        }

        //userDataCache.setUsersCurrentBotState(userId, botState);

        //replyMessage = botStateContext.processInputMessage(botState, message);

        //return replyMessage;
        return new SendMessage(String.valueOf(userId), getMsg);
    }

    private void setState(String message, long chatId ){
        User user = UserDataCache.Instance.getUserProfileData(chatId);

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

    private BotApiMethod<?> processCallbackQuery(CallbackQuery callbackQuery) {
        log.info("New callbackQuery from User: ");
        final String chatId = callbackQuery.getMessage().getChatId().toString();
        final long userId = callbackQuery.getFrom().getId();
        BotApiMethod<?> callBackAnswer = mainMenuService.getMainMenuMessage(chatId, "Воспользуйтесь главным меню");

        return callBackAnswer;
    }

    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackquery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }
}


