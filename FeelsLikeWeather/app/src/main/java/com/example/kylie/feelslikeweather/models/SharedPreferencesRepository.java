package com.example.kylie.feelslikeweather.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.kylie.feelslikeweather.Repository;
import com.example.kylie.feelslikeweather.utitlity.Print;

import java.util.ArrayList;

/**
 * Created by Kylie on 12/29/2016.
 */

public class SharedPreferencesRepository implements Repository {
    private SharedPreferences preferences;
    private ArrayList<String> locations;

    public SharedPreferencesRepository(Context context){
        locations =new ArrayList<>();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public ArrayList<String> getSavedLocations(){
        String locationString = preferences.getString("locations","");
        if(locationString==""){
            return null;
        }
        locations.clear();
        String[] tempArray= locationString.split("[|]");
        for(int i = 0; i <tempArray.length;i++){
            locations.add(tempArray[i]);
        }
        Print.out("getSavedlocations: "+ locations.toString());
        return locations;
    }

    public void saveLocation(String latLong){
        locations.add(latLong);
        SharedPreferences.Editor edit = preferences.edit();
        StringBuilder locationsString =new StringBuilder();

        for(int i= 0;i<locations.size();i++){
            Print.out("location build "+locations.get(i));
            locationsString.append(locations.get(i));
            if (i!=locations.size()-1){
                locationsString.append("|");
            }
        }

        Print.out("location result "+locationsString.toString());
        edit.putString("locations",locationsString.toString());
        edit.apply();
    }



    public void clearLocations(){
        SharedPreferences.Editor edit = preferences.edit();
        Print.out("Emptying:"+preferences.getString("locations","empty"));
        edit.clear();
        edit.commit();
    }
}
