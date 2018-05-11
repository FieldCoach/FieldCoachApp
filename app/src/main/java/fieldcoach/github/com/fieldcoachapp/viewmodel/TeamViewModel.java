package fieldcoach.github.com.fieldcoachapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import fieldcoach.github.com.fieldcoachapp.data.AppDatabase;
import fieldcoach.github.com.fieldcoachapp.model.Team;

/**
 * ViewModel class for Team objects.
 */
public class TeamViewModel extends AndroidViewModel {
    private LiveData<List<Team>> mTeams;
    private LiveData<Team> mTeam;
    private AppDatabase mDatabase;

    public TeamViewModel(@NonNull Application application) {
        super(application);
        mDatabase = AppDatabase.getInstance(application);
    }

    public LiveData<List<Team>> getTeams() {
        if(mTeams == null) {
            mTeams = mDatabase.teamDao().getAllTeams();
        }
        return mTeams;
    }

    public LiveData<Team> getTeam(int teamId) {
        mTeam = mDatabase.teamDao().getTeam(teamId);
        return mTeam;
    }

    public void insertTeam(Team team) {
        new InsertTeamAsyncTask(team).execute();
    }

    public void insertAllTeams(List<Team> teams) {
        new InsertAllTeamsAsyncTask(teams).execute();
    }

    public void deleteTeam(Team team) {
        new DeleteTeamAsyncTask(team).execute();
    }

    private class InsertTeamAsyncTask extends AsyncTask<Void, Void, Void> {
        private Team team;

        public InsertTeamAsyncTask(Team team) {
            this.team = team;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDatabase.teamDao().insertTeam(team);
            return null;
        }
    }

    private class InsertAllTeamsAsyncTask extends AsyncTask<Void, Void, Void> {
        private List<Team> teams;

        public InsertAllTeamsAsyncTask(List<Team> teams) {
            this.teams = teams;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDatabase.teamDao().insertAllTeams(teams);
            return null;
        }
    }

    private class DeleteTeamAsyncTask extends AsyncTask<Void, Void, Void> {
        private Team team;

        public DeleteTeamAsyncTask(Team team) {
            this.team = team;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDatabase.teamDao().deleteTeam(team);
            return null;
        }
    }
}
