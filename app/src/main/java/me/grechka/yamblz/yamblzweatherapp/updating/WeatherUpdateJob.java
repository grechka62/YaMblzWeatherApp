package me.grechka.yamblz.yamblzweatherapp.updating;

import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.model.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.model.repository.RepositoryImp;
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
    private Repository repository = new RepositoryImp();

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        Log.d("Job", "onRunJob: Job started");
        repository.saveCurrentWeather();
        return Result.SUCCESS;
    }
}