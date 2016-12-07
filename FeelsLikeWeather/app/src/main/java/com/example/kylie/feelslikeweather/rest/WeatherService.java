package com.example.kylie.feelslikeweather.rest;

import com.example.kylie.feelslikeweather.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kylie on 12/2/2016.
 */

public interface WeatherService {
    @GET("weather")
    Call<Weather> getCurrentWeather(@Query("zip") final String zip,
                                    @Query("APPID") final String key
                                    );

}
