package me.grechka.yamblz.yamblzweatherapp.settings;


import android.content.Context;
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

import me.grechka.yamblz.yamblzweatherapp.R;
import me.grechka.yamblz.yamblzweatherapp.model.repository.Repository;
import me.grechka.yamblz.yamblzweatherapp.model.repository.RepositoryImp;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends MvpAppCompatFragment implements SettingsView {

    private View view;
    @InjectPresenter
    SettingsPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.action_settings);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.update_frequency_group);
        String tag = getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE).getString("freq", "60");
        RadioButton button = (RadioButton) radioGroup.findViewWithTag(tag);
        button.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                presenter.changeUpdateSchedule(Integer.parseInt((String) view.findViewById(checkedId).getTag()));
                getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE).edit().
                        putString("freq", (String) view.findViewById(checkedId).getTag()).apply();
            }
        });
        return view;
    }

}
