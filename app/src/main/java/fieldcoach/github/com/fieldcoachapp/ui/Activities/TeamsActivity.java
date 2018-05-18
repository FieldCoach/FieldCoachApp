package fieldcoach.github.com.fieldcoachapp.ui.Activities;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import fieldcoach.github.com.fieldcoachapp.R;
import fieldcoach.github.com.fieldcoachapp.ui.Fragments.HomeFragment;
import fieldcoach.github.com.fieldcoachapp.ui.Fragments.TeamDetailsFragment;
import fieldcoach.github.com.fieldcoachapp.ui.Fragments.SquadFixtureFragment;
import fieldcoach.github.com.fieldcoachapp.ui.Fragments.TeamStatsFragment;
import fieldcoach.github.com.fieldcoachapp.ui.Fragments.TeamTablesFragment;

public class TeamsActivity extends AppCompatActivity
        implements HomeFragment.OnHomeInteractionListener,
                    SquadFixtureFragment.OnSquadFixtureInteractionListener,
                    TeamDetailsFragment.OnTeamDetailsInteractionListener,
                    TeamStatsFragment.OnTeamStatsInteractionListener,
                    TeamTablesFragment.OnTeamTableInteractionListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        bottomNavigationView = findViewById(R.id.navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return selectFragment(item);
            }
        });
    }

    private boolean selectFragment(@NonNull MenuItem item) {
        Fragment fragment = null;
        boolean selected = false;
        switch (item.getItemId()) {
            case R.id.action_home:
                fragment = new HomeFragment();
                selected = true;
                break;
            case R.id.action_squad:
                fragment = new TeamDetailsFragment();
                selected = true;
                break;
            case R.id.action_squad_list:
                fragment = new SquadFixtureFragment();
                selected = true;
                break;
        }

        if (fragment != null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }
        return selected;
    }

    @Override
    public void onHomeInteraction(Uri uri) {

    }

    @Override
    public void onSquadFixtureInteraction(Uri uri) {

    }

    @Override
    public void onTeamDetailsInteraction(Uri uri) {

    }

    @Override
    public void onTeamStatsInteraction(Uri uri) {

    }

    @Override
    public void onTeamTableInteraction(Uri uri) {

    }
}
