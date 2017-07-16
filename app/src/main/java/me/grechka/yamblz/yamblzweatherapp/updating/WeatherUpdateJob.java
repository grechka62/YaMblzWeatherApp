package me.grechka.yamblz.yamblzweatherapp.updating;

import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.model.response.CurrentWeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static me.grechka.yamblz.yamblzweatherapp.WeatherApp.getWeatherApi;

/**
 * Created by Grechka on 14.07.2017.
 */

public class WeatherUpdateJob extends Job {

    public static final String TAG = "job_weather_tag";

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        Log.d("Job", "onRunJob: Job was runned");
        Call call = getWeatherApi().getCurrentWeather("524901", "ru", "metric", WeatherApp.APIKEY);
        call.enqueue(new Callback<CurrentWeatherResponse>() {

            @Override
            public void onResponse(@NonNull Call<CurrentWeatherResponse> call, @NonNull Response<CurrentWeatherResponse> response) {
                Log.d("retrofit", "onRunJob: Have gone to Network");
                if (response.body() != null) {
                    Log.d("retrofit", "onRunJob: Have got positive answer");
                    CurrentWeatherResponse currentWeatherResponse = response.body();
                    double temperature = currentWeatherResponse.getMain().getTemp();
                    String description = currentWeatherResponse.getWeather().get(0).getDescription();
                    CurrentWeather currentWeather = new CurrentWeather(temperature, description);
                } else {
                    Log.d("retrofit", "onRunJob: Have got negative answer");
                }
            }
            @Override
            public void onFailure(@NonNull Call<CurrentWeatherResponse> call, @NonNull Throwable t) {

            }
        });
        return Result.SUCCESS;
    }
}