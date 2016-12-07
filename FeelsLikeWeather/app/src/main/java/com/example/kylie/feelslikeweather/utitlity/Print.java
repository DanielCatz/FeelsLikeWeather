package com.example.kylie.feelslikeweather.utitlity;

import android.util.Log;

/**
 * Created by Kylie on 12/2/2016.
 */

public class Print {
    /**
     * Better log function that links to where the message came from; Eases debugging and cleanup
     * @param message Text that helps you fix your failures as a human being
     */
    public static void out(String message){
        StackTraceElement[] t= new Throwable().getStackTrace();
        String origin = t[1].toString();//line of where i wa called
        origin= origin.substring(origin.indexOf('('));
        Log.wtf(origin,message);
    }
}
