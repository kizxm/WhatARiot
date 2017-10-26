package com.kizxm.whatariot.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kizxm.whatariot.R;
import com.kizxm.whatariot.models.Champion;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChampionListAdapter extends RecyclerView.Adapter<ChampionListAdapter.ChampionViewHolder> {
    private ArrayList<Champion> mChampions = new ArrayList<>();
    private Context mContext;

    public ChampionListAdapter(Context context, ArrayList<Champion> champions) {
        mContext = context;
        mChampions = champions;
    }

    @Override
    public ChampionListAdapter.ChampionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.champ_list_item, parent, false);
        ChampionViewHolder viewHolder = new ChampionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (ChampionListAdapter.ChampionViewHolder holder, int position) {
        holder.bindChampion(mChampions.get(position));
    }

    @Override
    public int getItemCount() {
        return mChampions.size();
    }

    public class ChampionViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.championImageView) ImageView mChampionImageView;
        @Bind(R.id.championNameTextView) TextView mNameTextView;
        @Bind(R.id.dataTextView) TextView mDataTextView;
        @Bind(R.id.idTextView) TextView mIdTextView;

        private Context mContext;

        public ChampionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindChampion(Champion champion) {
            mNameTextView.setText(champion.getName());
            mDataTextView.setText(champion.getHp() + " HP");
            mIdTextView.setText("Id # " + champion.getId());

        }

    }
}
