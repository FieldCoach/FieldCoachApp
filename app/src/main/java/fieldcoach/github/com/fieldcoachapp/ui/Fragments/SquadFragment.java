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
 * Displays active/selected squad.(Pitch/Field View). Team formation, substitutions, tactics etc.
 * set within this fragment.
 */

public class SquadFragment extends Fragment {

    public SquadFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_squad, container, false);
    }
}
