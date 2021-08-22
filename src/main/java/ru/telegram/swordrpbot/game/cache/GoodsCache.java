package ru.telegram.swordrpbot.game.cache;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.telegram.swordrpbot.game.equipment.Clothes;
import ru.telegram.swordrpbot.game.equipment.Ranged;
import ru.telegram.swordrpbot.game.equipment.Shield;
import ru.telegram.swordrpbot.game.equipment.Sword;
import ru.telegram.swordrpbot.game.parser.JsonParser;


import java.util.ArrayList;
import java.util.List;

//@Component
@Getter
@Slf4j
public enum GoodsCache{
    Instance;

    JsonParser jsonParser;

    private List<Sword> listSword;
    private List<Clothes> listClothes;
    private List<Ranged> listRanged;
    private List<Shield> listShield;

    public void setupJsonParser(JsonParser jsonParser) {
        this.jsonParser = new JsonParser();
        listSword = jsonParser.parse("sword.json", new Sword());
        listClothes = jsonParser.parse("clothes.json", new Clothes());
        listRanged = jsonParser.parse("ranged.json", new Ranged());
        listShield = jsonParser.parse("shield.json", new Shield());
        log.info("Setup Json Parser {}", listClothes.size());
    }

}
