package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import me.grechka.yamblz.yamblzweatherapp.models.City;
import me.grechka.yamblz.yamblzweatherapp.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.utils.RxSchedulers;

/**
 * Created by alexander on 22/07/2017.
 */

@Singleton
@InjectViewState
public class CitySearchPresenter extends MvpPresenter<CitySearchView> {

    private static final String TAG = CitySearchPresenter.class.getCanonicalName();

    private RxSchedulers schedulers;
    private Repository appRepository;

    @Inject
    public CitySearchPresenter(@NonNull Repository appRepository,
                               @NonNull RxSchedulers schedulers) {
        this.schedulers = schedulers;
        this.appRepository = appRepository;
    }

    public void loadSuggestions(@NonNull String input) {
        this.appRepository.obtainSuggestedCities(input)
                .compose(schedulers.getIoToMainTransformer())
                .subscribe(getViewState()::addSuggestion);
    }

    public void loadCity(@NonNull City item) {
        appRepository.obtainCityInfo(item.getPlaceId())
                .compose(schedulers.getComputationToMainTransformerSingle())
                .map(city -> new City.Builder(item)
                                .location(city.getInfo().getGeometry().getLocation())
                                .build())
                .subscribe(city -> {
                    getViewState().closeSelf();
                    appRepository.saveCity(city);
                });
    }

    @Override
    public void attachView(CitySearchView view) {
        super.attachView(view);
        getViewState().clearSuggestions();
    }
}
