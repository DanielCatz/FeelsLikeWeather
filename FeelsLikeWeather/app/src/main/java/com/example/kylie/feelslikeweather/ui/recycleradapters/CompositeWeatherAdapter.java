package com.example.kylie.feelslikeweather.ui.recycleradapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kylie.feelslikeweather.R;
import com.example.kylie.feelslikeweather.models.wrappers.DailyWrapper;
import com.example.kylie.feelslikeweather.models.wrappers.Report;
import com.example.kylie.feelslikeweather.models.wrappers.WeatherWrapper;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;
import com.example.kylie.feelslikeweather.screens.WeatherOverviewScreen;
import com.example.kylie.feelslikeweather.ui.viewholders.BannerViewHolder;
import com.example.kylie.feelslikeweather.ui.viewholders.CurrentWeatherViewHolder;
import com.example.kylie.feelslikeweather.ui.viewholders.DailyWeatherViewHolder;
import com.example.kylie.feelslikeweather.ui.viewholders.DetailedWeatherViewHolder;
import com.example.kylie.feelslikeweather.ui.viewholders.ICompositeViewHolder;
import com.example.kylie.feelslikeweather.utils.Print;
import com.example.kylie.feelslikeweather.utils.RecyclerComponentEnum;

import java.util.ArrayList;

import static com.example.kylie.feelslikeweather.utils.RecyclerComponentEnum.DAY;

/**
 * Created by Kylie on 3/16/2017.
 */

public class CompositeWeatherAdapter  extends RecyclerView.Adapter {

    private static final int BANNER = 0;
    private static final int HOURS =2 ;
    private static final int DAY =3 ;
    private static final int DAILY = 4;
    private ArrayList<Object> components;
    private WeatherOverviewScreen screen;


    public CompositeWeatherAdapter(WeatherOverviewScreen screen, ArrayList<Object> componentsToBuild){
        this.screen= screen;
        this.components= componentsToBuild;

    }

    @Override
    public int getItemViewType(int position) {
        if (components.get(position) instanceof WeatherWrapper) {
            return DAY;
        } else if (components.get(position) instanceof String) {
            return BANNER;
        } else if (components.get(position) instanceof Report) {
            return DAILY;
        }
        return BANNER;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case DAY:
                v = inflater.inflate(R.layout.detailed_weather_row_item,parent,false);
                holder = new DetailedWeatherViewHolder(v,screen);
                break;
            case BANNER:
                v = inflater.inflate(R.layout.banner_row_item,parent,false);
                holder = new BannerViewHolder(v,screen);
                break;
            case DAILY:
                v = inflater.inflate(R.layout.daily_row_item,parent,false);
                holder = new DailyWeatherViewHolder(v,screen);
                break;
            default:
                v = inflater.inflate(R.layout.detailed_weather_row_item,parent,false);
                holder = new CurrentWeatherViewHolder(v,screen);
        }
        //https://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //((ICompositeViewHolder) holder).bindData(components.get(position));
        switch (holder.getItemViewType()){
            case DAY:
                DetailedWeatherViewHolder dvh = (DetailedWeatherViewHolder) holder;
                dvh.bindData((WeatherWrapper)components.get(position));
                break;
            case BANNER:
                BannerViewHolder bvh = (BannerViewHolder) holder;
                bvh.bindData((String)components.get(position));
                break;
            case DAILY:
                DailyWeatherViewHolder dlvh = (DailyWeatherViewHolder) holder;
                dlvh.bindData((Report)components.get(position));
                break;
            default:
                Print.out("default");
        }
    }


    @Override
    public int getItemCount() {
        return components.size();
    }

}