package fieldcoach.github.com.fieldcoachapp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fieldcoach.github.com.fieldcoachapp.DummyData;
import fieldcoach.github.com.fieldcoachapp.R;

/**
 * Created by Aaron Crutchfield on 5/18/2018.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{


    private List<DummyData> list = new ArrayList<>();
    private HomeAdapterInteractionListener mListener;

    public interface HomeAdapterInteractionListener {
        void onCardClicked(DummyData dummyData);
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

    public void updateList(List<DummyData> dummyDataList) {
        list = dummyDataList;
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

        void onBindCard(DummyData dummyData) {
            tvTitle.setText(dummyData.getTitle());
            tvContent.setText(dummyData.getContent());
        }
    }
}
