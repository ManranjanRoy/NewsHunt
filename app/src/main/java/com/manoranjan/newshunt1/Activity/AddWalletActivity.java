package com.manoranjan.newshunt1.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.manoranjan.newshunt1.R;
import com.manoranjan.newshunt1.Service.FilePath;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AddWalletActivity extends AppCompatActivity {
    RadioButton radioslot1,radioslot2,radioslot3,radioslot4,radioslot5;
    String radiostatus="cod",radio_slot="1",paymentstatus="0";
    EditText ammount,txnid;
    TextView edit_upiid;
    LinearLayout bankdetailslayout,qrlayout;
    ImageView adharfront, adharback;
    String path, path1 = "null", path2 = "null";
    Bitmap bitmap = null, bitmap2 = null;
    private int CAMERA = 22, GALLERYDOC = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);
        radioslot1= (RadioButton) findViewById(R.id.radio_slot1);
        radioslot2= (RadioButton) findViewById(R.id.radio_slot2);
        radioslot3= (RadioButton) findViewById(R.id.radio_slot3);
        radioslot4= (RadioButton) findViewById(R.id.radio_slot4);
        radioslot5= (RadioButton) findViewById(R.id.radio_slot5);
        bankdetailslayout=findViewById(R.id.bankdetailslayout);
        qrlayout=findViewById(R.id.qrlayout);
        adharfront = findViewById(R.id.aadharfront);
        bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.globe);
        edit_upiid=findViewById(R.id.edit_upi);
        ammount=findViewById(R.id.edit_ammount);
        txnid=findViewById(R.id.edit_txnid);
        edit_upiid.setText("+911234569870");
        adharfront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhotoFromGallaryDoc();
            }
        });

    }

    public void choosePhotoFromGallaryDoc() {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("application/pdf");
           // intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), GALLERYDOC);
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
                path1 = contentURI.getPath();
                String PdfPathHolder = FilePath.getPath(this, contentURI);
                Log.d("filepath", String.valueOf(PdfPathHolder));
            }
        }
    }
    public byte[] getFileDataFromDrawable(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void onRadioButtonClicked1(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        bankdetailslayout.setVisibility(View.GONE);
        qrlayout.setVisibility(View.VISIBLE);
        // Check which RadioButton was clicked
        switch(view.getId()) {
            case R.id.radio_slot1:
                if(checked){
                    radioslot1.setChecked(true);
                    radioslot2.setChecked(false);
                    radioslot3.setChecked(false);
                    radioslot4.setChecked(false);
                    radioslot5.setChecked(false);

                }
                    radio_slot="1";
                edit_upiid.setText("+911234569870");
                Toast.makeText(getApplicationContext(),radio_slot,Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_slot2:
                if(checked){
                    radioslot1.setChecked(false);
                    radioslot2.setChecked(true);
                    radioslot3.setChecked(false);
                    radioslot4.setChecked(false);
                    radioslot5.setChecked(false);

                }
                radio_slot="2";
                edit_upiid.setText("+911234569870");
                Toast.makeText(getApplicationContext(),radio_slot,Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_slot3:
                if(checked)
                {
                    radioslot1.setChecked(false);
                    radioslot2.setChecked(false);
                    radioslot3.setChecked(true);
                    radioslot4.setChecked(false);
                    radioslot5.setChecked(false);

                }
                radio_slot="3";
                edit_upiid.setText("+911234569870");
                Toast.makeText(getApplicationContext(),radio_slot,Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_slot4:
                if(checked){
                    radioslot1.setChecked(false);
                    radioslot2.setChecked(false);
                    radioslot3.setChecked(false);
                    radioslot4.setChecked(true);
                    radioslot5.setChecked(false);

                }
                radio_slot="4";
                edit_upiid.setText("demoupi@icici");
                Toast.makeText(getApplicationContext(),radio_slot,Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_slot5:
                if(checked){
                    radioslot1.setChecked(false);
                    radioslot2.setChecked(false);
                    radioslot3.setChecked(false);
                    radioslot4.setChecked(false);
                    radioslot5.setChecked(true);
                }
                radio_slot="5";
                qrlayout.setVisibility(View.GONE);
                bankdetailslayout.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),radio_slot,Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void validatefields() {
        String name1 = edit_upiid.getText().toString();
        String amount = ammount.getText().toString();
        String txn = txnid.getText().toString();
        //String mdesc = metadesc.getText().toString();


        if (TextUtils.isEmpty(amount)) {
            ammount.setError("Please Enter Ammount");
            return;
        } else  if (TextUtils.isEmpty(txn)) {
            txnid.setError("Please Enter txn id");
            return;
        }else {
            if (path1.equals("null") ) {

                //addnews1();
            } else {
               // addnews();
            }
        }

    }
}