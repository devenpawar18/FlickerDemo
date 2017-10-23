package com.flickerdemo.screen.splash;

import com.flickerdemo.screen.BasePresenter;
import com.flickerdemo.screen.BaseView;

/**
 * Defines connection between the SplashView and the SplashPresenter.
 */
public interface SplashContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}