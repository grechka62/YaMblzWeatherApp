package me.grechka.yamblz.yamblzweatherapp.settings;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.model.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.model.repository.RepositoryImp;
import me.grechka.yamblz.yamblzweatherapp.updating.WeatherUpdateJob;

/**
 * Created by Grechka on 14.07.2017.
 */

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {

    public void changeUpdateSchedule(int minutes) {
        JobManager jobManager = JobManager.instance();
        jobManager.cancelAllForTag(WeatherUpdateJob.TAG);
        if (minutes > 0)
            jobManager.schedule(new JobRequest.Builder(WeatherUpdateJob.TAG)
                    .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                    .setPeriodic(minutes * 1000 * 60)
                    .build());
    }
}