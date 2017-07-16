package me.grechka.yamblz.yamblzweatherapp.activity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import me.grechka.yamblz.yamblzweatherapp.updating.WeatherUpdateJob;

/**
 * Created by Grechka on 14.07.2017.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    void showWeather() {
        getViewState().showWeather();
    }

    void showSettings() {
        getViewState().showSettings();
    }

    void showAbout() {
        getViewState().showAbout();
    }

    void setUpdateSchedule() {
        JobManager jobManager = JobManager.instance();
        if (jobManager.getAllJobRequestsForTag(WeatherUpdateJob.TAG).isEmpty())
            jobManager.schedule(new JobRequest.Builder(WeatherUpdateJob.TAG)
                    .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                    .setPeriodic(3600000)
                    .build());
    }
}
