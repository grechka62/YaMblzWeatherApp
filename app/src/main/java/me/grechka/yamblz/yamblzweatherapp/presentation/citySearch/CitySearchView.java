package me.grechka.yamblz.yamblzweatherapp.presentation.citySearch;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.Collection;

import me.grechka.yamblz.yamblzweatherapp.models.City;

/**
 * Created by alexander on 22/07/2017.
 */

public interface CitySearchView extends MvpView {
    void addSuggestion(City suggestion);
    void clearSuggestions();

    @StateStrategyType(SkipStrategy.class)
    void closeSelf();
}
