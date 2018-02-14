package com.example.android.lbf;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener{
    Context mContext;
    SqliteHelper sqliteHelper;
    BarcodeReader mBarcodeReader;

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
        User currentUser = sqliteHelper.Authenticate(new User(null, username));
        // ticket details activity by passing barcode
        if (currentUser != null) {
            //User Logged in Successfully Launch You home screen activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            //User Logged in Failed
            Intent intent = new Intent(LoginActivity.this, first_activity.class);
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