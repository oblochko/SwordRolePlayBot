package ru.telegram.swordrpbot.appconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import ru.telegram.swordrpbot.game.equipment.Armor;
import ru.telegram.swordrpbot.game.equipment.Clothes;
import ru.telegram.swordrpbot.game.equipment.Sword;
import ru.telegram.swordrpbot.game.scene.Player;
import ru.telegram.swordrpbot.game.specialties.Entity;
import ru.telegram.swordrpbot.game.specialties.Warrior;

@Slf4j
@Configuration
public class PlayerConfig {
    @Lazy
    @Bean
    public Entity entity(){
        Sword sword = new Sword("Деревянный меч", 0, 5);
        Armor clothes = new Clothes("Тканевая одежда", 0, 5);
        Entity entity = new Warrior(sword, clothes);
        log.info("Create Entity {}", entity);
        return entity;
    }

    @Lazy
    @Bean
    public Player player(Entity entity){
        Player player = new Player(entity);
        log.info("Create Player {}", player);
        return player;
    }
}
