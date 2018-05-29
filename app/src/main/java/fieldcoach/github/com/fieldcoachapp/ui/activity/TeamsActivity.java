package fieldcoach.github.com.fieldcoachapp.ui.activity;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import fieldcoach.github.com.fieldcoachapp.R;
import fieldcoach.github.com.fieldcoachapp.ui.fragment.TeamListFragment;
import fieldcoach.github.com.fieldcoachapp.ui.fragment.TeamDetailFragment;
import fieldcoach.github.com.fieldcoachapp.ui.fragment.SquadFixtureFragment;
import fieldcoach.github.com.fieldcoachapp.ui.fragment.TeamStatsFragment;
import fieldcoach.github.com.fieldcoachapp.ui.fragment.TeamTablesFragment;

public class TeamsActivity extends AppCompatActivity
        implements TeamListFragment.FragmentInteractionListener,
                SquadFixtureFragment.OnSquadFixtureInteractionListener,
                TeamDetailFragment.FragmentInteractionListener,
                TeamStatsFragment.OnTeamStatsInteractionListener,
                TeamTablesFragment.OnTeamTableInteractionListener {

    BottomNavigationView bottomNavigationView;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        fragment = TeamListFragment.newInstance(null, null);
        if (fragment != null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }

        bottomNavigationView = findViewById(R.id.navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return selectFragment(item);
            }
        });
    }

    private boolean selectFragment(@NonNull MenuItem item) {
        boolean selected = false;
        switch (item.getItemId()) {
            case R.id.action_teams:
                // TeamsActivity Intent
                selected = true;
                break;
            case R.id.action_training:
                // TrainingActivity Intent
                selected = true;
                break;
            case R.id.action_tactics:
                // TacticsActivity Intent
                selected = true;
                break;
            case R.id.action_board:
                // BoardActivity Intent
                selected = true;
                break;
        }

        // Launch activity using intent

        return selected;
    }

    @Override
    public void onSquadFixtureInteraction(Uri uri) {

    }

    @Override
    public void onTeamStatsInteraction(Uri uri) {

    }

    @Override
    public void onTeamTableInteraction(Uri uri) {

    }
}
