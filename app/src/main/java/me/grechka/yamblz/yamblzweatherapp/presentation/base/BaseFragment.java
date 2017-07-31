package me.grechka.yamblz.yamblzweatherapp.presentation.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;

/**
 * Created by alexander on 31/07/2017.
 */

public abstract class BaseFragment extends MvpAppCompatFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(obtainLayoutView(), container, false);
        ButterKnife.bind(this, v);
        onViewsCreated(savedInstanceState);
        return v;
    }

    @LayoutRes
    protected abstract int obtainLayoutView();

    protected void onViewsCreated(@Nullable Bundle savedInstanceState) {
    }
}
