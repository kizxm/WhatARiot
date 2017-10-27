package com.kizxm.whatariot.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kizxm.whatariot.R;
import com.kizxm.whatariot.adapters.ChampionPagerAdapter;
import com.kizxm.whatariot.models.Champion;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChampionDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private ChampionPagerAdapter adapterViewPager;
    ArrayList<Champion> mChampions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_detail);
        ButterKnife.bind(this);

        mChampions = Parcels.unwrap(getIntent().getParcelableExtra("champions"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ChampionPagerAdapter(getSupportFragmentManager(), mChampions);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
