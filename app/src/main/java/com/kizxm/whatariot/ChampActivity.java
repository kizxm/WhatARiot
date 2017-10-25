package com.kizxm.whatariot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChampActivity extends AppCompatActivity {
    private TextView mChampionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ);
        mChampionTextView = (TextView) findViewById(R.id.championTextView);
        Intent intent = getIntent();
        String champion = intent.getStringExtra("champion");
        mChampionTextView.setText("Champion Data: " + champion);
    }
}
