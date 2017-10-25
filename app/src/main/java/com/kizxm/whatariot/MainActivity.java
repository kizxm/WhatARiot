package com.kizxm.whatariot;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
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

        mChampionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String champion = mChampionEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, ChampActivity.class);
                intent.putExtra("champion", champion);
                        startActivity(intent);
            }
        });
    }
}
