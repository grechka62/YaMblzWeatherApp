package me.grechka.yamblz.yamblzweatherapp.repository;

import android.support.annotation.NonNull;

import io.reactivex.Single;
import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.repository.models.SuggestionResponseModel;

/**
 * Created by Grechka on 16.07.2017.
 */

public interface Repository {

    interface OnGotResponseListener {
        void onGotResponse();
        void onFailure(String message);
    }

    void registerCallBack(OnGotResponseListener callback);
    void updateCurrentWeather();

    CurrentWeather getCurrentWeather();
    CurrentWeather getSavedCurrentWeather();

    Single<SuggestionResponseModel> obtainSuggestedCities(@NonNull String input);
}
