package com.flickerdemo.screen.app;

import android.app.Application;
import android.content.Context;

import com.flickerdemo.application.FlickerDemoApplication;
import com.flickerdemo.application.scope.ApplicationScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.flickerdemo.application.FlickerDemoApplication.BASE_URL;

/**
 * Created by dpawar on 10/19/17.
 */

@dagger.Module
public class AppModule {
    private final FlickerDemoApplication mFlickerDemoApplication;
    private final EventBus mEventBus;

    private final Gson mGson;

    public AppModule(final FlickerDemoApplication pLuxeApplication) {
        this.mFlickerDemoApplication = pLuxeApplication;

        final EventBusBuilder eventBusBuilder = EventBus.builder();
        eventBusBuilder.throwSubscriberException(true);
        this.mEventBus = eventBusBuilder.build();

        this.mGson = new GsonBuilder().create();
    }

    @Provides
    @ApplicationScope
    public FlickerDemoApplication provideLuxeApplication() {
        return this.mFlickerDemoApplication;
    }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return this.mFlickerDemoApplication;
    }

    @Provides
    @ApplicationScope
    public EventBus provideEventBus() {
        return this.mEventBus;
    }

    @Provides
    @ApplicationScope
    public Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @ApplicationScope
    public Gson provideGson() {
        return this.mGson;
    }

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient(Cache cache) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.cache(cache);
        okHttpClient.addInterceptor(logging);
        return okHttpClient.build();
    }

    @Provides
    @ApplicationScope
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
