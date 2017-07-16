package me.grechka.yamblz.yamblzweatherapp.updating;

import android.support.annotation.NonNull;
import android.util.Log;

import com.evernote.android.job.Job;

import me.grechka.yamblz.yamblzweatherapp.model.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.model.repository.RepositoryImp;

/**
 * Created by Grechka on 14.07.2017.
 */

public class WeatherUpdateJob extends Job {

    public static final String TAG = "job_weather_tag";
    private Repository repository = new RepositoryImp();
    {repository.setContext(getContext());}

    @Override
    @NonNull
    protected Result onRunJob(Params params) {
        Log.d("Job", "onRunJob: Job started");
        repository.getCurrentWeather();
        return Result.SUCCESS;
    }
}