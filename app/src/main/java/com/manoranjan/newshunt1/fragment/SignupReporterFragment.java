package com.manoranjan.newshunt1.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.manoranjan.newshunt1.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;

public class SignupReporterFragment extends Fragment {
    private static final String IMAGE_DIRECTORY = "/demonuts";
    Spinner spinnercountry, spinnerstate, spinnerdistric, spinnerblock;
    ImageView profilepic, choosepic;
    String path, path1 = "null";
    Bitmap bitmap = null;
    String oldimgname = "";
    private int CAMERA = 22, GALLERYDOC = 11;

    public SignupReporterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup_reporter, container, false);

        spinnercountry = v.findViewById(R.id.spinner);
        spinnerstate = v.findViewById(R.id.spinnerstate);
        spinnerdistric = v.findViewById(R.id.spinnerDistrict);
        spinnerblock = v.findViewById(R.id.spinnerBlock);
        profilepic = v.findViewById(R.id.aadharfront);
        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhotoFromGallaryDoc();
            }
        });
        bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.globe);
        spinnerdata();
        return v;
    }

    public void choosePhotoFromGallaryDoc() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERYDOC);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERYDOC) {
            if (data != null) {

                Uri contentURI = data.getData();

                path1 = contentURI.getLastPathSegment();
                File f = new File("" + contentURI);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), contentURI);

                    profilepic.setImageBitmap(bitmap);
                    this.bitmap = bitmap;

                    //uploadImage(bitmap);
                    //compressImage(path1);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

  /*  private void validatefields() {
        name=tname.getText().toString();
        mobno=tmobno.getText().toString();
        email_id=temailid.getText().toString();
        address=taddress.getText().toString();
        //password=tpassword.getText().toString();
        if(TextUtils.isEmpty(name)) {
            tname.setError("Please Enter Your Name");
            return;
        } else if(TextUtils.isEmpty(email_id)) {
            temailid.setError("Please Enter your Email");
            return;
        }else if (!TextUtils.isEmpty(email_id) && !Patterns.EMAIL_ADDRESS.matcher(email_id).matches()){
            temailid.setError("Enter Valid Email");
            return;
        }
        else if(TextUtils.isEmpty(mobno)) {
            tmobno.setError("Enter Your Mobile No");
            return;
        }
        else if(mobno.length()>10) {
            tmobno.setError("Enter Your Mobile No");
            return;
        }
      *//*  else if(TextUtils.isEmpty(password)) {
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            tpassword.setError("Enter Your Password ");
            return;
        }*//*
     *//* else if(TextUtils.isEmpty(address)){
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            taddress.setError("Enter Your Address ");
            return;
        }*/
  /*
        else {
            if (path1.equals("null")){
                uploadImage();
            }
            else {
                loadotherdetails();
            }
            //uploadImage(bitmap);

        }
    }
*/

    private void spinnerdata() {
        // Spinner element


        // Spinner click listener
        //spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Malaysia");
        categories.add("United States");
        categories.add("Indonesia");
        categories.add("Italy");
        categories.add("India");
        categories.add("Singapore");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnercountry.setAdapter(dataAdapter);
        spinnerstate.setAdapter(dataAdapter);
        spinnerdistric.setAdapter(dataAdapter);
        spinnerblock.setAdapter(dataAdapter);

        spinnercountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerdistric.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerblock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}