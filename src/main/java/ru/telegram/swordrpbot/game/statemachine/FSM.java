package ru.telegram.swordrpbot.game.statemachine;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.statemachine.fight.Attack;
import ru.telegram.swordrpbot.game.statemachine.fight.Defeat;
import ru.telegram.swordrpbot.game.statemachine.fight.Defense;
import ru.telegram.swordrpbot.game.statemachine.fight.Victory;
import ru.telegram.swordrpbot.game.statemachine.store.*;
import ru.telegram.swordrpbot.game.statemachine.store.state.GoodBought;
import ru.telegram.swordrpbot.game.statemachine.store.state.LittleFunds;
import ru.telegram.swordrpbot.game.statemachine.store.state.NoAvailable;
import ru.telegram.swordrpbot.service.MainMenuService;

@Service
public class FSM{

    MainMenuService mainMenuService;

    public FSM() {
        System.out.println("Иницилизация прошла успешна");
        State.mainMenu = new MainMenu();
        State.profile = new Profile();
        State.store = new Store();
        State.location = new Location();
        State.registration = new Registration();
        State.errorregistr = new ErrorRegistr();
        State.armor = new Armor();
        State.clothes = new ClothesStore();
        State.goodbought = new GoodBought();
        State.littlefunds = new LittleFunds();
        State.noavailble = new NoAvailable();
        State.shield = new ShieldStore();
        State.weapon = new Weapon();
        State.sword = new SwordStore();
        State.ranged = new RangedStore();
        State.attack = new Attack();
        State.defense = new Defense();
        State.victory = new Victory();
        State.defeat = new Defeat();
        State.forest = new Forest();
    }

    public void update(String message, long userId){
        UserDataCache.Instance.getUsersCurrentBotState(userId).update(message, userId);
    }

    public BotApiMethod<?> enter(Message message) {
        long userId = message.getFrom().getId();
        //boolean flag = UserDataCache.Instance.getUserProfileData(userId).isFlag();
        return UserDataCache.Instance.getUsersCurrentBotState(userId).enter(message);
    }

}
