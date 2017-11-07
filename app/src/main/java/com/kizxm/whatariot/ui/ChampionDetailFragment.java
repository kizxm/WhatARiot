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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kizxm.whatariot.Constants;
import com.kizxm.whatariot.R;
import com.kizxm.whatariot.models.Champion;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

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
    private ArrayList<Champion> mChampions;
    private int mPosition;


    public static ChampionDetailFragment newInstance(ArrayList<Champion> champions, Integer position) {
        ChampionDetailFragment championDetailFragment = new ChampionDetailFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_CHAMPIONS, Parcels.wrap(champions));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

        championDetailFragment.setArguments(args);
        return championDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChampions = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_CHAMPIONS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mChampion = mChampions.get(mPosition);
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
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference championRef = FirebaseDatabase

                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_CHAMPIONS)
                    .child(uid);

            DatabaseReference pushRef = championRef.push();
            String pushId = pushRef.getKey();
            mChampion.setPushId(pushId);
            pushRef.setValue(mChampion);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }
}
