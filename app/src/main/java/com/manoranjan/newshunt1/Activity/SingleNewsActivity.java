package com.manoranjan.newshunt1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.manoranjan.newshunt1.Adaptor.BrowseSingleNewsAdaptor;
import com.manoranjan.newshunt1.Adaptor.ProductByCatAdaptor;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.StaticData.CatagoryList;
import com.manoranjan.newshunt1.StaticDtaa.StaticData;

public class SingleNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_news);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
       BrowseSingleNewsAdaptor adapter = new BrowseSingleNewsAdaptor(getApplicationContext(), CatagoryList.newsListModels);
        viewPager.setAdapter(adapter);
       // viewPager.setCurrentItem(StaticData.img_postion);

    }
}
