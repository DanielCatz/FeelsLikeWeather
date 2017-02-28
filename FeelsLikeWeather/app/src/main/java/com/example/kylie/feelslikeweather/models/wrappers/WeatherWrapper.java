package com.example.kylie.feelslikeweather.models.wrappers;

import com.example.kylie.feelslikeweather.models.darkskypojos.DarkSkyForecast;
import com.example.kylie.feelslikeweather.utils.Print;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Kylie on 12/28/2016.
 */

public class WeatherWrapper implements Serializable {

    private final DarkSkyForecast forecast;
    private String city;
    private String state;
    private String[] location;
    private String currentSummary;
    private Precipitation currentPrecipitation;
    private String currentIconRes;
    private Double currentTemperature;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    private String timezone;

    public String[] getLocation() {
        return location;
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
        timezone = forecast.getTimezone();
        location = getLocation(forecast.getLatitude(), forecast.getLongitude());
        currentSummary =  forecast.getCurrently().getSummary();
        currentIconRes = forecast.getCurrently().getIcon();
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

    public Precipitation getPrecipitation() {


        return null;
    }

    public String getProperIcon(){




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