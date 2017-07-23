package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

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

    @Override
    public void attachView(CitySearchView view) {
        super.attachView(view);

        Log.d(TAG, "Here");

        this.appRepository.obtainSuggestedCities("Mos")
                .flatMap(w -> this.appRepository.obtainCityInfo(w.getPredictions().get(0).getPlaceId()))
                .flatMap(c -> this.appRepository.getWeatherByLocation(c.getInfo().getGeometry().getLocation().getLatitude(),
                        c.getInfo().getGeometry().getLocation().getLatitude()))
                .compose(schedulers.getIoToMainTransformerSingle())
                .subscribe(c -> {
                    Log.d(TAG, c.toString());
                });
    }
}
