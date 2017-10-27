package com.kizxm.whatariot.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kizxm.whatariot.models.Champion;
import com.kizxm.whatariot.ui.ChampionDetailFragment;

import java.util.ArrayList;

public class ChampionPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Champion> mChampions;

    public ChampionPagerAdapter(FragmentManager fm, ArrayList<Champion> champions) {
        super(fm);
        mChampions = champions;
    }

    @Override
    public Fragment getItem(int position) {
        return ChampionDetailFragment.newInstance(mChampions.get(position));
    }

    @Override
    public int getCount() {
        return mChampions.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChampions.get(position).getName();
    }
}

