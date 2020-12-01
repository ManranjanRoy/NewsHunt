package com.manoranjan.newshunt1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manoranjan.newshunt1.Adaptor.BrowseProductslistAdaptor;
import com.manoranjan.newshunt1.Adaptor.ProductByCatAdaptor;
import com.manoranjan.newshunt1.Model.NewsListModel;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.StaticData.CatagoryList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends Fragment {
    RecyclerView nrecyclerview,catageryrecycler;
    ProductByCatAdaptor productByCatAdaptor;
    List<NewsListModel> newsListModels =new ArrayList<>();
    BrowseProductslistAdaptor browseProductslistAdaptor;
    public DynamicFragment() {
        // Required empty public constructor
    }
    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dynamic, container, false);
        catageryrecycler = v.findViewById(R.id.recyclerview1);
        catageryrecycler.setHasFixedSize(true);
        catageryrecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        nrecyclerview = v.findViewById(R.id.recyclerviewfproduct);
        nrecyclerview.setHasFixedSize(true);
        nrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        initViews();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        initViews();
    }
    private void initViews() {

        productByCatAdaptor=new ProductByCatAdaptor(getContext(), CatagoryList.subCatagoryModels);
        catageryrecycler.setAdapter(productByCatAdaptor);

       /* TextView textView=v.findViewById(R.id.txtTabItemNumber);
        textView.setText(String.valueOf("Category :  "+getArguments().getInt("position")));*/


        browseProductslistAdaptor = new BrowseProductslistAdaptor(getContext(), CatagoryList.newsListModels);
        nrecyclerview.setAdapter(browseProductslistAdaptor);

    }
}

