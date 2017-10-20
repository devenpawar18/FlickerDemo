package com.flickerdemo.screen.home;

import android.support.annotation.Nullable;
import android.widget.Toast;

import com.flickerdemo.api.model.PhotoInfo;
import com.flickerdemo.api.service.flicker.FlickerService;
import com.flickerdemo.api.service.util.ServiceUtils;
import com.flickerdemo.util.ViewUtils;
import com.flickerdemo.util.service.IFailureCallback;
import com.flickerdemo.util.service.ISuccessCallback;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

import static com.flickerdemo.util.APIUtils.API_FORMAT;
import static com.flickerdemo.util.APIUtils.API_KEY;
import static com.flickerdemo.util.APIUtils.API_METHOD;
import static com.flickerdemo.util.APIUtils.API_NO_JSON_CALLBACK;

/**
 * Implements the presenter interface in the corresponding HomeContract.
 */
public class HomePresenter implements HomeContract.Presenter {
    private static String PROGRESS_FETCH_TAG = "progress.fetch.tag";

    @Nullable
    private HomeContract.View mHomeView;

    @Inject
    protected FlickerService mFlickerService;

    public CompositeDisposable mDisposables = new CompositeDisposable();

    @Inject
    public HomePresenter() {

    }

    @Override
    public void takeView(final HomeContract.View pHomeView) {
        this.mHomeView = ViewUtils.checkNotNull(pHomeView, "MainView can't be null!");
        this.mHomeView = pHomeView;
        this.fetchPhotos();
    }

    @Override
    public void fetchPhotos() {
        final HomeActivity homeActivity = (HomeActivity) this.mHomeView.getContext();
        homeActivity.getPopupManager().showProgress(PROGRESS_FETCH_TAG, "Fetching Shit...");
        Observable<PhotoInfo> fetchObservable = this.mFlickerService.getPhotoInfo(API_METHOD, API_KEY, API_FORMAT, API_NO_JSON_CALLBACK, "Deven", 1);

        final ISuccessCallback<PhotoInfo> successCallback = new ISuccessCallback<PhotoInfo>() {
            @Override
            public void accept(final PhotoInfo pPhotoInfo) {
                mHomeView.updateView(pPhotoInfo);
                homeActivity.getPopupManager().dismissProgress(PROGRESS_FETCH_TAG);
            }
        };
        final IFailureCallback failureCallback = new IFailureCallback() {
            @Override
            public void accept(final Throwable pThrowable) {
                Toast.makeText(mHomeView.getContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        };
        this.mDisposables.add(ServiceUtils.defaults(fetchObservable, successCallback, failureCallback));
    }

    @Override
    public void dropView() {
        this.mDisposables.dispose();
        this.mHomeView = null;
    }

    @Override
    public void nextScreen() {
        // TODO: next screen
    }
}