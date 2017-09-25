package com.example.kylie.feelslikeweather.models.wrappers;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kylie on 3/17/2017.
 */
public class DailyWrapper implements Serializable {

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String summary;
    public DailyWrapper(){
        days = new ArrayList<>();
    }

    public ArrayList<Report> getDays() {
        return days;
    }

    public void addDay(Report day) {
        days.add(day);
    }

    ArrayList<Report> days;


}
