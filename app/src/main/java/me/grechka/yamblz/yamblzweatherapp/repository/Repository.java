package me.grechka.yamblz.yamblzweatherapp.repository;

import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;

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
