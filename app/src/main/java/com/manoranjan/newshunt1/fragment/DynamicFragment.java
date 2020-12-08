package com.manoranjan.newshunt1.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.manoranjan.newshunt1.Adaptor.BrowseProductslistAdaptor;
import com.manoranjan.newshunt1.Model.NewsData;
import com.manoranjan.newshunt1.Model.News_itemsModel;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.Response.NewsResponse;
import com.manoranjan.newshunt1.Service.CountryService;
import com.manoranjan.newshunt1.StaticData.CatagoryList;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends Fragment {
    public static BrowseProductslistAdaptor browseProductslistAdaptor;
    RecyclerView nrecyclerview;
    List<News_itemsModel> categoryModelList = new ArrayList<>();
    ProgressDialog progressDialog;

    int val;
    TextView c;

    public DynamicFragment() {
        // Required empty public constructor
    }

    /* public static DynamicFragment newInstance(String id) {
         return new DynamicFragment();
     }*/
    public static DynamicFragment addfrag(int val) {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", val);
        fragment.setArguments(args);
        return fragment;
    }

    /* public static DynamicFragment newInstance(int sectionNumber) {
         DynamicFragment fragment = new DynamicFragment();
         Bundle args = new Bundle();
         args.putInt(ARG_SECTION_NUMBER, sectionNumber);
         fragment.setArguments(args);
         return fragment;
     }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dynamic, container, false);
        val = getArguments().getInt("someInt", 0);
        c = v.findViewById(R.id.c);
        c.setText("Fragment - " + CatagoryList.catagoryModels1.get(val).getName());
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait ...");
        nrecyclerview = v.findViewById(R.id.recyclerviewfproduct);
        nrecyclerview.setHasFixedSize(true);
        nrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        get_news(Integer.parseInt(CatagoryList.catagoryModels1.get(val).getId()));
       /* Log.d("newssize",CatagoryList.catagoryModels1.get(Integer.parseInt(StaticData.tabid)).getName());
        TextView a=(TextView) v.findViewById(R.id.txtTabItemNumber);
        a.setText(CatagoryList.catagoryModels1.get(Integer.parseInt(StaticData.tabid)).getName());*/
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        //get_news(Integer.parseInt(StaticData.tabid));
    }

    public void get_news(int position) {

        Showprogess();
        CountryService countryService = new CountryService();
        countryService.getAPI().get_news("get-news?category=" + CatagoryList.catagoryModels1.get(val).getId() + "&page=1").enqueue(new Callback<NewsResponse>() {
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
                        c.setText("Fragment - " + CatagoryList.catagoryModels1.get(val).getName() + CatagoryList.catagoryModels1.get(val).getId());
                        // CatagoryList.newsListModels1=categoryModelList;
                        Log.d("newssize", String.valueOf(categoryModelList.size()));
                        BrowseProductslistAdaptor browseProductslistAdaptor =
                                new BrowseProductslistAdaptor(getContext(), categoryModelList);
                        nrecyclerview.setAdapter(browseProductslistAdaptor);
                        //startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                    } else {
                        dismissproggress();
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                dismissproggress();
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

