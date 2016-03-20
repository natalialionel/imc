package com.example.lionel.imc;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.orhanobut.dialogplus.DialogPlus;

import java.nio.ByteOrder;

public class MainActivity extends AppCompatActivity {

    private double peso;
    private double altura;

    private TextView resultadoImc;
    //private TextView pesoIdeal;

    private EditText editPeso;
    private EditText editAltura;

    private SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        carregarADS_Banner();
        //TypefaceProvider.registerDefaultIconSets(); //Android-Bootstrap

        resultadoImc = (TextView ) findViewById(R.id.textViewImc);
        //pesoIdeal = (TextView) findViewById(R.id.textViewPesoIdeal);

        editPeso = (EditText) findViewById(R.id.input_peso);
        editAltura = (EditText) findViewById(R.id.input_altura);
    }

    public void carregarADS_Banner(){
        try {
            RelativeLayout banner = (RelativeLayout) findViewById(R.id.banner);

            AdView ads = new AdView(this);
            ads.setAdSize(AdSize.SMART_BANNER);

            // insira o código do banner aqui

            ads.setAdUnitId("ca-app-pub-9023747170083444/6722176011");
            AdRequest request = new AdRequest.Builder().build();

            //seta o device como despositivo de teste para impedir que você clique nos bannes por acidente
            request.isTestDevice(this);

            banner.addView(ads);
            ads.loadAd(request);

        } catch (Exception e) {
            Log.e("carregarADS_Banner", e.getMessage());
        }
    }

    public void calcularImc(View view){

        double imcResult = 0;
        String messageResult = "";
        int colorResult = 0;

       if(editPeso.getText().toString().equals("") || editAltura.getText().toString().equals("")){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }else {

            peso = Double.valueOf(editPeso.getText().toString()).doubleValue();
            altura = Double.valueOf(editAltura.getText().toString()).doubleValue();

            double imc = round((peso / (altura * altura)), 2);

            if (imc < 16.00) {
                imcResult = imc;
                messageResult = "\nBaixo Peso Grau III, procure um especialista.";
                colorResult = Color.parseColor("#FF0000");

               /* resultadoImc.setTextColor(Color.parseColor("#FF0000"));
                resultadoImc.setText("O valor do seu IMC é: " + imc + "\nBaixo Peso Grau III, procure um especialista.");*/

            } else if (imc >= 16.00 && imc < 17.00) {
                imcResult = imc;
                messageResult = "\nBaixo Peso Grau II, procure um especialista.";
                colorResult = Color.parseColor("#FF0000");

                /*resultadoImc.setText("O valor do seu IMC é: " + imc + "\nBaixo Peso Grau II, procure um especialista.");
                resultadoImc.setTextColor(Color.parseColor("#FF0000"));*/
            } else if (imc >= 17.00 && imc < 18.50) {
                imcResult = imc;
                messageResult = "\nBaixo Peso Grau I, procure um especialista.";
                colorResult = Color.parseColor("#FFCC00");

                /*resultadoImc.setTextColor(Color.parseColor("#FFCC00"));
                resultadoImc.setText("O valor do seu IMC é: " + imc + "\nBaixo Peso Grau I, procure um especialista.");*/
            } else if (imc >= 18.50 && imc < 25.00) {
                imcResult = imc;
                messageResult = "\nParabéns, você está no seu peso ideal.";
                colorResult = Color.parseColor("#009900");

                /*resultadoImc.setTextColor(Color.parseColor("#009900"));
                resultadoImc.setText("O valor do seu IMC é: " + imc + "\nParabéns, você está no seu peso ideal.");*/
            } else if (imc >= 25.00 && imc < 30.00) {
                imcResult = imc;
                messageResult = "\nVocê está com sobrepeso, procure um especialista.";
                colorResult = Color.parseColor("#FFCC00");

                /*resultadoImc.setTextColor(Color.parseColor("#FFCC00"));
                resultadoImc.setText("O valor do seu IMC é: " + imc + "\nVocê está com sobrepeso, procure um especialista.");*/
            } else if (imc >= 30.00 && imc < 35.00) {
                imcResult = imc;
                messageResult = "\nObesidade Grau I, procure um especialista.";
                colorResult = Color.parseColor("#FF0000");

                /*resultadoImc.setTextColor(Color.parseColor("#FF0000"));
                resultadoImc.setText("O valor do seu IMC é: " + imc + "\nObesidade Grau I, procure um especialista.");*/
            } else if (imc >= 35.00 && imc < 40.00) {
                imcResult = imc;
                messageResult = "\nObesidade Grau II, procure um especialista.";
                colorResult = Color.parseColor("#FF0000");

                /*resultadoImc.setTextColor(Color.parseColor("#FF0000"));
                resultadoImc.setText("O valor do seu IMC é: " + imc + "\nObesidade Grau II, procure um especialista.");*/
            } else if (imc >= 40.00) {
                imcResult = imc;
                messageResult = "\nObesidade Grau III, procure um especialista.";
                colorResult = Color.parseColor("#FF0000");

                /*resultadoImc.setTextColor(Color.parseColor("#FF0000"));
                resultadoImc.setText("O valor do seu IMC é: " + imc + "\nObesidade Grau III, procure um especialista.");*/
            }

           adapter = new SimpleAdapter(this, true, 1, imcResult, messageResult, colorResult, 0);
           dialog();
        }
    }

    public void calcularPesoIdeal(View view) {

        if (editAltura.getText().toString().equals("")) {
            Toast.makeText(this, "Informe sua altura", Toast.LENGTH_SHORT).show();
        } else {

            altura = Double.valueOf(editAltura.getText().toString()).doubleValue();

            double pesoMin = altura * altura * 18.50;
            double pesoMax = altura * altura * 24.99;

            //pesoIdeal.setTextColor(Color.parseColor("#000099"));
            //pesoIdeal.setText("O peso ideal para sua altura é entre " + round(pesoMin, 2) + " kg e " + round(pesoMax, 2) + "kg.");
        }
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_sobre){
            Intent intent = new Intent(MainActivity.this, ScrollingActivitySobre.class);
            startActivity(intent);

            return true;
        }

        if(id == R.id.action_peso) {
            Intent intent = new Intent(MainActivity.this, Scrolling_PesoIdeal.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


    public void dialog(){



        DialogPlus dialog = DialogPlus.newDialog(this)
                .setAdapter(adapter)
                        //.setHeader(R.layout.header)
                /*.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                    }
                })*/
                .setExpanded(true)
                .setGravity(Gravity.BOTTOM)
                .create();
        dialog.show();
    }

}
