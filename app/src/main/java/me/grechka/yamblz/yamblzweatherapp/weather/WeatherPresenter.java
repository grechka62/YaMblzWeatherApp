package me.grechka.yamblz.yamblzweatherapp.weather;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.Serializable;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.repository.RepositoryImp;

/**
 * Created by Grechka on 15.07.2017.
 */

@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> implements Repository.OnGotResponseListener {

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
        getViewState().showCurrentWeather(currentWeather.temperature, currentWeather.description);
    }

    void showSavedCurrentWeather() {
        CurrentWeather currentWeather = repository.getSavedCurrentWeather();
        showCurrentWeather(currentWeather);
    }

    void closeDrawer() {
        getViewState().closeDrawer();
    }

    @Override
    public void onGotResponse() {
        CurrentWeather currentWeather = repository.getCurrentWeather();
        repository.putCurrentWeather(currentWeather);
        if (getViewState() != null)
            showCurrentWeather(currentWeather);
    }

    @Override
    public void onFailure() {
        getViewState().showMessage();
    }
}
