package com.kizxm.whatariot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChampActivity extends AppCompatActivity {

    public static final String TAG = ChampActivity.class.getSimpleName();

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
        getChampions(champion);
    }

    private void getChampions(String champion) {
        final PandaService pandaService = new PandaService();
        pandaService.findChampions(champion, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
