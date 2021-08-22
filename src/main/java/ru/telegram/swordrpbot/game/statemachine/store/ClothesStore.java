package ru.telegram.swordrpbot.game.statemachine.store;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.cache.GoodsCache;
import ru.telegram.swordrpbot.game.equipment.Clothes;
import ru.telegram.swordrpbot.game.scene.BuyException;
import ru.telegram.swordrpbot.game.scene.EntityException;
import ru.telegram.swordrpbot.game.statemachine.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClothesStore extends State {
    @Override
    public BotApiMethod<?> enter(Message message) {
        String chatId = message.getChatId().toString();
        long userId = message.getFrom().getId();
        StringBuffer getMsg = new StringBuffer("Добро пожаловать в раздел Одежды");
        int i = 0;
        List<Clothes> listClothes = GoodsCache.Instance.getListClothes();
        System.out.println(listClothes.size());
        for(Clothes it : listClothes){
            //System.out.println(String.valueOf(i) + " предмет:");
            it.info();
            getMsg.append(i + "предмет:\n");
            getMsg.append(it);
            getMsg.append("\n/buy_cl_" + i + "\n");
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
        if(message.matches("/buycl(.*)")) {
            String str = message.replaceAll("[^0-9]", "");
            int ind = Integer.valueOf(str);
            Clothes clothes = GoodsCache.Instance.getListClothes().get(ind);
            try {
                user.getPlayer().setArmor(clothes);
                user.setState(State.goodbought);
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
