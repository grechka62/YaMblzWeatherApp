package me.grechka.yamblz.yamblzweatherapp.settings;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.WeatherApp;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends MvpAppCompatFragment implements SettingsView,
        RadioGroup.OnCheckedChangeListener {

    private View view;
    private RadioGroup radioGroup;

    @InjectPresenter
    SettingsPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);

        WeatherApp.getComponent().inject(this);

        setToolbar();

        radioGroup = (RadioGroup) view.findViewById(R.id.update_frequency_group);
        radioGroup.setOnCheckedChangeListener(this);
        setCheckedFrequency();

        return view;
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.action_settings);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setCheckedFrequency() {
        String tag = presenter.getUpdateFrequency();
        RadioButton button = (RadioButton) radioGroup.findViewWithTag(tag);
        button.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int frequency = getFrequencyFromTag(checkedId);
        presenter.changeUpdateSchedule(frequency);
    }

    private int getFrequencyFromTag(@IdRes int checkedId) {
        return Integer.parseInt((String) view.findViewById(checkedId).getTag());
    }
}
