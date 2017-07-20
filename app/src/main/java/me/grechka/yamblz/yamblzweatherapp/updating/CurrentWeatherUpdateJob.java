package me.grechka.yamblz.yamblzweatherapp.updating;

import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.repository.RepositoryImp;

/**
 * Created by Grechka on 14.07.2017.
 */

public class CurrentWeatherUpdateJob extends Job {
    static final String TAG = "job_update_current_weather";

    @Inject
    Repository repository;

    CurrentWeatherUpdateJob() {
        WeatherApp.getComponent().inject(this);
    }

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        Log.d("Job", "onRunJob: Job started");
        repository.getCurrentWeather();
        return Result.SUCCESS;
    }
}