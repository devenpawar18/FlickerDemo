package com.flickerdemo.screen.home;

import android.content.Context;

import com.flickerdemo.api.model.PhotoInfo;
import com.flickerdemo.screen.BasePresenter;
import com.flickerdemo.screen.BaseView;

/**
 * Defines connection between the HomeView and the HomePresenter.
 */
public interface HomeContract {

    interface View extends BaseView<Presenter> {
        Context getContext();

        void updateView(final PhotoInfo pPhotoInfo);
    }

    interface Presenter extends BasePresenter<View> {
        void takeView(HomeContract.View pHomeView);

        void fetchPhotos();

        void dropView();
    }
}