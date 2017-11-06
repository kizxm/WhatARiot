package com.kizxm.whatariot.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kizxm.whatariot.Constants;
import com.kizxm.whatariot.R;
import com.kizxm.whatariot.adapters.FirebaseChampionViewHolder;
import com.kizxm.whatariot.models.Champion;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedChampionListActivity extends AppCompatActivity {
    private DatabaseReference mChampionReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_champ);
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mChampionReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CHAMPIONS)
                .child(uid);
        setUpFirebaseAdapter();

    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Champion, FirebaseChampionViewHolder>
                (Champion.class, R.layout.champ_list_item, FirebaseChampionViewHolder.class,
                        mChampionReference) {

            @Override
            protected void populateViewHolder(FirebaseChampionViewHolder viewHolder,
                                              Champion model, int position) {
                viewHolder.bindChampion(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}