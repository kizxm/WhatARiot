package com.kizxm.whatariot.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kizxm.whatariot.Constants;
import com.kizxm.whatariot.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mSearchedChampionReference;
    private ValueEventListener mSearchedChampionReferenceListener;

    @Bind (R.id.championButton) Button mChampionButton;
    @Bind (R.id.championEditText) EditText mChampionEditText;
    @Bind (R.id.riotView) TextView mRiotTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedChampionReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_CHAMPION);

        mSearchedChampionReferenceListener = mSearchedChampionReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot championSnapshot : dataSnapshot.getChildren()) {
                    String champion = championSnapshot.getValue().toString();
                    Log.d("Updated", "champion: " + champion);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Typeface deliFont = Typeface.createFromAsset(getAssets(), "fonts/delrium.ttf");
        mRiotTextView.setTypeface(deliFont);

        mChampionButton.setOnClickListener(this);
    }

            @Override
            public void onClick(View v) {
                if (v == mChampionButton) {

                    String champion = mChampionEditText.getText().toString();

                    saveChampionToFirebase(champion);

                    Intent intent = new Intent(MainActivity.this, ChampListActivity.class);
                    intent.putExtra("champion", champion);
                    startActivity(intent);
                }
            }


    public void saveChampionToFirebase(String champion) {
        mSearchedChampionReference.push().setValue(champion);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedChampionReference.removeEventListener(mSearchedChampionReferenceListener);
    }
}
