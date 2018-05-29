package fieldcoach.github.com.fieldcoachapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
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
    private String name;
    private int size;
    private String record;
    @TypeConverters(PlayerListTypeConverters.class)
    private List<Player> players;

    @Ignore
    public Team() {
    }

    @Ignore
    public Team (String name, int size, List<Player> players, String record) {
        this.name = name;
        this.size = size;
        this.players = players;
        this.record = record;
    }

    public Team(int id, String name, int size, List<Player> players) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.players = players;
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
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
        dest.writeList(this.players);
    }

    protected Team(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.size = in.readInt();
        this.record = in.readString();
        this.players = new ArrayList<Player>();
        in.readList(this.players, Player.class.getClassLoader());
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