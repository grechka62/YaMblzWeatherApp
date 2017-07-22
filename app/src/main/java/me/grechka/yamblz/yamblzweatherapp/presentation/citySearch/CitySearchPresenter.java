package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import me.grechka.yamblz.yamblzweatherapp.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.repository.models.Place;
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
}
