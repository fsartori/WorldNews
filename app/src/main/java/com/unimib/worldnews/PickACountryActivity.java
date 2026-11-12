package com.unimib.worldnews;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class PickACountryActivity extends AppCompatActivity {
    public final String TAG = PickACountryActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) { //in savedInstanceState viene passata l'informazione dell'Intent
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_a_country);
        //String email = getIntent().getStringExtra("EMAIL_KEY"); //stampo l'informazione presente nel bnndle, da mettere in un try ...catch.
        String email = "Sono in PickCountyActivity";
        Log.d(TAG, email);
    }
}