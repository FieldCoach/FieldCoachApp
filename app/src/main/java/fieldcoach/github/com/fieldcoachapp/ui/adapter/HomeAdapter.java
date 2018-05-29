package fieldcoach.github.com.fieldcoachapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fieldcoach.github.com.fieldcoachapp.R;
import fieldcoach.github.com.fieldcoachapp.model.Team;

/**
 * Created by Aaron Crutchfield on 5/18/2018.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{
    private List<Team> teams;
    private ListItemClickListener listener;

    public interface ListItemClickListener {
        void onCardClicked(Team team);
    }

    public HomeAdapter(ListItemClickListener listener, List<Team> teams) {
        this.listener = listener;
        this.teams = teams;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_home, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.onBindCard(teams.get(position));
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void updateList(List<Team> teams) {
        this.teams = teams;
        notifyDataSetChanged();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_team_name)
        TextView teamName;
        @BindView(R.id.tv_team_size)
        TextView teamSize;
        @BindView(R.id.tv_team_record)
        TextView teamRecord;

        HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCardClicked(teams.get(getAdapterPosition()));
                }
            });
        }

        void onBindCard(Team team) {
            teamName.setText(team.getName());
            teamSize.setText(itemView.getContext().getString(R.string.team_size, team.getSize()));
            teamRecord.setText(team.getRecord());
        }
    }
}
