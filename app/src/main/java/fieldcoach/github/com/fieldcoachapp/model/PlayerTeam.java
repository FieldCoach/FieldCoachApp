package fieldcoach.github.com.fieldcoachapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import fieldcoach.github.com.fieldcoachapp.data.converter.TeamIdsTypeConverters;
import fieldcoach.github.com.fieldcoachapp.data.converter.TeamNamesTypeConverters;

/**
 * PlayerTeam entity class.
 */
@Entity(tableName="playerteams")
public class PlayerTeam implements Parcelable {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String name;
    private String lastName;
    private int jerseyNumber;
    private String position;
    private int teamId;
    private int playerId;

    public PlayerTeam() {
    }

    public PlayerTeam(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamIds(int teamId) {
        this.teamId = teamId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.lastName);
        dest.writeInt(this.jerseyNumber);
        dest.writeString(this.position);
        dest.writeInt(this.teamId);
        dest.writeInt(this.playerId);
    }

    protected PlayerTeam(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.lastName = in.readString();
        this.jerseyNumber = in.readInt();
        this.position = in.readString();
        this.teamId = in.readInt();
        this.playerId = in.readInt();
    }

    public static final Creator<PlayerTeam> CREATOR = new Creator<PlayerTeam>() {
        @Override
        public PlayerTeam createFromParcel(Parcel source) {
            return new PlayerTeam(source);
        }

        @Override
        public PlayerTeam[] newArray(int size) {
            return new PlayerTeam[size];
        }
    };
}