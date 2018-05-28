package fieldcoach.github.com.fieldcoachapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fieldcoach.github.com.fieldcoachapp.R;
import fieldcoach.github.com.fieldcoachapp.model.Player;
import fieldcoach.github.com.fieldcoachapp.model.Team;

/**
 * Created by Aaron Crutchfield on 5/18/2018.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{


    private List<Team> list = new ArrayList<>();
    private HomeAdapterInteractionListener mListener;

    public HomeAdapter(HomeAdapterInteractionListener mListener) {
        this.mListener = mListener;
    }

    public interface HomeAdapterInteractionListener {
        void onCardClicked(Team team);
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
        holder.onBindCard(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<Team> teamList) {
        list = teamList;
        notifyDataSetChanged();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvContent;

        HomeViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onCardClicked(list.get(getAdapterPosition()));
                }
            });

            tvTitle = itemView.findViewById(R.id.tv_card_title);
            tvContent = itemView.findViewById(R.id.tv_card_content);
        }

        void onBindCard(Team team) {
            tvTitle.setText(team.getTeamName());

            StringBuilder stringBuilder = new StringBuilder();
            List<Player> playerList = team.getPlayerList();
            if (playerList != null) {
                for (Player player : playerList) {
                    if (player != null) {
                        stringBuilder
                                .append(player.getName())
                                .append(" - ")
                                .append(player.getPosition())
                                .append("\n");
                    }
                }
            }
            tvContent.setText(stringBuilder.toString());
        }
    }
}
