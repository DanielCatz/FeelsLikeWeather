package com.example.kylie.feelslikeweather.screens;

import com.example.kylie.feelslikeweather.models.darkskypojos.DarkSkyForecast;
import com.example.kylie.feelslikeweather.models.wrappers.DarkSkyPOJOWrapper;

/**
 * Created by Kylie on 12/2/2016.
 */

public interface CurrentWeatherScreen {

    public void refreshCurrentWeather(DarkSkyPOJOWrapper wrapper);

    public void failedCall();

    public void openDetailedWeatherActivity(DarkSkyPOJOWrapper forecast);

    public void addNewLocation(DarkSkyPOJOWrapper forecast, int position);
}
