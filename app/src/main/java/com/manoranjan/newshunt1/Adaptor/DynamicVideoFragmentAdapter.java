package com.manoranjan.newshunt1.Adaptor;

import android.os.Bundle;

import com.manoranjan.newshunt1.fragment.DynamicVideoFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DynamicVideoFragmentAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public DynamicVideoFragmentAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle b = new Bundle();
        b.putInt("position", position);
        Fragment frag = DynamicVideoFragment.addfrag(1);
        frag.setArguments(b);
        return frag;
        // return  new DynamicVideoFragment();
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}