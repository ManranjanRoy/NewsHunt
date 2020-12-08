package com.manoranjan.newshunt1.Adaptor;

import com.manoranjan.newshunt1.Model.CategoryModel;
import com.manoranjan.newshunt1.fragment.DynamicFragment;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class DynamicFragmentAdapter extends FragmentStatePagerAdapter {
    List<CategoryModel> catagoryModels;
    private int mNumOfTabs;

    public DynamicFragmentAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        // Log.d("position", String.valueOf(position))
        Fragment frag = DynamicFragment.addfrag(1);
        return frag;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}