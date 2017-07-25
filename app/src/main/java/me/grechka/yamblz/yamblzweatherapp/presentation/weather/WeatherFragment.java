package me.grechka.yamblz.yamblzweatherapp.presentation.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.models.City;
import me.grechka.yamblz.yamblzweatherapp.presentation.activity.MainPresenter;
import me.grechka.yamblz.yamblzweatherapp.presentation.citySearch.CitySearchFragment;

public class WeatherFragment extends MvpAppCompatFragment implements WeatherView,
        NavigationView.OnNavigationItemSelectedListener,
        CitySearchFragment.OnDismissListener,
        SwipeRefreshLayout.OnRefreshListener{

    private View view;
    private TextView cityTitleTextView;
    private TextView tempView;
    private TextView maxTempView;
    private TextView minTempView;
    private TextView descView;
    private TextView windView;
    private TextView humidityView;

    private TextView cityAreaHeaderTextView;
    private TextView cityTitleHeaderTextView;


    SwipeRefreshLayout swipeRefreshLayout;

    @Inject Context context;
    @Inject MainPresenter mainPresenter;

    @InjectPresenter WeatherPresenter presenter;

    @ProvidePresenter
    public WeatherPresenter providePresenter() {
        return WeatherApp
                .getComponent()
                .getWeatherPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather, container, false);
        WeatherApp.getComponent().inject(this);

        cityTitleTextView = (TextView) view.findViewById(R.id.city);
        tempView = (TextView) view.findViewById(R.id.cur_temp);
        maxTempView = (TextView) view.findViewById(R.id.temp_max);
        minTempView = (TextView) view.findViewById(R.id.temp_min);
        descView = (TextView) view.findViewById(R.id.description);
        windView = (TextView) view.findViewById(R.id.wind_value);
        humidityView = (TextView) view.findViewById(R.id.humidity_value);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        View navigationHeaderView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        onHeaderInit(navigationHeaderView);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        presenter.showSavedCurrentWeather();

        return view;
    }

    private void onHeaderInit(@NonNull View headerView) {
        View searchView = headerView.findViewById(R.id.fragment_weather_header_cities_search);
        cityTitleHeaderTextView = (TextView) headerView.findViewById(R.id.fragment_weather_header_city_title);
        cityAreaHeaderTextView = (TextView) headerView.findViewById(R.id.fragment_weather_header_city_area);

        searchView.setOnClickListener(v -> showCitySearch());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mainPresenter.navigate(item.getItemId());
        presenter.closeDrawer();
        return true;
    }

    @Override
    public void showCurrentWeather(String temperature,
                                   String description,
                                   String humidity,
                                   String tempMin,
                                   String tempMax,
                                   String wind) {
        tempView.setText(temperature);
        maxTempView.setText(tempMax);
        minTempView.setText(tempMin);
        descView.setText(description);
        windView.setText(wind);
        humidityView.setText(humidity);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showCity(@NonNull City city) {
        cityTitleTextView.setText(city.getTitle());
        cityTitleHeaderTextView.setText(city.getTitle());
        cityAreaHeaderTextView.setText(city.getExtendedTitle());
    }

    @Override
    public void showCitySearch() {
        CitySearchFragment.newInstance().show(getChildFragmentManager(), null);
    }

    @Override
    public void onDismissDialog() {
        presenter.updateCity();
        presenter.updateCurrentWeather();
    }

    @Override
    public void showMessage(String message) {
        if (message.equals("Error")) message = getResources().getString(R.string.error);
        else if (message.equals("No network")) message = getResources().getString(R.string.no_network);
        swipeRefreshLayout.setRefreshing(false);
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    @Override
    public void onRefresh() {
        presenter.updateCurrentWeather();
    }

    @Override
    public void closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
