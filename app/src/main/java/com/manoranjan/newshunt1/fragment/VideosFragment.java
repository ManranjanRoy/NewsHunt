package com.manoranjan.newshunt1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manoranjan.newshunt1.Adaptor.DynamicVideoFragmentAdapter;
import com.manoranjan.newshunt1.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.manoranjan.newshunt1.Adaptor.DynamicFragmentAdapter;
import com.manoranjan.newshunt1.Adaptor.DynamicVideoFragmentAdapter;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.StaticData.CatagoryList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    View v;
    public static VideosFragment newInstance() {
        return new VideosFragment();
    }

    public VideosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_videos, container, false);
        viewPager = v.findViewById(R.id.viewpager);
        mTabLayout =  v.findViewById(R.id.tabs);
        init();
        return  v;
    }
    @Override
    public void onResume() {
        super.onResume();
        //  init();
    }
    private void init() {

       /* viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        Toast.makeText(getContext(),  tabLayout.getSelectedTabPosition(), Toast.LENGTH_SHORT).show();*/

        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setDynamicFragmentToTabLayout();

    }
    private void setDynamicFragmentToTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("All"));
        for (int i = 0; i < CatagoryList.catagoryModels.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(CatagoryList.catagoryModels.get(i).getName()));
        }
        DynamicVideoFragmentAdapter mDynamicFragmentAdapter =
                new DynamicVideoFragmentAdapter(getActivity().getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(mDynamicFragmentAdapter);
        viewPager.setCurrentItem(0);
    }



}

