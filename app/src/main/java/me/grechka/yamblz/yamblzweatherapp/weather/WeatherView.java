package me.grechka.yamblz.yamblzweatherapp.weather;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.google.common.util.concurrent.ServiceManager;

/**
 * Created by Grechka on 14.07.2017.
 */

public interface WeatherView extends MvpView {

    //@StateStrategyType(AddToEndSingleStrategy.class)
    void showCurrentWeather(String temperature, String description);

}
