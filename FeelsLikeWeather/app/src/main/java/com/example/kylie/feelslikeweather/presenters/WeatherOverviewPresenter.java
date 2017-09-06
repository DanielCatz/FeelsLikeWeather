package com.example.kylie.feelslikeweather.presenters;

import com.example.kylie.feelslikeweather.models.pojos.Weather;
import com.example.kylie.feelslikeweather.models.wrappers.WeatherWrapper;
import com.example.kylie.feelslikeweather.screens.WeatherOverviewScreen;

import java.util.ArrayList;

/**
 * Created by Kylie on 2017-08-31.
 */

public class WeatherOverviewPresenter {

    private WeatherWrapper weather;
    private WeatherOverviewScreen screen;

    public WeatherOverviewPresenter(WeatherOverviewScreen screen, WeatherWrapper weather){
        this.weather = weather;
        this.screen = screen;
    }

    public void buildWeatherComponentsList(){
        ArrayList<Object> components = new ArrayList<>();
        //first I want a detailed weather block
        components.add(weather);
        components.add(weather);
        screen.initializeScreen(components);
    }
}
