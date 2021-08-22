package ru.telegram.swordrpbot.game.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.telegram.swordrpbot.game.equipment.Clothes;
import ru.telegram.swordrpbot.game.equipment.Ranged;
import ru.telegram.swordrpbot.game.equipment.Shield;
import ru.telegram.swordrpbot.game.equipment.Sword;

import java.io.File;
import java.util.ArrayList;

@Service
public class JsonParser {

    public JsonParser() {}

    public <T> ArrayList<T> parse(String path, T object) {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<T> list = new ArrayList<>();
        try {
            if (new Sword().getClass().isAssignableFrom(object.getClass())) {
                list = (ArrayList<T>) objectMapper.readValue(new File(path), new TypeReference<ArrayList<Sword>>() {
                });
                return list;
            }

            if (new Ranged().getClass().isAssignableFrom(object.getClass())) {
                list = (ArrayList<T>) objectMapper.readValue(new File(path), new TypeReference<ArrayList<Ranged>>() {
                });
                return list;
            }

            if (new Clothes().getClass().isAssignableFrom(object.getClass())) {
                list = (ArrayList<T>) objectMapper.readValue(new File(path), new TypeReference<ArrayList<Clothes>>() {
                });
                return list;
            }

            if (new Shield().getClass().isAssignableFrom(object.getClass())) {
                list = (ArrayList<T>) objectMapper.readValue(new File(path), new TypeReference<ArrayList<Shield>>() {
                });
                return list;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
