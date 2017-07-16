package me.grechka.yamblz.yamblzweatherapp;

import android.app.Application;

import com.arellomobile.mvp.MvpFacade;
import com.evernote.android.job.JobManager;

import me.grechka.yamblz.yamblzweatherapp.model.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.model.repository.RepositoryImp;
import me.grechka.yamblz.yamblzweatherapp.updating.OpenWeatherApi;
import me.grechka.yamblz.yamblzweatherapp.updating.WeatherJobCreator;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Grechka on 14.07.2017.
 */

public class WeatherApp extends Application {
    private static OpenWeatherApi openWeatherApi;
    public static final String APIKEY = "847aa9acae58b3e1ccd9da7ef3fc4d01";

    @Override
    public void onCreate() {
        super.onCreate();
        MvpFacade.init();
        initRetrofit();
        JobManager.create(this).addJobCreator(new WeatherJobCreator());
    }

    private void initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        openWeatherApi = retrofit.create(OpenWeatherApi.class);
    }

    public static OpenWeatherApi getWeatherApi() {
        return openWeatherApi;
    }
}