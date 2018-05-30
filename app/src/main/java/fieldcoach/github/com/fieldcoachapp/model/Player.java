package fieldcoach.github.com.fieldcoachapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import fieldcoach.github.com.fieldcoachapp.data.converter.TeamIdsTypeConverters;
import fieldcoach.github.com.fieldcoachapp.data.converter.TeamNamesTypeConverters;

/**
 * Player entity class.
 */
@Entity(tableName="players")
public class Player implements Parcelable {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private String name;
    private String lastName;

    @Ignore
    public Player() {
    }

    @Ignore
    public Player(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Player(int id, String name, String lastName) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.lastName);
    }

    protected Player(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.lastName = in.readString();
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}