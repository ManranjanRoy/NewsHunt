package com.manoranjan.newshunt1.Activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.manoranjan.newshunt1.Model.CatagoryModel;
import com.manoranjan.newshunt1.Model.NewsListModel;
import com.manoranjan.newshunt1.Model.SellerModel;
import com.manoranjan.newshunt1.Model.SizeModel;
import com.manoranjan.newshunt1.Model.SliderItem;
import com.manoranjan.newshunt1.Model.SubCatagoryModel;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.StaticData.CatagoryList;
import com.manoranjan.newshunt1.StaticDtaa.StaticData;
import com.manoranjan.newshunt1.fragment.CatagoriesFragment;
import com.manoranjan.newshunt1.fragment.HomeFragment;
import com.manoranjan.newshunt1.fragment.VideosFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    List<CatagoryModel> catagoryModels=new ArrayList<>();
    List<SubCatagoryModel> subCatagoryModels=new ArrayList<>();
    List<SizeModel> sizeModels=new ArrayList<>();
    List<SellerModel> sellerModels=new ArrayList<>();
    List<SliderItem> sliderItems=new ArrayList<>();
    List<NewsListModel> newsListModels =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        catagoryModels.clear();
        catagoryModels.add(new CatagoryModel("1","Sports",R.drawable.ic_home_black_24dp));
        catagoryModels.add(new CatagoryModel("2","International",R.drawable.ic_home_black_24dp));
        catagoryModels.add(new CatagoryModel("3","Crickets",R.drawable.ic_home_black_24dp));
        catagoryModels.add(new CatagoryModel("4","CoronaVirus",R.drawable.ic_home_black_24dp));
        catagoryModels.add(new CatagoryModel("5","Electronics",R.drawable.ic_home_black_24dp));
        catagoryModels.add(new CatagoryModel("6","Daily Share",R.drawable.ic_home_black_24dp));

        subCatagoryModels.clear();
        subCatagoryModels.add(new SubCatagoryModel("1","All",R.drawable.ic_home_black_24dp));
        subCatagoryModels.add(new SubCatagoryModel("2","Dress",R.drawable.ic_home_black_24dp));
        subCatagoryModels.add(new SubCatagoryModel("3","Top",R.drawable.ic_home_black_24dp));
        subCatagoryModels.add(new SubCatagoryModel("4","Jeans",R.drawable.ic_home_black_24dp));
        subCatagoryModels.add(new SubCatagoryModel("5","T-Shirt",R.drawable.ic_home_black_24dp));
        subCatagoryModels.add(new SubCatagoryModel("3","Top",R.drawable.ic_home_black_24dp));
        subCatagoryModels.add(new SubCatagoryModel("4","Jeans",R.drawable.ic_home_black_24dp));
        subCatagoryModels.add(new SubCatagoryModel("5","T-Shirt",R.drawable.ic_home_black_24dp));
        subCatagoryModels.add(new SubCatagoryModel("6","Saree",R.drawable.ic_home_black_24dp));

        sizeModels.add(new SizeModel("1"," S ",R.drawable.ic_home_black_24dp));
        sizeModels.add(new SizeModel("2"," M ",R.drawable.ic_home_black_24dp));
        sizeModels.add(new SizeModel("3"," L ",R.drawable.ic_home_black_24dp));
        sizeModels.add(new SizeModel("4"," XL ",R.drawable.ic_home_black_24dp));
        sizeModels.add(new SizeModel("5"," XXL",R.drawable.ic_home_black_24dp));
        sizeModels.add(new SizeModel("3","XXXL",R.drawable.ic_home_black_24dp));

        sellerModels.clear();
        sellerModels.add(new SellerModel("1","Seller name",R.drawable.ic_home_black_24dp));
        sellerModels.add(new SellerModel("2","Abc Company",R.drawable.ic_home_black_24dp));
        sellerModels.add(new SellerModel("3","Dummy Seller",R.drawable.ic_home_black_24dp));
        sellerModels.add(new SellerModel("4","Demo Seller",R.drawable.ic_home_black_24dp));
        sellerModels.add(new SellerModel("5","Electronics",R.drawable.ic_home_black_24dp));
        sellerModels.add(new SellerModel("6","Test Seller",R.drawable.ic_home_black_24dp));

        newsListModels.clear();
        newsListModels.add(new
                NewsListModel("1","Guwahati | Jagran News Desk: Former Assam Chief Minister Tarun Gogoi" ,"Desc","7.00","5.00","0","0",R.drawable.img1));
        newsListModels.add(new
                NewsListModel("2","Three Naxals, including a woman cadre, were killed in an encounter" ,"Desc","7.00","5.00","10","1",R.drawable.img1));
        newsListModels.add(new
                NewsListModel("3","In accordance with the state government, the colleges and universities" ,"Desc","7.00","5.00","0","0",R.drawable.img1));
        newsListModels.add(new
                NewsListModel("4","Bhumi Pednekar starrer Durgavati is now renamed as Durgamati The Myth" ,"Desc","7.00","5.00","0","1",R.drawable.img1));
        newsListModels.add(new
                NewsListModel("5","Anil Kapoor and Sunita Kapoor make one of the loveliest couples in Bollywood" ,"Desc","7.00","5.00","10","0",R.drawable.img1));

        CatagoryList.catagoryModels=catagoryModels;
        CatagoryList.subCatagoryModels=subCatagoryModels;
        CatagoryList.sizeModels=sizeModels;
        CatagoryList.newsListModels = newsListModels;
        CatagoryList.sellerModels=sellerModels;



        loadFragment(new HomeFragment());
        BottomNavigationView navigation = findViewById(R.id.bottom_nav);
        disableShiftMode(navigation);

        navigation.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                CatagoryList.fragment=fragment;
                break;

            case R.id.navigation_dashboard:
                fragment = new VideosFragment();
                CatagoryList.fragment=fragment;
                break;

            case R.id.navigation_notifications:
                fragment = new CatagoriesFragment();
                CatagoryList.fragment=fragment;
                break;

          /*  case R.id.navigation_more:
                fragment = new MoreFragment();
                CatagoryList.fragment=fragment;
                break;*/
        }

        return loadFragment(fragment);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadFragment(CatagoryList.fragment);
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShifting(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("Luan", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("Luan", "Unable to change value of shift mode");
        }
    }

}
