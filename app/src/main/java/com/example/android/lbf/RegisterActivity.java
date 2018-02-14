package com.example.android.lbf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

/**
 * Created by sambad on 2/14/18
 */

public class RegisterActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener{
    SqliteHelper sqliteHelper;
    BarcodeReader mBarcodeReader;
    Context mContext;

    //This method is for handling fromHtml method deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        mBarcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
        sqliteHelper = new SqliteHelper(this);
    }

    @Override
    public void onScanned(Barcode barcode) {
        mBarcodeReader.playBeep();
        String username= barcode.displayValue;
        //Check in the database is there any user associated with  this email
        if (!sqliteHelper.isUserExists(username)) {

            //Email does not exist now add new user to database
            sqliteHelper.addUser(new User(null, username));
            Intent intent = new Intent(RegisterActivity.this, first_activity.class);
            startActivity(intent);
            finish();
        } else {
            //Email exists with email input provided so show error user already exist
            Intent intent = new Intent(RegisterActivity.this, first_activity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }
}