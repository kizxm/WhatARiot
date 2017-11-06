package com.kizxm.whatariot.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kizxm.whatariot.Constants;
import com.kizxm.whatariot.R;
import com.kizxm.whatariot.models.Champion;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChampionDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.championImageView) ImageView mImageLabel;
    @Bind(R.id.championNameTextView) TextView mNameLabel;
    @Bind(R.id.dataTextView) TextView mDataLabel;
    @Bind(R.id.mpTextView) TextView mMpLabel;
    @Bind(R.id.idTextView) TextView mIdLabel;
    @Bind(R.id.largeImageTextView) TextView mLargeImageLabel;
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

        Picasso.with(view.getContext()).load(mChampion.getImage_url()).into(mImageLabel);

        mNameLabel.setText(mChampion.getName());
        mIdLabel.setText("Id# " + mChampion.getId());
        mDataLabel.setText(mChampion.getHp() + " Base HP");
        mMpLabel.setText(mChampion.getMp() + " Base MP");
        mLargeImageLabel.setText(mChampion.getBig_image_url());

        mLargeImageLabel.setOnClickListener(this);
        mSaveChampionButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mLargeImageLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mChampion.getBig_image_url()));
                    Log.d("Saved champ2:", "hello2");
            startActivity(webIntent);
        }
        if (v == mSaveChampionButton) {
            DatabaseReference championRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_CHAMPIONS);
            championRef.push().setValue(mChampion);
            Log.d("Saved champ:", "hello");
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }
}
