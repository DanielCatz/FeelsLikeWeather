package com.example.kylie.feelslikeweather.rest;

import com.example.kylie.feelslikeweather.utitlity.Print;
import com.example.kylie.feelslikeweather.model.Weather;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kylie on 12/2/2016.
 */

public class ApiClient {
    private static WeatherService REST_CLIENT;
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/"; //Change according to your API path.

    private static final String KEY = "3f8c3c96024e46f39fb2f5fa4fdbbd12";

    static {
        setupRestClient();
    }

    private ApiClient() {}

    public static WeatherService get() {
        return REST_CLIENT;
    }

    private static void setupRestClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        //Uncomment these lines below to start logging each request.

        /*
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


        REST_CLIENT = retrofit.create(WeatherService.class);
    }

    public static void getCurrentWeather(String zip,String countryCode){
zip = zip+","+countryCode;
        REST_CLIENT.getCurrentWeather(zip,KEY).enqueue(new Callback<Weather>() {
                        @Override
                       public void onResponse(Call<Weather> call, Response<Weather> response) {
                            Print.out(response.code() + ":" + response.message() + " Location : ");
                            Print.out(response.code() + ":" + response.message());
                            Print.out(response.body().getName());
                       }

                       @Override
                       public void onFailure(Call<Weather> call, Throwable t) {
                           Print.out("failed");
                       }
                   }
        );

    }

}
