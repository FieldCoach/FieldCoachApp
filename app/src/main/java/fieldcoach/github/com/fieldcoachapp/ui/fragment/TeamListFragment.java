package fieldcoach.github.com.fieldcoachapp.ui.fragment;


import android.app.Application;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fieldcoach.github.com.fieldcoachapp.DummyData;
import fieldcoach.github.com.fieldcoachapp.R;
import fieldcoach.github.com.fieldcoachapp.model.Player;
import fieldcoach.github.com.fieldcoachapp.model.Team;
import fieldcoach.github.com.fieldcoachapp.ui.adapter.TeamListAdapter;
import fieldcoach.github.com.fieldcoachapp.viewmodel.TeamViewModel;

/**
 * Displays sneak peak info for active squad, upcoming fixtures, table info, fixtures, training schedule.
 * Has an App Bar menu for Settings.
 */
public class TeamListFragment extends Fragment
    implements TeamListAdapter.ListItemClickListener {
    @BindView(R.id.rv_home)
    RecyclerView recyclerView;
    private TeamListAdapter adapter;
    private TeamViewModel teamViewModel;
    private int size;
    private Unbinder unbinder;
    private FragmentInteractionListener listener;

    public TeamListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamStatsFragment.
     */
    public static TeamListFragment newInstance(String param1, String param2) {
        return new TeamListFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Context context = getContext();
        Application application = (Application) Objects.requireNonNull(context).getApplicationContext();
        List<Team> staticTeams = DummyData.getTeams();
        teamViewModel = new TeamViewModel(application);
        teamViewModel.insertAllTeams(staticTeams);
        teamViewModel.getTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable List<Team> teams) {
                if(adapter == null) {
                    adapter = new TeamListAdapter(TeamListFragment.this, teams);
                } else {
                    adapter.updateList(teams);
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteractionListener) {
            listener = (FragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHomeInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fab_home)
    public void addNewTeam() {
        promptForTeamInfo();
    }

    private void promptForTeamInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        View view = getLayoutInflater().inflate(R.layout.dialog_create_team, null);
        final EditText teamName = view.findViewById(R.id.et_team_name);
        final Spinner teamSize = view.findViewById(R.id.sp_team_size);
        loadSpinner(teamSize);
        builder.setView(view);
        builder.setTitle(getString(R.string.td_title))
                .setPositiveButton(getString(R.string.dialog_positive_button), null)
                .setNegativeButton(getString(R.string.dialog_negative_button), null);
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamName.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(),
                            getString(R.string.td_empty_name_warning), Toast.LENGTH_SHORT).show();
                } else {
                    addTeamToDatabase(teamName.getText().toString(), size);
                    dialog.dismiss();
                }
            }
        });
    }

    private void addTeamToDatabase(String teamName, int teamSize) {
        Team team = new Team();
        team.setPlayers(new ArrayList<Player>());
        team.setName(teamName);
        team.setSize(teamSize);
        teamViewModel.insertTeam(team);
    }

    private void loadSpinner(Spinner spinner) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.team_sizes_array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        size = 11;
                        break;
                    case 1:
                        size = 10;
                        break;
                    case 2:
                        size = 8;
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                    size = 11;
            }
        });
    }

    @Override
    public void onItemClicked(Team team) {
        // promptForTeamPlayers(team);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface FragmentInteractionListener {
    }
}