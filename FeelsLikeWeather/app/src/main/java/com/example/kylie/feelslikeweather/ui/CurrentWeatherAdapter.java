package com.example.kylie.feelslikeweather.ui;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.kylie.feelslikeweather.R;
import com.example.kylie.feelslikeweather.models.wrappers.WeatherWrapper;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;
import com.example.kylie.feelslikeweather.ui.viewholders.CurrentWeatherViewHolder;
import com.example.kylie.feelslikeweather.ui.viewholders.ICompositeViewHolder;
import com.example.kylie.feelslikeweather.utils.Print;

import java.util.ArrayList;


/**
 * Created by Kylie on 12/27/2016.
 */

public class CurrentWeatherAdapter extends RecyclerView.Adapter {

    private ArrayList<WeatherWrapper> forecasts;
    private CurrentWeatherScreen screen;

    public CurrentWeatherAdapter(CurrentWeatherScreen screen){
        this.forecasts =new ArrayList<>();
        this.screen= screen;

    }

    public void addWeatherRow(WeatherWrapper event){
    //    Print.out("preadd"+forecasts.toString());
        forecasts.add(event);
   //     Print.out("postadd"+forecasts.toString());
        notifyDataSetChanged();
    }


    public void updateWeatherRow(WeatherWrapper event, int position){
//        Print.out("preupdate"+forecasts.toString());
        forecasts.set(position,event);
   //     Print.out("postupdate"+forecasts.toString());
        notifyDataSetChanged();
    }

    public void removeWeatherRow(int position){
        forecasts.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_weather_row_item, parent, false);
        return new CurrentWeatherViewHolder(v,screen);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ICompositeViewHolder) holder).bindData(forecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }



    public void prepareSpaceToLoadLocationsAsBatch(int numberOfSpacesToReserve) {
        forecasts.clear();
            for(int i = 0 ; i<numberOfSpacesToReserve;i++){
                forecasts.add(null);
            }
        notifyDataSetChanged();
    }


}
