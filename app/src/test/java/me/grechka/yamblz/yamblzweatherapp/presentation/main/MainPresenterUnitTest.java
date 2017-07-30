package me.grechka.yamblz.yamblzweatherapp.presentation.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import me.grechka.yamblz.yamblzweatherapp.base.BaseUnitTest;
import me.grechka.yamblz.yamblzweatherapp.presentation.activity.MainPresenter;
import me.grechka.yamblz.yamblzweatherapp.presentation.activity.MainView;

import static org.mockito.Mockito.*;

/**
 * Created by alexander on 30/07/2017.
 */

public class MainPresenterUnitTest extends BaseUnitTest {

    @Mock MainView view;
    private MainPresenter presenter = new MainPresenter();

    @Before
    @Override
    public void onInit() {
        super.onInit();
        presenter.attachView(view);
    }

    @Override
    public void onMockInit() {
    }

    @Test
    public void MainPresenter_navigation_toWeather() {
        presenter.showWeather();
        verify(view, only()).showWeather();
    }

    @Test
    public void MainPresenter_navigation_toSettings() {
        presenter.showSettings();
        verify(view, only()).showSettings();
    }

    @Test
    public void MainPresenter_navigation_toAbout() {
        presenter.showAbout();
        verify(view, only()).showAbout();
    }

    @Test
    public void MainPresenter_navigation_goBack() {
        presenter.goBack();
        verify(view, only()).goBack();
    }

    @Test
    public void MainPresenter_navigation_navigateTo() {
        presenter.navigate(anyInt());
        verify(view, only()).navigate(anyInt());
    }
}
