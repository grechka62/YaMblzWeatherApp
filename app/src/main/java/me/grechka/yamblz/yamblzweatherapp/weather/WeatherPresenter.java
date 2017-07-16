package me.grechka.yamblz.yamblzweatherapp.weather;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.Serializable;

import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.model.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.model.repository.RepositoryImp;

/**
 * Created by Grechka on 15.07.2017.
 */

@InjectViewState
public class WeatherPresenter extends MvpPresenter<WeatherView> implements Serializable {
    private Repository repository = new RepositoryImp();

    public void updateCurrentWeather() {
        repository.setPresenter(this);
        repository.getCurrentWeather();
    }

    public void showCurrentWeather(CurrentWeather currentWeather) {
        String temperature = Double.toString(currentWeather.temperature);
        getViewState().showCurrentWeather(temperature, currentWeather.description);
    }
}
