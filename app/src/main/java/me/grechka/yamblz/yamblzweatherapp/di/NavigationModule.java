package me.grechka.yamblz.yamblzweatherapp.di;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.grechka.yamblz.yamblzweatherapp.AboutFragment;
import me.grechka.yamblz.yamblzweatherapp.activity.MainPresenter;
import me.grechka.yamblz.yamblzweatherapp.settings.SettingsFragment;
import me.grechka.yamblz.yamblzweatherapp.weather.WeatherFragment;

/**
 * Created by Grechka on 18.07.2017.
 */

@Module
public class NavigationModule {

    @Provides
    @NonNull
    @Singleton
    MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

}
