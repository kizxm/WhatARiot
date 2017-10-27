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

import com.kizxm.whatariot.Constants;
import com.kizxm.whatariot.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind (R.id.championButton) Button mChampionButton;
    @Bind (R.id.championEditText) EditText mChampionEditText;
    @Bind (R.id.riotView) TextView mRiotTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Typeface deliFont = Typeface.createFromAsset(getAssets(), "fonts/delrium.ttf");
        mRiotTextView.setTypeface(deliFont);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mChampionButton.setOnClickListener(this);
    }

            @Override
            public void onClick(View v) {
                if (v == mChampionButton) {
                    String champion = mChampionEditText.getText().toString();
                    Intent intent = new Intent(MainActivity.this, ChampListActivity.class);
                    intent.putExtra("champion", champion);
                    startActivity(intent);
                }
            }

        private void addToSharedPreferences(String champion) {
        mEditor.putString(Constants.PREFERENCES_CHAMPION_KEY, champion).apply();
    }
}
