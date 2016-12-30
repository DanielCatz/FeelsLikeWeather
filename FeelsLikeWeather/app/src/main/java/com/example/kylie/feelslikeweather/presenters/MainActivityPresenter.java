package com.example.kylie.feelslikeweather.presenters;

import com.example.kylie.feelslikeweather.Repository;
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
    private Repository repository;

    public MainActivityPresenter(CurrentWeatherScreen screen,WeatherService weatherService, Repository repository){
        this.screen = screen;
        this.weatherService = weatherService;
        this.repository = repository;
    }





    public void refreshCurrentWeatherScreen(){
        ArrayList<String> settingsLocations = new ArrayList<>();
        settingsLocations.add("45.501688900000005,-73.567256");
        settingsLocations.add("47.646187,-122.141241");
        settingsLocations.add("45.476393,-73.651176");
        settingsLocations.add("37.8267,-122.4233");

        ArrayList<String> prefLocations = repository.getSavedLocations();

        if(prefLocations!=null){
            screen.loadWeatherLocationsFromSettings(prefLocations);
        }
    }

    public void clearSettings(){
        repository.clearLocations();
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
                                  // screen.refreshWeatherList(currentWeather.getName());
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
                                                  screen.addNewLocationToWeatherList(wrapper,position);

                                              }else{
                                                  screen.refreshWeatherList(wrapper);
                                              }

                                          }
                                      }
        );
    }


    public void appendWeatherForecast(String latLong){

        if(repository.getSavedLocations()!=null) {
            Print.out("adding from append");
            getWeatherForecast(latLong, true, repository.getSavedLocations().size());
            repository.saveLocation(latLong);
        }

    }
    public void refreshWeatherForecast(){

        Print.out("refresh from append");
       // repository.saveLocation(latLong);
        Print.out("reposize:"+ repository.getSavedLocations().size());
       // getWeatherForecast(latLong,true,repository.getSavedLocations().size());

    }

    public void rxUnSubscribe(){
        if(subscription!=null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
