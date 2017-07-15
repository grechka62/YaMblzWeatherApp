package me.grechka.yamblz.yamblzweatherapp.weather;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by Grechka on 15.07.2017.
 */

@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> {

    void onWeatherChanged() {
        getViewState().showCurrentWeather();
    }

}
