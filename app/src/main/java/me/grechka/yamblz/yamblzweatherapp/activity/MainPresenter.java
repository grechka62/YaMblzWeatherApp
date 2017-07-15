package me.grechka.yamblz.yamblzweatherapp.activity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * Created by Grechka on 14.07.2017.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    void showWeather() {
        getViewState().showWeather();
    }

    void showSettings() {
        getViewState().showSettings();
    }

    void showAbout() {
        getViewState().showAbout();
    }
}
