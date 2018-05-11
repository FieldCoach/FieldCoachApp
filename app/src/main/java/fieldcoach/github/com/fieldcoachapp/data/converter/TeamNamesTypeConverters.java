package fieldcoach.github.com.fieldcoachapp.data.converter;

import android.arch.persistence.room.TypeConverter;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Type converter for teamNames array.
 */
public class TeamNamesTypeConverters {
    @TypeConverter
    public static String[] stringToTeamNames(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            String[] teamNames = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                teamNames[i] = jsonArray.getString(i);
            }
            return teamNames;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String teamNamesToString(String[] teamNames) {
        JSONArray jsonArray = new JSONArray();
        for (String teamId : teamNames) {
            jsonArray.put(teamId);
        }
        return jsonArray.toString();
    }
}
