package me.grechka.yamblz.yamblzweatherapp;

import android.app.Application;

import com.arellomobile.mvp.MvpFacade;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.di.AppComponent;
import me.grechka.yamblz.yamblzweatherapp.di.ContextModule;
import me.grechka.yamblz.yamblzweatherapp.di.DaggerAppComponent;
import me.grechka.yamblz.yamblzweatherapp.di.DataModule;
import me.grechka.yamblz.yamblzweatherapp.di.JobModule;
import me.grechka.yamblz.yamblzweatherapp.di.ModelModule;
import me.grechka.yamblz.yamblzweatherapp.di.NavigationModule;
import me.grechka.yamblz.yamblzweatherapp.di.NetworkModule;
import me.grechka.yamblz.yamblzweatherapp.repository.PreferencesManager;
import me.grechka.yamblz.yamblzweatherapp.updating.WeatherJobUtils;

/**
 * Created by Grechka on 14.07.2017.
 */

public class WeatherApp extends Application {
    private static AppComponent component;

    @Inject
    PreferencesManager preferencesManager;
    @Inject
    WeatherJobUtils weatherJobUtils;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
        component.inject(this);
        MvpFacade.init();
        setUpdateSchedule();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .jobModule(new JobModule())
                //.modelModule(new ModelModule())
                .navigationModule(new NavigationModule())
                .networkModule(new NetworkModule())
                .dataModule(new DataModule())
                .build();
    }

    void setUpdateSchedule() {
        int minutes = Integer.parseInt(preferencesManager.getUpdateFrequency());
        weatherJobUtils.scheduleWeatherJob(minutes);
    }
}