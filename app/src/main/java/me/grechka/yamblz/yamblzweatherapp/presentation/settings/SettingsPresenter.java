package me.grechka.yamblz.yamblzweatherapp.presentation.settings;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.repository.PreferencesManager;
import me.grechka.yamblz.yamblzweatherapp.schedule.WeatherJobUtils;

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