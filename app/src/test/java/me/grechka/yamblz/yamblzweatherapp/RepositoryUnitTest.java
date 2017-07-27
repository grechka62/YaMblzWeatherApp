package me.grechka.yamblz.yamblzweatherapp;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Single;
import me.grechka.yamblz.yamblzweatherapp.base.BaseUnitTest;
import me.grechka.yamblz.yamblzweatherapp.interactor.Interactor;
import me.grechka.yamblz.yamblzweatherapp.models.response.SuggestionResponseModel;
import me.grechka.yamblz.yamblzweatherapp.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.repository.RepositoryImp;
import me.grechka.yamblz.yamblzweatherapp.repository.net.SuggestApi;
import me.grechka.yamblz.yamblzweatherapp.repository.net.WeatherApi;
import me.grechka.yamblz.yamblzweatherapp.repository.prefs.PreferencesManager;
import me.grechka.yamblz.yamblzweatherapp.utils.JsonProvider;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by alexander on 27/07/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class RepositoryUnitTest extends BaseUnitTest {

    @Mock SuggestApi suggestApi;
    @Mock WeatherApi weatherApi;
    @Mock Interactor interactor;
    @Mock PreferencesManager prefs;

    private Repository repository;
    private SuggestionResponseModel suggestions;


    @Before
    @Override
    public void onInit() {
        super.onInit();
        repository = new RepositoryImp(interactor, weatherApi, suggestApi, prefs);
    }

    @Override
    public void onMockInit() {
        suggestions = JsonProvider.openFile(SuggestionResponseModel.class, "places-suggestion-single.json");

        when(prefs.getCurrentCity()).thenReturn(null);
        when(suggestApi.obtainSuggestedCities(anyString(), anyString(), anyString()))
                .thenReturn(Single.just(suggestions));
    }

    @Test
    public void Repository_obtainSuggestionList_expectedPlaceModel() {
        repository.obtainSuggestedCities("earth")
                .subscribe(place -> {
                    System.out.println(place.toString());

                    assertEquals(place.getPlaceId(), "ChIJMbOLyNA_AocRYVRX-1HM0fw");
                    assertEquals(place.getTitle(), "HHH");
                    assertEquals(place.getExtendedTitle(), "Earth, TX, United States");
                });
    }
}
