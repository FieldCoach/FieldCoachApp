package fieldcoach.github.com.fieldcoachapp.ui.fragments;


import android.app.Application;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
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

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import fieldcoach.github.com.fieldcoachapp.R;
import fieldcoach.github.com.fieldcoachapp.model.Team;
import fieldcoach.github.com.fieldcoachapp.ui.adapters.HomeAdapter;
import fieldcoach.github.com.fieldcoachapp.viewmodel.TeamViewModel;

/**
 * Displays sneak peak info for active squad, upcoming fixtures, table info, fixtures, training schedule.
 * Has an App Bar menu for Settings.
 */

public class HomeFragment extends Fragment {

    @BindView(R.id.rv_home)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private TeamViewModel teamViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnHomeInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TeamStatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        // https://stackoverflow.com/questions/45632920/why-should-one-use-objects-requirenonnull/45632962?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
        Context context = getContext();
        Application application = (Application) Objects.requireNonNull(context).getApplicationContext();
        teamViewModel = new TeamViewModel(application);

        final HomeAdapter adapter = new HomeAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        teamViewModel.getTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable List<Team> teams) {
                adapter.updateList(teams);
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onHomeInteraction(uri);
        }
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
        final Context context = Objects.requireNonNull(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        final EditText editText = new EditText(context);
        editText.setSingleLine();

        builder.setTitle("Enter a team name")
                .setView(editText)
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                        if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                            addTeamToDatabase(editText, context);
                            dialogInterface.dismiss();
                            return true;
                        }
                        return false;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addTeamToDatabase(editText, context);
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

    private void addTeamToDatabase(EditText editText, Context context) {
        Team team = new Team();
        String teamName = editText.getText().toString();
        if (!teamName.equals("")) {
            team.setTeamName(teamName);
            promptForTeamPlayers(team, context);
        } else {
            Toast.makeText(context, "Please enter a valid name", Toast.LENGTH_SHORT).show();
        }
    }

    private void promptForTeamPlayers(Team team, Context context) {
        // TODO: 5/20/2018 promptForTeamPlayers() - Fill in logic for adding players to the team
        teamViewModel.insertTeam(team);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnHomeInteractionListener {
        // TODO: Update argument type and name
        void onHomeInteraction(Uri uri);
    }

}
