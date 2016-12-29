package com.example.kylie.feelslikeweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kylie.feelslikeweather.models.darkskypojos.DarkSkyForecast;
import com.example.kylie.feelslikeweather.models.wrappers.DarkSkyPOJOWrapper;
import com.example.kylie.feelslikeweather.models.wrappers.Precipitation;
import com.example.kylie.feelslikeweather.presenters.MainActivityPresenter;
import com.example.kylie.feelslikeweather.rest.WeatherService;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;
import com.example.kylie.feelslikeweather.ui.CurrentWeatherAdapter;
import com.example.kylie.feelslikeweather.utitlity.Print;



public class MainActivity extends AppCompatActivity implements CurrentWeatherScreen {
    TextView textblock;
    ProgressBar progressBar;
    RecyclerView currentWeatherRecycler;
    CurrentWeatherAdapter currentWeatherAdapter;
    MainActivityPresenter presenter;
    private boolean rxCallInWorks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        textblock = (TextView) findViewById(R.id.txt_main);
        currentWeatherRecycler = (RecyclerView)findViewById(R.id.recycle_main);
        progressBar = (ProgressBar) findViewById(R.id.pBar_main);
        progressBar.setVisibility(View.GONE);
        WeatherService weatherService= WeatherService.getInstance();
        presenter = new MainActivityPresenter(this, weatherService);
        initializeRecyclerView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                rxCallInWorks = true;
                presenter.getWeatherForecast();

            }
        });
    }

    public void initializeRecyclerView(){

        currentWeatherRecycler.setHasFixedSize(true);
        currentWeatherRecycler.setLayoutManager(new LinearLayoutManager(currentWeatherRecycler.getContext()));
        currentWeatherRecycler.setItemAnimator(new DefaultItemAnimator());
        currentWeatherAdapter = new CurrentWeatherAdapter(this);
        currentWeatherRecycler.setAdapter(currentWeatherAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void refreshCurrentWeather(DarkSkyPOJOWrapper forecast) {
        //hide swirl
        progressBar.setVisibility(View.GONE);
        currentWeatherAdapter.addCurrentWeather(forecast);
        showSuccess();
        rxCallInWorks= false;
    }

    @Override
    public void failedCall() {
        Print.out("noooooooo");
        progressBar.setVisibility(View.GONE);
        rxCallInWorks= false;
    }

    @Override
    public void openDetailedWeatherActivity(DarkSkyPOJOWrapper forecast) {
        Intent intent = new Intent(this, DetailedWeatherActivity.class);
        Precipitation p = new Precipitation();
        intent.putExtra("Forecast",forecast);
//        intent.putExtra("Forecast",p);
        startActivity(intent);
//        intent.putExtra(DetailActivity.CONTACT_MD5_EXTRA, contactMd5);
//        intent.putExtra(DetailActivity.CONTACT_THUMBNAIL_EXTRA, thumbnail);
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
//                new Pair<View, String>(imageView, activity.getString(R.string.anim_picture)),
//                new Pair<View, String>(nameTextView, activity.getString(R.string.anim_name)));
//        activity.startActivity(intent, options.toBundle());
    }

    public void showError(){
    Snackbar.make(findViewById(R.id.toolbar), "Issue", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
}

    public void showSuccess(){
        Snackbar.make(findViewById(R.id.toolbar), "Refreshed", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.rxUnSubscribe();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(rxCallInWorks)
            presenter.getWeatherForecast();
    }

}
