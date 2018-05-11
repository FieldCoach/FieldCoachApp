package fieldcoach.github.com.fieldcoachapp.data.converter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import fieldcoach.github.com.fieldcoachapp.model.Player;

/**
 * Type converter for player list.
 */
public class PlayerListTypeConverters {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Player> stringToPlayerList(String data) {
        if(data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<Player>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String playerListToString(List<Player> players) {
        return gson.toJson(players);
    }
}
