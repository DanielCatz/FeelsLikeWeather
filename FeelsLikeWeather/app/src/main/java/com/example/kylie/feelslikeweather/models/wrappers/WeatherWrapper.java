package com.example.kylie.feelslikeweather.models.wrappers;

import com.example.kylie.feelslikeweather.models.darkskypojos.DarkSkyForecast;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Kylie on 12/28/2016.
 */

public class WeatherWrapper implements Serializable {

    private final DarkSkyForecast forecast;
    private String city;
    private String state;
    private String[] location;
    private String time;
    private String currentSummary;
    private Precipitation currentPrecipitation;
    private String currentIconRes;
    private Double currentTemperature;
    public String[] getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getCurrentSummary() {
        return currentSummary;
    }

    public Precipitation getCurrentPrecipitation() {
        return currentPrecipitation;
    }

    public String getCurrentIconRes() {
        return currentIconRes;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCurrentSummary(String currentSummary) {
        this.currentSummary = currentSummary;
    }

    public void setCurrentPrecipitation(Precipitation currentPrecipitation) {
        this.currentPrecipitation = currentPrecipitation;
    }

    public void setCurrentIconRes(String currentIconRes) {
        this.currentIconRes = currentIconRes;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }



    public WeatherWrapper(DarkSkyForecast forecast) {
        this.forecast = forecast;
        extractCurrentWeather();
    }

    private void extractCurrentWeather() {
        location = getLocation(forecast.getLatitude(), forecast.getLongitude());
        time =getDateTime(forecast.getCurrently().getTime(),true);
        currentSummary =  forecast.getCurrently().getSummary();
        currentIconRes = getIcon();
        if(forecast.getCurrently().getPrecipProbability()>0) {
            currentPrecipitation = getPrecipitation();
        }
        currentTemperature = forecast.getCurrently().getTemperature();


    }

    public String getTempString(){
       return Math.round(currentTemperature)+"°";
    }

    private String[] getLocation(Double latitude, Double longitude) {
        //TODO:Stub
        return new String[]{latitude.toString(),longitude.toString()};
    }

    private String getDateTime(long unixTime, boolean isTwelveHourFormat) {
        java.sql.Timestamp timeStamp = new Timestamp(unixTime * 1000);
        java.sql.Date date = new java.sql.Date(timeStamp.getTime());
        SimpleDateFormat simpleDateFormat;
        if(isTwelveHourFormat){
        simpleDateFormat = new SimpleDateFormat("h:mm a");
        }else{
            simpleDateFormat = new SimpleDateFormat("k:mm");
        }
        String format = simpleDateFormat.format(date);
        return format;
    }

    public Precipitation getPrecipitation() {


        return null;
    }

    public String getIcon() {
        //string resource mapping
        return null;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}