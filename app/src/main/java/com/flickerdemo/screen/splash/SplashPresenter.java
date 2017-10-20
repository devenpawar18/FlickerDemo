package com.flickerdemo.screen.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.flickerdemo.screen.home.HomeActivity;
import com.flickerdemo.util.ViewUtils;

import javax.inject.Inject;

/**
 * Implements the presenter interface in the corresponding SplashContract.
 */
public class SplashPresenter implements SplashContract.Presenter {
    @Nullable
    private SplashContract.View mSplashView;

    @Inject
    public SplashPresenter() {

    }

    @Override
    public void takeView(final SplashContract.View pSplashView) {
        this.mSplashView = ViewUtils.checkNotNull(pSplashView, "MainView can't be null!");
        this.mSplashView = pSplashView;

        this.nextScreen();
    }

    @Override
    public void dropView() {
        this.mSplashView = null;
    }

    @Override
    public void nextScreen() {
        final int SPLASH_DISPLAY_LENGTH = 1000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(mSplashView.getSplashActivity(), HomeActivity.class);
                mSplashView.getSplashActivity().startActivity(mainIntent);
                mSplashView.getSplashActivity().finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}