package com.flickerdemo.application;

import android.app.Application;
import android.content.Context;

import com.flickerdemo.api.service.flicker.FlickerService;
import com.flickerdemo.screen.app.AppComponent;
import com.flickerdemo.screen.app.AppModule;
import com.flickerdemo.screen.app.DaggerAppComponent;

import javax.inject.Inject;

public class FlickerDemoApplication extends Application {
    public static String BASE_URL = "https://api.flickr.com/";

    private static FlickerDemoApplication sInstance;

    private AppComponent mAppComponent;

    @Inject
    protected FlickerService mFLickerService;

    public static FlickerDemoApplication getApplication() {
        return FlickerDemoApplication.sInstance;
    }

    public static FlickerDemoApplication get(final Context pContext) {
        return (FlickerDemoApplication) pContext.getApplicationContext();
    }

    @Override
    public void onCreate() {
        FlickerDemoApplication.sInstance = this;
        super.onCreate();

        this.mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();

        this.getAppComponent().inject(this);
    }

    public AppComponent getAppComponent() {
        return this.mAppComponent;
    }
}
