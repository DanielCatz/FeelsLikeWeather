package com.example.kylie.feelslikeweather.ui.viewholders;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kylie.feelslikeweather.R;
import com.example.kylie.feelslikeweather.models.wrappers.Report;
import com.example.kylie.feelslikeweather.screens.WeatherOverviewScreen;
import com.example.kylie.feelslikeweather.utils.Print;

import java.util.ArrayList;

/**
 * Created by Kylie on 3/16/2017.
 */

public class DailyWeatherViewHolder extends RecyclerView.ViewHolder {
    private final WeatherOverviewScreen screen;
    // TODO:Interface this?
        ImageView imgWeather;
        TextView txtTempHighValue;
        TextView txtTempLowValue;
        TextView txtWeather;
        TextView txtDay;
        Report forecast;
        LinearLayout container;
    private ArrayList<TextView> textViews;

    //TODO:deal with doodads



    public DailyWeatherViewHolder(View view, WeatherOverviewScreen targetScreen) {
        super(view);
        container = (LinearLayout) view.findViewById(R.id.day_root);
        screen = targetScreen;
        initializeTextViews(view);
        initializeImageViews(view);
        view.setOnClickListener(mClickListener);
        //TODO: deal with icon
    }

    private void setTextValues() {
        txtWeather.setText(forecast.getSummary());
        txtDay.setText("DAY");
        txtTempHighValue.setText( String.valueOf(forecast.getTemperatureMax()));
        txtTempLowValue.setText(String.valueOf(forecast.getTemperatureMin()));


    }

    public void bindData(Report forecast) {
            this.forecast= forecast;
            if(forecast!=null) {
                setTextValues();
                setCardStyleByWeather(forecast.getFormattedWeatherString());
                setWeatherImage();
            }
        }
    public void bindData(Report forecast,int position) {
        this.forecast= forecast;
        if(forecast!=null) {
            setTextValues();
            setCardStyleByWeather(forecast.getFormattedWeatherString());
            setWeatherImage();
        }
    }

        private void initializeTextViews(View view){
            textViews = new ArrayList<>();
            txtDay = (TextView) view.findViewById(R.id.day_weekday_txt);
            textViews.add(txtDay);
            txtWeather = (TextView) view.findViewById(R.id.day_summary_txt);
            textViews.add(txtWeather);
            txtTempHighValue = (TextView) view.findViewById(R.id.day_high_txt);
            textViews.add(txtTempHighValue);
            txtTempLowValue = (TextView) view.findViewById(R.id.day_low_txt);
            textViews.add(txtTempLowValue);
        }

        private void initializeImageViews(View view){

             imgWeather=(ImageView) view.findViewById(R.id.day_weather_ico);

        }

        private void setCardStyleByWeather(String weather){
            Print.out(weather);
            Print.out(R.color.colorClearDay);
            Print.out(ContextCompat.getColor(container.getContext(),R.color.colorClearDay));
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
            for(TextView t : textViews){
                t.setTextColor(Color.WHITE);
            }

                imgWeather.setColorFilter(Color.WHITE);

        }

        View.OnClickListener mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };


    private int getIconPngResource(Report forecast) {
        Resources res = imgWeather.getContext().getResources();
        return res.getIdentifier(forecast.getFormattedWeatherString(),"mipmap",imgWeather.getContext().getPackageName());
    }

    private void setWeatherImage(){
        imgWeather.setImageResource(getIconPngResource(forecast));
    }
//
    }

