package com.example.kylie.feelslikeweather.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.kylie.feelslikeweather.utils.Print;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kylie on 12/29/2016.
 */

public class SharedPreferencesRepository implements Repository {
    private SharedPreferences preferences;
    private ArrayList<String> locations;

    public SharedPreferencesRepository(Context context){
        locations =new ArrayList<>();
//        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences = context.getSharedPreferences("locations",Context.MODE_PRIVATE);
    }

    public ArrayList<String> getSavedLocations(){
        String locationString = preferences.getString("locations","");
        if(locationString==""){
            return null;
        }
        locations.clear();
        String[] tempArray= locationString.split("[|]");
//        for(int i = 0; i <tempArray.length;i++){
//            locations.add(tempArray[i]);
//        }

        locations.addAll(Arrays.asList(tempArray));
        //Print.out("getSavedlocations: "+ locations.toString());
        return locations;
    }

    public void saveLocation(String latLong){
        locations.add(latLong);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("locations",getDelimitedLocationString());
        edit.apply();
    }

    public boolean deleteLocationAtPosition(int position){
        if(locations.size()>position){
            locations.remove(position);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("locations",getDelimitedLocationString());
            edit.apply();
            return false;
        }
        return true;
    }

    public void clearLocations(){
        SharedPreferences.Editor edit = preferences.edit();
        locations.clear();
        Print.out("Emptying:"+preferences.getString("locations","empty"));
        edit.clear();
        edit.commit();
        Print.out("Emptied:"+preferences.getString("locations","empty"));
    }

    private String getDelimitedLocationString(){
        StringBuilder locationsString =new StringBuilder();
        for(int i= 0;i<locations.size();i++){
            locationsString.append(locations.get(i));
            if (i!=locations.size()-1){
                locationsString.append("|");
            }
        }
        return locationsString.toString();
    }
}
