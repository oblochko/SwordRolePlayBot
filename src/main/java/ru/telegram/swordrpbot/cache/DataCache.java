package ru.telegram.swordrpbot.cache;

import ru.telegram.swordrpbot.botapi.User;
import ru.telegram.swordrpbot.game.statemachine.State;

public interface DataCache {
    void setUsersCurrentBotState(long userId, State botState);

    State getUsersCurrentBotState(long userId);

    User getUserProfileData(long userId);

    void saveUserProfileData(long userId, User userProfileData);
}
