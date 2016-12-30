package com.example.kylie.feelslikeweather.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kylie.feelslikeweather.R;
import com.example.kylie.feelslikeweather.models.wrappers.DarkSkyPOJOWrapper;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;
import com.example.kylie.feelslikeweather.utitlity.Print;

import java.util.ArrayList;


/**
 * Created by Kylie on 12/27/2016.
 */

public class CurrentWeatherAdapter extends RecyclerView.Adapter {

    private ArrayList<DarkSkyPOJOWrapper> forecasts;
    private CurrentWeatherScreen screen;

    public CurrentWeatherAdapter(CurrentWeatherScreen screen){
        this.forecasts =new ArrayList<>();
        this.screen= screen;
    }

    public void addWeatherRow(DarkSkyPOJOWrapper event, int position){
            Print.out("add");
            forecasts.add(event);
            notifyDataSetChanged();
    }


    public void updateWeatherRow(DarkSkyPOJOWrapper event, int position){
        Print.out("refresh");
        forecasts.set(position,event);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_weather_row_item, parent, false);
        return new CurrentWeatherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((CurrentWeatherViewHolder) holder).bindData(forecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }



    public void setInitialListSize(int initialListSize) {
        for(int i = 0 ; i<initialListSize;i++){
            forecasts.add(null);
        }
    }

    class CurrentWeatherViewHolder extends RecyclerView.ViewHolder{

        TextView txtState;
        TextView txtCity;
        TextView txtTemp;
        TextView txtTime;
        ImageView imgIcon;
        DarkSkyPOJOWrapper forecast;
        //TODO:deal with doodads

        public CurrentWeatherViewHolder(View view) {
            super(view);
            txtState = (TextView) view.findViewById(R.id.txt_cur_state);
            txtTemp = (TextView) view.findViewById(R.id.txt_cur_temp);
            txtTime = (TextView) view.findViewById(R.id.txt_cur_time);
            txtCity = (TextView) view.findViewById(R.id.txt_cur_city);
            view.setOnClickListener(mClickListener);
            //TODO: deal with icon
        }

        public void bindData(DarkSkyPOJOWrapper forecast) {
            this.forecast= forecast;
            if(forecast!=null) {
                txtState.setText(forecast.getLocation()[0]);
                txtCity.setText(forecast.getLocation()[1]);
                txtTemp.setText(forecast.getTempString());
                txtTime.setText(forecast.getTime());
                //txtCity.setText(darkSkyForecast.getCurrently()
            }
        }

        public void updateCard(){

            txtState.setText("DERP");
            txtCity.setText(forecast.getLocation()[1]);
            txtTemp.setText("DERP");
            txtTime.setText(forecast.getTime());

        }

        View.OnClickListener mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Print.out("tapped item" + getAdapterPosition());
//                updateCard();
//                updateCardAt();
                screen.openDetailedWeatherActivity(forecast);
            }
        };

    }
}
