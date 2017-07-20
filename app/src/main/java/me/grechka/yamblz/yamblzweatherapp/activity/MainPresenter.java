package me.grechka.yamblz.yamblzweatherapp.activity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.repository.PreferencesManager;
import me.grechka.yamblz.yamblzweatherapp.updating.CurrentWeatherUpdateJob;
import me.grechka.yamblz.yamblzweatherapp.updating.WeatherJobUtils;

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

    void goBack() {
        getViewState().goBack();
    }

    public void navigate(int screenId) {
        getViewState().navigate(screenId);
    }
}
