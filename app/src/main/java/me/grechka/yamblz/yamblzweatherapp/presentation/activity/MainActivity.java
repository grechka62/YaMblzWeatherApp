package me.grechka.yamblz.yamblzweatherapp.presentation.activity;

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

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import me.grechka.yamblz.yamblzweatherapp.presentation.AboutFragment;
import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;
import me.grechka.yamblz.yamblzweatherapp.presentation.citySearch.CitySearchFragment;
import me.grechka.yamblz.yamblzweatherapp.presentation.settings.SettingsFragment;
import me.grechka.yamblz.yamblzweatherapp.presentation.weather.WeatherFragment;

public class MainActivity extends MvpAppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        MainView {

    @InjectPresenter MainPresenter presenter;

    private DrawerLayout drawerLayout;
    private TextView cityAreaHeaderTextView;
    private TextView cityTitleHeaderTextView;

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return WeatherApp.getComponent().getMainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) presenter.showWeather();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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

        //searchView.setOnClickListener(v -> showCitySearch());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        presenter.navigate(item.getItemId());
        return true;
    }

    @Override
    public void onBackPressed() {
        presenter.goBack();
    }

    @Override
    public void showWeather() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new WeatherFragment())
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if ((drawer != null) && (drawer.isDrawerOpen(GravityCompat.START)))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        presenter.goBack();
        return true;
    }
}
