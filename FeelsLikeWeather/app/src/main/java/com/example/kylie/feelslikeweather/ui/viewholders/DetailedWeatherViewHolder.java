package com.example.kylie.feelslikeweather.ui.viewholders;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kylie.feelslikeweather.R;
import com.example.kylie.feelslikeweather.models.wrappers.WeatherWrapper;
import com.example.kylie.feelslikeweather.screens.WeatherOverviewScreen;
import com.example.kylie.feelslikeweather.utils.Print;

import java.util.ArrayList;

/**
 * Created by Kylie on 3/16/2017.
 */

public class DetailedWeatherViewHolder extends RecyclerView.ViewHolder implements ICompositeViewHolder{
    private final WeatherOverviewScreen screen;
    // TODO:Interface this?
        ImageView imgWeather;
        ImageView imgPressure;
        ImageView imgDew;
        ImageView imgWind;
        ImageView imgPrecipitation;
        ImageView imgFeelsLike;
        ImageView imgHumidity;
        ImageView imgMoonPhase;
        ImageView imgSunset;
        ImageView imgVisibility;
        TextView txtNow;
        TextView txtTempValue;
        TextView txtWeather;
        TextView txtWindSpeed;
        TextView txtWindDirection;
        TextView txtPrecipitationValue;
        TextView txtPrecipitationAmount;
        TextView txtSunset;
        TextView txtSunsetTime;
        TextView txtMoonPhase;
        TextView txtMoonPhase2;
        TextView txtHumidity;
        TextView txtHumidityValue;
        TextView txtDew;
        TextView txtDewValue;
        TextView txtPressure;
        TextView txtPressureValue;
        TextView txtVisibility;
        TextView txtVisibilityValue;
        TextView txtFeelsValue;
        TextView txtFeelsLike;
        ArrayList<TextView> textViews;
        ArrayList<ImageView> imgViews;
        WeatherWrapper forecast;
        LinearLayout container;
        //TODO:deal with doodads



    public DetailedWeatherViewHolder(View view, WeatherOverviewScreen targetScreen) {
        super(view);
        container = (LinearLayout) view.findViewById(R.id.detailed_weather_root);
        screen = targetScreen;
        initializeTextViews(view);
        initializeImageViews(view);
        view.setOnClickListener(mClickListener);
        //TODO: deal with icon
    }

    private void setTextValues() {
        txtNow.setText(R.string.det_now_txt);
        txtTempValue.setText(forecast.getCurrent().getFormattedTempString());
        txtWeather.setText(forecast.getCurrent().getSummary());
        txtDewValue.setText(forecast.getCurrent().getDewPoint().toString());
        txtHumidityValue.setText(forecast.getCurrent().getHumidity().toString());
        txtFeelsValue.setText(forecast.getCurrent().getApparentTemperature().toString());
        txtPressureValue.setText(forecast.getCurrent().getPressure().toString());
        txtWindSpeed.setText(forecast.getCurrent().getWindSpeed().toString());
//
//        txtWindSpeed.setText(forecast.);//expose
//        txtWindDirection.setText(R.string.det_winddirection_txt);//expose
//        txtPrecipitationValue.setText(forecast);
//        txtPrecipitationAmount.setText(R.string.det_precipamount_txt);
//        txtSunset.setText(R.string.det_sunset_txt);
//        txtSunsetTime.setText(R.string.det_sunsettime_txt);
//        txtMoonPhase.setText(R.string.det_moonphase_txt);
//        txtMoonPhase2.setText(R.string.det_moonphase2_txt);
//        txtHumidity.setText(R.string.det_humid_txt);
//        txtHumidityValue.setText(R.string.det_humidvalue_txt);
//        txtDew.setText(R.string.det_dew_txt);
//        txtDewValue.setText(R.string.det_dewvalue_txt);
//        txtPressure.setText(R.string.det_pressure_txt);
//        txtPressureValue.setText(R.string.det_pressurevalue_txt);
//        txtVisibility.setText(R.string.det_visibility_txt);
//        txtVisibilityValue.setText(R.string.det_visidistance_txt);
//        txtFeelsValue.setText(R.string.det_feelsvalue_txt);
//        txtFeelsLike.setText(R.string.det_feels_txt);
    }

    public void bindData(WeatherWrapper forecast) {
            this.forecast= forecast;
            if(forecast!=null) {
                setTextValues();

                setCardStyleByWeather(forecast.getCurrent().getFormattedWeatherString());
            }
        }

//        private int getIconPngResource(WeatherWrapper forecast) {
//            Resources res = imgIcon.getContext().getResources();
//            return res.getIdentifier(forecast.getFormattedWeatherString(),"mipmap",imgIcon.getContext().getPackageName());
//        }

        private void initializeTextViews(View view){
            textViews = new ArrayList<>();

             txtNow = (TextView) view.findViewById(R.id.det_now_txt);
             textViews.add(txtNow);
             txtTempValue=(TextView) view.findViewById(R.id.det_temperaturevalue_txt);
            textViews.add(txtTempValue);
             txtWeather=(TextView) view.findViewById(R.id.det_weather_txt);
            textViews.add(txtWeather);
             txtWindSpeed=(TextView) view.findViewById(R.id.det_windspeed_txt);textViews.add(txtWindSpeed);
             txtWindDirection=(TextView) view.findViewById(R.id.det_winddirection_txt);textViews.add(txtWindDirection);
             txtPrecipitationValue=(TextView) view.findViewById(R.id.det_precipchance_txt);textViews.add(txtPrecipitationValue);
             txtPrecipitationAmount=(TextView) view.findViewById(R.id.det_precipamount_txt);textViews.add(txtPrecipitationAmount);
             txtSunset=(TextView) view.findViewById(R.id.det_sunset_txt);textViews.add(txtSunset);
             txtSunsetTime=(TextView) view.findViewById(R.id.det_sunsettime_txt);textViews.add(txtSunsetTime);
             txtMoonPhase=(TextView) view.findViewById(R.id.det_moonphase_txt);textViews.add(txtMoonPhase);
             txtMoonPhase2=(TextView) view.findViewById(R.id.det_moonphase2_txt);textViews.add(txtMoonPhase2);
             txtHumidity=(TextView) view.findViewById(R.id.det_humid_txt);textViews.add(txtHumidity);
             txtHumidityValue=(TextView) view.findViewById(R.id.det_humidvalue_txt);textViews.add(txtHumidityValue);
             txtDew=(TextView) view.findViewById(R.id.det_dew_txt);textViews.add(txtDew);
             txtDewValue=(TextView) view.findViewById(R.id.det_dewvalue_txt);textViews.add(txtDewValue);
             txtPressure=(TextView) view.findViewById(R.id.det_pressure_txt);textViews.add(txtPressure);
             txtPressureValue=(TextView) view.findViewById(R.id.det_pressurevalue_txt);textViews.add(txtPressureValue);
             txtVisibility=(TextView) view.findViewById(R.id.det_visibility_txt);textViews.add(txtVisibility);
             txtVisibilityValue=(TextView) view.findViewById(R.id.det_visidistance_txt);textViews.add(txtVisibilityValue);
             txtFeelsValue=(TextView) view.findViewById(R.id.det_feelsvalue_txt);textViews.add(txtFeelsValue);
             txtFeelsLike=(TextView) view.findViewById(R.id.det_feels_txt);textViews.add(txtFeelsLike);

        }

        private void initializeImageViews(View view){
            imgViews = new ArrayList<>();

             imgWeather=(ImageView) view.findViewById(R.id.det_weather_ico);imgViews.add(imgWeather);
             imgPressure=(ImageView) view.findViewById(R.id.det_pressure_ico);imgViews.add(imgPressure);
             imgDew=(ImageView) view.findViewById(R.id.det_dew_ico);imgViews.add(imgDew);
             imgWind=(ImageView) view.findViewById(R.id.det_wind_ico);imgViews.add(imgWind);
             imgPrecipitation=(ImageView) view.findViewById(R.id.det_precip_ico);imgViews.add(imgPrecipitation);
             imgFeelsLike=(ImageView) view.findViewById(R.id.det_feels_ico);imgViews.add(imgFeelsLike);
             imgHumidity=(ImageView) view.findViewById(R.id.det_humid_ico);imgViews.add(imgHumidity);
             imgMoonPhase=(ImageView) view.findViewById(R.id.det_moonphase_ico);imgViews.add(imgMoonPhase);
             imgSunset=(ImageView) view.findViewById(R.id.det_sunset_ico);imgViews.add(imgSunset);
             imgVisibility=(ImageView) view.findViewById(R.id.det_visibility_ico);imgViews.add(imgVisibility);
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
            for(TextView t : textViews){
                t.setTextColor(Color.WHITE);
            }
            for(ImageView i : imgViews){
                i.setColorFilter(Color.WHITE);
            }
        }

        View.OnClickListener mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };

    }

