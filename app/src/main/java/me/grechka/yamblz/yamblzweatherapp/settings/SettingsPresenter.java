package me.grechka.yamblz.yamblzweatherapp.settings;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.repository.PreferencesManager;
import me.grechka.yamblz.yamblzweatherapp.updating.CurrentWeatherUpdateJob;
import me.grechka.yamblz.yamblzweatherapp.updating.WeatherJobUtils;

/**
 * Created by Grechka on 14.07.2017.
 */

@InjectViewState
public class SettingsPresenter extends MvpPresenter<SettingsView> {

    @Inject
    WeatherJobUtils weatherJobUtils;

    @Inject
    PreferencesManager preferencesManager;

    public SettingsPresenter() {
        super();
        WeatherApp.getComponent().inject(this);
    }

    void changeUpdateSchedule(int minutes) {
        preferencesManager.putUpdateFrequency(Integer.toString(minutes));
        weatherJobUtils.rescheduleWeatherJob(minutes);
    }

    String getUpdateFrequency() {
        return preferencesManager.getUpdateFrequency();
    }

}