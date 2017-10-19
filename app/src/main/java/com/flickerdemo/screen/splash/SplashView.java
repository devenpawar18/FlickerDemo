package com.flickerdemo.screen.splash;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flickerdemo.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * SplashView - A Fragment which implements the SplashContract.View interface.
 */
public class SplashView extends Fragment implements SplashContract.View {
    @Inject
    protected SplashPresenter mPresenter;


    @Inject
    public SplashView() {
        Timber.d("Reached");
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((SplashActivity) getActivity()).getSplashComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.dropView();
    }

    @Override
    public Context getContext() {
        return this.getView().getContext();
    }

    @Override
    public SplashActivity getSplashActivity() {
        return (SplashActivity) getActivity();
    }
}