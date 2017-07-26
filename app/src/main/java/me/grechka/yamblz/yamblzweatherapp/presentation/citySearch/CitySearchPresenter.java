package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import me.grechka.yamblz.yamblzweatherapp.models.City;
import me.grechka.yamblz.yamblzweatherapp.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.utils.RxSchedulers;

/**
 * Created by alexander on 22/07/2017.
 */

@Singleton
@InjectViewState
public class CitySearchPresenter extends MvpPresenter<CitySearchView> {

    private RxSchedulers schedulers;
    private Repository appRepository;

    @Inject
    public CitySearchPresenter(@NonNull Repository appRepository,
                               @NonNull RxSchedulers schedulers) {
        this.schedulers = schedulers;
        this.appRepository = appRepository;
    }

    @Override
    public void attachView(CitySearchView view) {
        super.attachView(view);
        view.hideLoading();
        view.clearSuggestions();
    }

    public void setObservable(@NonNull Observable<CharSequence> observable) {
        observable.subscribe(this::fetchSuggestions);
    }

    public void fetchSuggestions(@NonNull CharSequence input) {
        getViewState().clearSuggestions();
        getViewState().showLoading();

        this.appRepository.obtainSuggestedCities(input.toString())
                .compose(schedulers.getIoToMainTransformer())
                .subscribe(city -> {
                    getViewState().hideLoading();
                    getViewState().addSuggestion(city);
                });
    }

    public void fetchCity(@NonNull City item) {
        appRepository.obtainCityInfo(item.getPlaceId())
                .compose(schedulers.getComputationToMainTransformerSingle())
                .map(city -> new City.Builder(item)
                                .location(city.getInfo().getGeometry().getLocation())
                                .build())
                .subscribe(city -> {
                    getViewState().closeDialog();
                    appRepository.saveCity(city);
                });
    }
}
