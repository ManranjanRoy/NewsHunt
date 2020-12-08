package com.manoranjan.newshunt1.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.manoranjan.newshunt1.Adaptor.VideoTabAdapter;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.StaticData.CatagoryList;
import com.manoranjan.newshunt1.StaticData.StaticData;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {
    ProgressDialog progressDialog;
    View v;
    private Toolbar toolbar;
    private TabLayout mTabLayout;
    private ViewPager viewPager;

    public VideosFragment() {
        // Required empty public constructor
    }

    public static VideosFragment newInstance() {
        return new VideosFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_videos, container, false);

        viewPager = v.findViewById(R.id.viewpager1);
        mTabLayout = v.findViewById(R.id.tabs1);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait ...");
        for (int k = 0; k < CatagoryList.catagoryModels1.size(); k++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(CatagoryList.catagoryModels1.get(k).getName()));
        }
        VideoTabAdapter adapter;
        adapter = new VideoTabAdapter
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
        //init();
        return v;
    }

    private void init() {
        mTabLayout.addTab(mTabLayout.newTab().setText("ALL"));
        mTabLayout.addTab(mTabLayout.newTab().setText("ALL1"));
        if (CatagoryList.catagoryModels1.size() > 0) {
            for (int i = 0; i < CatagoryList.catagoryModels1.size(); i++) {
                mTabLayout.addTab(mTabLayout.newTab().setText(CatagoryList.catagoryModels1.get(i).getName()));
            }
            VideoTabAdapter tabAdapter =
                    new VideoTabAdapter(getActivity().getSupportFragmentManager(), mTabLayout.getTabCount());
            viewPager.setAdapter(tabAdapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
            mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                    StaticData.tabid = String.valueOf(tab.getPosition());
                    Log.d("p", String.valueOf(tab.getPosition()));
                    //get_news(tab.getPosition());

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    StaticData.tabid = String.valueOf(tab.getPosition());
                    Log.d("p", String.valueOf(tab.getPosition()));
                    //get_news(tab.getPosition());
                }
            });
        }
    }

    private void Showprogess() {
        progressDialog.show();
    }

    private void dismissproggress() {
        progressDialog.dismiss();
    }
   /* public void get_news(int position) {

        CountryService countryService=new CountryService();
        countryService.getAPI().get_news("get-news?category="+CatagoryList.catagoryModels1.get(position).getId()+"&page=1").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                Log.d("response", response.body().toString());

                if (response.isSuccessful() && response.body()!=null) {
                    if (response.body().getError().equals("false")) {
                        Log.d("response", response.body().toString());
                        NewsData loginResponse = response.body().getData();
                        List<News_itemsModel> newslist=loginResponse.getNews_items();
                        CatagoryList.newsListModels1=newslist;
                        Log.d("newssize", String.valueOf(newslist.size()));
                        DynamicFragment.browseProductslistAdaptor.updatedata();
                        //startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                    } else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
            }
        });
    }*/

   /* public void getcategory() {
        // loginView.onError()
        // if (validatefiled()){
        Showprogess();
        CountryService countryService=new CountryService();
        countryService.getAPI().getcategory1(ApiLinks.front_category).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.d("response", response.body().toString());
                dismissproggress() ;
                if (response.isSuccessful() && response.body()!=null) {
                    if (response.body().getError().equals("false")) {
                        Log.d("response", response.body().toString());
                        CategoryData loginResponse = response.body().getData();
                        catagoryModels=loginResponse.getCategory();
                        Log.d("categorysize", String.valueOf(catagoryModels.size()));
                        init();
                        //startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                    } else {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                dismissproggress();
            }
        });
    }*/
}


