package com.example.kylie.feelslikeweather.utils;

import com.example.kylie.feelslikeweather.models.wrappers.RecyclerWrapper;

/**
 * Created by Kylie on 2017-08-31.
 */

public enum RecyclerComponentEnum {BANNER(0),HOUR(1),WEEK(2), HOURS(3), DAY(4);
    private int value;

    RecyclerComponentEnum(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }


}


