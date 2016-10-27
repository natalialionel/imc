package com.example.lionel.imc;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;



public class ScrollingActivitySobre extends AppCompatActivity {


    private TextView textImc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling_activity_sobre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textImc  = (TextView) findViewById(R.id.textViewSobreImc);

        Typeface tf = Typeface.createFromAsset(getAssets(), "STIXGeneral-Regular.ttf");
        textImc.setTypeface(tf, Typeface.NORMAL);

    }
}
