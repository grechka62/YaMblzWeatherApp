package me.grechka.yamblz.yamblzweatherapp.interactor;

import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.model.response.CurrentWeatherResponse;

/**
 * Created by Grechka on 19.07.2017.
 */

public interface Interactor {

    CurrentWeather getCurrentWeatherFromResponse(CurrentWeatherResponse response);
}
