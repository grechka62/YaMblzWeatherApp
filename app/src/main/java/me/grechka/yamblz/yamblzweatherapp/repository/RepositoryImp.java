package me.grechka.yamblz.yamblzweatherapp.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.interactor.Interactor;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.model.response.CurrentWeatherResponse;
import me.grechka.yamblz.yamblzweatherapp.updating.WeatherApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Grechka on 16.07.2017.
 */

public class RepositoryImp implements Repository {
    private final String APIKEY = "847aa9acae58b3e1ccd9da7ef3fc4d01";
    private final String MOSCOW_ID = "524901";

    private CurrentWeather currentWeather;
    private OnGotResponseListener callback;

    @Inject
    WeatherApi weatherApi;

    @Inject
    PreferencesManager preferencesManager;

    @Inject
    Interactor interactor;

    public RepositoryImp() {
        WeatherApp.getComponent().inject(this);
    }

    public void registerCallBack(OnGotResponseListener callback){
        this.callback = callback;
    }

    @Override
    public void updateCurrentWeather() {
        Call call = weatherApi.getCurrentWeather(MOSCOW_ID, "ru", "metric", APIKEY);
        call.enqueue(new Callback<CurrentWeatherResponse>() {

            @Override
            public void onResponse(@NonNull Call<CurrentWeatherResponse> call, @NonNull Response<CurrentWeatherResponse> response) {
                Log.d("retrofit", "Go to Network");
                if (response.body() != null) {
                    Log.d("retrofit", "Got positive answer");
                    currentWeather = interactor.getCurrentWeatherFromResponse(response.body());
                    callback.onGotResponse();
                } else {
                    Log.d("retrofit", "Got negative answer");
                    //TODO распарсить негативный ответ и показать пользователю нужное сообщение
                }
            }
            @Override
            public void onFailure(@NonNull Call<CurrentWeatherResponse> call, @NonNull Throwable t) {
                callback.onFailure();
            }
        });
    }

    @Override
    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    @Override
    public void putCurrentWeather(CurrentWeather currentWeather) {
        preferencesManager.putCurrentWeather(currentWeather);
    }

    @Override
    public CurrentWeather getSavedCurrentWeather() {
        return preferencesManager.getCurrentWeather();
    }
}
