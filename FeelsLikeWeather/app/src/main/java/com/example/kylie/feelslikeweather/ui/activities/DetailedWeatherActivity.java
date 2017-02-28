package com.example.kylie.feelslikeweather.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.kylie.feelslikeweather.R;
import com.example.kylie.feelslikeweather.models.wrappers.WeatherWrapper;
import com.example.kylie.feelslikeweather.utils.Print;

public class DetailedWeatherActivity extends AppCompatActivity {
TextView testText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        testText = (TextView) findViewById(R.id.txt_test);
        setSupportActionBar(toolbar);
        acceptBundle();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void acceptBundle(){
        WeatherWrapper forecast =(WeatherWrapper)getIntent().getSerializableExtra("Forecast");
        testText.setText(forecast.getTempString());
//        Precipitation forecast =(Precipitation)getIntent().getSerializableExtra("Forecast");
//        Print.out(forecast.amount);
    }
}
