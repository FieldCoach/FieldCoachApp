package fieldcoach.github.com.fieldcoachapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import fieldcoach.github.com.fieldcoachapp.data.converter.PlayerListTypeConverters;

/**
 * Team entity class.
 */
@Entity(tableName = "teams")
public class Team implements Parcelable {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String name;
    private int size;
    private String record;
    @TypeConverters(PlayerListTypeConverters.class)
    private List<Player> playerList;

    @Ignore
    public Team() {
    }

    @Ignore
    public Team(String name, int size, List<Player> playerList, String record) {
        this.name = name;
        this.size = size;
        this.record = record;
        this.playerList = playerList;
    }

    public Team(int id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.size);
        dest.writeString(this.record);
        dest.writeTypedList(this.playerList);
    }

    protected Team(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.size = in.readInt();
        this.record = in.readString();
        this.playerList = in.createTypedArrayList(Player.CREATOR);
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
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