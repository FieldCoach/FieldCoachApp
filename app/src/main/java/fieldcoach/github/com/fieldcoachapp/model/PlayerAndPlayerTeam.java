package fieldcoach.github.com.fieldcoachapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

// Ultimately, when we're pulling the data for players we want from a team we will want
// both the player data and playerteam data. Getting them at about the same time makes
// a lot of sense, and we can do that either through joins or by doing something
// similar to what this is - an embed and a relation
public class PlayerAndPlayerTeam {
    @Embedded
    Player player;

    @Embedded
    PlayerTeam playerTeam;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerTeam getPlayerTeams() {
        return playerTeam;
    }

    public void setPlayerTeams(PlayerTeam playerTeam) {
        this.playerTeam = playerTeam;
    }
}