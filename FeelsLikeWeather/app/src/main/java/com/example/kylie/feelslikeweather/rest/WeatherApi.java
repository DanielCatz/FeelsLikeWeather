package com.example.kylie.feelslikeweather.rest;

import com.example.kylie.feelslikeweather.models.darkskypojos.DarkSkyForcast;
import com.example.kylie.feelslikeweather.models.pojos.CurrentWeather;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Kylie on 12/2/2016.
 */

public interface WeatherApi {

    @GET("weather")
    Observable<CurrentWeather> getCurrentWeather(@Query("zip") final String zip,
                                                 @Query("APPID") final String key
    );

    @GET("forcast/{key}/{latLong}")
    Observable<DarkSkyForcast> getWeatherForcast(@Path("key") final String key,
                                                 @Path("latLong") final String latLong
    );



}
