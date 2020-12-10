package com.manoranjan.newshunt1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.manoranjan.newshunt1.Adaptor.AdslistAdaptor;
import com.manoranjan.newshunt1.Adaptor.BrowseProductslistAdaptor;
import com.manoranjan.newshunt1.Adaptor.ProductByCatAdaptor;
import com.manoranjan.newshunt1.Model.NewsListModel;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.StaticData.CatagoryList;

import java.util.ArrayList;
import java.util.List;

public class MyAdsActivity extends AppCompatActivity {
    RecyclerView  recyclerviewads;
    AdslistAdaptor adslistAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddAdsActivity.class));
            }
        });
        recyclerviewads = findViewById(R.id.recyclerviewads);
        recyclerviewads.setHasFixedSize(true);
        recyclerviewads.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adslistAdaptor = new AdslistAdaptor(getApplicationContext(), CatagoryList.newsListModels.subList(0, 3));
        recyclerviewads.setAdapter(adslistAdaptor);
    }
}
