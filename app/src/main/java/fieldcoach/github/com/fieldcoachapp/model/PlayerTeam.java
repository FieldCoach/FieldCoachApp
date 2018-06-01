package fieldcoach.github.com.fieldcoachapp.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * PlayerTeam entity class.
 */
@Entity(tableName="playerteams")
public class PlayerTeam implements Parcelable {
    @PrimaryKey (autoGenerate = true)
    private int playerTeamId;
    private String firstName;
    private String playerTeam_lastName;
    private int jerseyNumber;
    private String position;
    private int teamId;
    private int playerTeam_playerId;

    public PlayerTeam() {
    }

    public PlayerTeam(int playerTeamId, String firstName, String lastName) {
        this.playerTeamId = playerTeamId;
        this.firstName = firstName;
        this.playerTeam_lastName = lastName;
    }

    public int getPlayerTeamId() {
        return playerTeamId;
    }

    public void setPlayerTeamId(int playerTeamId) {
        this.playerTeamId = playerTeamId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPlayerTeam_lastName() {
        return playerTeam_lastName;
    }

    public void setPlayerTeam_lastName(String playerTeam_lastName) {
        this.playerTeam_lastName = playerTeam_lastName;
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

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getPlayerTeam_playerId() {
        return playerTeam_playerId;
    }

    public void setPlayerTeam_playerId(int playerTeam_playerId) {
        this.playerTeam_playerId = playerTeam_playerId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.playerTeamId);
        dest.writeString(this.firstName);
        dest.writeString(this.playerTeam_lastName);
        dest.writeInt(this.jerseyNumber);
        dest.writeString(this.position);
        dest.writeInt(this.teamId);
        dest.writeInt(this.playerTeam_playerId);
    }

    protected PlayerTeam(Parcel in) {
        this.playerTeamId = in.readInt();
        this.firstName = in.readString();
        this.playerTeam_lastName = in.readString();
        this.jerseyNumber = in.readInt();
        this.position = in.readString();
        this.teamId = in.readInt();
        this.playerTeam_playerId = in.readInt();
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