package com.example.kylie.feelslikeweather.models;

import java.util.ArrayList;

/**
 * Created by Kylie on 12/30/2016.
 */
public interface Repository {

    public ArrayList<String> getSavedLocations();
    public void saveLocation(String latLong);
    public void clearLocations();
    public boolean deleteLocationAtPosition(int position);
}
