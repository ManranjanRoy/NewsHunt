package com.manoranjan.newshunt1.Adaptor;

import android.util.Log;

import com.manoranjan.newshunt1.fragment.DynamicVideoFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class VideoTabAdapter
        extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public VideoTabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("tttttt", "Video");
        return DynamicVideoFragment.addfrag(position);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}