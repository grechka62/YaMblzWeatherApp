package me.grechka.yamblz.yamblzweatherapp.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.Single;
import me.grechka.yamblz.yamblzweatherapp.interactor.Interactor;
import me.grechka.yamblz.yamblzweatherapp.model.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.model.response.CurrentWeatherResponse;
import me.grechka.yamblz.yamblzweatherapp.repository.models.CityResponseModel;
import me.grechka.yamblz.yamblzweatherapp.repository.models.SuggestionResponseModel;
import me.grechka.yamblz.yamblzweatherapp.repository.net.SuggestApi;
import me.grechka.yamblz.yamblzweatherapp.repository.net.WeatherApi;
import me.grechka.yamblz.yamblzweatherapp.repository.prefs.PreferencesManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static me.grechka.yamblz.yamblzweatherapp.repository.net.WeatherApi.API_KEY;

/**
 * Created by Grechka on 16.07.2017.
 */

public class RepositoryImp implements Repository {
    private final String MOSCOW_ID = "524901";

    private CurrentWeather currentWeather;
    private OnGotResponseListener callback;

    private Interactor interactor;
    private WeatherApi weatherApi;
    private SuggestApi suggestApi;
    private PreferencesManager preferencesManager;


    public RepositoryImp(@NonNull Interactor interactor,
                         @NonNull WeatherApi weatherApi,
                         @NonNull SuggestApi suggestApi,
                         @NonNull PreferencesManager preferencesManager) {
        this.interactor = interactor;
        this.weatherApi = weatherApi;
        this.suggestApi = suggestApi;
        this.preferencesManager = preferencesManager;
    }

    public void registerCallBack(OnGotResponseListener callback){
        this.callback = callback;
    }

    @Override
    public void updateCurrentWeather() {
        Call call = weatherApi.getCurrentWeather(MOSCOW_ID, "ru", "metric", API_KEY);
        call.enqueue(new Callback<CurrentWeatherResponse>() {

            @Override
            public void onResponse(@NonNull Call<CurrentWeatherResponse> call, @NonNull Response<CurrentWeatherResponse> response) {
                Log.d("retrofit", "Go to Network");
                if (response.body() != null) {
                    Log.d("retrofit", "Got positive answer");
                    currentWeather = interactor.getCurrentWeatherFromResponse(response.body());
                    putCurrentWeather();
                    if (callback != null)
                        callback.onGotResponse();
                } else {
                    Log.d("retrofit", "Got negative answer");
                    if (callback != null)
                        callback.onFailure("Error");
                }
            }
            @Override
            public void onFailure(@NonNull Call<CurrentWeatherResponse> call, @NonNull Throwable t) {
                if (callback != null)
                    callback.onFailure("No network");
            }
        });
    }

    @Override
    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    @Override
    public CurrentWeather getSavedCurrentWeather() {
        return preferencesManager.getCurrentWeather();
    }

    @Override
    public Single<CityResponseModel> obtainCityInfo(@NonNull String cityId) {
        return suggestApi.obtainCity(cityId, SuggestApi.API_KEY);
    }

    @Override
    public Single<SuggestionResponseModel> obtainSuggestedCities(@NonNull String input) {
        return suggestApi.obtainSuggestedCities(input, SuggestApi.API_TYPES, SuggestApi.API_KEY);
    }

    private void putCurrentWeather() {
        preferencesManager.putCurrentWeather(currentWeather);
    }
}
