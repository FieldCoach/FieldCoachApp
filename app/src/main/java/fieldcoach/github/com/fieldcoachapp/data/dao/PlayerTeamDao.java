package fieldcoach.github.com.fieldcoachapp.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fieldcoach.github.com.fieldcoachapp.model.Player;
import fieldcoach.github.com.fieldcoachapp.model.PlayerAndPlayerTeam;
import fieldcoach.github.com.fieldcoachapp.model.PlayerTeam;

/**
 * PlayerTeam many-to-many relationship data access object.
 */
@Dao
public interface PlayerTeamDao {
    @Query("SELECT * FROM playerteams")
    LiveData<List<PlayerTeam>> getAllPlayerTeams();

    @Query("SELECT * FROM playerteams WHERE playerTeamId = :playerTeamId")
    LiveData<PlayerTeam> getPlayerTeam(int playerTeamId);

    @Query("SELECT * FROM playerteams WHERE "
           + "playerteams.teamId LIKE :teamId AND playerteams.playerTeam_playerId LIKE :playerId")
    LiveData<List<PlayerTeam>> getPlayerTeamsByTeamAndPlayer(int teamId, int playerId);

    @Query("SELECT * FROM playerteams WHERE "
            + "playerteams.teamId LIKE :teamId")
    LiveData<List<PlayerTeam>> getPlayerTeamsByTeam(int teamId);

    @Query("SELECT * FROM playerteams WHERE "
            + "playerteams.playerTeam_playerId LIKE :playerId")
    LiveData<List<PlayerTeam>> getPlayerTeamsByPlayer(int playerId);

    // These method names end up getting ridiculous because of the table name, but you can see that
    // this is the join method that we talked about that pulls players and playerteams as a single
    // object when bringing in what we want for a team. This keeps the correct players with their
    // corresponding playerteams at all times so we don't have to do a search to keep them together.
    @Query("SELECT players.*, playerteams.* FROM players INNER JOIN playerteams ON players.playerId = playerteams.playerTeam_playerId WHERE playerteams.teamId LIKE :teamId")
    List<PlayerAndPlayerTeam> getPlayersAndPlayerTeamsByTeam(int teamId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlayerTeam(PlayerTeam playerTeam);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllPlayerTeams(List<PlayerTeam> playerTeams);

    @Delete
    void deletePlayer(Player... players);
}
