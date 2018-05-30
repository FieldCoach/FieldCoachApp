package fieldcoach.github.com.fieldcoachapp.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fieldcoach.github.com.fieldcoachapp.model.Player;
import fieldcoach.github.com.fieldcoachapp.model.PlayerTeam;

/**
 * PlayerTeam many-to-many relationship data access object.
 */
@Dao
public interface PlayerTeamDao {
    @Query("SELECT * FROM playerteams")
    LiveData<List<PlayerTeam>> getAllPlayerTeams();

    @Query("SELECT * FROM playerteams WHERE ID = :playerTeamId")
    LiveData<PlayerTeam> getPlayerTeam(int playerTeamId);

    @Query("SELECT * FROM playerteams WHERE "
           + "playerteams.teamId LIKE :teamId AND playerteams.playerId LIKE :playerId")
    LiveData<List<PlayerTeam>> getPlayerTeamsByTeamAndPlayer(int teamId, int playerId);

    @Query("SELECT * FROM playerteams WHERE "
            + "playerteams.teamId LIKE :teamId")
    LiveData<List<PlayerTeam>> getPlayerTeamsByTeam(int teamId);

    @Query("SELECT * FROM playerteams WHERE "
            + "playerteams.playerId LIKE :playerId")
    LiveData<List<PlayerTeam>> getPlayerTeamsByPlayer(int playerId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlayerTeam(PlayerTeam playerTeam);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllPlayerTeams(List<PlayerTeam> playerTeams);

    @Delete
    void deletePlayer(Player... players);
}
