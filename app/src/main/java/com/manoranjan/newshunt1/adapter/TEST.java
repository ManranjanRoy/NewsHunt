package com.manoranjan.newshunt1.adapter;


import com.manoranjan.newshunt1.fragment.DynamicVideoFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TEST
        extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public TEST(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        return DynamicVideoFragment.addfrag(position);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}