package fieldcoach.github.com.fieldcoachapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.List;

import fieldcoach.github.com.fieldcoachapp.data.dao.PlayerDao;
import fieldcoach.github.com.fieldcoachapp.data.dao.PlayerTeamDao;
import fieldcoach.github.com.fieldcoachapp.data.dao.TeamDao;
import fieldcoach.github.com.fieldcoachapp.model.Player;
import fieldcoach.github.com.fieldcoachapp.model.PlayerTeam;
import fieldcoach.github.com.fieldcoachapp.model.Team;

/**
 * App database class.
 */
@Database(entities = {Player.class, Team.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "fieldcoach-db";
    private static AppDatabase sInstance;
    public abstract PlayerDao playerDao();
    public abstract PlayerTeamDao playerTeamDao();
    public abstract TeamDao teamDao();

    public synchronized static AppDatabase getInstance(Context context) {
        if(sInstance == null) {
            sInstance = buildDatabase(context.getApplicationContext());
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context appContext) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME).build();
    }

    public static LiveData<List<Player>> getAllPlayers(final AppDatabase database) {
        return database.playerDao().getAllPlayers();
    }

    public static LiveData<Player> getPlayer(final AppDatabase database, int playerId) {
        return database.playerDao().getPlayer(playerId);
    }

    public static void insertPlayer(final AppDatabase database, final List<Player> players) {
        database.runInTransaction(new Runnable() {
            @Override
            public void run() {
                database.playerDao().insertAllPlayers(players);
            }
        });
    }

    public static LiveData<List<PlayerTeam>> getAllPlayerTeams(final  AppDatabase database) {
        return database.playerTeamDao().getAllPlayerTeams();
    }

    public static LiveData<PlayerTeam> getPlayerTeam(final  AppDatabase database, int playerTeamId) {
        return database.playerTeamDao().getPlayerTeam(playerTeamId);
    }

    public static LiveData<List<PlayerTeam>> getPlayerTeamsByTeam(final AppDatabase database, int teamId) {
        return database.playerTeamDao().getPlayerTeamsByTeam(teamId);
    }

    public static LiveData<List<Team>> getAllTeams(final AppDatabase database) {
        return database.teamDao().getAllTeams();
    }

    public static LiveData<Team> getTeam(final AppDatabase database, int teamId) {
        return database.teamDao().getTeam(teamId);
    }

    public static void insertTeam(final AppDatabase database, final Team team) {
        database.runInTransaction(new Runnable() {
            @Override
            public void run() {
                database.teamDao().insertTeam(team);
            }
        });
    }

    public static void insertAllTeams(final AppDatabase database, final List<Team> teams) {
        database.runInTransaction(new Runnable() {
            @Override
            public void run() {
                database.teamDao().insertAllTeams(teams);
            }
        });
    }
}
