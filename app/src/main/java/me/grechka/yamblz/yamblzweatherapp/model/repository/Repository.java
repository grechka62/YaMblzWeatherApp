package me.grechka.yamblz.yamblzweatherapp.model.repository;

import android.content.Context;

import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.weather.WeatherPresenter;

/**
 * Created by Grechka on 16.07.2017.
 */

public interface Repository {

    void setPresenter(WeatherPresenter presenter);

    void setContext(Context context);

    void getCurrentWeather();

    void saveCurrentWeather();

    void updateCurrentWeather();
}
