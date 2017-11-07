package com.kizxm.whatariot.adapters;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.kizxm.whatariot.R;
import com.kizxm.whatariot.models.Champion;
import com.kizxm.whatariot.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;


public class FirebaseChampionViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

    View mView;
    Context mContext;
    public ImageView mChampionImageView;

    public FirebaseChampionViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindChampion(Champion champion) {
        mChampionImageView = (ImageView) mView.findViewById(R.id.championImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.championNameTextView);
        TextView dataTextView = (TextView) mView.findViewById(R.id.dataTextView);
        TextView idTextView = (TextView) mView.findViewById(R.id.idTextView);

        Picasso.with(mContext)
                .load(champion.getImage_url())
                .into(mChampionImageView);

        nameTextView.setText(champion.getName());
        idTextView.setText("Id# " + champion.getId());
        dataTextView.setText(champion.getHp() + " Base HP");
    }

    @Override
    public void onItemSelected() {
//        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
//                R.animator.drag_scale_on);
//        set.setTarget(itemView);
//        set.start();
    }

    @Override
        public void onItemClear() {
//            AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
//                    R.animator.drag_scale_off);
//            set.setTarget(itemView);
//            set.start();
        }

    }