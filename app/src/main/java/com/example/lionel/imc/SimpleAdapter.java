package com.example.lionel.imc;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lionel on 20/03/16.
 */
public class SimpleAdapter extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private double resultado;
    private String message;
    private int color;
    private String messagePesoIdeal;
    private double pesMin;
    private double pesMax;
    private int colorPessoaIdeal;
    private String messageResultado;

    public SimpleAdapter(Context context,double resultado, String message, int color, String messagePesoIdeal, double pesMin, double pesMax, int colorPessoaIdeal) {
        layoutInflater = LayoutInflater.from(context);
        this.resultado = resultado;
        this.message = message;
        this.color = color;
        this.messagePesoIdeal = messagePesoIdeal;
        this.pesMin = pesMin;
        this.pesMax = pesMax;
        this.colorPessoaIdeal = colorPessoaIdeal;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;

        if (view == null) {

            view = layoutInflater.inflate(R.layout.dialog, parent, false);

            viewHolder = new ViewHolder();
          /*  viewHolder.imc = (TextView) view.findViewById(R.id.imc);

            viewHolder.cardIMC = (CardView) view.findViewById(R.id.card);
            */
            viewHolder.pesoIdeal = (TextView) view.findViewById(R.id.pesoidealResult);
            viewHolder.cardPesoIdeal = (CardView) view.findViewById(R.id.cardPesoIdeal);
            viewHolder.message = (TextView) view.findViewById(R.id.textViewResultado);
            viewHolder.cardResultado = (CardView) view.findViewById(R.id.card_Resultado);
            viewHolder.layoutRow1 = (RelativeLayout)view.findViewById(R.id.row1);
            viewHolder.layoutRow2 = (RelativeLayout)view.findViewById(R.id.row2);
            viewHolder.layoutRow3 = (RelativeLayout)view.findViewById(R.id.row3);
            viewHolder.layoutRow4 = (RelativeLayout)view.findViewById(R.id.row4);
            viewHolder.layoutRow5 = (RelativeLayout)view.findViewById(R.id.row5);
            viewHolder.layoutRow6 = (RelativeLayout)view.findViewById(R.id.row6);
            viewHolder.layoutRow7 = (RelativeLayout)view.findViewById(R.id.row7);
            viewHolder.layoutRow8 = (RelativeLayout)view.findViewById(R.id.row8);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
/*
            viewHolder.imc.setText("O valor do seu IMC é "+resultado + message);
            viewHolder.cardIMC.setCardBackgroundColor(color);
*/
            viewHolder.pesoIdeal.setText(messagePesoIdeal + " " + pesMin + " kg e " + pesMax + "kg.");
            //viewHolder.cardPesoIdeal.setCardBackgroundColor(colorPessoaIdeal);
            viewHolder.message.setText("O valor do seu IMC é " + resultado + message);
            viewHolder.cardResultado.setCardBackgroundColor(color);

        int colorLayout= Color.parseColor("#BDBDBD");

        if (resultado < 16.00) {
            viewHolder.layoutRow1.setBackgroundColor(colorLayout);

        } else if (resultado >= 16.00 && resultado < 17.00) {
            viewHolder.layoutRow2.setBackgroundColor(colorLayout);

        } else if (resultado >= 17.00 && resultado < 18.50) {
            viewHolder.layoutRow3.setBackgroundColor(colorLayout);

        } else if (resultado >= 18.50 && resultado < 25.00) {
            viewHolder.layoutRow4.setBackgroundColor(colorLayout);

        } else if (resultado >= 25.00 && resultado < 30.00) {
            viewHolder.layoutRow5.setBackgroundColor(colorLayout);

        } else if (resultado >= 30.00 && resultado < 35.00) {
            viewHolder.layoutRow6.setBackgroundColor(colorLayout);

        } else if (resultado >= 35.00 && resultado < 40.00) {
            viewHolder.layoutRow7.setBackgroundColor(colorLayout);

        } else if (resultado >= 40.00) {
            viewHolder.layoutRow8.setBackgroundColor(colorLayout);

        }

        return view;
    }

    static class ViewHolder {
        TextView imc;
        TextView pesoIdeal;
        TextView message;
        CardView cardIMC;
        CardView cardPesoIdeal;
        CardView cardResultado;
        RelativeLayout layoutRow1;
        RelativeLayout layoutRow2;
        RelativeLayout layoutRow3;
        RelativeLayout layoutRow4;
        RelativeLayout layoutRow5;
        RelativeLayout layoutRow6;
        RelativeLayout layoutRow7;
        RelativeLayout layoutRow8;
    }


}