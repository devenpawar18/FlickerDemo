package com.flickerdemo.application;

import android.app.Application;
import android.content.Context;

import com.flickerdemo.api.service.flicker.FlickerService;
import com.flickerdemo.application.scope.ApplicationScope;
import com.flickerdemo.screen.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

import dagger.Component;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickerDemoApplication extends Application {
    private static String BASE_URL = "https://api.flickr.com/";

    private static FlickerDemoApplication sInstance;

    private AppComponent mAppComponent;

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

        this.mAppComponent = DaggerFlickerDemoApplication_AppComponent.builder()
                .module(new Module(this)).build();
    }

    public AppComponent getAppComponent() {
        return this.mAppComponent;
    }

    @dagger.Module
    public static class Module {
        private final FlickerDemoApplication mFlickerDemoApplication;
        private final EventBus mEventBus;

        private final Gson mGson;

        public Module(final FlickerDemoApplication pLuxeApplication) {
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


    @ApplicationScope
    @Component(
            modules = {
                    Module.class,
            }
    )
    public static interface AppComponent {
        public void inject(final BaseActivity pBaseActivity);

        public FlickerDemoApplication provideLuxeApplication();

        public Context provideContext();

        public EventBus provideEventBus();

        public Gson provideGson();

        public FlickerService provideFlickerService();
    }
}
