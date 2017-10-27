package com.kizxm.whatariot.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kizxm.whatariot.R;
import com.kizxm.whatariot.models.Champion;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChampionDetailFragment extends Fragment {
    @Bind(R.id.championImageView) ImageView mImageLabel;
    @Bind(R.id.championNameTextView) TextView mNameLabel;
    @Bind(R.id.dataTextView) TextView mDataLabel;
    @Bind(R.id.idTextView) TextView mIdLabel;
    @Bind(R.id.largeImageTextView) TextView mLargeImageLabel;
    @Bind(R.id.mpTextView) TextView mMpLabel;
    @Bind(R.id.speedTextView) TextView mSpeedLabel;
    @Bind(R.id.saveChampionButton) TextView mSaveChampionButton;

    private Champion mChampion;

    public static ChampionDetailFragment newInstance(Champion champion) {
        ChampionDetailFragment championDetailFragment = new ChampionDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("champion", Parcels.wrap(champion));
        championDetailFragment.setArguments(args);
        return championDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChampion = Parcels.unwrap(getArguments().getParcelable("champion"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champion_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mChampion.getBig_image_url()).into(mImageLabel);

        mNameLabel.setText(mChampion.getName());
        mDataLabel.setText(mChampion.getHp() + " Base HP");
        mIdLabel.setText("id# " + mChampion.getId());
        mLargeImageLabel.setText(mChampion.getBig_image_url());
        mMpLabel.setText(mChampion.getMp() + " Base MP");
        mSpeedLabel.setText(mChampion.getMovespeed() + " Base Move Speed");

        return view;
    }

}
