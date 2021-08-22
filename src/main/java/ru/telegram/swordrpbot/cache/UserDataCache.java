package ru.telegram.swordrpbot.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.telegram.swordrpbot.appconfig.BotConfig;
import ru.telegram.swordrpbot.appconfig.PlayerConfig;
import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.cache.DataCache;
import ru.telegram.swordrpbot.game.scene.Player;
import ru.telegram.swordrpbot.game.statemachine.State;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public enum UserDataCache {
    Instance;

    private List<User> usersList = new ArrayList<>();

    public void setUsersCurrentBotState(long userId, State botState) {
        usersList.stream()
                .filter((u)->u.getUserId() == userId)
                .collect(Collectors.toList())
                .get(0).setState(botState);
    }

    public State getUsersCurrentBotState(long userId) {
        return usersList.stream()
                .filter((u)->u.getUserId() == userId)
                .collect(Collectors.toList())
                .get(0).getState();
    }

    public User getUserProfileData(long userId) {
        return usersList.stream()
                .filter((u)->u.getUserId() == userId)
                .collect(Collectors.toList())
                .get(0);
    }

    public boolean saveUserProfileData(long userId) {
        log.info("User save started, chatId:{}", userId);
        boolean flag = usersList.stream().noneMatch(u-> u.getUserId() == userId);

        if(!flag) return false;
        User user = new User(userId);
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(PlayerConfig.class);
        Player player = ctx.getBean(Player.class);
        user.setPlayer(player);
        usersList.add(user);

        log.info("User saved successfully, chatId:{}, state {}", userId, user.getState());

        return true;
    }

    public void saveUser(){
        try {
            FileOutputStream fos = new FileOutputStream("database.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(usersList);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUser() {
        try {
            FileInputStream fis = new FileInputStream("database.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            usersList = (ArrayList<User>) ois.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
