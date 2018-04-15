package com.example.kylie.feelslikeweather.models.wrappers;

import com.example.kylie.feelslikeweather.models.darkskypojos.DarkSkyForecast;
import com.example.kylie.feelslikeweather.models.darkskypojos.Datum_;
import com.example.kylie.feelslikeweather.models.darkskypojos.Datum__;
import com.example.kylie.feelslikeweather.utils.Print;

import java.io.Serializable;

/**
 * Created by Kylie on 12/28/2016.
 */

public class WeatherWrapper implements Serializable {

    private final DarkSkyForecast forecast;
    private String city;
    private String state;
    private String[] location;
    private int sunriseTime;
    private int sunsetTime;

    private Report current;

    public HourWrapper getHourly() {
        return hourWrapper;
    }
    private HourWrapper hourWrapper;
    private MinuteWrapper minuteWrapper;
    private DailyWrapper dailyWrapper;

    public String getTimezone() {
        return timezone;
    }

    public Report getCurrent() {
        return current;
    }

    public DailyWrapper getDaily(){
        return dailyWrapper;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    private String timezone;

    public String[] getLocation() {
        return location;
    }

    public void setLocation(String[] location) {
        this.location = location;
    }

    public WeatherWrapper(DarkSkyForecast forecast) {
        //TODO: May need this for detailed view
        this.forecast = forecast;
        hourWrapper = new HourWrapper();
        current = new Report();
        dailyWrapper = new DailyWrapper();
        extractCurrentWeather();
        extractHour();
        extractDays();
    }

    private void extractCurrentWeather() {


        timezone = forecast.getTimezone();
        location = getLocation(forecast.getLatitude(), forecast.getLongitude());
        //TODO: Find out how to set time

        if(forecast.getCurrently().getPrecipProbability()>0) {
            //Precipitation

        }
        current.setDewPoint(forecast.getCurrently().getDewPoint());
        current.setHumidity(forecast.getCurrently().getHumidity());
        current.setPressure(forecast.getCurrently().getPressure());
        current.setWindBearing(forecast.getCurrently().getWindBearing());
        current.setWindSpeed(forecast.getCurrently().getWindSpeed());
        current.setSummary(forecast.getCurrently().getSummary());
        current.setIcon(forecast.getCurrently().getIcon());
        current.setTemperature(forecast.getCurrently().getTemperature().floatValue());
        current.setVisibility(forecast.getCurrently().getVisibility());
        current.setApparentTemperature(forecast.getCurrently().getApparentTemperature());
        current.setMoonPhase(forecast.getDaily().getData().get(0).getMoonPhase());

    }

    private void extractDays(){

        dailyWrapper.setSummary(forecast.getDaily().getSummary());
        forecast.getDaily().getIcon();
        for(Datum__ day : forecast.getDaily().getData()){
            Report entry = new Report();
            entry.setDewPoint(day.getDewPoint());
            entry.setHumidity(day.getHumidity());
            entry.setPressure(day.getPressure());
            entry.setWindBearing(day.getWindBearing());
            entry.setWindSpeed(day.getWindSpeed());
            entry.setSummary(day.getSummary());
            entry.setIcon(day.getIcon());
            entry.setTemperatureMax(day.getTemperatureMax().floatValue());
            entry.setTemperatureMin(day.getTemperatureMin().floatValue());
            entry.setVisibility(day.getVisibility());
            entry.setApparentTemperature(forecast.getCurrently().getApparentTemperature());
            entry.setMoonPhase(day.getMoonPhase());
            dailyWrapper.addDay(entry);

        }



    }

    private void extractHour(){

        hourWrapper.setSummary(forecast.getHourly().getSummary());
        hourWrapper.setIcon(forecast.getHourly().getIcon());

        for(Datum_ entry : forecast.getHourly().getData()){
            Report hour = new Report();
            hour.setApparentTemperature(entry.getApparentTemperature());
            hour.setCloudCover(entry.getCloudCover());
            hour.setDewPoint(entry.getDewPoint());
            hour.setHumidity(entry.getHumidity());
            hour.setIcon(entry.getIcon());
            hour.setOzone(entry.getOzone());
            hour.setPrecipAccumulation(entry.getPrecipAccumulation());
            hour.setPrecipProbability(entry.getPrecipProbability());
            hour.setPrecipType(entry.getPrecipType());
            hour.setPressure(entry.getPressure());
            hour.setPrecipIntensity(entry.getPrecipIntensity());
            hour.setSummary(entry.getSummary());
            hour.setTemperature(entry.getTemperature().floatValue());
            hour.setTime(entry.getTime());
            hour.setVisibility(entry.getVisibility());
            hour.setWindBearing(entry.getWindBearing());
            hour.setWindSpeed(entry.getWindSpeed());
            hourWrapper.addHour(hour);
        }
    }



    private String[] getLocation(Double latitude, Double longitude) {
        return new String[]{latitude.toString(),longitude.toString()};
    }

    public Precipitation getPrecipitation() {


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