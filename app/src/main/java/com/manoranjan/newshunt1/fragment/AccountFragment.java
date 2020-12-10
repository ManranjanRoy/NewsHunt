package com.manoranjan.newshunt1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manoranjan.newshunt1.Activity.AddNewsActivity;
import com.manoranjan.newshunt1.R;

import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        startActivity(new Intent(getContext(), AddNewsActivity.class));
        return v;
    }
}