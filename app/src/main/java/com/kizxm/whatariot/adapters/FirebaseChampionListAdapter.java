package com.kizxm.whatariot.adapters;


import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.kizxm.whatariot.models.Champion;
import com.kizxm.whatariot.util.ItemTouchHelperAdapter;
import com.kizxm.whatariot.util.OnStartDragListener;

public class FirebaseChampionListAdapter extends FirebaseRecyclerAdapter<Champion, FirebaseChampionViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseChampionListAdapter(Class<Champion> modelClass, int modelLayout,
                                       Class<FirebaseChampionViewHolder> viewHolderClass,
                                       Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
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
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}