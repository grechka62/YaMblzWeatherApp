package me.grechka.yamblz.yamblzweatherapp.repository.net;

import me.grechka.yamblz.yamblzweatherapp.model.response.CurrentWeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Grechka on 14.07.2017.
 */

public interface WeatherApi {
    String API_KEY = "847aa9acae58b3e1ccd9da7ef3fc4d01";

    @GET("data/2.5/weather")
    Call<CurrentWeatherResponse> getCurrentWeather(@Query("id") String city,
                                           @Query("lang") String lang,
                                           @Query("units") String units,
                                           @Query("APPID") String key);
}
