package fieldcoach.github.com.fieldcoachapp.ui.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fieldcoach.github.com.fieldcoachapp.R;

/**
 * Displays sneak peak info for active squad, upcoming fixtures, table info, fixtures, training schedule.
 * Has an App Bar menu for Settings.
 */

public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


}
