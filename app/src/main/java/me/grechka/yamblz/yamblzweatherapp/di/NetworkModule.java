package me.grechka.yamblz.yamblzweatherapp.di;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.grechka.yamblz.yamblzweatherapp.repository.WeatherApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Grechka on 18.07.2017.
 */

@Module
public class NetworkModule {

    @Provides
    @NonNull
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public WeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }
}
