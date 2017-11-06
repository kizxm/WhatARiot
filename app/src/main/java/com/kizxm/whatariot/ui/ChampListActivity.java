package com.kizxm.whatariot.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private ChampionListAdapter mAdapter;
    public ArrayList<Champion> mChampions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String champion = intent.getStringExtra("champion");

        getChampions(champion);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentChampion = mSharedPreferences.getString(Constants.PREFERENCES_CHAMPION_KEY, null);
        if (mRecentChampion != null) {
            getChampions(mRecentChampion);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
                public boolean onQueryTextSubmit (String query) {
                addToSharedPreferences(query);
                getChampions(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
                   mChampions = pandaService.processResults(response);


                   ChampListActivity.this.runOnUiThread(new Runnable() {

                           @Override
                           public void run() {
                               mAdapter = new ChampionListAdapter(getApplicationContext(), mChampions);
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

        private void addToSharedPreferences (String champion) {
            mEditor.putString(Constants.PREFERENCES_CHAMPION_KEY, champion).apply();
        }
    }