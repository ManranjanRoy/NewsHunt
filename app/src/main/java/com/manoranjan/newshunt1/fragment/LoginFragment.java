package com.manoranjan.newshunt1.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manoranjan.newshunt1.Activity.AddNewsActivity;
import com.manoranjan.newshunt1.Activity.DashBoardActivity;
import com.manoranjan.newshunt1.Activity.SignupRepoterActivity;
import com.manoranjan.newshunt1.Model.User_data;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.Response.LoginResponse;
import com.manoranjan.newshunt1.Service.ApiLinks;
import com.manoranjan.newshunt1.Service.CountryService;
import com.manoranjan.newshunt1.StaticData.Configss;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    ProgressDialog progressDialog;
    EditText username, password;
    Button login;
    boolean loggedIn;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please Wait ...");
        username = v.findViewById(R.id.edit_usename);
        password = v.findViewById(R.id.edit_password);
        login = v.findViewById(R.id.login);
        init(v);

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Configss.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
        if (!loggedIn) {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            //We will start the Profile Activity
           /* Intent intent = new Intent(this,Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();*/
        } else {
            Intent intent = new Intent(getContext(), AddNewsActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            //loadprofile();
            //requestMultiplePermissions();
        }

    }

    void init(View v) {
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
                if (validatefiled()) {
                    final String email = username.getText().toString().trim();
                    final String password1 = password.getText().toString().trim();
                    loginfunction(email, password1);
                }
                // startActivity(new Intent(getContext(), DashBoardActivity.class));
            }
        });
    }

    public boolean validatefiled() {
        final String email = username.getText().toString().trim();
        final String password1 = password.getText().toString().trim();
        /*Intent intent = new Intent(Login.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
        // finish();
        if (email.isEmpty() || password1.isEmpty()) {
            Toast.makeText(getContext(), "Enter Correct Email Or Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void loginfunction(String email, String password) {
        // loginView.onError();
        if (validatefiled()) {
            Showprogess();
            CountryService countryService = new CountryService();
            countryService.getAPI().login(ApiLinks.login, email, password).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Log.d("response", response.body().toString());
                    dismissproggress();
                    if (response.isSuccessful() && response.body() != null) {
                        if (response.body().getError().equals("false")) {
                            Log.d("response", response.body().toString());
                            LoginResponse loginResponse = response.body();
                            User_data profiles = loginResponse.getData().getUser_data();
                            Log.d("accesstoken", loginResponse.getData().getToken());
                            SharedPreferences sharedPreferences = getContext().getSharedPreferences
                                    (Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            //Adding values to editor
                            editor.putBoolean(Configss.LOGGEDIN_SHARED_PREF, true);
                            //  editor.putString(Config.login_role,role);
                            editor.putString(Configss.login_role, "0");
                            editor.putString(Configss.tokencode, loginResponse.getData().getToken());
                            editor.commit();
                            startActivity(new Intent(getContext(), DashBoardActivity.class));

                        } else {
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    dismissproggress();
                }
            });
        }
        // loginView.onSucess();

    }

    private void Showprogess() {
        progressDialog.show();
    }

    private void dismissproggress() {
        progressDialog.dismiss();
    }
}
