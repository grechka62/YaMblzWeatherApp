package me.grechka.yamblz.yamblzweatherapp.interactor;

import java.util.Locale;

import me.grechka.yamblz.yamblzweatherapp.interactor.Interactor;
import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.model.response.CurrentWeatherResponse;

/**
 * Created by Grechka on 19.07.2017.
 */

public class InteractorImp implements Interactor {

    @Override
    public CurrentWeather getCurrentWeatherFromResponse(CurrentWeatherResponse response) {
        String temperature = Long.toString(Math.round(response.getMain().getTemp()));
        String description = response.getWeather().get(0).getDescription();
        return new CurrentWeather(temperature, description);
    }
}
