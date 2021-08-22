package ru.telegram.swordrpbot.game.statemachine.store;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.cache.GoodsCache;
import ru.telegram.swordrpbot.game.equipment.Clothes;
import ru.telegram.swordrpbot.game.equipment.Sword;
import ru.telegram.swordrpbot.game.scene.BuyException;
import ru.telegram.swordrpbot.game.scene.EntityException;
import ru.telegram.swordrpbot.game.statemachine.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwordStore extends State {
    @Override
    public BotApiMethod<?> enter(Message message) {
        String chatId = message.getChatId().toString();
        long userId = message.getFrom().getId();
        StringBuffer getMsg = new StringBuffer("Добро пожаловать в раздел Мечей");
        int i = 0;
        List<Sword> listSword = GoodsCache.Instance.getListSword();
        System.out.println(listSword.size());
        for(Sword it : listSword){
            //System.out.println(String.valueOf(i) + " предмет:");
            it.info();
            getMsg.append(i + "предмет:\n");
            getMsg.append(it);
            getMsg.append("\n/buy_sw_" + i + "\n");
            i++;
        }

        return mainMenuService.getMainMenuMessage(chatId, getMsg.toString(),
                new ArrayList<String>(Arrays.asList("Обратно")));
    }

    @Override
    public void update(String message, long userId) {
        User user = UserDataCache.Instance.getUserProfileData(userId);
        if(message.equals("Обратно"))
            user.setState(State.armor);
        if(message.matches("/buysw(.*)")) {
            String str = message.replaceAll("[^0-9]", "");
            int ind = Integer.valueOf(str);
            Sword sword = GoodsCache.Instance.getListSword().get(ind);
            try {
                user.setState(State.goodbought);
                user.getPlayer().setWeapon(sword);
                System.out.println("Поздравляем с упешной покупкой");
            } catch (BuyException e) {
                user.setState(State.littlefunds);
                System.out.println("У вас недостаточно средств");
            } catch (EntityException e) {
                user.setState(State.noavailble);
                System.out.println("Вашему классу не доступно это оружие");
            }
        }
    }
}
