package me.grechka.yamblz.yamblzweatherapp.presentation.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import me.grechka.yamblz.yamblzweatherapp.events.OnDismissDialogListener;
import me.grechka.yamblz.yamblzweatherapp.models.City;
import me.grechka.yamblz.yamblzweatherapp.presentation.AboutFragment;
import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.presentation.base.BaseActivity;
import me.grechka.yamblz.yamblzweatherapp.presentation.citySearch.CitySearchFragment;
import me.grechka.yamblz.yamblzweatherapp.presentation.settings.SettingsFragment;
import me.grechka.yamblz.yamblzweatherapp.presentation.weather.WeatherFragment;

public class MainActivity extends BaseActivity
        implements MainView,
        OnDismissDialogListener,
        NavigationView.OnNavigationItemSelectedListener{

    private TextView cityAreaHeaderTextView;
    private TextView cityTitleHeaderTextView;

    private WeatherFragment weatherFragment;

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

    @InjectPresenter MainPresenter presenter;

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return WeatherApp
                .getComponent()
                .getMainPresenter();
    }

    @Override
    protected int obtainLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInit() {
        super.onInit();
        presenter.showWeather();
    }

    @Override
    protected void onViewsCreated(Bundle savedInstanceState) {
        super.onViewsCreated(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View navigationHeaderView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        onHeaderInit(navigationHeaderView);
    }

    private void onHeaderInit(@NonNull View headerView) {
        View searchView = headerView.findViewById(R.id.fragment_weather_header_cities_search);
        cityTitleHeaderTextView = (TextView) headerView.findViewById(R.id.fragment_weather_header_city_title);
        cityAreaHeaderTextView = (TextView) headerView.findViewById(R.id.fragment_weather_header_city_area);

        searchView.setOnClickListener(v -> showCitySearch());
    }

    public void showCitySearch() {
        CitySearchFragment.newInstance().show(getSupportFragmentManager(), null);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        presenter.navigate(item.getItemId());
        closeDrawer();
        return true;
    }

    @Override
    public void onBackPressed() {
        presenter.goBack();
    }

    @Override
    public void showWeather() {
        weatherFragment = new WeatherFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, weatherFragment)
                .commit();
    }

    @Override
    public void showSettings() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SettingsFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showAbout() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new AboutFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigate(int screenId) {
        switch (screenId) {
            case R.id.nav_settings:
                presenter.showSettings();
                break;
            case R.id.nav_about:
                presenter.showAbout();
                break;
        }
    }

    @Override
    public void goBack() {
        if (!closeDrawer()) super.onBackPressed();
    }

    @Override
    public void onDialogDismissed() {
        presenter.updateCity();
        weatherFragment.onDialogDismissed();
    }

    @Override
    public void setCityToHeader(@NonNull City city) {
        cityTitleHeaderTextView.setText(city.getTitle());
        cityAreaHeaderTextView.setText(city.getExtendedTitle());
    }

    @Override
    public boolean onSupportNavigateUp() {
        goBack();
        return true;
    }

    private boolean closeDrawer() {
        if (!drawerLayout.isDrawerOpen(GravityCompat.START)) return false;
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
