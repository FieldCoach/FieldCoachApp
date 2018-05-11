package fieldcoach.github.com.fieldcoachapp.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fieldcoach.github.com.fieldcoachapp.model.Player;

/**
 * Player data access object.
 */
@Dao
public interface PlayerDao {
    @Query("SELECT * FROM players")
    LiveData<List<Player>> getAllPlayers();

    @Query("SELECT * FROM players WHERE ID = :playerId")
    LiveData<Player> getPlayer(int playerId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlayer(Player player);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllPlayers(List<Player> players);

    @Delete
    void deletePlayer(Player... players);
}
