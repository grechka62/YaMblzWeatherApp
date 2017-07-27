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

    private Gson gson = new Gson();
    private Repository repository;

    private String suggestionServerResponse = "{\"predictions\":[{\"description\":\"Earth, TX, United States\",\"id\":\"c57834729dd1424a330efa045f5cd2b3485ecb98\",\"matched_substrings\":[{\"length\":5,\"offset\":0}],\"place_id\":\"ChIJMbOLyNA_AocRYVRX-1HM0fw\",\"reference\":\"CjQwAAAAz-BXq9wNCvJBjZ-XxUXe5Dn7UtEBSAF8jNx2NQNXJfsxZ3GcuR6I9iIXIChc7inpEhAczXi8ZJsTCQF7Kosl-0XuGhSx7oWqj9J4GoWZrT5dv1Zi-B2lRQ\",\"structured_formatting\":{\"main_text\":\"Earth\",\"main_text_matched_substrings\":[{\"length\":5,\"offset\":0}],\"secondary_text\":\"TX, United States\"},\"terms\":[{\"offset\":0,\"value\":\"Earth\"},{\"offset\":7,\"value\":\"TX\"},{\"offset\":11,\"value\":\"United States\"}],\"types\":[\"locality\",\"political\",\"geocode\"]}]}";

    @Before
    @Override
    public void onInit() {
        super.onInit();
        repository = new RepositoryImp(interactor, weatherApi, suggestApi, prefs);
    }

    @Override
    public void onMockInit() {
        when(prefs.getCurrentCity()).thenReturn(null);
        when(suggestApi.obtainSuggestedCities(anyString(), anyString(), anyString()))
                .thenReturn(Single.just(gson.fromJson(suggestionServerResponse, SuggestionResponseModel.class)));
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
