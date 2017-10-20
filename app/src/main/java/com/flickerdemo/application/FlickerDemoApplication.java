package com.flickerdemo.application;

import android.app.Application;
import android.content.Context;

import com.flickerdemo.api.service.flicker.FlickerService;
import com.flickerdemo.application.scope.ApplicationScope;
import com.flickerdemo.screen.BaseActivity;
import com.flickerdemo.screen.app.AppComponent;
import com.flickerdemo.screen.app.AppModule;
import com.flickerdemo.screen.app.DaggerAppComponent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

import javax.inject.Inject;

import dagger.Component;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

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
