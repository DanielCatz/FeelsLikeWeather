package com.example.kylie.feelslikeweather.ui.viewholders;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kylie.feelslikeweather.R;
import com.example.kylie.feelslikeweather.screens.WeatherOverviewScreen;

/**
 * Created by Kylie on 2017-09-07.
 */

public class BannerViewHolder extends RecyclerView.ViewHolder {

    private final LinearLayout container;
    private TextView banner;

    public BannerViewHolder(View view, WeatherOverviewScreen targetScreen) {
        super(view);
        banner= (TextView) view.findViewById(R.id.banner_txt);
        container = (LinearLayout) view.findViewById(R.id.banner_root);
    }

    public void bindData(String info){

        banner.setText(info);
        container.setBackgroundColor(ContextCompat.getColor(container.getContext(),R.color.colorPrimary));
    }



}
