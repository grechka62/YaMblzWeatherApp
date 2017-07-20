package me.grechka.yamblz.yamblzweatherapp.di;

import javax.inject.Singleton;

import dagger.Component;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.activity.MainActivity;
import me.grechka.yamblz.yamblzweatherapp.activity.MainPresenter;
import me.grechka.yamblz.yamblzweatherapp.repository.RepositoryImp;
import me.grechka.yamblz.yamblzweatherapp.settings.SettingsFragment;
import me.grechka.yamblz.yamblzweatherapp.settings.SettingsPresenter;
import me.grechka.yamblz.yamblzweatherapp.updating.CurrentWeatherUpdateJob;
import me.grechka.yamblz.yamblzweatherapp.updating.WeatherJobUtils;
import me.grechka.yamblz.yamblzweatherapp.weather.WeatherFragment;
import me.grechka.yamblz.yamblzweatherapp.weather.WeatherPresenter;

/**
 * Created by Grechka on 19.07.2017.
 */

@Component(modules = {ContextModule.class, JobModule.class, //ModelModule.class,
        NavigationModule.class, NetworkModule.class, DataModule.class })
@Singleton
public interface AppComponent {

    void inject(WeatherApp weatherApp);
    void inject(WeatherFragment weatherFragment);
    void inject(WeatherPresenter weatherPresenter);
    void inject(SettingsFragment settingsFragment);
    void inject(SettingsPresenter settingsPresenter);
    void inject(RepositoryImp repository);
    void inject(CurrentWeatherUpdateJob currentWeatherUpdateJob);
    void inject(WeatherJobUtils weatherJobUtils);

    MainPresenter getMainPresenter();

}
