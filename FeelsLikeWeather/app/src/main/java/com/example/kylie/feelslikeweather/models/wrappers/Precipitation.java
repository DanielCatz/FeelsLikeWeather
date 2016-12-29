package com.example.kylie.feelslikeweather.models.wrappers;

import com.example.kylie.feelslikeweather.models.darkskypojos.Alert;

import java.io.Serializable;

/**
 * Created by Kylie on 12/28/2016.
 */

public class Precipitation implements Serializable {
    public String amount;
    public Alert alert;
    public Precipitation(){
    amount = "tite";

    }
}
