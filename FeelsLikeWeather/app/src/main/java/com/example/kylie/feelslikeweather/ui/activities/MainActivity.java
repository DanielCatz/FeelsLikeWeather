package com.example.kylie.feelslikeweather.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.example.kylie.feelslikeweather.R;
import com.example.kylie.feelslikeweather.models.SharedPreferencesRepository;
import com.example.kylie.feelslikeweather.models.wrappers.LocationService;
import com.example.kylie.feelslikeweather.models.wrappers.WeatherWrapper;
import com.example.kylie.feelslikeweather.models.wrappers.Precipitation;
import com.example.kylie.feelslikeweather.presenters.MainActivityPresenter;
import com.example.kylie.feelslikeweather.rest.WeatherService;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;
import com.example.kylie.feelslikeweather.ui.CurrentWeatherAdapter;
import com.example.kylie.feelslikeweather.utils.Print;

import java.util.ArrayList;

//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class MainActivity extends AppCompatActivity implements CurrentWeatherScreen {
    TextView textblock;
    SwipeRefreshLayout progressBar;
    RecyclerView currentWeatherRecycler;
    CurrentWeatherAdapter currentWeatherAdapter;
    MainActivityPresenter presenter;

    Intent data;
    private boolean rxCallInWorks;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    int refreshCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        refreshCount=0;
        textblock = (TextView) findViewById(R.id.txt_main);
        currentWeatherRecycler = (RecyclerView)findViewById(R.id.recycle_main);
        progressBar = (SwipeRefreshLayout) findViewById(R.id.p_bar_main);
        manageRefresh();
        rxCallInWorks = false;
        WeatherService weatherService= WeatherService.getInstance();
        SharedPreferencesRepository pref = new SharedPreferencesRepository(this);
        LocationService locationService = new LocationService();
        presenter = new MainActivityPresenter(this, weatherService,pref, locationService);
        initializeRecyclerView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLocation();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.clearSettings();
            }
        });


        presenter.refreshCurrentWeatherScreen();
    }
    @Override
    public void loadWeatherLocationsFromSettings(int numberOfSpacesToReserve){
       // enqueueRefresh();
        //get array from settings
        currentWeatherAdapter.prepareSpaceToLoadLocationsAsBatch(numberOfSpacesToReserve);
        rxCallInWorks = true;
      //  dequeueRefresh();
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    @Override
    public void addNewLocationToWeatherList(WeatherWrapper forecast) {
        //hide swirl
        enqueueRefresh();
        currentWeatherAdapter.addWeatherRow(forecast);
        rxCallInWorks= false;
        showMessage("Location Added");
        dequeueRefresh();
    }

    @Override
    public void refreshWeatherListView(WeatherWrapper forecast, int position) {
        enqueueRefresh();
        currentWeatherAdapter.updateWeatherRow(forecast,position);
        showMessage("Location(s) Updated");
        dequeueRefresh();
        rxCallInWorks= false;
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
    public void failedCall() {
        Print.out("MY API ISSUE");
        dequeueRefresh();
        rxCallInWorks= false;
    }

    @Override
    public void openDetailedWeatherActivity(WeatherWrapper forecast) {
        Intent intent = new Intent(this, DetailedWeatherActivity.class);
        Precipitation p = new Precipitation();
        intent.putExtra("Forecast",forecast);
        startActivity(intent);
//        intent.putExtra("Forecast",p);
//        intent.putExtra(DetailActivity.CONTACT_MD5_EXTRA, contactMd5);
//        intent.putExtra(DetailActivity.CONTACT_THUMBNAIL_EXTRA, thumbnail);
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
//                new Pair<View, String>(imageView, activity.getString(R.string.anim_picture)),
//                new Pair<View, String>(nameTextView, activity.getString(R.string.anim_name)));
//        activity.startActivity(intent, options.toBundle());
    }

    public void showMessage(String msg){
    Snackbar.make(findViewById(R.id.toolbar), msg, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
}

    public void selectLocation(){
        presenter.selectLocationRequest();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.data = data;
        presenter.handleOnActivityResult(requestCode,resultCode);

    }
    @Override
    public Intent getIntentData(){
        return data;
    }

    private void enqueueRefresh(){
        refreshCount++;
        manageRefresh();
    }
    private void dequeueRefresh(){
        refreshCount--;
        manageRefresh();
    }

    private void manageRefresh() {
        Print.out(refreshCount);
        if(refreshCount==0){
            progressBar.setRefreshing(false);
        }else{
            progressBar.setRefreshing(true);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        presenter.rxUnSubscribe();

    }

    @Override
    protected void onResume() {//if interrupted
        super.onResume();
        if(rxCallInWorks);
           //presenter.refreshCurrentWeatherScreen();
    }

}