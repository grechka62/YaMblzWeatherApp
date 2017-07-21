package me.grechka.yamblz.yamblzweatherapp.schedule;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by Grechka on 14.07.2017.
 */

public class WeatherJobCreator implements JobCreator {

    @Override
    public Job create(String tag) {
        switch (tag) {
            case CurrentWeatherUpdateJob.TAG:
                return new CurrentWeatherUpdateJob();
            default:
                return null;
        }
    }
}
