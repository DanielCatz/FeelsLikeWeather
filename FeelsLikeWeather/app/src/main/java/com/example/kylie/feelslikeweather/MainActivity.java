package com.example.kylie.feelslikeweather;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kylie.feelslikeweather.presenters.MainActivityPresenter;
import com.example.kylie.feelslikeweather.rest.WeatherService;
import com.example.kylie.feelslikeweather.screens.CurrentWeatherScreen;
import com.example.kylie.feelslikeweather.utitlity.Print;


public class MainActivity extends AppCompatActivity implements CurrentWeatherScreen {
    TextView textblock;
    ProgressBar progressBar;
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
        progressBar = (ProgressBar) findViewById(R.id.pBar_main);
        progressBar.setVisibility(View.GONE);
        WeatherService weatherService= WeatherService.getInstance();
        presenter = new MainActivityPresenter(this, weatherService);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                rxCallInWorks = true;
                presenter.getWeatherForecast();
//                Snackbar.make(view, "Refreshed", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
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
    public void refreshCurrentWeather(String data) {
        //hide swirl
        progressBar.setVisibility(View.GONE);
        textblock.setText(data);
        rxCallInWorks= false;
    }

    @Override
    public void failedCall() {
        Print.out("noooooooo");

        progressBar.setVisibility(View.GONE);

        rxCallInWorks= false;
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
