package com.example.kylie.feelslikeweather.models.wrappers;

import com.example.kylie.feelslikeweather.models.darkskypojos.DarkSkyForecast;
import com.example.kylie.feelslikeweather.utitlity.Print;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Kylie on 12/28/2016.
 */

public class DarkSkyPOJOWrapper implements Serializable {

    private final DarkSkyForecast forecast;

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

    private String[] location;
    private String time;
    private String currentSummary;
    private Precipitation currentPrecipitation;
    private String currentIconRes;
    private Double currentTemperature;

    public DarkSkyPOJOWrapper(DarkSkyForecast forecast) {
        this.forecast = forecast;
        extractCurrentWeather();
    }

    private void extractCurrentWeather() {
        location = getLocation(forecast.getLatitude(), forecast.getLongitude());
        time =getDateTime(forecast.getCurrently().getTime(),false);
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
        simpleDateFormat = new SimpleDateFormat("hh-mm-ss-aa");
        }else{
            simpleDateFormat = new SimpleDateFormat("kk-mm-ss");
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
}