package com.example.kylie.feelslikeweather.presenters;

import com.example.kylie.feelslikeweather.models.pojos.CurrentWeather;
import com.example.kylie.feelslikeweather.rest.WeatherService;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;

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
                                   screen.refreshCurrentWeather(currentWeather.getName());
                                   //TODO check here https://kmangutov.wordpress.com/2015/03/28/android-mvp-consuming-restful-apis/
                               }
                           }
                );
    }


    public void getWeatherForcast(){

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
                                              screen.refreshCurrentWeather(currentWeather.getName());
                                              //TODO check here https://kmangutov.wordpress.com/2015/03/28/android-mvp-consuming-restful-apis/
                                          }
                                      }
        );
    }

    public void rxUnSubscribe(){
        if(subscription!=null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
