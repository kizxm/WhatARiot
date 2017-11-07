package com.kizxm.whatariot.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.kizxm.whatariot.models.Champion;
import com.kizxm.whatariot.ui.ChampionDetailActivity;
import com.kizxm.whatariot.util.ItemTouchHelperAdapter;
import com.kizxm.whatariot.util.OnStartDragListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseChampionListAdapter extends FirebaseRecyclerAdapter<Champion, FirebaseChampionViewHolder>
        implements ItemTouchHelperAdapter {

    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Champion> mChampions = new ArrayList<>();


    public FirebaseChampionListAdapter(Class<Champion> modelClass, int modelLayout,
                                       Class<FirebaseChampionViewHolder> viewHolderClass,
                                       Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mChampions.add(dataSnapshot.getValue(Champion.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseChampionViewHolder viewHolder, Champion model, int position) {
        viewHolder.bindChampion(model);
        viewHolder.mChampionImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChampionDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("champions", Parcels.wrap(mChampions));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mChampions, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mChampions.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Champion champion : mChampions) {
            int index = mChampions.indexOf(champion);
            DatabaseReference ref = getRef(index);
            champion.setIndex(Integer.toString(index));
            ref.setValue(champion);
        }
    }

    @Override
    public void cleanup () {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}