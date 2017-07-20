package me.grechka.yamblz.yamblzweatherapp.di;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.model.response.Clouds;
import me.grechka.yamblz.yamblzweatherapp.model.response.Coord;

/**
 * Created by Grechka on 19.07.2017.
 */

@Module
public class ModelModule {

    @Provides
    @NonNull
    public CurrentWeather provideCurrentWeather(String temperature, String description) {
        return new CurrentWeather(temperature, description);
    }

}
