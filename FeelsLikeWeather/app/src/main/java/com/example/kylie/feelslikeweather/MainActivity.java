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

import com.example.kylie.feelslikeweather.models.SharedPreferencesRepository;
import com.example.kylie.feelslikeweather.models.wrappers.DarkSkyPOJOWrapper;
import com.example.kylie.feelslikeweather.models.wrappers.Precipitation;
import com.example.kylie.feelslikeweather.presenters.MainActivityPresenter;
import com.example.kylie.feelslikeweather.rest.WeatherService;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;
import com.example.kylie.feelslikeweather.ui.CurrentWeatherAdapter;
import com.example.kylie.feelslikeweather.utitlity.Print;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;

//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class MainActivity extends AppCompatActivity implements CurrentWeatherScreen {
    TextView textblock;
    ProgressBar progressBar;
    RecyclerView currentWeatherRecycler;
    CurrentWeatherAdapter currentWeatherAdapter;
    MainActivityPresenter presenter;
    private boolean rxCallInWorks;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);

        textblock = (TextView) findViewById(R.id.txt_main);
        currentWeatherRecycler = (RecyclerView)findViewById(R.id.recycle_main);
        progressBar = (ProgressBar) findViewById(R.id.pBar_main);
        progressBar.setVisibility(View.GONE);
        rxCallInWorks = false;
        WeatherService weatherService= WeatherService.getInstance();
        SharedPreferencesRepository pref = new SharedPreferencesRepository(this);
        presenter = new MainActivityPresenter(this, weatherService,pref);
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
        progressBar.setVisibility(View.GONE);
    }
    @Override
    public void loadWeatherLocationsFromSettings(ArrayList<String> settingsLocations){
        progressBar.setVisibility(View.VISIBLE);
        rxCallInWorks = true;
        //get array from settings
        currentWeatherAdapter.setInitialListSize(settingsLocations.size());
        int i =0;
        for(String location : settingsLocations){
            presenter.refreshWeatherForecast(location,i);
            i++;
        }
    }

    @Override
    public void addNewLocationToWeatherList(DarkSkyPOJOWrapper forecast, int position) {
        //hide swirl

        progressBar.setVisibility(View.GONE);
        currentWeatherAdapter.addWeatherRow(forecast,position);
        showMessage("Location Added");
        rxCallInWorks= false;
    }

    @Override
    public void refreshWeatherList(DarkSkyPOJOWrapper forecast, int position) {
        progressBar.setVisibility(View.GONE);
        currentWeatherAdapter.updateWeatherRow(forecast,position);
        showMessage("Location(s) Updated");
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
        progressBar.setVisibility(View.GONE);
        rxCallInWorks= false;
    }

    @Override
    public void openDetailedWeatherActivity(DarkSkyPOJOWrapper forecast) {
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
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            showMessage("Repairable?");
        } catch (GooglePlayServicesNotAvailableException e) {
            showMessage("Service Not Available");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                String latLong = place.getLatLng().latitude+","+place.getLatLng().longitude;
                showMessage(latLong);
                presenter.appendWeatherForecast(latLong);
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Print.out(status.getStatusMessage());
                showMessage(status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
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
//        if(rxCallInWorks)
//            presenter.getWeatherForecast(locations.get(0),false,0);
    }

}
