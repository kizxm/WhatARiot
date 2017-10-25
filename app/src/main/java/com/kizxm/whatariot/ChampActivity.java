package com.kizxm.whatariot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChampActivity extends AppCompatActivity {
    @Bind(R.id.championTextView) TextView mChampionTextView;
    @Bind(R.id.listView) ListView mListView;

    private String[] champions = new String[] {"Mi Mero Mole", "Mother's Bistro",
            "Life of Pie", "Screen Door", "Luc Lac", "Sweet Basil",
            "Slappy Cakes", "Equinox", "Miss Delta's", "Andina",
            "Lardo", "Portland City Grill", "Fat Head's Brewery",
            "Chipotle", "Subway"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ);

        ButterKnife.bind(this);
        
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, champions);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String champion = ((TextView)view).getText().toString();
                Toast.makeText(ChampActivity.this, champion, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String champion = intent.getStringExtra("champion");
        mChampionTextView.setText("Champion Data: " + champion);
    }
}
