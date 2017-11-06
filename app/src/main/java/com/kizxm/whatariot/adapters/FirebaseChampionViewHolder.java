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

public class FirebaseChampionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

View mView;
Context mContext;

    public FirebaseChampionViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindChampion(Champion champion) {
        ImageView championImageView = (ImageView) mView.findViewById(R.id.championImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.championNameTextView);
        TextView dataTextView = (TextView) mView.findViewById(R.id.dataTextView);
        TextView idTextView = (TextView) mView.findViewById(R.id.idTextView);

        Picasso.with(mContext)
                .load(champion.getImage_url())
                .into(championImageView);

        nameTextView.setText(champion.getName());
        idTextView.setText("Id# " + champion.getId());
        dataTextView.setText(champion.getHp() + " Base HP");
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Champion> champions = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CHAMPIONS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    champions.add(snapshot.getValue(Champion.class));
                }
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, ChampionDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("champions", Parcels.wrap(champions));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}