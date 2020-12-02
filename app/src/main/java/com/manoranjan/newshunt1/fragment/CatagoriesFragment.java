package com.manoranjan.newshunt1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manoranjan.newshunt1.Activity.DashBoardActivity;
import com.manoranjan.newshunt1.Activity.SignupRepoterActivity;
import com.manoranjan.newshunt1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatagoriesFragment extends Fragment {


    public CatagoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_catagories, container, false);

        init(v);

        return  v;
    }
    void init(View v){
        v.findViewById(R.id.reportersignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /*   FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new SignupReporterFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
                startActivity(new Intent(getContext(), SignupRepoterActivity.class));
            }
        });
        v.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), DashBoardActivity.class));
            }
        });
    }
}
