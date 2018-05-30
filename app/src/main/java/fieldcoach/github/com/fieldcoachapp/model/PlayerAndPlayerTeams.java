package fieldcoach.github.com.fieldcoachapp.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class PlayerAndPlayerTeams {
    @Embedded
    Player player;

    @Relation(parentColumn =  "player.id", entityColumn = "playerteam.id")
    //Relation returns a list, even if we only want a single PlayerTeam object
    List<PlayerTeam> playerTeams;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<PlayerTeam> getPlayerTeams() {
        return playerTeams;
    }

    public void setPlayerTeams(List<PlayerTeam> playerTeams) {
        this.playerTeams = playerTeams;
    }
}