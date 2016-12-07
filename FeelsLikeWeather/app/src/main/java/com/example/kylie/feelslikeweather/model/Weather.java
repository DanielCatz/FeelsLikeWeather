package com.example.kylie.feelslikeweather.model;

/**
 * Created by Kylie on 12/2/2016.
 */

public class Weather {
private int visibility;
    private String station;
    private String name;

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weather() {
    }
}
