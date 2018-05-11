package fieldcoach.github.com.fieldcoachapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import fieldcoach.github.com.fieldcoachapp.data.AppDatabase;
import fieldcoach.github.com.fieldcoachapp.model.Player;

/**
 * ViewModel class for Player objects.
 */
public class PlayerViewModel extends AndroidViewModel {
    private LiveData<List<Player>> mPlayers;
    private LiveData<Player> mPlayer;
    private AppDatabase mDatabase;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        mDatabase = AppDatabase.getInstance(application);
    }

    public LiveData<List<Player>> getAllPlayers() {
        if(mPlayers == null) {
            mPlayers = mDatabase.playerDao().getAllPlayers();
        }
        return mPlayers;
    }

    public LiveData<Player> getPlayer(int playerId) {
        mPlayer = mDatabase.playerDao().getPlayer(playerId);
        return mPlayer;
    }

    public void insertAllPlayers(List<Player> players) {
        new InsertAllPlayersAsyncTask(players).execute();
    }

    public void insertPlayer(Player player) {
        new InsertPlayerAsyncTask(player).execute();
    }

    public void deletePlayer(Player player) {
        new DeletePlayerAsyncTask(player).execute();
    }

    private class InsertPlayerAsyncTask extends AsyncTask<Void, Void, Void> {
        private Player player;

        public InsertPlayerAsyncTask(Player player) {
            this.player = player;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDatabase.playerDao().insertPlayer(player);
            return null;
        }
    }

    private class InsertAllPlayersAsyncTask extends AsyncTask<Void, Void, Void> {
        private List<Player> players;

        public InsertAllPlayersAsyncTask(List<Player> players) {
            this.players = players;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDatabase.playerDao().insertAllPlayers(players);
            return null;
        }
    }

    private class DeletePlayerAsyncTask extends AsyncTask<Void, Void, Void> {
        private Player player;

        public DeletePlayerAsyncTask(Player player) {
            this.player = player;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDatabase.playerDao().deletePlayer(player);
            return null;
        }
    }
}
