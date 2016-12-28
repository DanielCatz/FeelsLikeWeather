package com.example.kylie.feelslikeweather.rest;

import android.support.v4.util.LruCache;


import okhttp3.OkHttpClient;


import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Kylie on 12/2/2016.
 */

public class WeatherService {
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/"; //Change according to your API path.
    private static final String DARK_SKY = "https://api.darksky.net/";
    private static final String LATLONG = "47.646187,-122.141241";
    private static final String KEY = "3f8c3c96024e46f39fb2f5fa4fdbbd12";
    private static final String DARK_KEY = "188b80440d0420d6323cdfdbb431e987";

    private static WeatherService _instance;
    private WeatherApi REST_CLIENT;
    private LruCache<Class<?>, Observable<?>> apiObservables;


    public static WeatherService getInstance() {
        if (_instance == null){
            synchronized (WeatherService.class){
                if(_instance==null) {
                    _instance = new WeatherService();
                }
            }
        }
        return _instance;
    }

    private WeatherService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        //Uncomment these lines below to start logging each request.


//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        httpClient.addInterceptor(logging);

        apiObservables = new LruCache<>(10);
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(DARK_SKY)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


        REST_CLIENT = retrofit.create(WeatherApi.class);
    }

    public WeatherApi getAPI() {
        return REST_CLIENT;
    }
    public String getLatLong(){
        return LATLONG;
    }
    public String getKey(){
        return DARK_KEY;
    }

    /**
     * Method to clear the entire cache of observables
     */
    public void clearCache(){
        apiObservables.evictAll();
    }

    public Observable<?> getPreparedObservable(Observable<?> rawObservable, Class<?> theClass, boolean cacheObservable,boolean useCache){
        Observable<?> preparedObservable = null;

        if(useCache)//this way we don't reset anything in the cache if this is the only instance of us not wanting to use it.
            preparedObservable = apiObservables.get(theClass);

        if(preparedObservable!=null)
            return preparedObservable;



        //we are here because we have never created this observable before or we didn't want to use the cache...

        preparedObservable = rawObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        if(cacheObservable){
            preparedObservable = preparedObservable.cache();
            apiObservables.put(theClass, preparedObservable);
        }


        return preparedObservable;


    }

}
