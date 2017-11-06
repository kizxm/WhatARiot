package com.kizxm.whatariot.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.kizxm.whatariot.Constants;
import com.kizxm.whatariot.R;
import com.kizxm.whatariot.adapters.FirebaseChampionListAdapter;
import com.kizxm.whatariot.adapters.FirebaseChampionViewHolder;
import com.kizxm.whatariot.models.Champion;
import com.kizxm.whatariot.util.OnStartDragListener;
import com.kizxm.whatariot.util.SimpleItemTouchHelperCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedChampionListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mChampionReference;
    private FirebaseChampionListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_champ);
        ButterKnife.bind(this);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_CHAMPIONS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mChampionReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CHAMPIONS)
                .child(uid);

        mFirebaseAdapter = new FirebaseChampionListAdapter(Champion.class, R.layout.champion_list_item_drag, FirebaseChampionViewHolder.class,
                mChampionReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}