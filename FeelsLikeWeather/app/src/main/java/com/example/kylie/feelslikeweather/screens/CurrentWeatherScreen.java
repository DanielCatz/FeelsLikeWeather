package com.example.kylie.feelslikeweather.screens;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.kylie.feelslikeweather.models.wrappers.WeatherWrapper;

import java.util.ArrayList;

/**
 * Created by Kylie on 12/2/2016.
 */

public interface CurrentWeatherScreen {


    public void refreshWeatherListView(WeatherWrapper wrapper, int position);

    public void failedCall();

    public void showToast(String msg);

    public void openDetailedWeatherActivity(WeatherWrapper forecast);

    public void addNewLocationToWeatherList(WeatherWrapper forecast);

    public void loadWeatherLocationsFromSettings(int numberOfSpacesToReserve);



    public AppCompatActivity getActivity();
    public Intent getIntentData();

    public void deleteLocationAtPosition(int position);
}
