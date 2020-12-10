package com.manoranjan.newshunt1.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.manoranjan.newshunt1.Adaptor.BrowseProductslistAdaptor;
import com.manoranjan.newshunt1.Adaptor.ReporterNewslistAdaptor;
import com.manoranjan.newshunt1.Model.NewsData;
import com.manoranjan.newshunt1.Model.News_itemsModel;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.Response.NewsResponse;
import com.manoranjan.newshunt1.Service.CountryService;
import com.manoranjan.newshunt1.StaticData.CatagoryList;

import java.util.ArrayList;
import java.util.List;

public class ReportersNewsActivity extends AppCompatActivity {
    public static ReporterNewslistAdaptor browseProductslistAdaptor;
    RecyclerView nrecyclerview;
    List<News_itemsModel> categoryModelList = new ArrayList<>();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporters_news);
        progressDialog = new ProgressDialog(ReportersNewsActivity.this);
        progressDialog.setMessage("Please Wait ...");
        nrecyclerview = findViewById(R.id.recyclerviewfproduct);
        nrecyclerview.setHasFixedSize(true);
        nrecyclerview.setLayoutManager(new LinearLayoutManager(ReportersNewsActivity.this));
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showalert();
                //startActivity(new Intent(getApplicationContext(),AddAdsActivity.class));
            }
        });
        get_news(4);
    }
    public void showalert(){
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        final BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.SheetDialog);
        dialog.setContentView(view);
        dialog.show();
        LinearLayout image = view.findViewById(R.id.uploadimage);
        LinearLayout video = view.findViewById(R.id.uploadvideo);
        ImageView cancel = view.findViewById(R.id.cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddNewsActivity.class));
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddVideoActivity.class));
            }
        });
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
                categoryModelList.clear();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getError().equals("false")) {
                        Log.d("response", response.body().toString());
                        NewsData loginResponse = response.body().getData();
                        categoryModelList = loginResponse.getNews_items();
                        // CatagoryList.newsListModels1=categoryModelList;
                        Log.d("newssize", String.valueOf(categoryModelList.size()));
                        ReporterNewslistAdaptor browseProductslistAdaptor =
                                new ReporterNewslistAdaptor(getApplicationContext(), categoryModelList);
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
