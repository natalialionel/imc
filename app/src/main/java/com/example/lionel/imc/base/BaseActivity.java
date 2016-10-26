package com.example.lionel.imc.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.lionel.imc.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

/**
 * Created by lionel on 10/09/16.
 */
public class BaseActivity extends AppCompatActivity {

    public void carregarADS_Banner(Context context, RelativeLayout banner){
        try {


            AdView ads = new AdView(context);
            ads.setAdSize(AdSize.SMART_BANNER);

            // insira o código do banner aqui

            ads.setAdUnitId("ca-app-pub-9023747170083444/6722176011");
            AdRequest request = new AdRequest.Builder().build();

            //seta o device como despositivo de teste para impedir que você clique nos bannes por acidente
            request.isTestDevice(this);

            banner.addView(ads);
            /*ads.loadAd(request);*/

            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .addTestDevice("D8DDDDC717FD35FE857CD28D53D94C6D")
                    .build();
            ads.loadAd(adRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class PesoIdealPojo{
        private String message;
        private double pesMin;
        private double pesMax;
        private int cor;

        public PesoIdealPojo(String message, double pesMin, double pesMax, int cor) {
            this.message = message;
            this.pesMin = pesMin;
            this.pesMax = pesMax;
            this.cor = cor;
        }

        public String getMessage() {
            return message;
        }
        public double getPesMin() {
            return pesMin;
        }
        public double getPesMax() {
            return pesMax;
        }
        public int getCor() {
            return cor;
        }

        @Override
        public String toString() {
            return "PesoIdealPojo{" +
                    "message='" + message + '\'' +
                    ", pesMin=" + pesMin +
                    ", pesMax=" + pesMax +
                    ", cor=" + cor +
                    '}';
        }
    }
}
