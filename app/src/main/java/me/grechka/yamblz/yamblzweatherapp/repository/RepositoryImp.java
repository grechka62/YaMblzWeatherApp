package me.grechka.yamblz.yamblzweatherapp.repository;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import me.grechka.yamblz.yamblzweatherapp.interactor.Interactor;
import me.grechka.yamblz.yamblzweatherapp.models.City;
import me.grechka.yamblz.yamblzweatherapp.models.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.models.response.CityLocation;
import me.grechka.yamblz.yamblzweatherapp.models.response.CurrentWeatherResponse;
import me.grechka.yamblz.yamblzweatherapp.models.response.CityResponseModel;
import me.grechka.yamblz.yamblzweatherapp.models.response.Place;
import me.grechka.yamblz.yamblzweatherapp.models.response.SuggestionResponseModel;
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

    private City city;
    private CurrentWeather currentWeather;

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
        this.city = getCity();
    }

    @Override
    public Single<CurrentWeather> updateCurrentWeather() {
        CityLocation location = city.getLocation();

        return weatherApi
                .getWeatherByLocation(location.getLatitude(), location.getLongitude(), API_KEY)
                .map(weather -> {
                    currentWeather = interactor.getCurrentWeatherFromResponse(weather);
                    preferencesManager.putCurrentWeather(currentWeather);
                    return currentWeather;
                });
    }

    @Override
    public void saveCity(@NonNull City city) {
        this.city = city;
        preferencesManager.saveCity(city);
    }

    @Override
    public City getCity() {
        if (city != null) return city;
        return preferencesManager.getCurrentCity();
    }

    @Override
    public Single<CurrentWeather> getCurrentWeather() {
        return Single.just(currentWeather);
    }

    @Override
    public Single<CurrentWeather> getSavedCurrentWeather() {
        return Single.just(preferencesManager.getCurrentWeather());
    }

    @Override
    public Single<CurrentWeatherResponse> getWeatherByLocation(double latitude, double longitude) {
        return weatherApi.getWeatherByLocation(latitude, longitude, API_KEY);
    }

    @Override
    public Single<CityResponseModel> obtainCityInfo(@NonNull String cityId) {
        return suggestApi.obtainCity(cityId, SuggestApi.API_KEY);
    }

    @Override
    public Observable<City> obtainSuggestedCities(@NonNull String input) {
        return suggestApi
                .obtainSuggestedCities(input, SuggestApi.API_TYPES, SuggestApi.API_KEY)
                .map(SuggestionResponseModel::getPredictions)
                .flatMapObservable(Observable::fromIterable)
                .map(place -> new City.Builder()
                        .placeId(place.getPlaceId())
                        .title(place.getPlaceInfo().getMainText())
                        .extendedTitle(place.getDescription())
                        .build());
    }
}
