package fieldcoach.github.com.fieldcoachapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import fieldcoach.github.com.fieldcoachapp.data.converter.PlayerListTypeConverters;

/**
 * Team entity class.
 */
@Entity(tableName = "teams")
public class Team implements Parcelable {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String teamName;

    public Team() {
    }

    public Team(int id, String teamName, List<Player> playerList) {
        this.id = id;
        this.teamName = teamName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.teamName);
    }

    protected Team(Parcel in) {
        this.id = in.readInt();
        this.teamName = in.readString();
    }

    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel source) {
            return new Team(source);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}