package com.example.kylie.feelslikeweather.screens;

/**
 * Created by Kylie on 12/2/2016.
 */

public interface CurrentWeatherScreen {

    public void refreshCurrentWeather(String data);

    public void failedCall();
}
