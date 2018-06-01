package fieldcoach.github.com.fieldcoachapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Player entity class.
 */
@Entity(tableName="players")
public class Player implements Parcelable {
    @PrimaryKey (autoGenerate = true)
    private int playerId;
    private String name;
    private String playerLastName;
    private int playerJerseyNumber;
    private String playerPosition;

    @Ignore
    public Player(int playerJerseyNumber, String playerPosition) {
        this.playerJerseyNumber = playerJerseyNumber;
        this.playerPosition = playerPosition;
    }

    @Ignore
    public Player(String name, String playerLastName, int playerJerseyNumber, String playerPosition) {
        this.name = name;
        this.playerLastName = playerLastName;
        this.playerJerseyNumber = playerJerseyNumber;
        this.playerPosition = playerPosition;
    }

    public Player(int playerId, String name, String playerLastName, int playerJerseyNumber, String playerPosition) {
        this.playerId = playerId;
        this.name = name;
        this.playerLastName = playerLastName;
        this.playerJerseyNumber = playerJerseyNumber;
        this.playerPosition = playerPosition;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int id) {
        this.playerId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }

    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public int getPlayerJerseyNumber() {
        return playerJerseyNumber;
    }

    public void setPlayerJerseyNumber(int playerJerseyNumber) {
        this.playerJerseyNumber = playerJerseyNumber;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.playerId);
        dest.writeString(this.name);
        dest.writeString(this.playerLastName);
        dest.writeInt(playerJerseyNumber);
        dest.writeString(playerPosition);
    }

    protected Player(Parcel in) {
        this.playerId = in.readInt();
        this.name = in.readString();
        this.playerLastName = in.readString();
        this.playerJerseyNumber = in.readInt();
        this.playerPosition = in.readString();
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