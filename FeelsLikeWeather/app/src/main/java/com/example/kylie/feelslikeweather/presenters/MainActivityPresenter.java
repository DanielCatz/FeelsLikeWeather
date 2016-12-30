package com.example.kylie.feelslikeweather.presenters;

import com.example.kylie.feelslikeweather.models.darkskypojos.DarkSkyForecast;
import com.example.kylie.feelslikeweather.models.pojos.CurrentWeather;
import com.example.kylie.feelslikeweather.models.wrappers.DarkSkyPOJOWrapper;
import com.example.kylie.feelslikeweather.rest.WeatherService;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;
import com.example.kylie.feelslikeweather.utitlity.Print;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by Kylie on 12/17/2016.
 */

public class MainActivityPresenter{

    private WeatherService weatherService;
    private CurrentWeatherScreen screen;
    private Subscription subscription;

    public MainActivityPresenter(CurrentWeatherScreen screen,WeatherService weatherService){
        this.screen = screen;
        this.weatherService = weatherService;
    }



    public void getCurrentWeather(){

        String zip = "98007,US";
        String key = weatherService.getKey();
        //Observable<CurrentWeather> call = weatherService.getAPI().getCurrentWeather(zip,key);
        Observable<CurrentWeather> call = (Observable<CurrentWeather>)
                weatherService.getPreparedObservable(weatherService.getAPI().getCurrentWeather(zip,key), CurrentWeather.class, true, false);
        subscription = call.subscribe(new Observer<CurrentWeather>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   screen.failedCall();
                               }

                               @Override
                               public void onNext(CurrentWeather currentWeather) {
                                  // screen.refreshCurrentWeather(currentWeather.getName());
                                   //TODO check here https://kmangutov.wordpress.com/2015/03/28/android-mvp-consuming-restful-apis/
                               }
                           }
                );
    }


    public void getWeatherForecast(String latLong, final boolean isNewLocation,final int position){
        String key = weatherService.getKey();

        Observable<DarkSkyForecast> call = (Observable<DarkSkyForecast>)
                weatherService.getPreparedObservable(weatherService.getAPI().getWeatherForecast(key,latLong), DarkSkyForecast.class, false, false);

        subscription = call.subscribe(new Observer<DarkSkyForecast>() {
                                          @Override
                                          public void onCompleted() {

                                          }

                                          @Override
                                          public void onError(Throwable e) {
                                                Print.out(e.getMessage());
                                              screen.failedCall();
                                          }

                                          @Override
                                          public void onNext(DarkSkyForecast forecast) {
                                              DarkSkyPOJOWrapper wrapper = new DarkSkyPOJOWrapper(forecast);
                                              if(isNewLocation){
                                                  screen.addNewLocation(wrapper,position);
                                              }else{
                                                  screen.refreshCurrentWeather(wrapper);
                                              }

                                          }
                                      }
        );
    }

    public void rxUnSubscribe(){
        if(subscription!=null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
