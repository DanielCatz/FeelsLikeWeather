package com.example.kylie.feelslikeweather.ui.viewholders;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.kylie.feelslikeweather.R;
import com.example.kylie.feelslikeweather.models.wrappers.WeatherWrapper;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;
import com.example.kylie.feelslikeweather.screens.WeatherOverviewScreen;
import com.example.kylie.feelslikeweather.utils.Print;

/**
 * Created by Kylie on 2017-08-31.
 */

public class CurrentWeatherViewHolder extends RecyclerView.ViewHolder implements ICompositeViewHolder{
    // TODO:Interface this?
    TextView txtState;
    TextView txtCity;
    TextView txtTemp;
    TextClock txtTime;
    ImageView imgIcon;
    WeatherWrapper forecast;
    LinearLayout container;
    CurrentWeatherScreen screen;
    //TODO:deal with doodads

    public CurrentWeatherViewHolder(View view, CurrentWeatherScreen targetScreen) {
        super(view);
        txtState = (TextView) view.findViewById(R.id.txt_cur_state);
        txtTime = (TextClock) view.findViewById(R.id.txt_cur_time);
        txtCity = (TextView) view.findViewById(R.id.txt_cur_city);
        txtTemp = (TextView) view.findViewById(R.id.txt_cur_temp);
        imgIcon = (ImageView) view.findViewById(R.id.img_cur_icon);
        container = (LinearLayout) view.findViewById(R.id.current_weather_root);
        screen = targetScreen;
        view.setOnClickListener(mClickListener);
        //TODO: deal with icon
    }

    public CurrentWeatherViewHolder(View view, WeatherOverviewScreen targetScreen) {
        super(view);
        txtState = (TextView) view.findViewById(R.id.txt_cur_state);
        txtTime = (TextClock) view.findViewById(R.id.txt_cur_time);
        txtCity = (TextView) view.findViewById(R.id.txt_cur_city);
        txtTemp = (TextView) view.findViewById(R.id.txt_cur_temp);
        imgIcon = (ImageView) view.findViewById(R.id.img_cur_icon);
        container = (LinearLayout) view.findViewById(R.id.current_weather_root);
//        screen = targetScreen;
        view.setOnClickListener(mClickListener);
        //TODO: deal with icon
    }

    public void bindData(WeatherWrapper forecast) {
        this.forecast= forecast;
        if(forecast!=null) {
            txtState.setText(forecast.getState());
            txtCity.setText(forecast.getCity());
            txtTemp.setText(forecast.getCurrent().getFormattedTempString());
            txtTime.setFormat24Hour(null);
//                txtTime.getFormat12Hour();
            txtTime.setTimeZone(forecast.getTimezone());

            imgIcon.setImageResource(getIconPngResource(forecast));
            setCardStyleByWeather(forecast.getCurrent().getFormattedWeatherString());

        }
    }

    private int getIconPngResource(WeatherWrapper forecast) {
        Resources res = imgIcon.getContext().getResources();
        return res.getIdentifier(forecast.getCurrent().getFormattedWeatherString(),"mipmap",imgIcon.getContext().getPackageName());
    }

    private void setCardStyleByWeather(String weather){
        Print.out(weather);
        switch (weather){

            case "clearday":
                container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorClearDay));
                brightenTextAndIcons();
                break;

            case "clearnight":
                container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorClearNight));
                brightenTextAndIcons();
                break;
            case "cloudy":
                container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorCloudy));
                brightenTextAndIcons();
                break;
            case "fog":
                container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorFog));
                brightenTextAndIcons();
                break;
            case "partlycloudyday":
                container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorPartlyCloudyDay));
                brightenTextAndIcons();
                break;
            case "partlycloudnight":
                container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorPartlyCloudyNight));
                brightenTextAndIcons();
                break;
            case "rain":
                container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorRain));
                brightenTextAndIcons();
                break;
            case "sleet":
                container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorSleet));
                brightenTextAndIcons();
                break;
            case "snow":
                container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorSnow));
                break;
            case "wind":
                container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorWind));
                brightenTextAndIcons();
                break;
            default:
                container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorPrimary));
                brightenTextAndIcons();

        }

    }

    private void brightenTextAndIcons(){

        imgIcon.setColorFilter(Color.WHITE);
        txtState.setTextColor(Color.WHITE);
        txtCity.setTextColor(Color.WHITE);
        txtTemp.setTextColor(Color.WHITE);
        txtTime.setTextColor(Color.WHITE);

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Print.out("tapped item" + getAdapterPosition());
            screen.openDetailedWeatherActivity(forecast);
        }
    };

}