package me.grechka.yamblz.yamblzweatherapp.presentation.weather;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.models.City;
import me.grechka.yamblz.yamblzweatherapp.models.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.utils.RxSchedulers;

/**
 * Created by Grechka on 15.07.2017.
 */

@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {
    public final String NO_INFORMATION = "-";

    RxSchedulers scheduler;
    Repository repository;

    @Inject
    public WeatherPresenter(@NonNull RxSchedulers scheduler,
                            @NonNull Repository repository) {
        this.scheduler = scheduler;
        this.repository = repository;
    }

    @Override
    public void attachView(WeatherView view) {
        super.attachView(view);
        getViewState().showCityTitle(repository.getCity().getTitle());
    }

    void updateCurrentWeather() {
        repository
                .updateCurrentWeather()
                .compose(scheduler.getIoToMainTransformerSingle())
                .subscribe(this::showCurrentWeather);
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
        repository.getSavedCurrentWeather()
                .subscribe(weather -> {
                    if (weather.temperature.compareTo(NO_INFORMATION) == 0)
                        repository.updateCurrentWeather();
                    else showCurrentWeather(weather);
                });
    }

    void closeDrawer() {
        getViewState().closeDrawer();
    }
}