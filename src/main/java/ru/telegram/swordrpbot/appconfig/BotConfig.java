package ru.telegram.swordrpbot.appconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.telegram.swordrpbot.RolePlayTelegramBot;
import ru.telegram.swordrpbot.botapi.TelegramFacade;
import ru.telegram.swordrpbot.game.equipment.Armor;
import ru.telegram.swordrpbot.game.equipment.Clothes;
import ru.telegram.swordrpbot.game.equipment.Sword;
import ru.telegram.swordrpbot.game.scene.Player;
import ru.telegram.swordrpbot.game.specialties.Entity;
import ru.telegram.swordrpbot.game.specialties.Warrior;
import ru.telegram.swordrpbot.game.statemachine.*;

@Slf4j
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String userName;
    private String botToken;

    @Bean
    public RolePlayTelegramBot MySuperTelegramBot(TelegramFacade telegramFacade) {
        System.out.println(userName);
        System.out.println(botToken);
        RolePlayTelegramBot mySuperTelegramBot = new RolePlayTelegramBot(telegramFacade);
        mySuperTelegramBot.setBotUserName(userName);
        mySuperTelegramBot.setBotToken(botToken);

        return mySuperTelegramBot;
    }

    /*@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        //messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }*/
}
