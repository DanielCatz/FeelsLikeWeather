package com.example.kylie.feelslikeweather.presenters;

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
        components.add(weather);//big weather block
        components.add(weather.getHourly().getHours().get(0).getSummary());// next Hour
        components.add(weather.getHourly().getSummary());//24h,button
        components.add(weather.getDaily().getSummary());//week
        for(int i = 0; i< 8; i++){//today plus next seven days
            components.add(weather.getDaily().getDays().get(i));
        }
        components.add("POWERED BY DARK SKY");
        screen.initializeScreen(components);
    }
}
