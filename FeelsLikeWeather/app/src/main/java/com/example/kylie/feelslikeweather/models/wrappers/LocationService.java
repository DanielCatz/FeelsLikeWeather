package com.example.kylie.feelslikeweather.models.wrappers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;

import com.example.kylie.feelslikeweather.utils.Print;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kylie on 1/5/2017.
 */

public class LocationService {

    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private Place place;
    private String state;
    private String city;

    public LocationService(){
        state="";
        city = "";
    }

    /***
     * Begins a Places Activity to choose a location
     * @param activity
     */
    public void LaunchPlacesIntentForResult(AppCompatActivity activity){
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(activity);
            activity.startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {

        } catch (GooglePlayServicesNotAvailableException e) {

        }
    }

    /***
     * Needs to be called from an activity's Overriden OnActivityResult
     * @param requestCode
     * @param resultCode
     * @param data
     * @param context
     */
    public String getPlacesResult(int requestCode, int resultCode, Intent data,Context context){
        String latLong = "";
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                place = PlaceAutocomplete.getPlace(context, data);
                latLong = place.getLatLng().latitude+","+place.getLatLng().longitude;
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(context, data);
                Print.out(status.getStatusMessage());
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        return latLong;
    }



    public List<Address> getAddress(Context context,String latLong){
        String[] latLongArr = latLong.split(",");
        List<Address> addresses = null;
        try {
            Geocoder mGeocoder = new Geocoder(context, Locale.getDefault());
            addresses = mGeocoder.getFromLocation(Double.valueOf(latLongArr[0]), Double.valueOf(latLongArr[1]), 1);
        }catch (IOException e){

        }
        return addresses;
    }


    public String getCity(Context context,String latLong) {
            List<Address> addresses = getAddress(context,latLong);
            if (addresses != null && addresses.size() > 0) {
                return addresses.get(0).getAdminArea();
            }
        return null;
    }


    public String getState(Context context,String latLong) {
        List<Address> addresses = getAddress(context,latLong);
        if (addresses != null && addresses.size() > 0) {
                return addresses.get(0).getLocality();
            }
        return null;
    }
}
