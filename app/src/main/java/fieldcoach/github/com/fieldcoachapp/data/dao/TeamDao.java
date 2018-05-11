package fieldcoach.github.com.fieldcoachapp.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import fieldcoach.github.com.fieldcoachapp.model.Team;

/**
 * Team database access object.
 */
@Dao
public interface TeamDao {
    @Query("SELECT * FROM teams")
    LiveData<List<Team>> getAllTeams();

    @Query("SELECT * FROM teams WHERE id = :teamId")
    LiveData<Team> getTeam(int teamId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeam(Team team);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTeams(List<Team> teams);

    @Delete
    void deleteTeam(Team... teams);
}
