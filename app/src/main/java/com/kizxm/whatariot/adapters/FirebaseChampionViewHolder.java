package com.kizxm.whatariot.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kizxm.whatariot.Constants;
import com.kizxm.whatariot.R;
import com.kizxm.whatariot.models.Champion;
import com.kizxm.whatariot.ui.ChampionDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseChampionViewHolder extends RecyclerView.ViewHolder {

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
}