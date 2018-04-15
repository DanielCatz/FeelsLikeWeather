package com.example.kylie.feelslikeweather.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.kylie.feelslikeweather.R;
import com.example.kylie.feelslikeweather.models.wrappers.WeatherWrapper;
import com.example.kylie.feelslikeweather.presenters.WeatherOverviewPresenter;
import com.example.kylie.feelslikeweather.screens.WeatherOverviewScreen;
import com.example.kylie.feelslikeweather.ui.recycleradapters.CompositeWeatherAdapter;

import java.util.ArrayList;

public class WeatherOverviewActivity extends AppCompatActivity implements WeatherOverviewScreen {
    RecyclerView compositeRecycler;
    WeatherOverviewPresenter presenter;
    private CompositeWeatherAdapter compositeWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        compositeRecycler = (RecyclerView) findViewById(R.id.recycle_main);
        setSupportActionBar(toolbar);
        //
        presenter = new WeatherOverviewPresenter(this,getBundle());
        presenter.buildWeatherComponentsList();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public WeatherWrapper getBundle() {
        return (WeatherWrapper) getIntent().getSerializableExtra("Forecast");
    }


    @Override
    public void initializeScreen(ArrayList<Object> weather) {
        compositeRecycler.setHasFixedSize(true);
        compositeRecycler.setLayoutManager(new LinearLayoutManager(compositeRecycler.getContext()));
        compositeRecycler.setItemAnimator(new DefaultItemAnimator());
        compositeWeatherAdapter = new CompositeWeatherAdapter(this,weather);
        compositeRecycler.setAdapter(compositeWeatherAdapter);
    }


}
