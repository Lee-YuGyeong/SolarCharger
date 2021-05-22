package com.market.solarcharger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Splash extends AppCompatActivity {

    String url = "https://weather.naver.com/today/09215109";
    String msg;
    final Bundle bundle = new Bundle();
    Elements e;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        BackgroundThread thread = new BackgroundThread();
        thread.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("weather", msg);

                if (msg.matches("흐림") || msg.matches("구름많음") ) {
                    image = getResources().getIdentifier("clouds" , "drawable", getPackageName());
                }else if(msg.matches("맑음")){
                    image = getResources().getIdentifier("sun_image" , "drawable", getPackageName());

                }else if(msg.matches("비")){
                    image = getResources().getIdentifier("rain" , "drawable", getPackageName());
                }
                intent.putExtra("image", image);
                startActivity(intent);
                finish();
            }
        }, 700);

    }

    class BackgroundThread extends Thread {

        @Override
        public void run() {
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
                e = doc.select(".weather");

            } catch (IOException e) {
                e.printStackTrace();
            }
            msg = e.text();
        }

    }

}