package fieldcoach.github.com.fieldcoachapp.ui.fragment;


import android.app.Application;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fieldcoach.github.com.fieldcoachapp.R;
import fieldcoach.github.com.fieldcoachapp.model.Player;
import fieldcoach.github.com.fieldcoachapp.model.Team;
import fieldcoach.github.com.fieldcoachapp.ui.adapter.HomeAdapter;
import fieldcoach.github.com.fieldcoachapp.viewmodel.TeamViewModel;

/**
 * Displays sneak peak info for active squad, upcoming fixtures, table info, fixtures, training schedule.
 * Has an App Bar menu for Settings.
 */

public class HomeFragment extends Fragment
    implements HomeAdapter.HomeAdapterInteractionListener{
    @BindView(R.id.rv_home)
    RecyclerView recyclerView;
    private HomeAdapter adapter;
    private TeamViewModel teamViewModel;
    private Unbinder unbinder;
    private OnHomeInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TeamStatsFragment.
     */
    public static HomeFragment newInstance(String param1, String param2) {
        return new HomeFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Context context = getContext();
        Application application = (Application) Objects.requireNonNull(context).getApplicationContext();
        teamViewModel = new TeamViewModel(application);
        teamViewModel.getTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable List<Team> teams) {
                if(adapter == null) {
                    adapter = new HomeAdapter(HomeFragment.this, teams);
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
        if (context instanceof OnHomeInteractionListener) {
            mListener = (OnHomeInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnHomeInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        final EditText editText = new EditText(getContext());
        editText.setSingleLine();
        builder.setTitle("Enter a team name")
                .setView(editText)
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                        if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            addTeamToDatabase(editText);
                            dialogInterface.dismiss();
                            return true;
                        }
                        return false;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addTeamToDatabase(editText);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create().show();
    }

    private void addTeamToDatabase(EditText editText) {
        Team team = new Team();
        team.setPlayerList(new ArrayList<Player>());
        String teamName = editText.getText().toString();
        if (!teamName.equals("")) {
            team.setTeamName(teamName);
            promptForTeamPlayers(team);
        } else {
            Toast.makeText(getContext(), "Please enter a valid name", Toast.LENGTH_SHORT).show();
        }
    }

    private void promptForTeamPlayers(final Team team) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        final List<Player> playerList = team.getPlayerList();
        builder.setTitle("Enter player info")
                .setView(R.layout.dialog_player_info_form)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        playerList.add(getPlayerInfo(dialogInterface));
                        team.setPlayerList(playerList);
                        teamViewModel.insertTeam(team);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }

    private Player getPlayerInfo(DialogInterface dialogInterface) {
        Dialog dialog = (Dialog) dialogInterface;
        EditText etFirstName = dialog.findViewById(R.id.et_first_name);
        EditText etLastName = dialog.findViewById(R.id.et_last_name);
        EditText etJerseyNumber = dialog.findViewById(R.id.et_jersey_number);
        EditText etPosition = dialog.findViewById(R.id.et_position);
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String jerseyNumber = etJerseyNumber.getText().toString();
        String position = etPosition.getText().toString();
        Player player = new Player();
        if (!(firstName.equals("") || lastName.equals("") ||
                jerseyNumber.equals("") || position.equals(""))) {
            player.setName(firstName);
            player.setLastName(lastName);
            player.setJerseyNumber(Integer.valueOf(jerseyNumber));
            player.setPosition(position);
            return player;
        }
        return null;
    }

    @Override
    public void onCardClicked(Team team) {
        promptForTeamPlayers(team);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnHomeInteractionListener {
    }
}