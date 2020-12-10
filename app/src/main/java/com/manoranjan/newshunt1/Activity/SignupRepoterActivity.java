package com.manoranjan.newshunt1.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.Response.SignupResponse;
import com.manoranjan.newshunt1.Service.ApiLinks;
import com.manoranjan.newshunt1.Service.CountryService;
import com.manoranjan.newshunt1.StaticData.StaticData;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class SignupRepoterActivity extends AppCompatActivity {
    private static final String IMAGE_DIRECTORY = "/demonuts";
    EditText name, phone, otp, adharnumber, editpincode, username, password, cpassword, email;
    TextView spinnerstate, spinnerdistric, spinnerblock;
    Spinner spinnercountry;
    ImageView adharfront, adharback;
    String path, path1 = "null", path2 = "null";
    Bitmap bitmap = null, bitmap2 = null;
    String oldimgname = "";
    int imgno = 1;
    ProgressDialog progressDialog;
    private int CAMERA = 22, GALLERYDOC = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_repoter);

        spinnercountry = findViewById(R.id.spinner);
        spinnerstate = findViewById(R.id.spinnerstate);
        spinnerdistric = findViewById(R.id.spinnerdistic);
        spinnerblock = findViewById(R.id.spinnerblock);
        adharfront = findViewById(R.id.aadharfront);
        adharback = findViewById(R.id.aadharback);

        name = findViewById(R.id.edit_name);
        phone = findViewById(R.id.edit_phone);
        otp = findViewById(R.id.edit_otp);
        adharnumber = findViewById(R.id.edit_adharnumber);
        editpincode = findViewById(R.id.edit_pincode);
        username = findViewById(R.id.edit_usename);
        password = findViewById(R.id.edit_password);
        cpassword = findViewById(R.id.edit_cpassword);
        email = findViewById(R.id.edit_email);
        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatefields();
            }
        });

        adharfront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgno = 1;
                choosePhotoFromGallaryDoc();
            }
        });
        adharback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgno = 2;
                choosePhotoFromGallaryDoc();
            }
        });
        bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.globe);
        bitmap2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.globe);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        // I suppressed the missing-permission warning because this wouldn't be executed in my
        // case without location services being enabled
        @SuppressLint("MissingPermission") android.location.Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        if (lastKnownLocation == null || lastKnownLocation.equals("null")) {
        } else {
            double userLat = lastKnownLocation.getLatitude();
            double userLong = lastKnownLocation.getLongitude();
            StaticData.lat = userLat;
            StaticData.longi = userLong;
        }
        requestMultiplePermissions();


        spinnerdata();
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Geocoder geocoder;
                            List<Address> addresses;
                            geocoder = new Geocoder(SignupRepoterActivity.this, Locale.getDefault());
                            try {
                                addresses = geocoder.getFromLocation(StaticData.lat, StaticData.longi, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                String city = addresses.get(0).getLocality();
                                String state = addresses.get(0).getAdminArea();
                                String country = addresses.get(0).getCountryName();
                                String postalCode = addresses.get(0).getPostalCode();
                                String knownName = addresses.get(0).getFeatureName();
                                spinnerstate.setText(state);
                                spinnerdistric.setText(city);
                                spinnerblock.setText(knownName);
                                editpincode.setText(postalCode);

                                Log.d("TAG", "onCreate: "  + "/" + addresses);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            Toast.makeText(getApplicationContext(), "Denied", Toast.LENGTH_SHORT).show();
                            // requestMultiplePermissions();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    public void choosePhotoFromGallaryDoc() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERYDOC);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERYDOC) {
            if (data != null) {

                Uri contentURI = data.getData();

                path1 = contentURI.getLastPathSegment();
                File f = new File("" + contentURI);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                    if (imgno == 1) {
                        path1 = contentURI.getLastPathSegment();
                        adharfront.setImageBitmap(bitmap);
                        this.bitmap = bitmap;
                    } else if (imgno == 2) {
                        path2 = contentURI.getLastPathSegment();
                        adharback.setImageBitmap(bitmap);
                        this.bitmap2 = bitmap;
                    }
                    //uploadImage(bitmap);
                    //compressImage(path1);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void validatefields() {
        String name1 = name.getText().toString();
        String mobno = phone.getText().toString();
        String otp1 = otp.getText().toString();
        String email1 = email.getText().toString();
        String adharno = adharnumber.getText().toString();
        String pincode = editpincode.getText().toString();
        String username1 = username.getText().toString();
        String password1 = password.getText().toString();
        String cpassword1 = cpassword.getText().toString();
        if (TextUtils.isEmpty(name1)) {
            name.setError("Please Enter Your Name");
            return;
        }
        if (TextUtils.isEmpty(email1)) {
            email.setError("Please Enter Your Email");
            return;
        }
    /*    else if(TextUtils.isEmpty(otp1)) {
            otp.setError("Verify your number");
            return;
        }*/
        else if (TextUtils.isEmpty(mobno)) {
            phone.setError("Enter Your Mobile No");
            return;
        } else if (TextUtils.isEmpty(adharno)) {
            adharnumber.setError("Enter Your Aadhar No");
            return;
        } else if (mobno.length() > 10) {
            phone.setError("Enter Your Mobile No");
            return;
        } else if (TextUtils.isEmpty(pincode)) {
            editpincode.setError("Enter Pincode");
            return;
        }
        if (TextUtils.isEmpty(username1)) {
            username.setError("Please Enter UserName");
            return;
        } else if (TextUtils.isEmpty(password1)) {
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            password.setError("Enter Your Password ");
            return;
        } else if (TextUtils.isEmpty(cpassword1)) {
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            cpassword.setError("Enter Your Confirm Password ");
            return;
        } else if (!password1.equals(cpassword1)) {
            cpassword.setError("Enter  Same Password ");
            return;
        }
       /* else if(TextUtils.isEmpty(spinnerstate.getText().toString())) {
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            spinnerstate.setError("Enter Your Confirm Password ");
            return;
        }  else if(TextUtils.isEmpty(spinnerdistric.getText().toString())) {
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            spinnerdistric.setError("Enter Your Confirm Password ");
            return;
        }else if(TextUtils.isEmpty(spinnerblock.getText().toString())) {
            //Toast.makeText(getApplicationContext(),"Enter Your Password",Toast.LENGTH_SHORT).show();
            spinnerblock.setError("Enter Your Confirm Password ");
            return;
        }*/

        else {

            loadotherdetails();
        }
        //uploadImage(bitmap);
    }

    private void spinnerdata() {
        // Spinner element


        // Spinner click listener
        //spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("India");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(SignupRepoterActivity.this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnercountry.setAdapter(dataAdapter);


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

    }

    private void Showprogess() {
        progressDialog.show();
    }

    private void dismissproggress() {
        progressDialog.dismiss();
    }

    private void loadotherdetails() {
        final ProgressDialog loading = ProgressDialog.show(SignupRepoterActivity.this, "Updating...", "Please wait...", false, false);

        String name1 = name.getText().toString();
        String mobno = phone.getText().toString();
        String otp1 = otp.getText().toString();
        String email1 = email.getText().toString();
        String adharno = adharnumber.getText().toString();
        String pincode1 = editpincode.getText().toString();
        String username1 = username.getText().toString();
        String password1 = password.getText().toString();
        String cpassword1 = cpassword.getText().toString();

        File file = new File(path1);
        File file1 = new File(path2);

        //RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"), file);
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/*"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("aadharFront", file1.getName(),
                RequestBody.create(MediaType.parse("application/*"), file1));
        MultipartBody.Part body1 = MultipartBody.Part.createFormData("aadharBack", file1.getName(),
                RequestBody.create(MediaType.parse("application/*"), file1));
        Log.d("body123", String.valueOf(body));

        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), name1);
        RequestBody phone = RequestBody.create(MediaType.parse("text/plain"), mobno);
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), email1);
        RequestBody aadhar = RequestBody.create(MediaType.parse("text/plain"), adharno);
        RequestBody pincode = RequestBody.create(MediaType.parse("text/plain"), pincode1);
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), password1);
        RequestBody confirm_password = RequestBody.create(MediaType.parse("text/plain"), cpassword1);
        RequestBody country = RequestBody.create(MediaType.parse("text/plain"), "India");
        RequestBody state = RequestBody.create(MediaType.parse("text/plain"), spinnerstate.getText().toString());
        RequestBody district = RequestBody.create(MediaType.parse("text/plain"), spinnerdistric.getText().toString());
        RequestBody block = RequestBody.create(MediaType.parse("text/plain"), spinnerblock.getText().toString());

        new CountryService().getAPI().createUser(ApiLinks.register,
                name, phone, email, aadhar, pincode, password, confirm_password, country, state, district, block, body, body1).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, retrofit2.Response<SignupResponse> response) {
                loading.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getError().equals("false")) {
                        Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                       /* Intent i = new Intent(EditProfile.this, EditProfile.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);*/
                    }
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Server Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}