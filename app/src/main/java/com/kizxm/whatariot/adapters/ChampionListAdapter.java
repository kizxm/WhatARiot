package com.kizxm.whatariot.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kizxm.whatariot.Constants;
import com.kizxm.whatariot.R;
import com.kizxm.whatariot.models.Champion;
import com.kizxm.whatariot.ui.ChampionDetailActivity;
import com.kizxm.whatariot.ui.ChampionDetailFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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

    public class ChampionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.championImageView) ImageView mChampionImageView;
        @Bind(R.id.championNameTextView) TextView mNameTextView;
        @Bind(R.id.dataTextView) TextView mDataTextView;
        @Bind(R.id.idTextView) TextView mIdTextView;

        private Context mContext;
        private int mOrientation;


        public ChampionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

            mOrientation = itemView.getResources().getConfiguration().orientation;
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
        }

    public void bindChampion(Champion champion) {
        Picasso.with(mContext).load(champion.getImage_url()).into(mChampionImageView);
        mNameTextView.setText(champion.getName());
        mDataTextView.setText(champion.getHp() + " HP");
        mIdTextView.setText("Id # " + champion.getId());

    }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, ChampionDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_CHAMPIONS, Parcels.wrap(mChampions));
                mContext.startActivity(intent);
            }
            Intent intent = new Intent(mContext, ChampionDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("champions", Parcels.wrap(mChampions));
            mContext.startActivity(intent);
        }



    private void createDetailFragment(int position) {
        ChampionDetailFragment detailFragment = ChampionDetailFragment.newInstance(mChampions, position);
        FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.championDetailContainer, detailFragment);
        ft.commit();
    }

    }

}