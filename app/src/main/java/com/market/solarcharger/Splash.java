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


    TextView text_weather;
    String url = "https://weather.naver.com/today/09215109";
    String msg;
    final Bundle bundle = new Bundle();
    Elements e;


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
                startActivity(intent);
                finish();
            }
        }, 300);

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