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
 * Displays a list of squads. FAB to add a new squad
 */

public class SquadListFragment extends Fragment {

    public SquadListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_squad_list, container, false);
    }
}
