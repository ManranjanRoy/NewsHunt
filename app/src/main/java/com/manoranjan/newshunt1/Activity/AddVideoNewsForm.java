package com.manoranjan.newshunt1.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.manoranjan.newshunt1.Datamodel.SubCategoryData;
import com.manoranjan.newshunt1.Model.CategoryData;
import com.manoranjan.newshunt1.Model.CategoryModel;
import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.Response.AddNewsREsponse;
import com.manoranjan.newshunt1.Response.CategoryResponse;
import com.manoranjan.newshunt1.Response.SubCategoryResponse;
import com.manoranjan.newshunt1.Service.CountryService;
import com.manoranjan.newshunt1.StaticData.CatagoryList;
import com.manoranjan.newshunt1.StaticData.Configss;
import com.manoranjan.newshunt1.StaticData.StaticData;
import com.manoranjan.newshunt1.fragment.HomeFragment;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AddVideoNewsForm extends AppCompatActivity {
    private static final String IMAGE_DIRECTORY = "/demonuts";
    EditText title, desc, metatitle, metadesc;
    TextView spinnercountry, spinnerstate, spinnerdistric, spinnerblock;
    Spinner spinnertype, spinnercategory, spinnersubcategory;
    ImageView adharfront, adharback;
    String path, path1 = "null", path2 = "null";
    Bitmap bitmap = null, bitmap2 = null;
    String oldimgname = "";
    int imgno = 1;
    ProgressDialog progressDialog;
    String tokencode;
    String newstype = "Text";
    String catid = "0";
    String subcat_id = "0";
    List<String> category = new ArrayList<>();
    List<CategoryModel> catagoryModels = new ArrayList<>();
    List<String> subcategory = new ArrayList<>();
    List<CategoryModel> subcatagoryModels = new ArrayList<>();
    private int CAMERA = 22, GALLERYDOC = 11;
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video_news_form);
        SharedPreferences sharedPreferences = getSharedPreferences(Configss.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        //Fetching the boolean value form sharedpreferences
        tokencode = sharedPreferences.getString(Configss.tokencode, "default");
        progressDialog = new ProgressDialog(AddVideoNewsForm.this);
        progressDialog.setMessage("Please Wait ...");
        spinnertype = findViewById(R.id.spinnertype);
        spinnercountry = findViewById(R.id.spinnercountry);
        spinnerstate = findViewById(R.id.spinnerstate);
        spinnerdistric = findViewById(R.id.spinnerdistic);
        spinnerblock = findViewById(R.id.spinnerblock);
        spinnercategory = findViewById(R.id.spinnercategory);
        spinnersubcategory = findViewById(R.id.spinnersubcategory);
        adharfront = findViewById(R.id.aadharfront);
        adharback = findViewById(R.id.aadharback);

        title = findViewById(R.id.edit_name);
        desc = findViewById(R.id.edit_desc);
        metatitle = findViewById(R.id.edit_metaname);
        metadesc = findViewById(R.id.edit_metadesc);

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatefields();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                CatagoryList.fragment = fragment;
                Intent intent = new Intent(AddVideoNewsForm.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
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
        checkgps();
        getcategory();
        bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.globe);
        bitmap2 = BitmapFactory.decodeResource(getResources(),
                R.drawable.globe);

        requestMultiplePermissions();
        spinnerdata();
        Places.initialize(getApplicationContext(), StaticData.api_key);

        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(this);
        findViewById(R.id.searchlocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS_COMPONENTS);
                // Start the autocomplete intent.
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(AddVideoNewsForm.this);
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);

            }
        });
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NotNull Place place) {
                // TODO: Get info about the selected place.
                Log.i("TAG", "Place: " + place.getName() + ", " + place.getId());
            }


            @Override
            public void onError(@NotNull Status status) {
                // TODO: Handle the error.
                Log.i("TAG", "An error occurred: " + status);
            }
        });
    }

    void checkgps() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        } else {
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            String locationProvider = LocationManager.NETWORK_PROVIDER;
            @SuppressLint("MissingPermission") android.location.Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
            if (lastKnownLocation == null || lastKnownLocation.equals("null")) {
            } else {
                double userLat = lastKnownLocation.getLatitude();
                double userLong = lastKnownLocation.getLongitude();
                StaticData.lat = userLat;
                StaticData.longi = userLong;
            }
            setplacttotext();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public void setplacttotext() {

        double userLat = StaticData.lat;
        double userLong = StaticData.longi;
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(AddVideoNewsForm.this, Locale.getDefault());

        Log.d("TAG", "onCreate: " + userLat + "/" + userLong);

        try {
            addresses = geocoder.getFromLocation(userLat, userLong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            spinnercountry.setText(country);
            spinnerstate.setText(state);
            spinnerdistric.setText(city);
            spinnerblock.setText(knownName);


        } catch (IOException e) {
            e.printStackTrace();
        }

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
        } else if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                final LatLng location = place.getLatLng();
                double userLat = location.latitude;
                double userLong = location.longitude;

                StaticData.lat = userLat;
                StaticData.longi = userLong;

                setplacttotext();
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("TAG", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        } else if (requestCode == GALLERYDOC) {
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
        String name1 = title.getText().toString();
        String desc1 = desc.getText().toString();
        String mtitle = metatitle.getText().toString();
        String mdesc = metadesc.getText().toString();

        if (TextUtils.isEmpty(name1)) {
            title.setError("Please Enter Your Name");
            return;
        }
        if (TextUtils.isEmpty(desc1)) {
            desc.setError("Please Content");
            return;
        } else if (catid.equals("0")) {
            metatitle.setError("Select Cat Id");
            return;
        } else {
            if (path1.equals("null") && path2.equals("null")) {
                addnews1();
            } else {
                addnews();
            }
        }

    }

    private void spinnerdata() {
        List<String> categories = new ArrayList<String>();
        categories.add("Text");
        categories.add("Video");
        List<String> spinnernews = new ArrayList<String>();
        spinnernews.add("No");
        spinnernews.add("YES");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddVideoNewsForm.this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnertype.setAdapter(dataAdapter);

        //spinnersubcategory.setAdapter(dataAdapter1);

        spinnertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String item = parent.getItemAtPosition(position).toString();
                newstype = item.trim();
                // Showing selected spinner item
                // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    private void addnews() {
        final ProgressDialog loading = ProgressDialog.show(AddVideoNewsForm.this, "Updating...", "Please wait...", false, false);

        String name1 = title.getText().toString();
        String desc1 = desc.getText().toString();
        String mtitle = metatitle.getText().toString();
        String mdesc = metadesc.getText().toString();
        File file = new File(path1);
        File file1 = new File(path2);

        //RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"), file);
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/*"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file1.getName(),
                RequestBody.create(MediaType.parse("application/*"), file1));
        MultipartBody.Part body1 = MultipartBody.Part.createFormData("bannerimage", file1.getName(),
                RequestBody.create(MediaType.parse("application/*"), file1));
        Log.d("body123", String.valueOf(body));

        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), name1);
        RequestBody desc = RequestBody.create(MediaType.parse("text/plain"), desc1);
        RequestBody mtitle1 = RequestBody.create(MediaType.parse("text/plain"), mtitle);
        RequestBody mdesc1 = RequestBody.create(MediaType.parse("text/plain"), mdesc);
        RequestBody cat = RequestBody.create(MediaType.parse("text/plain"), catid);
        RequestBody subcat = RequestBody.create(MediaType.parse("text/plain"), subcat_id);
        RequestBody country = RequestBody.create(MediaType.parse("text/plain"), "India");
        RequestBody state = RequestBody.create(MediaType.parse("text/plain"), spinnerstate.getText().toString());
        RequestBody district = RequestBody.create(MediaType.parse("text/plain"), spinnerdistric.getText().toString());
        RequestBody block = RequestBody.create(MediaType.parse("text/plain"), spinnerblock.getText().toString());
        RequestBody video = RequestBody.create(MediaType.parse("text/plain"), "");
        RequestBody newstype1 = RequestBody.create(MediaType.parse("text/plain"), newstype);

        new CountryService().getAPI().add_news("Bearer " + tokencode,
                name, desc, cat, newstype1, subcat, country, state, district, block, video, mtitle1, mdesc1, body, body1).enqueue(new Callback<AddNewsREsponse>() {
            @Override
            public void onResponse(Call<AddNewsREsponse> call, retrofit2.Response<AddNewsREsponse> response) {
                loading.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getError().equals("false")) {
                        Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                       /* Intent i = new Intent(EditProfile.this, EditProfile.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);*/
                    }
                }
            }

            @Override
            public void onFailure(Call<AddNewsREsponse> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Server Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addnews1() {
        final ProgressDialog loading = ProgressDialog.show(AddVideoNewsForm.this, "Updating...", "Please wait...", false, false);

        String name1 = title.getText().toString();
        String desc1 = desc.getText().toString();
        String mtitle = metatitle.getText().toString();
        String mdesc = metadesc.getText().toString();

        //RequestBody requestFile = RequestBody.create(MediaType.parse("*/*"), file);

        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), name1);
        RequestBody desc = RequestBody.create(MediaType.parse("text/plain"), desc1);
        RequestBody mtitle1 = RequestBody.create(MediaType.parse("text/plain"), mtitle);
        RequestBody mdesc1 = RequestBody.create(MediaType.parse("text/plain"), mdesc);
        RequestBody cat = RequestBody.create(MediaType.parse("text/plain"), catid);
        RequestBody subcat = RequestBody.create(MediaType.parse("text/plain"), subcat_id);
        RequestBody country = RequestBody.create(MediaType.parse("text/plain"), "India");
        RequestBody state = RequestBody.create(MediaType.parse("text/plain"), spinnerstate.getText().toString());
        RequestBody district = RequestBody.create(MediaType.parse("text/plain"), spinnerdistric.getText().toString());
        RequestBody block = RequestBody.create(MediaType.parse("text/plain"), spinnerblock.getText().toString());
        RequestBody video = RequestBody.create(MediaType.parse("text/plain"), "");
        RequestBody newstype1 = RequestBody.create(MediaType.parse("text/plain"), newstype);

        new CountryService().getAPI().add_newsnimg("Bearer " + tokencode,
                name1, desc1, catid, newstype, subcat_id, "India", spinnerstate.getText().toString(),
                spinnerdistric.getText().toString(), spinnerblock.getText().toString(), "", mtitle, mdesc).enqueue(new Callback<AddNewsREsponse>() {
            @Override
            public void onResponse(Call<AddNewsREsponse> call, retrofit2.Response<AddNewsREsponse> response) {
                loading.dismiss();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getError().equals("false")) {
                        Toast.makeText(getApplicationContext(), "" + response.body().getMessage(), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                       /* Intent i = new Intent(EditProfile.this, EditProfile.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);*/
                    }
                }
            }

            @Override
            public void onFailure(Call<AddNewsREsponse> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), "Server Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getcategory() {
        // loginView.onError()
        // if (validatefiled()){
        Showprogess();
        CountryService countryService = new CountryService();
        countryService.getAPI().getcategory("Bearer " + tokencode).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.d("response", response.body().toString());
                dismissproggress();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getError().equals("false")) {
                        Log.d("response", response.body().toString());
                        CategoryData loginResponse = response.body().getData();
                        catagoryModels = loginResponse.getCategory();
                        for (int i = 0; i < catagoryModels.size(); i++) {
                            category.add(catagoryModels.get(i).getName());
                        }

                        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(AddVideoNewsForm.this,
                                android.R.layout.simple_spinner_item, category);

                        // Drop down layout style - list view with radio button
                        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // attaching data adapter to spinner
                        spinnercategory.setAdapter(dataAdapter1);

                        spinnercategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String item = adapterView.getItemAtPosition(i).toString();
                                // Toast.makeText(AddVideoNewsForm.this, item, Toast.LENGTH_SHORT).show();
                                for (int j = 0; j < catagoryModels.size(); j++) {
                                    if (catagoryModels.get(j).getName().equals(item)) {
                                        catid = catagoryModels.get(i).getId();
                                        break;
                                    }
                                }
                                getsubcategory();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });


                        //startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                dismissproggress();
            }
        });
    }

    public void getsubcategory() {
        // loginView.onError()
        // if (validatefiled()){
        //  Showprogess();
        CountryService countryService = new CountryService();
        countryService.getAPI().getsubcategory("Bearer " + tokencode, catid).enqueue(new Callback<SubCategoryResponse>() {
            @Override
            public void onResponse(Call<SubCategoryResponse> call, Response<SubCategoryResponse> response) {
                Log.d("response", response.body().toString());
                //dismissproggress() ;
                subcategory.clear();
                subcatagoryModels.clear();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().getError().equals("false")) {
                        Log.d("response", response.body().toString());
                        SubCategoryData loginResponse = response.body().getData();
                        subcatagoryModels = loginResponse.getCategory();
                        for (int i = 0; i < subcatagoryModels.size(); i++) {
                            subcategory.add(subcatagoryModels.get(i).getName());
                        }

                        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(AddVideoNewsForm.this,
                                android.R.layout.simple_spinner_item, subcategory);

                        // Drop down layout style - list view with radio button
                        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // attaching data adapter to spinner
                        spinnersubcategory.setAdapter(dataAdapter1);
                        spinnersubcategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String item = adapterView.getItemAtPosition(i).toString();
                                //Toast.makeText(AddVideoNewsForm.this, item, Toast.LENGTH_SHORT).show();
                                for (int j = 0; j < subcatagoryModels.size(); j++) {
                                    if (subcatagoryModels.get(j).getName().equals(item)) {
                                        catid = subcatagoryModels.get(i).getId();
                                        break;
                                    }

                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });

                        //startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));

                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<SubCategoryResponse> call, Throwable t) {
                dismissproggress();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment fragment = new HomeFragment();
        CatagoryList.fragment = fragment;
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    // loginView.onSucess();
    private void Showprogess() {
        progressDialog.show();
    }

    private void dismissproggress() {
        progressDialog.dismiss();
    }
}