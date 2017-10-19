package com.flickerdemo.api.service.flicker;

import android.support.annotation.NonNull;

import com.flickerdemo.api.model.PhotoInfo;
import com.flickerdemo.api.service.util.ServiceAdapterWrapper;
import com.flickerdemo.application.scope.ApplicationScope;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.Observable;

@ApplicationScope
public class FlickerService {
    private final IFlickerService mFlickerService;

    @Inject
    public FlickerService(@NonNull final EventBus pEventBus) {
        this.mFlickerService = ServiceAdapterWrapper.wrapService(IFlickerService.class, pEventBus);
    }

    public Observable<PhotoInfo> getPhotoInfo(@NonNull final String pMethod, @NonNull final String pAPIKey, @NonNull final String pFormat, @NonNull final String pNoJSONCallback, @NonNull final String pText, @NonNull final int pPage) {
        return this.mFlickerService.getPhotoInfo(pMethod, pAPIKey, pFormat, pNoJSONCallback, pText, pPage);
    }
}