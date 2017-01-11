package com.example.kylie.feelslikeweather.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kylie.feelslikeweather.R;
import com.example.kylie.feelslikeweather.models.wrappers.WeatherWrapper;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;
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



    public void prepareSpaceToLoadLocationsAsBatch(int numberOfSpacesToReserve) {
        forecasts.clear();
            for(int i = 0 ; i<numberOfSpacesToReserve;i++){
                forecasts.add(null);
            }
        notifyDataSetChanged();
    }

    class CurrentWeatherViewHolder extends RecyclerView.ViewHolder{

        TextView txtState;
        TextView txtCity;
        TextView txtTemp;
        TextView txtTime;
        ImageView imgIcon;
        WeatherWrapper forecast;
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

        public void bindData(WeatherWrapper forecast) {
            this.forecast= forecast;
            if(forecast!=null) {
                txtState.setText(forecast.getState());
                txtCity.setText(forecast.getCity());
                txtTemp.setText(forecast.getTempString());
                txtTime.setText(forecast.getTime());
            }
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
