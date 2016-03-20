package com.example.lionel.imc;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by lionel on 20/03/16.
 */
public class SimpleAdapter extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private boolean isGrid;
    private int quantidade;
    private double resultado;
    private int tipo;
    private String message;
    private int color;

    public SimpleAdapter(Context context, boolean isGrid, int quantidade, double resultado, String message, int color, int tipo) {
        layoutInflater = LayoutInflater.from(context);
        this.isGrid = isGrid;
        this.quantidade = quantidade;
        this.resultado = resultado;
        this.tipo = tipo;
        this.message = message;
        this.color = color;
    }

    @Override
    public int getCount() {
        return quantidade;
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
            if (isGrid) {
                view = layoutInflater.inflate(R.layout.dialog, parent, false);
            } else {
                view = layoutInflater.inflate(R.layout.dialog, parent, false);
            }

            viewHolder = new ViewHolder();
            //viewHolder.textView = (TextView) view.findViewById(R.id.text_view);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.text_view_item_2);
            viewHolder.cardView = (CardView) view.findViewById(R.id.card);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (isGrid) {
            if(tipo == 0){
                viewHolder.textView2.setText("O valor do seu IMC é "+resultado + message);
                viewHolder.cardView.setCardBackgroundColor(color);
            }else if(tipo == 1){
                viewHolder.textView2.setText("PESO IDEAL "+resultado + message);
            }

            //viewHolder.textView2.setText( "" );
        }

        return view;
    }

    static class ViewHolder {
        TextView textView;
        TextView textView2;
        CardView cardView;
    }
}
