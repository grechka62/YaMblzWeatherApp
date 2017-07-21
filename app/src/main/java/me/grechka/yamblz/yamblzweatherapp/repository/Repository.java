package me.grechka.yamblz.yamblzweatherapp.repository;

import android.content.Context;

import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.weather.WeatherPresenter;

/**
 * Created by Grechka on 16.07.2017.
 */

public interface Repository {

    interface OnGotResponseListener {
        void onGotResponse();
        void onFailure(String message);
    }

    void registerCallBack(OnGotResponseListener callback);

    void updateCurrentWeather();

    CurrentWeather getCurrentWeather();

    //void putCurrentWeather();

    CurrentWeather getSavedCurrentWeather();

}
