package com.example.kylie.feelslikeweather.presenters;

import com.example.kylie.feelslikeweather.models.Repository;
import com.example.kylie.feelslikeweather.models.darkskypojos.DarkSkyForecast;
import com.example.kylie.feelslikeweather.models.wrappers.LocationService;
import com.example.kylie.feelslikeweather.models.wrappers.WeatherWrapper;
import com.example.kylie.feelslikeweather.rest.WeatherService;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;
import com.example.kylie.feelslikeweather.utils.Print;

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
    private LocationService locationService;
    public MainActivityPresenter(CurrentWeatherScreen screen, WeatherService weatherService, Repository repository, LocationService locationService){
        this.screen = screen;
        this.weatherService = weatherService;
        this.repository = repository;
        this.locationService =  locationService;
    }

    public void handleRefreshCurrentWeatherScreen(){
        ArrayList<String> prefLocations = repository.getSavedLocations();
        if(prefLocations==null)
        {
            screen.loadWeatherLocationsFromSettings(0);
        }else{
            screen.loadWeatherLocationsFromSettings(prefLocations.size());
            int i = 0;
            for (String location : prefLocations) {
                refreshWeatherForecast(location, i);
                i++;
            }
        }

    }


    public void handleClearSettings(){
        repository.clearLocations();
        this.handleRefreshCurrentWeatherScreen();
    }

    public void handleDeleteWeatherLocation(int position){
        repository.deleteLocationAtPosition(position);
            screen.deleteLocationAtPosition(position);

    }



    private void getWeatherForecast(final String latLong, final boolean isUpdate, final int position){

        String key = weatherService.getKey();
        Observable<DarkSkyForecast> call = (Observable<DarkSkyForecast>)
                weatherService.getPreparedObservable(weatherService.getAPI().getWeatherForecast(key,latLong), DarkSkyForecast.class, false, false);
        subscription = call.subscribe(new Observer<DarkSkyForecast>() {
                                          @Override
                                          public void onCompleted() {
                                              //screen.showToast("Done");

                                          }

                                          @Override
                                          public void onError(Throwable e) {
                                                Print.out(e.getMessage());
                                              screen.failedCall();
                                          }

                                          @Override
                                          public void onNext(DarkSkyForecast forecast) {
                                              WeatherWrapper wrapper = new WeatherWrapper(forecast);
                                              wrapper.setState(locationService.getState(screen.getActivity(),latLong));
                                              wrapper.setCity(locationService.getCity(screen.getActivity(),latLong));
                                              if(isUpdate){
                                                  screen.refreshWeatherListView(wrapper,position);
                                              }else{
                                                  screen.addNewLocationToWeatherList(wrapper);
                                              }


                                          }
                                      }
        );
    }


    private void appendWeatherForecast(String latLong){
            Print.out("adding from append");
            getWeatherForecast(latLong, false,0);
            repository.saveLocation(latLong);
    }
    private void refreshWeatherForecast(String latLong,int row){

        getWeatherForecast(latLong,true,row);
    }

    public void handleSelectLocation(){
        locationService.LaunchPlacesIntentForResult(screen.getActivity());
    }

    public void handleOnActivityResult(int requestCode, int resultCode){
        String latLong;
        latLong=locationService.getPlacesResult(requestCode,resultCode,screen.getIntentData(),screen.getActivity());
        appendWeatherForecast(latLong);
    }


    public void rxUnSubscribe(){
        if(subscription!=null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }

}
