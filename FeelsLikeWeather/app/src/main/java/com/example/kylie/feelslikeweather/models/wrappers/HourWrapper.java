package com.example.kylie.feelslikeweather.models.wrappers;

import com.example.kylie.feelslikeweather.utils.RecyclerComponentEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kylie on 3/17/2017.
 */
public class HourWrapper extends RecyclerWrapper implements Serializable{
    private RecyclerComponentEnum type = RecyclerComponentEnum.HOURS;

    public List<Report> getHours() {
        return hours;
    }

    private List<Report> hours;
    private String icon;

    public String getSummary() {
        return summary;
    }

    private String summary;


    public HourWrapper(){
        hours = new ArrayList<Report>();
        setRecyclerType(RecyclerComponentEnum.HOURS);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void addHour(Report entry){

        hours.add(entry);
    }


}
