package me.grechka.yamblz.yamblzweatherapp.presentation.weather;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.repository.Repository;

/**
 * Created by Grechka on 15.07.2017.
 */

@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> implements Repository.OnGotResponseListener {
    public final String NO_INFORMATION = "-";

    @Inject
    Repository repository;

    public WeatherPresenter() {
        super();
        WeatherApp.getComponent().inject(this);
        repository.registerCallBack(this);
    }

    void updateCurrentWeather() {
        repository.updateCurrentWeather();
    }

    private void showCurrentWeather(CurrentWeather currentWeather) {
        getViewState().showCurrentWeather(currentWeather.temperature,
                currentWeather.description,
                currentWeather.humidity,
                currentWeather.tempMin,
                currentWeather.tempMax,
                currentWeather.wind);
    }

    void showSavedCurrentWeather() {
        CurrentWeather currentWeather = repository.getSavedCurrentWeather();
        if (currentWeather.temperature.compareTo(NO_INFORMATION) == 0)
            repository.updateCurrentWeather();
        else
            showCurrentWeather(currentWeather);
    }

    void closeDrawer() {
        getViewState().closeDrawer();
    }

    @Override
    public void onGotResponse() {
        CurrentWeather currentWeather = repository.getCurrentWeather();
        showCurrentWeather(currentWeather);
    }

    @Override
    public void onFailure(String message) {
        getViewState().showMessage(message);
    }
}
