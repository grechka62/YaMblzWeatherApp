package me.grechka.yamblz.yamblzweatherapp.activity;

import com.arellomobile.mvp.MvpView;

/**
 * Created by Grechka on 15.07.2017.
 */

public interface MainView extends MvpView {

    void showWeather();

    void showSettings();

    void showAbout();

    void navigate(int id);

}
