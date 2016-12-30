package com.example.kylie.feelslikeweather.screens;

import com.example.kylie.feelslikeweather.models.darkskypojos.DarkSkyForecast;
import com.example.kylie.feelslikeweather.models.wrappers.DarkSkyPOJOWrapper;

import java.util.ArrayList;

/**
 * Created by Kylie on 12/2/2016.
 */

public interface CurrentWeatherScreen {

    public void refreshWeatherList(DarkSkyPOJOWrapper wrapper);

    public void failedCall();

    public void openDetailedWeatherActivity(DarkSkyPOJOWrapper forecast);

    public void addNewLocationToWeatherList(DarkSkyPOJOWrapper forecast, int position);

    public void loadWeatherLocationsFromSettings(ArrayList<String> settingsLocations);
}
