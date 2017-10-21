package com.flickerdemo.screen.app;

import android.content.Context;

import com.flickerdemo.api.service.flicker.FlickerService;
import com.flickerdemo.application.FlickerDemoApplication;
import com.flickerdemo.application.scope.ApplicationScope;
import com.flickerdemo.screen.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import dagger.Component;

/**
 * Created by dpawar on 10/19/17.
 */

@ApplicationScope
@Component(
        modules = {
                AppModule.class,
        }
)
public interface AppComponent {
    public void inject(final FlickerDemoApplication pFlickerDemoApplication);

    public void inject(final BaseActivity pBaseActivity);

    public FlickerDemoApplication provideLuxeApplication();

    public Context provideContext();

    public EventBus provideEventBus();

    // Managers

    // Services
    public FlickerService provideFlickerService();
}