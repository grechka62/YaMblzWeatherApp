package me.grechka.yamblz.yamblzweatherapp.weather;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Grechka on 14.07.2017.
 */

public interface WeatherView extends MvpView {

    @StateStrategyType(value = SingleStateStrategy.class)
    void showCurrentWeather(String temperature, String description);

}
