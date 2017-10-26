package com.kizxm.whatariot.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.kizxm.whatariot.R;
import com.kizxm.whatariot.models.Champion;
import com.kizxm.whatariot.services.PandaService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChampActivity extends AppCompatActivity {

    public static final String TAG = ChampActivity.class.getSimpleName();

    @Bind(R.id.championTextView) TextView mChampionTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Champion> champions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ);

        ButterKnife.bind(this);

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
            public void onResponse(Call call, Response response) {
                   champions = pandaService.processResults(response);

                   ChampActivity.this.runOnUiThread(new Runnable() {

                       @Override
                       public void run() {
                           String[] championNames = new String[champions.size()];
                           for (int i = 0; i < championNames.length; i++) {
                               championNames[i] = champions.get(i).getName();
                           }

                           ArrayAdapter adapter = new ArrayAdapter(ChampActivity.this, android.R.layout.simple_list_item_1, championNames);
                           mListView.setAdapter(adapter);

                           for (Champion champion : champions) {
                               Log.d(TAG, champion.getName());
                               Log.d(TAG, champion.getId());
                           }

                       }
                   });
            }

        });
    }
}
