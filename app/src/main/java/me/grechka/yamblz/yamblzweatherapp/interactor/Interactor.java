package me.grechka.yamblz.yamblzweatherapp.interactor;

import me.grechka.yamblz.yamblzweatherapp.models.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.models.response.CurrentWeatherResponse;

/**
 * Created by Grechka on 19.07.2017.
 */

public interface Interactor {

    CurrentWeather getCurrentWeatherFromResponse(CurrentWeatherResponse response);
}
