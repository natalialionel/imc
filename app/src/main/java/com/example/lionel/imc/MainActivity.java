package com.example.lionel.imc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lionel.imc.base.BaseActivity;
import com.orhanobut.dialogplus.DialogPlus;

public class MainActivity extends BaseActivity {

    private double peso;
    private double altura;

    private EditText editPeso;
    private EditText editAltura;

    private SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editPeso = (EditText) findViewById(R.id.input_peso);
        editAltura = (EditText) findViewById(R.id.input_altura);

        RelativeLayout banner = (RelativeLayout) findViewById(R.id.banner);
        carregarADS_Banner(this, banner);
    }

    public void calcularImc(View view){

        double imcResult = 0;
        String messageResult = "";
        int colorResult = 0;

        String replacePeso;
        String replaceAltura;


       if(editPeso.getText().toString().equals("") || editAltura.getText().toString().equals("") ){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }else {
           replaceAltura = editAltura.getText().toString();
           replaceAltura = replaceAltura.replace(",", ".");

           replacePeso = editPeso.getText().toString();
           replacePeso = replacePeso.replace(",", ".");

            peso = Double.valueOf(replacePeso.toString()).doubleValue();
            altura = Double.valueOf(replaceAltura.toString()).doubleValue();

            double imc = round((peso / (altura * altura)), 2);

            if (imc < 16.00) {
                imcResult = imc;
                messageResult = "\nVocê está com magreza grave, procure um especialista.";
                colorResult = Color.parseColor("#01579B");
            } else if (imc >= 16.00 && imc < 17.00) {
                imcResult = imc;
                messageResult = "\nVocê está com magreza moderada, procure um especialista.";
                colorResult = Color.parseColor("#0288D1");
            } else if (imc >= 17.00 && imc < 18.50) {
                imcResult = imc;
                messageResult = "\nVocê está com magreza leve, procure um especialista.";
                colorResult = Color.parseColor("#03A9F4");
            } else if (imc >= 18.50 && imc < 25.00) {
                imcResult = imc;
                messageResult = "\nParabéns, você está no seu peso ideal.";
                colorResult = Color.parseColor("#00C853");
            } else if (imc >= 25.00 && imc < 30.00) {
                imcResult = imc;
                messageResult = "\nVocê está com sobrepeso, procure um especialista.";
                colorResult = Color.parseColor("#FFD600");
            } else if (imc >= 30.00 && imc < 35.00) {
                imcResult = imc;
                messageResult = "\nVocê está com Obesidade Grau I, procure um especialista.";
                colorResult = Color.parseColor("#F9A825");
            } else if (imc >= 35.00 && imc < 40.00) {
                imcResult = imc;
                messageResult = "\nVocê está com Obesidade Grau II, procure um especialista.";
                colorResult = Color.parseColor("#E53935");
            } else if (imc >= 40.00) {
                imcResult = imc;
                messageResult = "\nVocê está com Obesidade Grau III, procure um especialista.";
                colorResult = Color.parseColor("#B71C1C");
            }

           PesoIdealPojo pesoIdeal = calcularPesoIdeal();
//           Toast.makeText(this, pesoIdeal.toString(), Toast.LENGTH_SHORT).show();

           adapter = new SimpleAdapter(this, imcResult, messageResult, colorResult, pesoIdeal.getMessage(), pesoIdeal.getPesMin(), pesoIdeal.getPesMax(), pesoIdeal.getCor());
           dialog(adapter);


        }
    }

    public PesoIdealPojo calcularPesoIdeal() {
        PesoIdealPojo idealPojo = null;

        if (editAltura.getText().toString().equals("")) {
            Toast.makeText(this, "Informe sua altura", Toast.LENGTH_SHORT).show();
        } else {

            altura = Double.valueOf(editAltura.getText().toString()).doubleValue();

            double pesoMin = altura * altura * 18.50;
            double pesoMax = altura * altura * 24.99;

            idealPojo = new PesoIdealPojo("O peso ideal para sua altura é entre ", round(pesoMin, 2), round(pesoMax, 2), Color.parseColor("#000099"));
        }
        return idealPojo;
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


    public void dialog(SimpleAdapter adapter){

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
        hideKeyBoard();

    }

    public void hideKeyBoard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
