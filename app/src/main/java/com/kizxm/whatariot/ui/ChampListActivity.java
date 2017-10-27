package com.kizxm.whatariot.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kizxm.whatariot.Constants;
import com.kizxm.whatariot.R;
import com.kizxm.whatariot.adapters.ChampionListAdapter;
import com.kizxm.whatariot.models.Champion;
import com.kizxm.whatariot.services.PandaService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Response;

public class ChampListActivity extends AppCompatActivity {
    private SharedPreferences mSharedPreferences;
    private String mRecentChampion;

    public static final String TAG = ChampListActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private ChampionListAdapter mAdapter;

    public ArrayList<Champion> champions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String champion = intent.getStringExtra("champion");

        getChampions(champion);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentChampion = mSharedPreferences.getString(Constants.PREFERENCES_CHAMPION_KEY, champion);

        Log.d("Shared Pref champ", mRecentChampion);

    }

    private void getChampions(final String champion) {
        final PandaService pandaService = new PandaService();
        pandaService.findChampions(champion, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                   champions = pandaService.processResults(response);

                   ChampListActivity.this.runOnUiThread(new Runnable() {

                           @Override
                           public void run() {
                               mAdapter = new ChampionListAdapter(getApplicationContext(), champions);
                               mRecyclerView.setAdapter(mAdapter);
                               RecyclerView.LayoutManager layoutManager =
                                       new LinearLayoutManager(ChampListActivity.this);
                               mRecyclerView.setLayoutManager(layoutManager);
                               mRecyclerView.setHasFixedSize(true);
                           }
                   });
            }

        });
    }
}
