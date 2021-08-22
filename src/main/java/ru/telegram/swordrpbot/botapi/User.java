package ru.telegram.swordrpbot.botapi;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.telegram.swordrpbot.cache.UserDataCache;
import ru.telegram.swordrpbot.game.scene.Player;
import ru.telegram.swordrpbot.game.statemachine.State;

import java.io.*;

@Slf4j
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User implements Serializable {
    private String name;
    private long userId;
    private State state;
    @Autowired
    private Player player;
    private boolean flag;

    public User() {

    }

    public User (long userId) {
        //this.player = new Player();
        this.userId = userId;
        state = State.registration;
        System.out.println(state);
        flag = false;
        log.info("Create User");
    }

    public void setName(String name) {
        this.name = name;
        player.setName(name);
    }

    /*@Override
    public long hashCode()
    {
        return hash;
    }*/

    @Override
    public boolean equals(Object obj)
    {
        return this.userId == ((User)obj).getUserId();
    }
    /*public void setPlayer(Player player) {
        this.player = player;
    }*/
}
