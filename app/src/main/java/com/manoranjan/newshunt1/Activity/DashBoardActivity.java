package com.manoranjan.newshunt1.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.manoranjan.newshunt1.Adaptor.AdslistAdaptor;
import com.manoranjan.newshunt1.Adaptor.BrowseProductslistAdaptor;
import com.manoranjan.newshunt1.Adaptor.ProductByCatAdaptor;
import com.manoranjan.newshunt1.Adaptor.ReporterNewslistAdaptor;
import com.manoranjan.newshunt1.Model.NewsData;
import com.manoranjan.newshunt1.Model.NewsListModel;
import com.manoranjan.newshunt1.Model.News_itemsModel;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.Response.NewsResponse;
import com.manoranjan.newshunt1.Service.CountryService;
import com.manoranjan.newshunt1.StaticData.CatagoryList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ProgressDialog progressDialog;
    RecyclerView nrecyclerview, recyclerviewads;
    List<News_itemsModel> news_itemsModels = new ArrayList<>();
    AdslistAdaptor adslistAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        progressDialog = new ProgressDialog(DashBoardActivity.this);
        progressDialog.setMessage("Please Wait ...");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.setDrawerListener(toggle);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.llwallet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MyWalletActivity.class));
            }
        });
        findViewById(R.id.llnews).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ReportersNewsActivity.class));
            }
        });
        findViewById(R.id.llads).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MyAdsActivity.class));
            }
        });
        findViewById(R.id.llcomision).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MyCommisionActivity.class));
            }
        });
        nrecyclerview = findViewById(R.id.recyclerviewfproduct);
        nrecyclerview.setHasFixedSize(true);
        nrecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerviewads = findViewById(R.id.recyclerviewads);
        recyclerviewads.setHasFixedSize(true);
        recyclerviewads.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        adslistAdaptor = new AdslistAdaptor(getApplicationContext(), CatagoryList.newsListModels.subList(0, 3));
        recyclerviewads.setAdapter(adslistAdaptor);

        get_news(4);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.dash_board, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(), "Camera is clicked", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_gallery) {
            Toast.makeText(getApplicationContext(), "Gallery is clicked", Toast.LENGTH_SHORT).show();

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void get_news(int position) {

        Showprogess();
        CountryService countryService = new CountryService();
        countryService.getAPI().get_news("get-news?category=" + position + "&page=1").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                Log.d("response", response.body().toString());
                dismissproggress();
                // CatagoryList.newsListModels1.clear();
                news_itemsModels.clear();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getError().equals("false")) {
                        Log.d("response", response.body().toString());
                        NewsData loginResponse = response.body().getData();
                        news_itemsModels = loginResponse.getNews_items();
                        // CatagoryList.newsListModels1=categoryModelList;
                        Log.d("newssize", String.valueOf(news_itemsModels.size()));
                        ReporterNewslistAdaptor browseProductslistAdaptor =
                                new ReporterNewslistAdaptor(getApplicationContext(), news_itemsModels);
                        nrecyclerview.setAdapter(browseProductslistAdaptor);
                        //startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                    } else {
                        dismissproggress();
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                dismissproggress();
                get_news(4);
            }
        });
    }

    private void Showprogess() {
        progressDialog.show();
    }

    private void dismissproggress() {
        progressDialog.dismiss();
    }
}