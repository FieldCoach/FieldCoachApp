package fieldcoach.github.com.fieldcoachapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import fieldcoach.github.com.fieldcoachapp.ui.HomeFragment;
import fieldcoach.github.com.fieldcoachapp.ui.SquadFragment;
import fieldcoach.github.com.fieldcoachapp.ui.SquadListFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                fragment = new SquadFragment();
                selected = true;
                break;
            case R.id.action_squad_list:
                fragment = new SquadListFragment();
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

}
