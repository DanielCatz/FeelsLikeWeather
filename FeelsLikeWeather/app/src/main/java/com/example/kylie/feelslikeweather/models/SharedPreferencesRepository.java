package com.example.kylie.feelslikeweather.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;

/**
 * Created by Kylie on 12/29/2016.
 */

public class SharedPreferencesRepository {
    private SharedPreferences preferences;

    public SharedPreferencesRepository(Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }
    //TODO:write to Shared preferences in the format "lat,long|lat,long|..."
    public ArrayList<String> getSavedLocations(){
        ArrayList<String> locations =new ArrayList<>();
        String[] tempArray= preferences.getString("locations","NULL").split("|");
        for(int i = 0; i <tempArray.length;i++){
            locations.add(tempArray[i]);
        }
        return locations;
    }

}
