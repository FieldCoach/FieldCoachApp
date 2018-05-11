package fieldcoach.github.com.fieldcoachapp.data.converter;

import android.arch.persistence.room.TypeConverter;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * Type converter for teamIds array.
 */
public class TeamIdsTypeConverters {
    @TypeConverter
    public static int[] stringToTeamIds(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            int[] teamIds = new int[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                teamIds[i] = Integer.parseInt(jsonArray.getString(i));
            }
            return teamIds;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @TypeConverter
    public static String teamIdsToString(int[] teamIds) {
        JSONArray jsonArray = new JSONArray();
        for (int teamId : teamIds) {
            jsonArray.put(teamId);
        }
        return jsonArray.toString();
    }
}
