package com.example.lionel.imc;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Scrolling_PesoIdeal extends AppCompatActivity {
    private TextView textPeso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling__peso_ideal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface tf = Typeface.createFromAsset(getAssets(), "STIXGeneral-Regular.ttf");
        textPeso = (TextView) findViewById(R.id.PesoIdeal_TV);

        textPeso.setTypeface(tf, Typeface.NORMAL);


    }
}
