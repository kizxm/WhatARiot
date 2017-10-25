package com.kizxm.whatariot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mChampionButton;
    private EditText mChampionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChampionEditText = (EditText) findViewById(R.id.championEditText);
        mChampionButton = (Button) findViewById(R.id.championButton);
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
