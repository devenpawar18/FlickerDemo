package com.flickerdemo.screen.splash;

import android.content.Context;

import com.flickerdemo.screen.BasePresenter;
import com.flickerdemo.screen.BaseView;

/**
 * Defines connection between the SplashView and the SplashPresenter.
 */
public interface SplashContract {

    interface View extends BaseView<Presenter> {
        Context getContext();

        SplashActivity getSplashActivity();
    }

    interface Presenter extends BasePresenter<View> {
        void takeView(SplashContract.View pSplashView);

        void dropView();
    }
}