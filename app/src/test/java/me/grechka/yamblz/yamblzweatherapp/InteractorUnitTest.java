package me.grechka.yamblz.yamblzweatherapp;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import me.grechka.yamblz.yamblzweatherapp.interactor.Interactor;
import me.grechka.yamblz.yamblzweatherapp.interactor.InteractorImp;
import me.grechka.yamblz.yamblzweatherapp.models.CurrentWeather;
import me.grechka.yamblz.yamblzweatherapp.models.response.CurrentWeatherResponse;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InteractorUnitTest {

    @Mock Context context;
    @Mock Resources resources;

    private Gson gson;
    private Interactor interactor;
    private String serverResponseJson =
            "{\"coord\":{\"lon\":37.62,\"lat\":55.75},\"sys\":{\"type\":1,\"id\":7325,\"message\":0.0025,\"country\":\"RU\",\"sunrise\":1501118787,\"sunset\":1501177464},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"main\":{\"temp\":27.26,\"pressure\":1007,\"humidity\":57,\"temp_min\":27,\"temp_max\":28},\"visibility\":10000,\"wind\":{\"speed\":6,\"deg\":250},\"clouds\":{\"all\":75},\"dt\":1501152218,\"id\":524901,\"name\":\"Moscow\"}";

    @Before
    public void onInit() {
        MockitoAnnotations.initMocks(this);

        gson = new Gson();
        interactor = new InteractorImp(context);

        when(context.getResources()).thenReturn(resources);
        when(resources.getString(R.string.west_south_west)).thenReturn("SW");
    }

    @Test
    public void Interactor_weatherResponseConverter_expectWeatherModel() {
        CurrentWeatherResponse weatherResponse = gson.fromJson(serverResponseJson, CurrentWeatherResponse.class);
        CurrentWeather weather = interactor.getCurrentWeatherFromResponse(weatherResponse);

        assertEquals(weather.temperature, "27");
        assertEquals(weather.description, "broken clouds");
        assertEquals(weather.humidity, "57");
        assertEquals(weather.tempMax, "28");
        assertEquals(weather.tempMin, "27");
        assertEquals(weather.wind, "6 м/с, SW");
    }
}