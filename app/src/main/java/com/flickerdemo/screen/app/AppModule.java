package com.flickerdemo.screen.app;

import android.content.Context;

import com.flickerdemo.application.FlickerDemoApplication;
import com.flickerdemo.application.scope.ApplicationScope;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusBuilder;

import dagger.Provides;

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
}
