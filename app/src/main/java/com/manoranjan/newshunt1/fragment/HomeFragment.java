package com.manoranjan.newshunt1.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.manoranjan.newshunt1.Adaptor.TabAdapter;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.StaticData.CatagoryList;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    View v;
    ProgressDialog progressDialog;
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager viewPager;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait ...");
        viewPager = v.findViewById(R.id.viewpager);
        mTabLayout = v.findViewById(R.id.tabs);
        for (int k = 0; k < CatagoryList.catagoryModels1.size(); k++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(CatagoryList.catagoryModels1.get(k).getName()));
        }
        TabAdapter adapter;
        adapter = new TabAdapter
                (getActivity().getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        //init();
    }

}


