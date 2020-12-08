package com.manoranjan.newshunt1.StaticData;


import com.manoranjan.newshunt1.Model.CatagoryModel;
import com.manoranjan.newshunt1.Model.CategoryModel;
import com.manoranjan.newshunt1.Model.NewsListModel;
import com.manoranjan.newshunt1.Model.News_itemsModel;
import com.manoranjan.newshunt1.Model.SellerModel;
import com.manoranjan.newshunt1.Model.SizeModel;
import com.manoranjan.newshunt1.Model.SubCatagoryModel;
import com.manoranjan.newshunt1.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

public class CatagoryList {

    public static List<CatagoryModel> catagoryModels = null;
    public static List<CategoryModel> catagoryModels1 = new ArrayList<>();
    public static List<SubCatagoryModel> subCatagoryModels = null;
    public static List<SizeModel> sizeModels = null;
    public static List<SellerModel> sellerModels = null;
    public static List<NewsListModel> newsListModels = null;
    public static List<News_itemsModel> newsListModels1 = new ArrayList<>();

    public static Fragment fragment = new HomeFragment();

}
